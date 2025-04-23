/*
 * Copyright 2021 CGI Deutschland B.V. & Co. KG
 *
 * The copyright of this file is vested in CGI Deutschland B.V. & Co. KG.
 * Licensed under the CGI Space Germany License, Version 1.1 (the "License"); you may not use this file except in compliance with the License.
 *
 * If a copy of this license was not distributed with this file, please contact CGI Deutschland B.V. & Co. KG.
 *
 * This file may only be reproduced in whole or in part, or stored in a retrieval system, or transmitted in any form,
 * or by any means electronic, mechanical, photocopying or otherwise, either with the prior permission of CGI Deutschland B.V. & Co. KG
 * or in accordance with the terms of a contract made with CGI Deutschland B.V. & Co. KG on use of this source code.
 */
package com.cgi.space.psi.tasks

import com.cgi.space.psi.tasks.utils.OpenAPIUtil
import groovy.transform.CompileDynamic
import groovy.transform.TupleConstructor
import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Matcher
import java.util.regex.MatchResult
import java.util.regex.Pattern
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Generate documentation references (TOD - REQ)
 */
@CompileDynamic
class GenerateDocReferences extends DefaultTask {

    private static final Pattern FILENAME_PATTERN_OPS = Pattern.compile('((REQ-[0-9]{2}-[0-9]{2})-[0-9]{2}-.*)\\.md')
    private static final Pattern FILENAME_PATTERN_TASK = Pattern.compile('((REQ-[0-9]{2}-[0-9]{2})-.*)\\.md')
    private static final Pattern REQ_PATTERN = Pattern.compile('PSI-(([0-9]{2}-[0-9]{2})-[0-9]{2})-[0-9]{2}')
    private static final String INDEX_SUFFIX = '-index.md'

    @InputDirectory
    File requirementsBaseline

    @OutputDirectory
    File todTaskRequirements

    @OutputDirectory
    File todOpsRequirements

    @OutputDirectory
    File todOpsEndpoints

    @Internal
    Map<String, Set<String>> endpointMap = [:]

    private final Map<String, RequirementIndex> reqIndices = [:]

    @TaskAction
    void generateDocReferences() {
        // delete old mappings
        Files.list(todTaskRequirements.toPath()).forEach(Files::delete)
        Files.list(todOpsRequirements.toPath()).forEach(Files::delete)

        // Collect requirements per TOD entry
        for (Path reqFile : Files.newDirectoryStream(requirementsBaseline.toPath())) {
            RequirementFile fileInfo = RequirementFile.parse(reqFile)
            if (fileInfo != null) {
                logger.info('Parsing requirement file {}', fileInfo.path)
                reqIndices.computeIfAbsent(fileInfo.categoryIndex, RequirementIndex::new).add(fileInfo)
                generateTodRequirements(fileInfo)
            }
        }

        for (var reqIndex : reqIndices.values()) {
            generateReqIndex(reqIndex)
        }

        Files.list(todOpsEndpoints.toPath()).forEach(Files::delete)
        for (var entry : endpointMap.entrySet()) {
            generateTodEndpoint(entry.key, entry.value)
        }
    }

    private void generateTodRequirements(RequirementFile fileInfo) {
        File targetDirectory = fileInfo.level == TodLevel.TASK ? todTaskRequirements : todOpsRequirements
        Path todFile = targetDirectory.toPath()
            .resolve(fileInfo.filenameWithoutSuffix.replace('REQ-', 'TOD-') + '-requirements.md')
        try (BufferedWriter writer = Files.newBufferedWriter(todFile)) {
            SortedSet<String> requirements = parseRequirements(fileInfo)
            if (requirements.isEmpty()) {
                logger.warn('No requirements found in {} (wrong format?)', fileInfo.path)
            }

            writer.write(OpenAPIUtil.generateFileHeader())
            for (String req : requirements) {
                writer.write('* ' + req)
                writer.newLine()
            }
        }
    }

    private SortedSet<String> parseRequirements(RequirementFile fileInfo) {
        SortedSet<String> requirements = new TreeSet<>()
        Iterator<MatchResult> it = Files.lines(fileInfo.path)
            .flatMap(line -> REQ_PATTERN.matcher(line).results())
            .iterator()
        while (it.hasNext()) {
            MatchResult req = it.next()
            if ((fileInfo.level == TodLevel.OPERATION && !fileInfo.filenameWithoutSuffix.startsWith("REQ-" + req.group(1)))
                || (fileInfo.level == TodLevel.TASK && !fileInfo.filenameWithoutSuffix.startsWith("REQ-" + req.group(2)))) {
                logger.warn('Suspicious requirement {} in {}', req.group(), fileInfo.path)
            } else if (requirements.contains(req.group())) {
                throw new GradleException("Duplicate requirement " + req.group() + " in " + fileInfo.path)
            }
            requirements.add(req.group())
        }
        return requirements
    }

    private void generateReqIndex(RequirementIndex reqIndex) {
        // Task-level requirements first, then sorted by operation id
        Collections.sort(reqIndex.opsFiles)
        Path indexFile = requirementsBaseline.toPath().resolve(reqIndex.category + INDEX_SUFFIX)
        try (BufferedWriter writer = Files.newBufferedWriter(indexFile)) {
            writer.write(OpenAPIUtil.generateFileHeader())

            if (reqIndex.taskFile != null) {
                writer.newLine()
                writer.write('@include [' + reqIndex.taskFile + '](' + reqIndex.taskFile + '.md)')
                writer.newLine()
            }

            for (String reqFile : reqIndex.opsFiles) {
                writer.newLine()
                writer.write('# ' + reqFile)
                writer.newLine()
                writer.newLine()
                writer.write('@include [' + reqFile + '](' + reqFile + '.md)')
                writer.newLine()
                writer.newLine()
                writer.write('{#page:break}')
                writer.newLine()
            }
        }
    }

    private void generateTodEndpoint(String todIndex, Set<String> endpoints) {
        String filename = Files.list(todOpsEndpoints.toPath().getParent())
            .map((path) -> path.fileName.toString())
            .filter((name) -> name.startsWith(todIndex))
            .findFirst()
            .orElseThrow(() -> new GradleException("TOD operation " + todIndex + " not found, but required for endpoint " + endpoints))
        Path outputPath = todOpsEndpoints.toPath().resolve(filename.replace('.md', '-endpoints.md'))

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write(OpenAPIUtil.generateFileHeader())
            writer.newLine()
            for (String endpoint : endpoints) {
                writer.write('* ' + endpoint)
                writer.newLine()
            }
        }
    }

    private static enum TodLevel {

        TASK, OPERATION

    }

    @TupleConstructor
    private static final class RequirementFile {

        static RequirementFile parse(Path path) {
            String filename = path.fileName
            if (filename.endsWith(INDEX_SUFFIX)) {
                return null
            }

            Matcher matcher = FILENAME_PATTERN_OPS.matcher(filename)
            if (matcher.matches()) {
                return [path, TodLevel.OPERATION, matcher.group(1), matcher.group(2)]
            }
            matcher = FILENAME_PATTERN_TASK.matcher(filename)
            if (matcher.matches()) {
                return [path, TodLevel.TASK, matcher.group(1), matcher.group(2)]
            }
            return null
        }

        Path path
        TodLevel level
        String filenameWithoutSuffix
        String categoryIndex

    }

    @TupleConstructor
    private static final class RequirementIndex {

        final String category
        String taskFile = null
        List<String> opsFiles = []

        void add(RequirementFile fileInfo) {
            if (fileInfo.level == TodLevel.TASK) {
                taskFile = fileInfo.filenameWithoutSuffix
            } else {
                opsFiles.add(fileInfo.filenameWithoutSuffix)
            }
        }

    }

}

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
import groovy.transform.CompileStatic
import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Matcher
import java.util.regex.MatchResult
import java.util.regex.Pattern
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 * Generate documentation references (TOD - REQ)
 */
@CompileStatic
class GenerateMadrIndexTask extends DefaultTask {

    private static final Pattern FILENAME_PATTERN = Pattern.compile('([0-9]{4}-[0-9]{2}-[0-9]{2})-.*\\.md')
    private static final Pattern TITLE_PATTERN = Pattern.compile('# (.*)')

    @InputDirectory
    File madrDir

    @OutputFile
    File outputFile

    @TaskAction
    void generateDocReferences() {
        // Collect decision records
        Map<String, String> decisions = new TreeMap<>()
        for (Path madrFile : Files.newDirectoryStream(madrDir.toPath())) {
            String filename = madrFile.fileName
            Matcher matcher = FILENAME_PATTERN.matcher(filename)
            if (matcher.matches()) {
                String title = readTitle(madrFile)
                decisions.put(filename, matcher.group(1) + ' ' + title)
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath())) {
            writer.write(OpenAPIUtil.generateFileHeader())
            if (decisions.isEmpty()) {
                writer.newLine()
                writer.write("There are currently no decisions in this category.")
                writer.newLine()
            }
            for (decision in decisions) {
                writer.newLine()
                writer.write("@include [$decision.value]($decision.key)")
                writer.newLine()
            }
        }
    }

    private String readTitle(Path madrFile) {
        String title = Files.lines(madrFile)
                .flatMap(line -> TITLE_PATTERN.matcher(line).results())
                .map(matcher -> matcher.group(1))
                .findFirst()
                .orElseThrow(() -> new GradleException("No title found in $madrFile"))
        return title;
    }

}

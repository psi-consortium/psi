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
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Collect references to candidate requirments in MADR and REQ.
 */
@CompileStatic
class GenerateCandidateReqReferences extends DefaultTask {

    /**
    * Helper class for handling a candidate requirements state and the reason for it.
    */
    class CandReqState {
        String candState;
        String stateReason;

        public CandReqState(candState, stateReason){
            this.candState = candState;
            this.stateReason = stateReason;
        }
    }

    private static final Pattern CAND_REQ_NO_PATTERN = Pattern.compile('P&S_[0-9]{3}\\w?')
    private static final Pattern CAND_REQ_MADR_PATTERN = Pattern.compile('\\* (P&S_[0-9]{3}[a-z]?): ?(.*)')
    private static final Pattern REQ_PATTERN = Pattern.compile('PSI-[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}')

    @InputDirectory
    File candidateInput

    @InputFiles
    FileCollection madrInput

    @OutputDirectory
    File madrOutput

    @InputFiles
    FileCollection reqInput

    @OutputDirectory
    File reqOutput

    private final Map<String, Map<String, CandReqState>> madrs = [:]
    private final Map<String, List<String>> requirements = [:]

    @TaskAction
    void generateCandidateReqReferences() {
        init()
        processMadrs()
        processReqs()
    }

    private void init() {
        for (String candidate : candidateInput.list()) {
            Matcher matcher = CAND_REQ_NO_PATTERN.matcher(candidate)
            if (matcher.find()) {
                madrs.put(matcher.group(0), new TreeMap<>())
                requirements.put(matcher.group(0), new ArrayList<>())
            }
        }
        Files.list(madrOutput.toPath()).forEach(Files::delete)
        Files.list(reqOutput.toPath()).forEach(Files::delete)
    }

    private void processMadrs() {
        for (File madr : madrInput) {
            String madrId = madr.getName().replace('.md', '');
            String state = null;
            String curReqId = null;
            for (String line : Files.readAllLines(madr.toPath())) {
                if (line.contains("### Not Considered")) {
                    state = "Descoped"
                } else if (line.contains("### Implemented")) {
                    state = "Justified"
                } else if (line.startsWith("#")) {
                    state = null;
                } else if (line.startsWith("  ") && !line.isBlank() && curReqId != null) {
                    def reqState = madrs.get(curReqId).get(madrId)
                    reqState.stateReason += "\n" + line
                } else if (state != null) {
                    Matcher matcher = CAND_REQ_MADR_PATTERN.matcher(line)
                    if (matcher.find()) {
                        curReqId = matcher.group(1);
                        String candReqStateReason = "${state} Reason: ${matcher.group(2)}";
                        CandReqState currentState = new CandReqState(state, candReqStateReason);

                        madrs.computeIfAbsent(curReqId , k -> new TreeMap<>())
                            .compute(madrId, (k, currentMapEntry) -> 
                                (currentMapEntry == null || currentMapEntry.equals(currentState)) ? currentState : new CandReqState("Partially justified", "${currentMapEntry.stateReason}\n\n${candReqStateReason}") 
                            );
                    } else {
                        curReqId = null
                    }
                }
            }
        }

        for (def entry : madrs.entrySet()) {
            Path path = madrOutput.toPath().resolve(entry.getKey() + '.md')
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(OpenAPIUtil.generateFileHeader())
                if (entry.getValue().isEmpty()) {
                    writer.write('* None')
                    writer.newLine()
                }
                for (def madr : entry.getValue()) {
                    writer.write("* ${madr.value.candState} in ${madr.key}")
                    writer.newLine()
                    writer.newLine()
                    writer.write("${madr.value.stateReason}")
                    writer.newLine()
                }
            }
        }
    }

    private void processReqs() {
        for (File reqFile : reqInput) {
            for (String line : Files.readAllLines(reqFile.toPath())) {
                Matcher reqMatcher = REQ_PATTERN.matcher(line)
                if (reqMatcher.find()) {
                    String req = reqMatcher.group(0)
                    Matcher candidateMatcher = CAND_REQ_NO_PATTERN.matcher(line)
                    while (candidateMatcher.find()) {
                        requirements.computeIfAbsent(candidateMatcher.group(0), k -> new ArrayList<>()).add(req)
                    }
                }
            }
        }

        for (def entry : requirements.entrySet()) {
            Path path = reqOutput.toPath().resolve(entry.getKey() + '.md')
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(OpenAPIUtil.generateFileHeader())
                if (entry.getValue().isEmpty()) {
                    writer.write('* None')
                    writer.newLine()
                } else {
                    Collections.sort(entry.getValue())
                }
                for (String req : entry.getValue()) {
                    writer.write('* ' + req)
                    writer.newLine()
                }
            }
        }
    }

}


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
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.Path

/**
 * This task generates an index of all previously generated index files to be included
 * in our main ICD.
 */
class GenerateRestApiSchemaIndexTask extends DefaultTask {

    @InputDirectory
    File inputDir

    @OutputFile
    File outputFile

    @TaskAction
    void generateRestApiSchemaIndex() {
        outputFile.withWriter { writer ->
            writer.writeLine(OpenAPIUtil.generateFileHeader())

            Files.find(inputDir.toPath(), Integer.MAX_VALUE, (path, basicFileAttributes) -> {
                path.toFile().getName().matches(".+\\.md") && !path.toFile().getCanonicalFile().equals(outputFile.getCanonicalFile())
            }).map(Path::getFileName).sorted().forEach(filename -> {
                writer.writeLine("@include [$filename]($filename)")
            })
        }
    }

}

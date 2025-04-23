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
package com.cgi.space.psi.tasks.utils

import org.gradle.api.GradleException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * A utility class to execute commands.
 */
class CommandUtil {

    static final Logger logger = LoggerFactory.getLogger(CommandUtil.class)

    /**
     * Executes the provided command and returns its output.
     */
    static String execute(String command, File dir = null, silent = true) {
        def result = new StringBuilder()
        def error = new StringBuilder()

        def process = dir == null ? command.execute() : command.execute(null, dir)
        process.consumeProcessErrorStream(error)

        def reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
        String line
        while ((line = reader.readLine()) != null) {
            result.append(line)
            result.append('\n')
            if (!silent) {
                logger.lifecycle(line)
            }
        }
        def exitCode = process.waitFor()

        if (exitCode != 0) {
            throw new GradleException("Failed to execute '$command': ${error}")
        }
        return result.toString().trim()
    }

    /**
     * Executes the provided command with a ProcessBuilder and returns the output.
     *
     * @param command a string array containing the program and its arguments.
     * @return the execution result.
     */
    static String executeWithProcessBuilder(String... command) {
        def result = new StringBuilder()

        def processBuilder = new ProcessBuilder()
        processBuilder.command(command)

        def process = processBuilder.start()
        def reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
        String line
        while ((line = reader.readLine()) != null) {
            result.append(line)
            result.append('\n')
        }
        def exitCode = process.waitFor()
        if (exitCode != 0) {
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))
            result.setLength(0)
            while ((line = reader.readLine()) != null) {
                result.append(line)
                result.append('\n')
            }
            throw new GradleException("Failed to execute '$command': $result")
        }
        return result.toString().trim()
    }
}

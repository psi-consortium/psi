/*
 * Copyright 2025 CGI Deutschland B.V. & Co. KG
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
package com.cgi.space.psi.tasks;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFiles;
import org.gradle.api.tasks.TaskAction;

/**
 * Generates a factory (to be used by MapStruct) that can create matching instances for each subtype.
 */
public class GenerateMapStructFactoryTask extends DefaultTask {

    private static final String PREFIX_SUBTYPE = "JsonSubTypes.Type";
    private static final String PREFIX_SUBTYPE_VALUE = "value = ";
    private static final String PREFIX_INTERFACE = "public interface ";

    @Getter
    @Setter
    @InputFiles
    private FileCollection modelFiles;
    @Getter
    @Setter
    @OutputFiles
    private File outputFile;

    @TaskAction
    void generateMapStructFactory() throws IOException {
        Set<String> imports = new TreeSet<>();
        imports.add("javax.annotation.Generated");
        imports.add("org.springframework.stereotype.Component");

        Map<String, Set<String>> subtypes = new TreeMap<>();
        for (File modelFile : modelFiles) {
            parseModel(modelFile, subtypes);
        }
        for (Entry<String, Set<String>> entry : subtypes.entrySet()) {
            imports.add("com.cgi.space.psi.common.model." + entry.getKey());
            for (String subtype : entry.getValue()) {
                imports.add("com.cgi.space.psi.common.model." + subtype);
            }
        }

        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(outputFile.toPath()))) {
            out.println("package com.cgi.space.psi.pss.stub.mapper;");
            out.println();
            for (String aImport : imports) {
                out.println("import " + aImport + ";");
            }
            out.println();

            out.println("@Component");
            out.println("@Generated(\"" + GenerateMapStructFactoryTask.class.getName() + "\")");
            out.println("public class TMFactory {");
            out.println();
            for (Entry<String, Set<String>> entry : subtypes.entrySet()) {
                generateFactory(entry.getKey(), "", entry.getValue(), out);
                if (subtypes.containsKey(entry.getKey() + "FVO")) {
                    generateFactory(entry.getKey(), "FVO", entry.getValue(), out);
                }
                if (subtypes.containsKey(entry.getKey() + "MVO")) {
                    generateFactory(entry.getKey(), "MVO", entry.getValue(), out);
                }
            }
            out.println("}");
        }
    }

    private void parseModel(File modelFile, Map<String, Set<String>> allSubtypes) throws IOException {
        Set<String> subtypes = new TreeSet<>();
        Files.lines(modelFile.toPath()).forEachOrdered(line -> {
            if (line.contains(PREFIX_SUBTYPE)) {
                final int indexOfValue = line.indexOf(PREFIX_SUBTYPE_VALUE) + PREFIX_SUBTYPE_VALUE.length();
                String subtype = line.substring(indexOfValue, line.indexOf(".class", indexOfValue));
                subtypes.add(subtype);
            }
            else if (line.startsWith(PREFIX_INTERFACE)) {
                String interfaceName = line.substring(PREFIX_INTERFACE.length(), line.indexOf(" ", PREFIX_INTERFACE.length()));
                if (!interfaceName.startsWith("Entity") && !interfaceName.startsWith("PartyRef") && !subtypes.isEmpty()) {
                    allSubtypes.put(interfaceName, subtypes);
                }
            }
        });
    }

    private void generateFactory(String interfaceName, String mappingSuffix, Set<String> subtypes, PrintWriter out) {
        out.println("    public " + interfaceName + " create" + interfaceName + "(" + interfaceName + mappingSuffix + " archetype) {");
        out.println("        if (archetype == null) {");
        out.println("            return null;");
        out.println("        }");
        for (String subtype : subtypes) {
            out.println("        else if (archetype instanceof " + subtype + mappingSuffix + ") {");
            out.println("            return new " + subtype + "();");
            out.println("        }");
        }
        out.println("        else {"); // else already printed
        out.println("            throw new AssertionError(\"Illegal type \" + archetype.getClass());");
        out.println("        }");
        out.println("    }");
        out.println();
    }

}

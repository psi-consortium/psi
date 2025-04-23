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

import com.cgi.space.psi.tasks.transformers.AddProperty;
import com.cgi.space.psi.tasks.transformers.AddTagDescription;
import com.cgi.space.psi.tasks.transformers.ImportPath;
import com.cgi.space.psi.tasks.transformers.ImportSchema;
import com.cgi.space.psi.tasks.transformers.OasTransformer;
import com.cgi.space.psi.tasks.transformers.OverwriteProperty;
import com.cgi.space.psi.tasks.transformers.OverwritePropertyFormat;
import com.cgi.space.psi.tasks.transformers.PruneExamples;
import com.cgi.space.psi.tasks.transformers.PruneRequestBodies;
import com.cgi.space.psi.tasks.transformers.PruneResponses;
import com.cgi.space.psi.tasks.transformers.PruneSchemas;
import com.cgi.space.psi.tasks.transformers.PruneTags;
import com.cgi.space.psi.tasks.transformers.RemoveOneOf;
import com.cgi.space.psi.tasks.transformers.RemoveOperation;
import com.cgi.space.psi.tasks.transformers.RemovePaths;
import com.cgi.space.psi.tasks.transformers.RemoveProperty;
import com.cgi.space.psi.tasks.transformers.RemoveRequiredProperty;
import com.cgi.space.psi.tasks.transformers.RemoveSchema;
import com.cgi.space.psi.tasks.transformers.RenameProperty;
import com.cgi.space.psi.tasks.transformers.RenameSchema;
import com.cgi.space.psi.tasks.transformers.ReplaceResponseContent;
import com.cgi.space.psi.tasks.transformers.ReplaceServerUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import io.swagger.util.Yaml;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

/**
 * Task to apply various transformations to OAS specifications.
 */
public class TransformOASTask extends DefaultTask {

    @Getter
    @Setter
    @InputFile // tracked by gradle
    private File input;
    @Getter
    @Setter
    @OutputFile
    private File output;
    @Getter
    @InputFiles // tracked by gradle
    private Set<File> implicitInputs = new HashSet<>();
    @Getter
    @Input // tracked by gradle
    private List<OasTransformer> transformations = new ArrayList<>();

    public void DefaultTask() {
        setGroup("Documentation");
    }

    public void replaceServerUrl() {
        this.replaceServerUrl("https://serverRoot/tmf-api/", "/psi-api/");
        this.replaceServerUrl("/v4", "/v2");

        this.replaceServerUrl("https://serverRoot/", "/psi-api/");
        this.replaceServerUrl("/v5", "/v2");
    }

    public void replaceServerUrl(String pattern, String replacement) {
        this.transformations.add(new ReplaceServerUrl(pattern, replacement));
    }

    public void replaceResponseContent(String pattern, String replacement) {
        this.transformations.add(new ReplaceResponseContent(pattern, replacement));
    }

    public void removePaths(String pattern) {
        this.transformations.add(new RemovePaths(pattern));
    }

    public void importPath(String pattern, File file) {
        implicitInputs.add(file);
        this.transformations.add(new ImportPath(pattern, file));
    }

    public void removeOperation(String pathPattern, String operationPattern) {
        this.transformations.add(new RemoveOperation(pathPattern, operationPattern));
    }

    public void addTagDescription(String tagName, String tagDescription) {
        this.transformations.add(new AddTagDescription(tagName, tagDescription));
    }

    public void pruneTags() {
        this.transformations.add(new PruneTags());
    }

    public void renameSchema(String pattern, String replacement) {
        this.transformations.add(new RenameSchema(pattern, replacement));
    }

    public void removeSchema(String pattern) {
        this.transformations.add(new RemoveSchema(pattern));
    }

    public void removeOneOf(String schema) {
        this.transformations.add(new RemoveOneOf(schema));
    }

    public void pruneSchemas() {
        this.transformations.add(new PruneSchemas());
    }

    public void importSchema(String name, File file) {
        implicitInputs.add(file);
        this.transformations.add(new ImportSchema(name, file));
    }

    public void addProperty(String schemaPattern, String propertyName, String propertyJson) throws JsonProcessingException {
        this.transformations.add(new AddProperty(schemaPattern, propertyName, propertyJson));
    }

    public void renameProperty(String schemaPattern, String propertyPattern, String replacement) {
        this.transformations.add(new RenameProperty(schemaPattern, propertyPattern, replacement));
    }

    public void removeProperty(String schemaPattern, String propertyPattern) {
        this.transformations.add(new RemoveProperty(schemaPattern, propertyPattern));
    }

    public void overwriteProperty(String schemaPattern, String propertyName, String propertyJson) throws JsonProcessingException {
        this.transformations.add(new OverwriteProperty(schemaPattern, propertyName, propertyJson));
    }

    public void overwritePropertyFormat(String schemaName, String propertyName, String propertyFormat) {
        this.transformations.add(new OverwritePropertyFormat(schemaName, propertyName, propertyFormat));
    }

    public void removeRequiredProperty(String schemaPattern, String propertyPattern) {
        this.transformations.add(new RemoveRequiredProperty(schemaPattern, propertyPattern));
    }

    public void pruneAll() {
        pruneTags();
        pruneRequestBodies();
        pruneResponses();
        pruneExamples();
        pruneSchemas();
    }

    public void pruneRequestBodies() {
        this.transformations.add(new PruneRequestBodies());
    }

    public void pruneResponses() {
        this.transformations.add(new PruneResponses());
    }

    public void pruneExamples() {
        this.transformations.add(new PruneExamples());
    }

    @TaskAction
    void oasTransform() throws IOException {
        var openApi = new OpenAPIV3Parser().read(input.toString(), null, null);
        for (OasTransformer transformation : transformations) {
            transformation.accept(openApi);
        }

        if (output.getName().endsWith("json")) {
            Json.mapper()
                    .writer(new PattyLikePrinter())
                    .writeValue(getProject().file(output), openApi);
        }
        else {
            Yaml.pretty().writeValue(getProject().file(output), openApi);
        }
    }

    private static class PattyLikePrinter extends DefaultPrettyPrinter {

        public PattyLikePrinter() {
            super();
            _arrayIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
            _objectFieldValueSeparatorWithSpaces = ": ";
        }

        public PattyLikePrinter(PattyLikePrinter base) {
            super(base);
        }

        @Override
        public PattyLikePrinter createInstance() {
            return new PattyLikePrinter(this);
        }

    }

}

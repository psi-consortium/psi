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

import groovy.transform.CompileDynamic
import io.github.classgraph.AnnotationEnumValue
import io.github.classgraph.AnnotationInfo
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import io.github.classgraph.MethodInfo
import io.github.classgraph.MethodInfoList
import io.github.classgraph.ScanResult
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction

import static java.util.Comparator.comparing
import static java.util.function.Function.identity

/**
 * Parses the endpoints and operations defined in the mockup for use in other tasks.
 */
@CompileDynamic
class ParseMockupEndpointsTask extends DefaultTask {

    @InputDirectory
    File mockupClassDir

    @Internal
    Map<String, Set<String>> endpointMap = [:];

    @Internal
    Map<String, Set<String>> operationsMap = [:];

    @TaskAction
    void generateDocReferences() {
        // Collect TOD entries in controllers
        final ClassGraph classGraph = new ClassGraph()
            .enableClassInfo()
            .enableMethodInfo()
            .enableAnnotationInfo()
            .enableInterClassDependencies()
            .overrideClasspath(mockupClassDir)
            .acceptPackages('com.cgi.space.psi')
        try (ScanResult scanResult = classGraph.scan()) {
            for (ClassInfo classInfo : scanResult.allClasses) {
                parseClass(classInfo)
            }
        }
    }

    /**
     * Parses a class if it is annotated with {@link RequestMapping}.
     */
    private void parseClass(ClassInfo classInfo) {
        AnnotationInfo requestMapping = classInfo.getAnnotationInfo('org.springframework.web.bind.annotation.RequestMapping')
        if (requestMapping != null) {
            String[] pathPrefix = requestMapping.parameterValues.get('value').value
            if (pathPrefix.length != 1) {
                throw new GradleException('Controller ' + classInfo.simpleName + ' is mapped to multiple paths')
            }

            def shortPrefix = pathPrefix[0].replace("/psi-api", "")
            // Sort methods as they are defined in the source
            List<MethodInfo> methods = new ArrayList<>(classInfo.methodInfo)
            Collections.sort(methods, comparing(MethodInfo::getMinLineNum).thenComparing(identity()))
            for (MethodInfo methodInfo : methods) {
                parseMethod(shortPrefix, methodInfo)
            }
        }
    }

    /**
     * Parses a method of a controller if it is annotated with {@link PsiOperation} and {@link RequestMapping}.
     */
    private void parseMethod(String pathPrefix, MethodInfo methodInfo) {
        AnnotationInfo psiOperation = methodInfo.getAnnotationInfo('com.cgi.space.psi.common.api.PsiOperation')
        AnnotationInfo requestMapping = methodInfo.getAnnotationInfo('org.springframework.web.bind.annotation.RequestMapping')
        if (requestMapping == null) {
            // Since RequestMapping can be inherited, have a second look at the implemented interfaces
            for (ClassInfo parentClass : methodInfo.classInfo.interfaces) {
                MethodInfoList parentMethods = parentClass.methodInfo.get(methodInfo.name)
                if (parentMethods != null && parentMethods.size() > 0) {
                    requestMapping = parentMethods.get(0).getAnnotationInfo('org.springframework.web.bind.annotation.RequestMapping')
                    break
                }
            }
        }

        if (psiOperation != null && requestMapping != null) {
            String[] pathSuffix = requestMapping.parameterValues.get('value').value
            if (pathSuffix.length != 1) {
                throw new GradleException('Method ' + methodInfo.classInfo.simpleName + '::' + methodInfo.name + ' is mapped to multiple paths')
            }
            AnnotationEnumValue[] httpMethod = requestMapping.parameterValues.get('method').value
            if (httpMethod.length != 1) {
                throw new GradleException('Method ' + methodInfo.classInfo.simpleName + '::' + methodInfo.name + ' is mapped to multiple http methods')
            }
            for (String operation : psiOperation.parameterValues.get('value').value) {
                String fullPath = httpMethod[0].valueName + ' ' + pathPrefix + pathSuffix[0];
                endpointMap.computeIfAbsent(operation, (k) -> new LinkedHashSet<>()).add(fullPath)
                operationsMap.computeIfAbsent(fullPath, (k) -> new LinkedHashSet<>()).add(operation)
            }
        }
    }

}

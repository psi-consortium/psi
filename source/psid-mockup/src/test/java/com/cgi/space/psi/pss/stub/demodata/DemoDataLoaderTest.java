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
package com.cgi.space.psi.pss.stub.demodata;

import com.cgi.space.psi.common.model.Individual;
import com.cgi.space.psi.common.model.Organization;
import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.ServiceSpecification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(properties = "psi.demo-data.path=../../aiv/testing/aitf/testdata/")
public class DemoDataLoaderTest {

    @Autowired
    private MongoTemplate template;

    @Test
    public void testSetApplicationStartup() {
        assertThat(template.exists(new Query(), Individual.class), is(true));
        assertThat(template.exists(new Query(), Organization.class), is(true));
        assertThat(template.exists(new Query(), ResourceSpecification.class), is(true));
        assertThat(template.exists(new Query(), ServiceSpecification.class), is(true));
        assertThat(template.exists(new Query(), ProductSpecification.class), is(true));
        assertThat(template.exists(new Query(), ProductOffering.class), is(true));
    }

}

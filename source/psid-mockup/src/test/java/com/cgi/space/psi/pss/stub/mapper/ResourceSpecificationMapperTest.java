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
package com.cgi.space.psi.pss.stub.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cgi.space.psi.common.model.LogicalResourceSpecification;
import com.cgi.space.psi.common.model.PhysicalResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecificationFVO;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ResourceSpecificationMapperTest {

    @Autowired
    private ResourceSpecificationMapper resourceSpecificationMapper;

    @Test
    public void testToLogicalResourceSpecification() {
        ResourceSpecificationFVO create = new ResourceSpecificationFVO();
        create.setAtType(LogicalResourceSpecification.class.getSimpleName());

        final ResourceSpecification result = resourceSpecificationMapper.toResourceSpecification(create);
        assertThat(result, is(instanceOf(LogicalResourceSpecification.class)));
    }

    @Test
    public void testToPhysicalResourceSpecification() {
        ResourceSpecificationFVO create = new ResourceSpecificationFVO();
        create.setAtType(PhysicalResourceSpecification.class.getSimpleName());

        final ResourceSpecification result = resourceSpecificationMapper.toResourceSpecification(create);
        assertThat(result, is(instanceOf(PhysicalResourceSpecification.class)));
    }

}

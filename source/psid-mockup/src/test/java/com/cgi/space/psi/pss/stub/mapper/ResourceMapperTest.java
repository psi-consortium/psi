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

import com.cgi.space.psi.common.model.LogicalResource;
import com.cgi.space.psi.common.model.PhysicalResource;
import com.cgi.space.psi.common.model.Resource;
import com.cgi.space.psi.common.model.ResourceFVO;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ResourceMapperTest {

    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void testToLogicalResource() {
        ResourceFVO create = new ResourceFVO();
        create.setAtType(LogicalResource.class.getSimpleName());

        final Resource result = resourceMapper.toResource(create);
        assertThat(result, is(instanceOf(LogicalResource.class)));
    }

    @Test
    public void testToPhysicalResource() {
        ResourceFVO create = new ResourceFVO();
        create.setAtType(PhysicalResource.class.getSimpleName());

        final Resource result = resourceMapper.toResource(create);
        assertThat(result, is(instanceOf(PhysicalResource.class)));
    }

}

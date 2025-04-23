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
package com.cgi.space.psi.tasks.transformers;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Adds a description to the given tag.
 */
@ToString
@AllArgsConstructor
public class AddTagDescription implements OasTransformer {

    private final String tagName;
    private final String tagDescription;

    @Override
    public void accept(OpenAPI api) {
        for (Tag tag : api.getTags()) {
            if (tag.getName().matches(tagName)) {
                tag.setDescription(tagDescription);
            }
        }
    }

}

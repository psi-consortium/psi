/*
 * Copyright 2023 CGI Deutschland B.V. & Co. KG
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
package com.cgi.space.psi.pss.stub.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utility methods for Controllers.
 */
public final class ControllerUtil {

    private ControllerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Returns the correct response depending on whether the entity is null (= not found) or not (=
     * ok).
     *
     * @param <T> the entity type
     * @param entity the entity
     * @return the http response
     */
    public static <T> ResponseEntity<T> okOrNotFound(T entity) {
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
    }

}

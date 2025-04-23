/*
 * Copyright 2024 CGI Deutschland B.V. & Co. KG
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

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

/**
 * Utility class to ease iterating over a {@link Collection} that can be <code>null</code>.
 */
@UtilityClass
@SuppressWarnings("checkstyle:com.puppycrawl.tools.checkstyle.checks.design.HideUtilityClassConstructorCheck")
public class StreamUtils {

    /**
     * Calls forEach if the given collection is not null.
     *
     * @param <T> The type of elements in the collection
     * @param collection The collection
     * @param action The action to be performed for each element
     */
    public static <T> void forEach(Collection<T> collection, Consumer<T> action) {
        if (collection != null) {
            collection.forEach(action);
        }
    }

    /**
     * Returns a stream for the given collection, or an empty one if the collection is null.
     *
     * @param <T> The type of elements in the collection
     * @param collection The collection
     * @return a sequential {@code Stream} over the elements in the collection
     */
    public static <T> Stream<T> stream(Collection<T> collection) {
        if (collection != null) {
            return collection.stream();
        }
        else {
            return Stream.empty();
        }
    }

    /**
     * Returns a stream consisting all elements of the given stream that are of the desired type.
     *
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @param in the input stream
     * @param type the type to filter for
     * @return the new stream
     */
    public static <T, R> Stream<R> filter(Stream<T> in, Class<R> type) {
        return in.filter(type::isInstance).map(type::cast);
    }

}

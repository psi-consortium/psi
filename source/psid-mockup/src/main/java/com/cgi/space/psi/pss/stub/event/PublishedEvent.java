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
package com.cgi.space.psi.pss.stub.event;

/**
 * Events that shall be published to all listeners.
 *
 * @param <T> the class of the value
 */
public class PublishedEvent<T> extends AbstractEvent<T> {

    /**
     * Constructs the event.
     *
     * @param topic the topic
     * @param type  the type
     * @param value the value
     */
    public PublishedEvent(String topic, String type, T value) {
        super(topic, type, value);
    }

}

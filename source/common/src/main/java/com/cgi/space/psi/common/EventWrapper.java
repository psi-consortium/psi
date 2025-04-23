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
package com.cgi.space.psi.common;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cgi.space.psi.common.model.Event;
import com.cgi.space.psi.common.model.Topic;

/**
 * The class is a wrapper for the {@link Event} class. One addition to the {@link Event} is the
 * <code>topicId</code> field.
 */
@Document(collection = "event")
public class EventWrapper {

  private Event event;
  private String topicId;

  /**
   * Creates a new {@link EventWrapper}.
   *
   * @param event The {@link Event} object to be wrapped.
   * @param topicId The Identifier of the {@link Topic} to which the {@link Event} belongs.
   */
  public EventWrapper(Event event, String topicId) {
    this.event = event;
    this.topicId = topicId;
  }

  /**
   * @return The wrapped {@link Event}.
   */
  public Event getEvent() {
    return event;
  }

  /**
   * @return The Identifier of the related {@link Topic} to which the {@link Event} belongs.
   */
  public String getTopicId() {
    return topicId;
  }
}

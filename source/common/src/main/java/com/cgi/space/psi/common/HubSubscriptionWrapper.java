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

import com.cgi.space.psi.common.model.HubSubscription;
import com.cgi.space.psi.common.model.Topic;

/**
 * The class is a wrapper for the {@link HubSubscription} class. One addition to the {@link
 * HubSubscription} is the <code>topicId</code> field.
 */
@Document(collection = "hubSubscription")
public class HubSubscriptionWrapper {

  private HubSubscription hubSubscription;
  private String topicId;

  /**
   * Creates a new {@link HubSubscriptionWrapper}.
   *
   * @param hubSubscription The {@link HubSubscription} object to be wrapped.
   * @param topicId The Identifier of the {@link Topic} to which the {@link HubSubscription}
   *     belongs.
   */
  public HubSubscriptionWrapper(HubSubscription hubSubscription, String topicId) {
    this.hubSubscription = hubSubscription;
    this.topicId = topicId;
  }

  /**
   * @return The wrapped {@link HubSubscription}.
   */
  public HubSubscription getHubSubscription() {
    return hubSubscription;
  }

  /**
   * @return The Identifier of the related {@link Topic} to which the {@link HubSubscription}
   *     belongs.
   */
  public String getTopicId() {
    return topicId;
  }
}

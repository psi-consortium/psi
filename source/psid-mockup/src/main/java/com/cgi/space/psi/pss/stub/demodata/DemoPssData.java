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

import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.pss.stub.event.AbstractEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Demo PSS Data: The default Topics needed for the Event Management API
 * will be automatically inserted into the DB on startup.
 */
@Component
public class DemoPssData implements ApplicationStartupAware {

    public static final String TOPIC_ID_ORDER = "a447761a-7fd3-46cd-8947-7fa01ffb1a3c";
    public static final String TOPIC_ID_INQUIRY = "6a7b89e5-944c-43bd-b41b-5f863d4be766";
    public static final String TOPIC_ID_BILL = "051b505d-cb15-4a5a-b239-1daa6365104a";
    public static final String TOPIC_ID_ALARM = "55d4ee38-abda-426b-a739-7c7949c1ecb0";

    private static final String TOPIC = "Topic";

    @Autowired
    private MongoTemplate template;
    @Value("${psi.demo-data.enabled:true}")
    private boolean loadDemoData;

    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        if (!loadDemoData) {
            return;
        }
        template.remove(Topic.class).all();
        createTopics();
    }

    private void createTopics() {
        Topic orderTopic = new Topic();
        orderTopic.setId(TOPIC_ID_ORDER);
        orderTopic.setName(AbstractEvent.TOPIC_ORDER);
        orderTopic.setAtType(TOPIC);

        Topic inquiryTopic = new Topic();
        inquiryTopic.setId(TOPIC_ID_INQUIRY);
        inquiryTopic.setName(AbstractEvent.TOPIC_INQUIRY);
        inquiryTopic.setAtType(TOPIC);

        Topic billTopic = new Topic();
        billTopic.setId(TOPIC_ID_BILL);
        billTopic.setName(AbstractEvent.TOPIC_BILL);
        billTopic.setAtType(TOPIC);

        Topic alarmTopic = new Topic();
        alarmTopic.setId(TOPIC_ID_ALARM);
        alarmTopic.setName(AbstractEvent.TOPIC_ALARM);
        alarmTopic.setAtType(TOPIC);

        template.save(orderTopic);
        template.save(inquiryTopic);
        template.save(billTopic);
        template.save(alarmTopic);
    }
}

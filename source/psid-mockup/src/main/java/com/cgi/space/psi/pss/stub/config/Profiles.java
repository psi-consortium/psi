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
package com.cgi.space.psi.pss.stub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The class represents the available Spring Profiles in which the
 * {@link com.cgi.space.psi.pss.stub.PSIDApplicationMockup} can be executed.
 */
@Component
public class Profiles {

    /**
     * The profile name for a PSS.
     */
    public static final String PSS = "pss";

    /**
     * The profile name for a Provider.
     */
    public static final String PROVIDER = "pvd";

    @Value("${spring.profiles.active:pss}")
    private String activeProfile;

    /**
     * @return <code>True</code> if the PSS profile is active, <code>False</code> otherwise.
     */
    public boolean isPssActive() {
        return Profiles.PSS.equals(activeProfile);
    }

    /**
     * @return <code>True</code> if the Provider profile is active, <code>False</code> otherwise.
     */
    public boolean isProviderActive() {
        return Profiles.PROVIDER.equals(activeProfile);
    }
}

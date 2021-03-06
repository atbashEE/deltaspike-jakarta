/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.example.security.requestedpage.cdi;

import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

public class AuthenticationListener
{
    @Inject
    private ViewNavigationHandler viewNavigationHandler;

    @Inject
    private LoggedInAccessDecisionVoter loggedInAccessDecisionVoter;

    public void handleLoggedIn(@Observes UserEvent.LoggedIn event) 
    {
        this.viewNavigationHandler.navigateTo(loggedInAccessDecisionVoter.getDeniedPage());
        System.err.println("handling loggedin");
    }

    public void handleFailed(@Observes UserEvent.LoginFailed event)
    {
        this.viewNavigationHandler.navigateTo(Pages.Login.class);
        System.err.println("handling failed");
    }

}

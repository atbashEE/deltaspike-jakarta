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
package org.apache.deltaspike.test.servlet.impl.event.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

import org.apache.deltaspike.core.api.common.DeltaSpike;
import org.apache.deltaspike.core.api.lifecycle.Destroyed;
import org.apache.deltaspike.core.api.lifecycle.Initialized;

/**
 * Application scoped observer which listens for {@link ServletContext} events on the CDI event bus.
 */
@ApplicationScoped
public class ServletContextEventsObserver
{

    private final List<String> eventLog = new ArrayList<String>();

    @Inject
    @DeltaSpike
    private ServletContext servletContext;

    public void contextInitialized(@Observes @Initialized ServletContext context)
    {
        eventLog.add("Initialized ServletContext: " + context.getServletContextName());
        if (servletContext != null)
        {
            eventLog.add("ServletContext was injected");
        }
    }

    public void contextDestroyed(@Observes @Destroyed ServletContext context)
    {
        eventLog.add("Destroyed ServletContext: " + context.getServletContextName());
    }

    public int getEventCount()
    {
        return eventLog.size();
    }

    public List<String> getEventLog()
    {
        return Collections.unmodifiableList(eventLog);
    }

}

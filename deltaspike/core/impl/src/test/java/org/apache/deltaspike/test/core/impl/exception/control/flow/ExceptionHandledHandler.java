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

package org.apache.deltaspike.test.core.impl.exception.control.flow;

import org.apache.deltaspike.core.api.exception.control.BeforeHandles;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ExceptionHandler
public class ExceptionHandledHandler
{
    private boolean exAscCalled = false;
    private boolean iaeAscCalled = false;
    private boolean npeDescCalled = false;

    public void exHandler(@Handles ExceptionEvent<Exception> event)
    {
        exAscCalled = true;
    }

    public void npeHandler(@Handles ExceptionEvent<IllegalArgumentException> event)
    {
        iaeAscCalled = true;
        event.handled();
    }

    public void npeDescHandler(@BeforeHandles ExceptionEvent<NullPointerException> event)
    {
        npeDescCalled = true;
        event.handled();
    }

    public boolean isExAscCalled()
    {
        return exAscCalled;
    }

    public boolean isIaeAscCalled()
    {
        return iaeAscCalled;
    }

    public boolean isNpeDescCalled()
    {
        return npeDescCalled;
    }
}

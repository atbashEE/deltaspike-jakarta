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
package org.apache.deltaspike.test.jsf.impl.config.view.controller.uc002;

import org.apache.deltaspike.core.api.config.view.controller.PostRenderView;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


@Named("pageBean")
@RequestScoped
class PageBean002
{
    private boolean called = false;

    @PostRenderView
    protected void postRenderViewCallbackMethod()
    {
        called = true;
    }

    public boolean getCalled()
    {
        return called;
    }
}

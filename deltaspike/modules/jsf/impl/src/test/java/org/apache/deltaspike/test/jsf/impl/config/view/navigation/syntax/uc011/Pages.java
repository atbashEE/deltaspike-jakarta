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
package org.apache.deltaspike.test.jsf.impl.config.view.navigation.syntax.uc011;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.spi.config.view.ViewConfigRoot;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ViewConfigRoot
@Folder(name = "/") //there are multiple folders which should be grouped by the outermost interface
interface Pages extends ViewConfig
{
    //result: /index.xhtml
    class Index implements Pages
    {
    }

    //result: /admin
    interface Admin extends Pages
    {
        @View
        class Index implements Admin
        {
        }
    }

    //result: /public
    @Folder
    interface Public extends Pages
    {
        @View
        class Index implements Public
        {
        }
    }
}

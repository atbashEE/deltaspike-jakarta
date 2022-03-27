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
package org.apache.deltaspike.jsf.impl.util;

import org.apache.deltaspike.core.api.config.view.controller.ViewControllerRef;
import org.apache.deltaspike.core.api.config.view.metadata.SimpleCallbackDescriptor;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigDescriptor;

import java.lang.annotation.Annotation;

public abstract class ViewControllerUtils
{
    public static void executeViewControllerCallback(ViewConfigDescriptor viewDefinitionEntry,
                                                     Class<? extends Annotation> callbackType)
    {
        if (viewDefinitionEntry == null)
        {
            return;
        }

        SimpleCallbackDescriptor initViewCallbackDescriptor = viewDefinitionEntry.getExecutableCallbackDescriptor(
                ViewControllerRef.class, callbackType, SimpleCallbackDescriptor.class);

        if (initViewCallbackDescriptor != null)
        {
            initViewCallbackDescriptor.execute();
        }
    }
}

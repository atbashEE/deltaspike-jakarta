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
package org.apache.deltaspike.testcontrol.api.literal;

import org.apache.deltaspike.core.spi.filter.ClassFilter;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;
import org.apache.deltaspike.core.util.metadata.AnnotationInstanceProvider;
import org.apache.deltaspike.testcontrol.api.TestControl;

import jakarta.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;
import java.util.logging.Handler;

public class TestControlLiteral extends AnnotationLiteral<TestControl> implements TestControl
{
    private static final long serialVersionUID = 6684011035751678259L;

    private final TestControl defaultInstance;

    public TestControlLiteral()
    {
        this.defaultInstance = AnnotationInstanceProvider.of(TestControl.class);
    }

    @Override
    public Class<? extends Annotation>[] startScopes()
    {
        return defaultInstance.startScopes();
    }

    @Override
    public Class<? extends ProjectStage> projectStage()
    {
        return defaultInstance.projectStage();
    }

    @Override
    public Class<? extends Handler> logHandler()
    {
        return defaultInstance.logHandler();
    }

    @Override
    public boolean startExternalContainers()
    {
        return defaultInstance.startExternalContainers();
    }

    @Override
    public Class<? extends Label> activeAlternativeLabel()
    {
        return defaultInstance.activeAlternativeLabel();
    }

    @Override
    public Class<? extends ClassFilter> classFilter()
    {
        return defaultInstance.classFilter();
    }
}

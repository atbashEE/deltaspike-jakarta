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
package org.apache.deltaspike.test.testcontrol.uc012;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.test.category.SeCategory;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import javax.inject.Inject;

//Usually NOT needed! Currently only needed due to our arquillian-setup
@Category(SeCategory.class)



@RunWith(CdiTestRunner.class)
public class ApplicationScopedBeanTest
{
    @Inject
    private ApplicationScopedTestBean testBean;

    @Inject
    private ApplicationScopedTestBeanClient testBeanClient;

    @Test
    public void beanAccess()
    {
        this.testBean.setValue(0);
        Assert.assertEquals(1, this.testBeanClient.getNextValue());
        Assert.assertEquals(1, this.testBean.getValue());
    }

    @AfterClass
    public static void finalCheck()
    {
        int value = BeanProvider.getContextualReference(ApplicationScopedTestBean.class).getValue();
        int nextValue = BeanProvider.getContextualReference(ApplicationScopedTestBeanClient.class).getNextValue();

        if (value == 0)
        {
            throw new IllegalStateException("new application-scoped bean instance was created");
        }

        if (nextValue == 1)
        {
            throw new IllegalStateException("new application-scoped bean instance was created");
        }
    }
}

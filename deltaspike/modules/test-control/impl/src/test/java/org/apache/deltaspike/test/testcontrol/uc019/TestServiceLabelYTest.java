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
package org.apache.deltaspike.test.testcontrol.uc019;

import org.apache.deltaspike.test.category.SeCategory;
import org.apache.deltaspike.testcontrol.api.TestControl;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import javax.inject.Inject;

//Usually NOT needed! Currently only needed due to our arquillian-setup
@Category(SeCategory.class)

@RunWith(CdiTestRunner.class)
@TestControl(
        activeAlternativeLabel = TestServiceLabelYTest.TestLabelY.class,
        classFilter = TestServiceLabelYTest.LabelYFilter.class)
public class TestServiceLabelYTest
{
    @Inject
    private TestService testService;

    @Test
    public void resultY()
    {
        Assert.assertEquals("result-y", testService.getValue());
    }

    public static class TestLabelY implements TestControl.Label
    {
    }

    //replaces the text based config of labeled-alternatives
    public static class LabelYFilter extends TestLabeledAlternativeFilter
    {
        public LabelYFilter()
        {
            super(TestServiceLabelYTest.TestLabelY.class);
        }
    }
}

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
package org.apache.deltaspike.test.core.api.partialbean.uc006;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.test.core.api.partialbean.shared.TestPartialBeanBinding;
import org.apache.deltaspike.test.core.api.partialbean.util.ArchiveUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Ignore // FIXME We need fix Arquillian for Weld 5 Or Payara 6
public class ScopedPartialBeanTest
{
    @Deployment
    public static WebArchive war()
    {
        final String simpleName = ScopedPartialBeanTest.class.getSimpleName();
        final String archiveName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);

        final JavaArchive testJar = ShrinkWrap.create(JavaArchive.class, archiveName + ".jar")
                .addPackage(ScopedPartialBeanTest.class.getPackage())
                .addPackage(TestPartialBeanBinding.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        final WebArchive webArchive =  ShrinkWrap.create(WebArchive.class, archiveName + ".war")
                .addAsLibraries(ArchiveUtils.getDeltaSpikeCoreAndPartialBeanArchive())
                .addAsLibraries(testJar)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return webArchive;
    }

    @Test
    public void testPartialBeanWithApplicationScope() throws Throwable
    {
        ApplicationScopedPartialBean bean = BeanProvider.getContextualReference(ApplicationScopedPartialBean.class);
        bean.getManualResult();

        Assert.assertEquals("willFail3", bean.willFail3());

        try
        {
            bean.willFail2();

            Assert.fail("#willFail2 should actually throw a ClassNotFoundException");
        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getClass(), ClassNotFoundException.class);
        }
    }
}

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
package org.apache.deltaspike.test.core.impl.resourceloader;

import org.apache.deltaspike.core.api.resourceloader.InjectableResource;
import org.apache.deltaspike.test.category.SeCategory;
import org.apache.deltaspike.test.util.ArchiveUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import jakarta.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@RunWith(Arquillian.class)
@Category(SeCategory.class)
@Ignore // FIXME We need to configure Arquillian for Weld 5 (or Payara 6)
public class ClasspathResourceTest
{
    @Deployment
    public static Archive<?> createResourceLoaderArchive()
    {
        Archive<?> arch = ShrinkWrap.create(WebArchive.class, ClasspathResourceTest.class.getSimpleName() + ".war")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(ArchiveUtils.getDeltaSpikeCoreArchive());
        return arch;
    }

    @Inject
    @InjectableResource(location="myconfig.properties")
    private InputStream inputStream;

    @Inject
    @InjectableResource(location="myconfig.properties")
    private Properties properties;


    @Test
    public void testInputStream() throws IOException
    {
        Assert.assertNotNull(inputStream);
        Properties p = new Properties();
        p.load(inputStream);
        Assert.assertEquals("somevalue", p.getProperty("some.propertykey", "wrong answer"));
    }

    @Test
    public void testProperties()
    {
        Assert.assertEquals("somevalue",
            properties.getProperty("some.propertykey", "wrong answer"));
    }

    @Test
    public void testAmbiguousFileLookup(@InjectableResource(location="META-INF/beans.xml") InputStream inputStream)
    {
        // for some reason, this works
        Assert.assertNull(inputStream);
    }

    @Test
    public void testSuccessfulAmbiguousLookup(@InjectableResource(location="META-INF/beans.xml") List<InputStream> inputStreams)
    {
        Assert.assertTrue(inputStreams.size() > 1); //the count is different on as7 compared to the standalone setup
    }
}

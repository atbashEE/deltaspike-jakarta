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
package org.apache.deltaspike.test.core.impl.custom.spi;

import org.apache.deltaspike.test.util.ArchiveUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Ignore // FIXME We need to configure Arquillian for Weld 5 (or Payara 6)
public class ServiceUtilsWarFileTest extends ServiceUtilsTest
{
    @Deployment
    public static WebArchive deploy()
    {
        String simpleName = ServiceUtilsWarFileTest.class.getSimpleName();
        String archiveName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);

        JavaArchive testJar = ShrinkWrap.create(JavaArchive.class, archiveName + ".jar")
                .addPackage(ServiceUtilsWarFileTest.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return ShrinkWrap.create(WebArchive.class, archiveName + ".war")
                .addAsLibraries(ArchiveUtils.getDeltaSpikeCoreArchive())
                .addAsLibraries(testJar)
                //due to an issue with arquillian we can just add it to the web-archive (and not the jar)
                .addAsServiceProvider(MyInterface.class, MyImpl.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}

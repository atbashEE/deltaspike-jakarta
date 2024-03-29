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
package org.apache.deltaspike.test.core.api.exclude;

import java.io.IOException;

import org.apache.deltaspike.test.category.EnterpriseArchiveProfileCategory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.apache.deltaspike.test.core.api.exclude.ExcludeWarFileTest.getConfigContent;

/**
 * Tests for {@link org.apache.deltaspike.core.api.exclude.Exclude}
 */
@RunWith(Arquillian.class)
@Category(EnterpriseArchiveProfileCategory.class)
@Ignore // FIXME We need to configure Arquillian for Weld 5 (or Payara 6)
public class ExcludeEarFileTest extends ExcludeTest
{

    @Deployment
    public static EnterpriseArchive deployEar() throws IOException
    {
        //workaround for tomee - the ear-file needs to have the same name as the war-file
        String simpleName = ExcludeWarFileTest.class.getSimpleName();
        String archiveName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);


        // we needs to package the config into the EAR, as Weld3 based app servers boot the whole container
        // with just the EAR classloader. No WAR file packaged config is reachable for BeforeBeanDiscovery
        JavaArchive excludeConfigJar = ShrinkWrap.create(JavaArchive.class, "excludeConfig.jar")
            .addAsManifestResource(new StringAsset(getConfigContent()), "apache-deltaspike.properties");

        return ShrinkWrap.create(EnterpriseArchive.class, archiveName + ".ear")
                .addAsLibrary(excludeConfigJar)
                .addAsModule(ExcludeWarFileTest.deploy());
    }
}

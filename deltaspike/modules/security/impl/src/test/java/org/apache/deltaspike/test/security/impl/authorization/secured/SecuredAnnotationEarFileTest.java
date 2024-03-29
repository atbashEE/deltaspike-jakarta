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
package org.apache.deltaspike.test.security.impl.authorization.secured;

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


/**
 * Test for {@link org.apache.deltaspike.security.api.authorization.Secured}
 */
@RunWith(Arquillian.class)
@Category(EnterpriseArchiveProfileCategory.class)
@Ignore // FIXME We need fix Arquillian for Weld 5 Or Payara 6
public class SecuredAnnotationEarFileTest extends SecuredAnnotationTest
{
    public static final String CONFIG = "deltaspike.bean-manager.delegate_lookup=false\n"; // Weld3 bug :(

    @Deployment
    public static EnterpriseArchive deployEar()
    {
        //workaround for tomee - the ear-file needs to have the same name as the war-file
        String simpleName = SecuredAnnotationWarFileTest.class.getSimpleName();
        String archiveName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);

        JavaArchive configJar = ShrinkWrap.create(JavaArchive.class, "dsConfig.jar")
            .addAsManifestResource(new StringAsset(CONFIG),
                "apache-deltaspike.properties");

        return ShrinkWrap.create(EnterpriseArchive.class, archiveName + ".ear")
                .addAsLibrary(configJar)
                .addAsModule(SecuredAnnotationWarFileTest.deploy());
    }
}

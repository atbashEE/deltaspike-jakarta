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
package org.apache.deltaspike.test.jsf.impl.config.view.navigation.parameter.uc006;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.deltaspike.test.category.WebProfileCategory;
import org.apache.deltaspike.test.jsf.impl.util.ArchiveUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@RunWith(Arquillian.class)
@Category(WebProfileCategory.class)
@Ignore // FIXME We need fix Arquillian for Weld 5 Or Payara 6
public class NavigationParameterTest
{

    @Drone
    private WebDriver driver;

    @ArquillianResource
    private URL contextPath;

    @Deployment
    public static WebArchive deploy()
    {
        WebArchive archive = ShrinkWrap
                .create(WebArchive.class, "nav-parameter-uc006.war")
                .addPackage(PageBean006.class.getPackage())
                .addClass(Pages.class)
                .addAsLibraries(ArchiveUtils.getDeltaSpikeCoreAndJsfArchive())
                .addAsLibraries(ArchiveUtils.getDeltaSpikeSecurityArchive())
                .addAsWebResource("navigation/origin.xhtml", "/origin.xhtml")
                .addAsWebResource("navigation/pages/customErrorPage.xhtml", "/pages/customErrorPage.xhtml")
                .addAsWebInfResource("default/WEB-INF/web.xml", "web.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return archive;
    }

    @Test
    @RunAsClient
    public void testNavigationActionWithParameter() throws MalformedURLException
    {
        driver.get(new URL(contextPath, "origin.xhtml").toString());

        WebElement button = driver.findElement(By.id("parameter:pb006AnyMethod"));
        button.click();
        Assert.assertTrue(ExpectedConditions.textToBePresentInElement(By.id("customErrorPage"),
                "This is a custom error page")
                .apply(driver));
    }

}

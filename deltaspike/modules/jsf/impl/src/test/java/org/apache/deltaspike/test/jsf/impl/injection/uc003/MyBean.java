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
package org.apache.deltaspike.test.jsf.impl.injection.uc003;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Model
public class MyBean
{

    private String stringValue;

    private AnotherBean convertedValue;

    public String getStringValue()
    {
        return stringValue;
    }

    public void setStringValue(String stringValue)
    {
        this.stringValue = stringValue;
    }

    public AnotherBean getConvertedValue()
    {
        return convertedValue;
    }

    public void setConvertedValue(AnotherBean convertedValue)
    {
        this.convertedValue = convertedValue;
    }

    public void testAction()
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Worked"));
    }

    public boolean isValid(String value)
    {
        return "DeltaSpike".equals(value);
    }

}

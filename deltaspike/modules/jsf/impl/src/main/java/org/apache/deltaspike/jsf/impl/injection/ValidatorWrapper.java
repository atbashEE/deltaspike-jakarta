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
package org.apache.deltaspike.jsf.impl.injection;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

public class ValidatorWrapper extends AbstractContextualReferenceWrapper<Validator> implements Validator
{
    public ValidatorWrapper()
    {
    }

    public ValidatorWrapper(Validator wrapped, boolean fullStateSavingFallbackEnabled)
    {
        super(wrapped, fullStateSavingFallbackEnabled);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException
    {
        getWrapped().validate(facesContext, component, value);
    }

    @Override
    protected Validator resolveInstanceForClass(FacesContext facesContext, Class<?> wrappedClass)
    {
        FacesValidator facesValidator = wrappedClass.getAnnotation(FacesValidator.class);

        if (facesValidator == null)
        {
            return null;
        }

        return facesContext.getApplication().createValidator(facesValidator.value());
    }
}

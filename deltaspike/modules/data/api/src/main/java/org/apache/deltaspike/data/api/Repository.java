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
package org.apache.deltaspike.data.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.deltaspike.partialbean.api.PartialBeanBinding;

import javax.enterprise.inject.Stereotype;

/**
 * The Repository annotation needs to be present in order to have the
 * interface or class to be processed by the CDI extension.
 */
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@PartialBeanBinding
public @interface Repository
{
    /**
     * Relates the repository to a specific Entity. Can be left to
     * default when the Entity is determined by one of the base
     * repository classes.
     */
    Class<?> forEntity() default Object.class;

    /**
     * The method prefix for method expressions. Can be adapted to
     * domain specific conventions.
     */
    String methodPrefix() default "";

}

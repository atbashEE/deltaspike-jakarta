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
package org.apache.deltaspike.test.core.api.config.injectable;

import org.apache.deltaspike.core.api.config.Source;
import org.apache.deltaspike.core.impl.config.MapConfigSource;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@Source
@ApplicationScoped
public class CdiSource extends MapConfigSource
{
    public CdiSource() {
        super(create());
    }

    private static Map<String, String> create() {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("custom-source.test", "eulav");
        return map;
    }

    @Override
    public String getConfigName() {
        return "cdi-test";
    }
}

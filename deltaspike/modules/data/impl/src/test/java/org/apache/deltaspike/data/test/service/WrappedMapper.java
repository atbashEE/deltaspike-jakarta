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
package org.apache.deltaspike.data.test.service;

import java.util.List;

import org.apache.deltaspike.data.api.mapping.QueryInOutMapper;
import org.apache.deltaspike.data.test.domain.Simple;
import org.apache.deltaspike.data.test.domain.dto.BooleanWrapper;

public class WrappedMapper implements QueryInOutMapper<Simple>
{
    private final SimpleMapper delegate = new SimpleMapper();

    @Override
    public boolean mapsParameter(Object parameter)
    {
        if (parameter != null && parameter instanceof BooleanWrapper)
        {
            return true;
        }
        return delegate.mapsParameter(parameter);
    }

    @Override
    public Object mapParameter(Object parameter)
    {
        if (parameter instanceof BooleanWrapper)
        {
            return ((BooleanWrapper) parameter).getWrapped();
        }
        return delegate.mapParameter(parameter);
    }

    @Override
    public Object mapResult(Simple result)
    {
        return delegate.mapResult(result);
    }

    @Override
    public Object mapResultList(List<Simple> result)
    {
        return delegate.mapResultList(result);
    }

}

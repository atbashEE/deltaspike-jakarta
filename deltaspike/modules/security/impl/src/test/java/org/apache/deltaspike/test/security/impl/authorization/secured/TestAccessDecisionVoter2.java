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

import org.apache.deltaspike.security.api.authorization.AccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;

import jakarta.enterprise.context.RequestScoped;
import java.util.Collections;
import java.util.Set;

@RequestScoped
public class TestAccessDecisionVoter2 implements AccessDecisionVoter
{
    private static final long serialVersionUID = 1331427301357439806L;

    private boolean called;

    @Override
    public Set<SecurityViolation> checkPermission(AccessDecisionVoterContext accessDecisionVoterContext)
    {
        this.called = true;
        return Collections.emptySet();
    }

    public boolean isCalled()
    {
        return called;
    }
}

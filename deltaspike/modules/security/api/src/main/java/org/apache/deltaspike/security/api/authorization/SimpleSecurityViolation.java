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
package org.apache.deltaspike.security.api.authorization;

/**
 * Implementation which just returns the given reason
 */
class SimpleSecurityViolation implements SecurityViolation
{
    private static final long serialVersionUID = -5017812464381395966L;

    private final String reason;

    SimpleSecurityViolation(String reason)
    {
        this.reason = reason;
    }

    @Override
    public String getReason()
    {
        return reason;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof SimpleSecurityViolation))
        {
            return false;
        }

        SimpleSecurityViolation that = (SimpleSecurityViolation) o;

        return reason != null ? reason.equals(that.reason) : that.reason == null;
    }

    @Override
    public int hashCode()
    {
        return reason != null ? reason.hashCode() : 0;
    }
}

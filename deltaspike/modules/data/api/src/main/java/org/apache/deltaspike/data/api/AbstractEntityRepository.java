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

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;


/**
 * Base Repository class to be extended by concrete implementations
 * (abstract classes with implementation methods).
 *
 * @param <E>   Entity type.
 * @param <PK>  Primary key type.
 */
@Repository
public abstract class AbstractEntityRepository<E, PK extends Serializable>
        implements EntityRepository<E, PK>, EntityPersistenceRepository<E, PK>,
        EntityCountRepository<E>
{

    /**
     * Utility method to get hold of the entity manager for this Repository.
     * @return          Entity manager instance.
     */
    protected abstract EntityManager entityManager();

    /**
     * Utility method to create a criteria query.
     * @return          Criteria query
     */
    protected abstract CriteriaQuery<E> criteriaQuery();

    /**
     * Utility method to create a typed query.
     * @param qlString  Query string
     * @return          Typed query
     */
    protected abstract TypedQuery<E> typedQuery(String qlString);

    /**
     * Get the entity class this Repository is related to.
     * @return          Repository entity class.
     */
    protected abstract Class<E> entityClass();

    /**
     * Get the entity table name this Repository is related to.
     * @return          Repository entity table name.
     */
    protected abstract String tableName();

    /**
     * Get the entity name this Repository is related to.
     * @return          Repository entity name.
     */
    protected abstract String entityName();

}

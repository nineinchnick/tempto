/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.teradata.test.query;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.JDBCType;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.teradata.test.context.ThreadLocalTestContextHolder.testContext;

/**
 * Interface for executors of a sql queries.
 */
public interface QueryExecutor
        extends Closeable
{

    String DEFAULT_DB_NAME = "default";

    /**
     * Executes statement. Can be either SELECT or DDL/DML.
     * DDL/DML integer result is wrapped into QueryResult
     *
     * @return Result of executed statement.
     */
    QueryResult executeQuery(String sql, QueryParam... params)
            throws QueryExecutionException;

    /**
     * Force given query to be executed as update
     */
    QueryResult executeQuery(String sql, QueryType queryType, QueryParam... params)
            throws QueryExecutionException;

    Connection getConnection();

    /**
     * Executes given query on DB setup in test context.
     */
    public static QueryResult query(String sql, QueryParam... params)
            throws QueryExecutionException
    {
        return defaultQueryExecutor().executeQuery(sql, params);
    }

    public static QueryResult query(String sql, QueryType queryType, QueryParam... params)
            throws QueryExecutionException
    {
        return defaultQueryExecutor().executeQuery(sql, queryType, params);
    }

    public static QueryExecutor defaultQueryExecutor()
    {
        return testContext().getDependency(QueryExecutor.class, DEFAULT_DB_NAME);
    }

    public static QueryParam param(JDBCType type, Object value)
    {
        return new QueryParam(type, value);
    }

    public void close();

    public static class QueryParam
    {
        final JDBCType type;
        final Object value;

        private QueryParam(JDBCType type, Object value)
        {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return toStringHelper(this)
                    .add("type", type)
                    .add("value", value)
                    .toString();
        }
    }
}

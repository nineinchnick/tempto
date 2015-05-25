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

package com.teradata.test.fulfillment.table;

public class TableRequirements
{

    /**
     * Requirement for mutable table.
     * Test code is allowed to mutate (insert/delete rows) for mutable table
     */
    public static MutableTableRequirement mutableTable(TableDefinition tableDefinition)
    {
        return new MutableTableRequirement(tableDefinition);
    }

    /**
     * Requirement for mutable table.
     * Test code is allowed to mutate (insert/delete rows) for mutable table
     */
    public static MutableTableRequirement mutableTable(TableDefinition tableDefinition, String name, MutableTableRequirement.State state)
    {
        return new MutableTableRequirement(tableDefinition, name, state);
    }

    /**
     * Requirement for immutable table.
     */
    public static ImmutableTableRequirement immutableTable(TableDefinition tableDefinition)
    {
        return new ImmutableTableRequirement(tableDefinition);
    }
}

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

import com.teradata.test.fulfillment.NamedObjectsState;

import java.util.Map;

import static com.teradata.test.context.ThreadLocalTestContextHolder.testContext;

public class ImmutableTablesState
        extends NamedObjectsState<TableInstance>
{

    public static ImmutableTablesState immutableTablesState()
    {
        return testContext().getDependency(ImmutableTablesState.class);
    }

    public ImmutableTablesState(Map<String, TableInstance> immutableTableInstances)
    {
        super(immutableTableInstances, "immutable table");
    }
}

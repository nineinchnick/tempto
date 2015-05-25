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

package com.teradata.test.internal.configuration

import com.teradata.test.configuration.Configuration
import spock.lang.Specification

import static com.teradata.test.internal.configuration.TestConfigurationFactory.CLASSPATH_PROTOCOL
import static com.teradata.test.internal.configuration.TestConfigurationFactory.LOCAL_TEST_CONFIGURATION_URI_KEY
import static com.teradata.test.internal.configuration.TestConfigurationFactory.TEST_CONFIGURATION_URI_KEY

class TestConfigurationFactoryTest
        extends Specification
{

  def 'read test configuration'() {
    setup:
    System.setProperty(TEST_CONFIGURATION_URI_KEY, CLASSPATH_PROTOCOL + "/configuration/global-configuration-test.yaml");
    System.setProperty(LOCAL_TEST_CONFIGURATION_URI_KEY, CLASSPATH_PROTOCOL + "/configuration/local-configuration-test.yaml");

    when:
    Configuration configuration = TestConfigurationFactory.createTestConfiguration()

    then:
    configuration.getStringMandatory('value.local') == 'local'
    configuration.getStringMandatory('value.both') == 'local'
    configuration.getStringMandatory('value.global') == 'global'

    configuration.getStringMandatory('resolve.local') == 'local'
    configuration.getStringMandatory('resolve.both') == 'local'
    configuration.getStringMandatory('resolve.global') == 'global'
  }
}

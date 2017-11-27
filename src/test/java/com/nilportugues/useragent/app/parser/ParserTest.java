/**
 * Copyright 2012 Twitter, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nilportugues.useragent.app.parser;

import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests parsing results match the expected results in the test_resources yamls
 *
 * @author Steve Jiang (@sjiang) <gh at iamsteve com>
 */
public class ParserTest {

    private Yaml yaml = new Yaml();
    Parser parser;

    @Before
    public void initParser() throws Exception {
        parser = new Parser();
    }

    @Test
    public void testParseUserAgent() {
        testUserAgentFromYaml("test_ua.yaml");
    }

    @Test
    public void testParseOS() {
        testOSFromYaml("test_os.yaml");
    }

    @Test
    public void testParseAdditionalOS() {
        testOSFromYaml("additional_os_tests.yaml");
    }

    @Test
    public void testParseDevice() {
        testDeviceFromYaml("test_device.yaml");
    }

    @Test
    public void testParseFirefox() {
        testUserAgentFromYaml("firefox_user_agent_strings.yaml");
    }

    @Test
    public void testParsePGTS() {
        testUserAgentFromYaml("pgts_browser_list.yaml");
    }

    @Test
    public void testParseAll() {
        final String agentString1 = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; fr; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5,gzip(gfe),gzip(gfe)";
        final String agentString2 = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3";

        final Client expected1 = new Client(
            new UserAgent("Firefox", "3", "5", "5"),
            new OS("Mac OS X", "10", "4", null, null),
            new Device("Other"));

        final Client expected2 = new Client(
            new UserAgent("Mobile Safari", "5", "1", null),
            new OS("iOS", "5", "1", "1", null),
            new Device("iPhone"));

        assertThat(parser.parse(agentString1), is(expected1));
        assertThat(parser.parse(agentString2), is(expected2));
    }

    @Test
    public void testReplacementQuoting() throws Exception {
        final String testConfig = "user_agent_parsers:\n"
            + "  - regex: 'ABC([\\\\0-9]+)'\n"
            + "    family_replacement: 'ABC ($1)'\n"
            + "os_parsers:\n"
            + "  - regex: 'CatOS OH-HAI=/\\^\\.\\^\\\\='\n"
            + "    os_replacement: 'CatOS 9000'\n"
            + "device_parsers:\n"
            + "  - regex: 'CashPhone-([\\$0-9]+)\\.(\\d+)\\.(\\d+)'\n"
            + "    device_replacement: 'CashPhone $1'\n";

        final Parser testParser = parserFromStringConfig(testConfig);
        final Client result = testParser.parse("ABC12\\34 (CashPhone-$9.0.1 CatOS OH-HAI=/^.^\\=)");

        assertThat(result.getUserAgent().getFamily(), is("ABC (12\\34)"));
        assertThat(result.getOs().getFamily(), is("CatOS 9000"));
        assertThat(result.getDevice().getFamily(), is("CashPhone $9"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConfigThrows() throws Exception {
        parserFromStringConfig("user_agent_parsers:\n  - family_replacement: 'a'");
    }

    @SuppressWarnings("unchecked")
    private void testUserAgentFromYaml(final String filename) {
        final InputStream yamlStream = getClass().getClassLoader().getResourceAsStream(filename);

        final List<Map> testCases = (List<Map>) ((Map) yaml.load(yamlStream)).get("test_cases");
        for (Map<String, String> testCase : testCases) {
            // Skip tests with js_ua as those overrides are not yet supported in java
            if (testCase.containsKey("js_ua"))
                continue;

            String uaString = testCase.get("user_agent_string");
            assertThat(uaString, parser.parseUserAgent(uaString), is(UserAgent.fromMap(testCase)));
        }
    }

    @SuppressWarnings("unchecked")
    private void testOSFromYaml(final String filename) {
        final InputStream yamlStream = getClass().getClassLoader().getResourceAsStream(filename);

        final List<Map> testCases = (List<Map>) ((Map) yaml.load(yamlStream)).get("test_cases");
        for (Map<String, String> testCase : testCases) {
            // Skip tests with js_ua as those overrides are not yet supported in java
            if (testCase.containsKey("js_ua"))
                continue;

            String uaString = testCase.get("user_agent_string");
            assertThat(uaString, parser.parseOS(uaString), is(OS.fromMap(testCase)));
        }
    }

    @SuppressWarnings("unchecked")
    private void testDeviceFromYaml(final String filename) {
        final InputStream yamlStream = getClass().getClassLoader().getResourceAsStream(filename);

        final List<Map> testCases = (List<Map>) ((Map) yaml.load(yamlStream)).get("test_cases");
        for (Map<String, String> testCase : testCases) {

            String uaString = testCase.get("user_agent_string");
            assertThat(uaString, parser.parseDevice(uaString), is(Device.fromMap(testCase)));
        }
    }

    private Parser parserFromStringConfig(String configYamlAsString) throws Exception {
        final InputStream yamlInput = new ByteArrayInputStream(configYamlAsString.getBytes("UTF8"));
        return new Parser(yamlInput);
    }

}

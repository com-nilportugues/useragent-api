package com.nilportugues.useragent.app.modules.context.useragent.parser;

import org.junit.Before;
import org.junit.Test;

public class CachingParserTest extends ParserTest {

    @Before
    public void initParser() throws Exception {
        parser = new CachingParser();
    }

    @Test
    public void testCachedParseUserAgent() {
        super.testParseUserAgent();
        super.testParseUserAgent();
    }

    @Test
    public void testCachedParseOS() {
        super.testParseOS();
        super.testParseOS();
    }

    @Test
    public void testCachedParseAdditionalOS() {
        super.testParseAdditionalOS();
        super.testParseAdditionalOS();
    }

    @Test
    public void testCachedParseDevice() {
        super.testParseDevice();
        super.testParseDevice();
    }

    @Test
    public void testCachedParseFirefox() {
        super.testParseFirefox();
        super.testParseFirefox();
    }

    @Test
    public void testCachedParsePGTS() {
        super.testParsePGTS();
        super.testParsePGTS();
    }

    @Test
    public void testCachedParseAll() {
        super.testParseAll();
        super.testParseAll();
    }

    @Test
    public void testCachedReplacementQuoting() throws Exception {
        super.testReplacementQuoting();
        super.testReplacementQuoting();
    }
}

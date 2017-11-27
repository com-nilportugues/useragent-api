package com.nilportugues.useragent.app.modules.context.useragent.parser;

public class UserAgentTest extends DataTest<UserAgent> {

    @Override
    protected UserAgent getRandomInstance(long seed, StringGenerator g) {
        random.setSeed(seed);

        final String family = g.getString(256),
            major = (random.nextBoolean() ? g.getString(8) : null),
            minor = (random.nextBoolean() ? g.getString(8) : null),
            patch = (random.nextBoolean() ? g.getString(8) : null);

        return new UserAgent(family, major, minor, patch);
    }

    @Override
    protected UserAgent getBlankInstance() {
        return new UserAgent(null, null, null, null);
    }
}

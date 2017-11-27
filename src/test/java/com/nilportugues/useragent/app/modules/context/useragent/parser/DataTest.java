package com.nilportugues.useragent.app.modules.context.useragent.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public abstract class DataTest<T> {
    Random random;
    private Random seedRandom = new Random();

    @Before
    public void initialize() {
        random = new Random();
    }

    protected abstract T getRandomInstance(long seed, StringGenerator g);

    protected abstract T getBlankInstance();

    private long nextSeed() {
        return seedRandom.nextLong();
    }

    class StringGenerator {
        private HashSet<String> generated = new HashSet<String>();
        private Random strRand;
        private final boolean unique;

        StringGenerator(long seed, boolean unique) {
            strRand = new Random(seed);
            this.unique = unique;
        }

        String getString(int maxLen) {
            if (!unique) {
                return genString(maxLen);
            }
            while (true) {
                final String ret = genString(maxLen);
                if (generated.add(ret)) {
                    return ret;
                }
            }
        }

        private String genString(int maxLen) {
            int len = strRand.nextInt(maxLen + 1);
            final StringBuilder sb = new StringBuilder(len);
            for (int i = len; i-- > 0;) {
                // ascii printable range 32 to 126
                sb.append((char) (strRand.nextInt(126 - 32 + 1) + 32));
            }
            return sb.toString();
        }
    }

    @Test
    public void testEqualsAndHashCode() {
        for (int i = 1000; i-- > 0;) {
            long seed = nextSeed();
            final T first = getRandomInstance(seed, new StringGenerator(seed, false));
            final T second = getRandomInstance(seed, new StringGenerator(seed, false));

            assertThat(first, is(second));
            assertThat(first.hashCode(), is(second.hashCode()));
        }
    }

    @Test
    public void testNotEquals() {
        for (int i = 1000; i-- > 0;) {
            final StringGenerator uniqueGenerator = new StringGenerator(nextSeed(), true);
            final T first = getRandomInstance(nextSeed(), uniqueGenerator);
            final T second = getRandomInstance(nextSeed(), uniqueGenerator);

            assertThat(first, is(not(second)));
        }
    }

    @Test
    public void testBlankInstances() {
        final T first = getBlankInstance(), second = getBlankInstance();

        assertThat(first, is(second));
        assertThat(first.hashCode(), is(second.hashCode()));
    }
}

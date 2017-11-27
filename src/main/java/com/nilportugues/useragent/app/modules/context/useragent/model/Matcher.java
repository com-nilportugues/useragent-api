package com.nilportugues.useragent.app.modules.context.useragent.model;

class Matcher {
    String pattern;
    MatchingType matchType;

    public Matcher(String s, MatchingType m) {
        pattern = s;
        matchType = m;
    }

    public MatchingType getMatchType() {
        return matchType;
    }

    public boolean match(String token) {
        return matchType.matches(token, pattern);
    }

    @Override
    public String toString() {
        return matchType.name() + "(" + pattern + ")";
    }
}

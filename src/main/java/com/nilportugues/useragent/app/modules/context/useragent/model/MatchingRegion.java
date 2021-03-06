package com.nilportugues.useragent.app.modules.context.useragent.model;

enum MatchingRegion {
    REGULAR(true, false),
    PARENTHESIS(false, true),
    BOTH(true, true),
    CONSUMED(false, false);

    private boolean paren;
    private boolean regular;

    private MatchingRegion(boolean r, boolean p) {
        paren = p;
        regular = r;
    }

    public boolean includesParenthesis() {
        return paren;
    }

    public boolean includesRegular() {
        return regular;
    }
}

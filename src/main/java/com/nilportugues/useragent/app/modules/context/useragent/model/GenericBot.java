package com.nilportugues.useragent.app.modules.context.useragent.model;

class GenericBot {
    public java.util.regex.Pattern pattern;
    public int[] groups;
    public boolean discardAll;

    public GenericBot(String pattern, int[] groups, boolean discardAll) {
        this.pattern = java.util.regex.Pattern.compile(pattern);
        this.groups = groups;
        this.discardAll = discardAll;
    }
}

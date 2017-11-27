package com.nilportugues.useragent.app.modules.context.useragent.services;

import com.nilportugues.useragent.app.modules.context.useragent.parser.Parser;

import java.io.IOException;

public class UserParserSingleton {

    private static Parser instance;

    private UserParserSingleton() {

    }

    public static synchronized Parser getInstance() {
        if (instance == null) {
            try {
                instance = new Parser();
            } catch (IOException e) {
                instance = null;
            }
        }

        return instance;
    }
}

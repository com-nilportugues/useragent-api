package com.nilportugues.useragent.app.modules.context.useragent.model;

/**
 * This represents a class that will be able to detect a user-agent features.
 */
public interface IUserAgentDetector {
    /**
     * Parse a user-agent string
     *
     * @param ua The user agent string as sent by the browser
     * @return The result of the detection
     */
    UserAgentDetectionResult parseUserAgent(String ua);
}

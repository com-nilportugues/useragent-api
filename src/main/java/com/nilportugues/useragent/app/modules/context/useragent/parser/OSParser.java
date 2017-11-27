package com.nilportugues.useragent.app.modules.context.useragent.parser;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OSParser {
    private final List<OSPattern> patterns;

    public OSParser(List<OSPattern> patterns) {
        this.patterns = patterns;
    }

    public static OSParser fromList(List<Map<String, String>> configList) {

        final List<OSPattern> configPatterns = configList
            .stream()
            .map(OSParser::patternFromMap)
            .collect(Collectors.toList());

        return new OSParser(configPatterns);
    }

    private static OSPattern patternFromMap(Map<String, String> configMap) {
        String regex = configMap.get("regex");
        if (regex == null) {
            throw new IllegalArgumentException("OS is missing regex");
        }

        return (new OSPattern(Pattern.compile(regex),
            configMap.get("os_replacement"),
            configMap.get("os_v1_replacement"),
            configMap.get("os_v2_replacement"),
            configMap.get("os_v3_replacement")));
    }

    public OS parse(String agentString) {
        if (agentString == null) {
            return null;
        }

        OS os;
        for (OSPattern p : patterns) {
            if ((os = p.match(agentString)) != null) {
                return os;
            }
        }
        return new OS("Other", null, null, null, null);
    }

    private static class OSPattern {
        private final Pattern pattern;
        private final String osReplacement, v1Replacement, v2Replacement, v3Replacement;

        private OSPattern(Pattern pattern, String osReplacement, String v1Replacement, String v2Replacement, String v3Replacement) {
            this.pattern = pattern;
            this.osReplacement = osReplacement;
            this.v1Replacement = v1Replacement;
            this.v2Replacement = v2Replacement;
            this.v3Replacement = v3Replacement;
        }

        private OS match(String agentString) {
            String family = null, v1 = null, v2 = null, v3 = null, v4 = null;
            Matcher matcher = pattern.matcher(agentString);

            if (!matcher.find()) {
                return null;
            }

            int groupCount = matcher.groupCount();

            if (osReplacement != null) {
                if (groupCount >= 1) {
                    family = Pattern.compile("(" + Pattern.quote("$1") + ")")
                        .matcher(osReplacement)
                        .replaceAll(matcher.group(1));
                } else {
                    family = osReplacement;
                }
            } else if (groupCount >= 1) {
                family = matcher.group(1);
            }

            if (v1Replacement != null) {
                v1 = v1Replacement;
            } else if (groupCount >= 2) {
                v1 = matcher.group(2);
            }
            if (v2Replacement != null) {
                v2 = v2Replacement;
            } else if (groupCount >= 3) {
                v2 = matcher.group(3);
            }
            if (v3Replacement != null) {
                v3 = v3Replacement;
            } else if (groupCount >= 4) {
                v3 = matcher.group(4);
            }
            if (groupCount >= 5) {
                v4 = matcher.group(5);
            }

            return family == null ? null : new OS(family, v1, v2, v3, v4);
        }
    }
}

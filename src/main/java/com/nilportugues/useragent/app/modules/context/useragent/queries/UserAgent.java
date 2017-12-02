package com.nilportugues.useragent.app.modules.context.useragent.queries;

import com.nilportugues.useragent.app.infrastructure.cqrs.query.IQuery;
import com.nilportugues.useragent.app.infrastructure.cqrs.query.IQueryHandler;
import com.nilportugues.useragent.app.modules.context.useragent.model.Locale;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetector;

import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class UserAgent {

    public static class Query implements IQuery {
        private String userAgent;
        private String acceptLanguage;

        public Query(String userAgent, String acceptLanguage) {
            this.userAgent = userAgent;
            this.acceptLanguage = acceptLanguage;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public String getAcceptLanguage() {
            return acceptLanguage;
        }
    }

    @Named("UserAgent.QueryHandler")
    public static class QueryHandler implements IQueryHandler<Query> {

        @Override
        public CompletableFuture<UserAgentDetectionResult> handle(final Query query) {
            return CompletableFuture.supplyAsync(() -> {
                final UserAgentDetectionResult userAgent = new UserAgentDetector().parseUserAgent(query.getUserAgent());
                fallbackLanguageFromAcceptLanguage(query, userAgent);
                return userAgent;
            });
        }

        private void fallbackLanguageFromAcceptLanguage(final Query query, final UserAgentDetectionResult userAgent) {

            try {

                final java.util.Locale acceptLanguageLocale = getAcceptLanguageLocale(query);

                if (null != acceptLanguageLocale) {
                    languageLocaleFromAcceptLanguage(userAgent, acceptLanguageLocale);
                    countryLocaleFromAcceptLanguage(userAgent, acceptLanguageLocale);
                }
            } catch (Throwable ignored) {
                // ignored.printStackTrace();
            }
        }

        private void languageLocaleFromAcceptLanguage(final UserAgentDetectionResult userAgent,
            final java.util.Locale acceptLanguageLocale) {
            try {
                final Locale locale = userAgent.getLocale();
                final String localeName = locale.getLanguage().getLabel();

                final String localeLanguage = acceptLanguageLocale
                    .toLanguageTag()
                    .substring(acceptLanguageLocale.toLanguageTag().indexOf("-") + 1);

                boolean languageIsUnknown = Optional.ofNullable(localeName).isPresent();

                if (languageIsUnknown && !Objects.equals("", localeName)) {
                    userAgent.setLocale(new Locale(localeLanguage, locale.getCountry().getLabel()));
                }
            } catch (Throwable ignored) {
                // ignored.printStackTrace();
            }
        }

        private void countryLocaleFromAcceptLanguage(final UserAgentDetectionResult userAgent,
            final java.util.Locale acceptLanguageLocale) {
            try {
                final Locale locale = userAgent.getLocale();
                final String countryName = locale.getCountry().getLabel();
                final String localeCountry = acceptLanguageLocale.getCountry();
                boolean countryIsUnknown = countryName.equalsIgnoreCase("UNKNOWN");

                if (countryIsUnknown && !Objects.equals("", countryName)) {
                    userAgent.getLocale().setCountry(localeCountry);
                }
            } catch (Throwable ignored) {
                // ignored.printStackTrace();
            }
        }

        private java.util.Locale getAcceptLanguageLocale(final Query query) {

            for (String str : query.getAcceptLanguage().split(",")) {
                String[] arr = str.trim().replace("-", "_").split(";");

                // Parse the locale
                java.util.Locale locale = null;
                String[] l = arr[0].split("_");
                switch (l.length) {
                    case 2:
                        locale = new java.util.Locale(l[0], l[1]);
                        break;
                    case 3:
                        locale = new java.util.Locale(l[0], l[1], l[2]);
                        break;
                    default:
                        locale = new java.util.Locale(l[0]);
                        break;
                }

                // Parse the q-value
                Double q = 1.0D;
                for (String s : arr) {
                    s = s.trim();
                    if (s.startsWith("q=")) {
                        q = Double.parseDouble(s.substring(2).trim());
                        break;
                    }
                }

                return locale;
            }
            return null;
        }
    }
}

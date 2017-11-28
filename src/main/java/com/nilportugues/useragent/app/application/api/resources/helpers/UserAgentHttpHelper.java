package com.nilportugues.useragent.app.application.api.resources.helpers;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
public class UserAgentHttpHelper {

    public static String getHeader(final String headerName) {
        final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final HttpServletRequest request = servletRequestAttributes.getRequest();

        return request.getHeader(headerName);
    }
}

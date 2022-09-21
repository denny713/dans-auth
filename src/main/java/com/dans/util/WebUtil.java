package com.dans.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    public static String getHeaderValue(HttpServletRequest request, String key) {
        return request.getHeader(key);
    }
}

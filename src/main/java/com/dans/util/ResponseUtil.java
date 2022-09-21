package com.dans.util;

import com.dans.data.model.response.Response;

public class ResponseUtil {

    public static Response setSuccess(String msg) {
        Response response = new Response();
        try {
            response.setResult(true);
            response.setMessage("Success : " + msg);
        } catch (Exception e) {
            response.setResult(true);
            response.setMessage("Success : " + msg);
        }
        return response;
    }

    public static Response setFailed(String msg) {
        Response response = new Response();
        try {
            response.setResult(false);
            response.setMessage("Failed : " + msg);
        } catch (Exception e) {
            response.setResult(false);
            response.setMessage("Failed : " + msg);
        }
        return response;
    }
}

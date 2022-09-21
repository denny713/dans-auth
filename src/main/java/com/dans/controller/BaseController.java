package com.dans.controller;

import com.dans.data.model.response.Response;
import com.dans.service.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    protected TokenEndpoint tokenEndpoint;

    @Autowired
    protected UserService userService;

    protected Response response;
}

package com.tk.service.apigateway.application;

import com.google.common.collect.Lists;

import java.util.List;

public class WorkflowHttpUrls {
    public static final String API = "http://localhost:8080";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";

    public static List<String> getEndpointsWithNoTokenNeeded() {
        return Lists.newArrayList(LOGIN, REGISTER);
    }
}

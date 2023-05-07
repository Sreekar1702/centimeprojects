package com.example.mainapp.util;

import com.example.mainapp.constants.StringConstants;
import com.example.mainapp.model.User;
import com.example.mainapp.service.RestAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class APIServiceInvocationHelper {
    @Autowired
    private RestAPIService restAPIService;

    public String sendRequest(User user) {
        return restAPIService.getHello() + StringConstants.EMPTY_STRING + restAPIService.postConcatUser(user);
    }

}

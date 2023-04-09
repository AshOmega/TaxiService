package com.test.taxiservice.apigw.controllers;

import com.test.taxiservice.apigw.common.Constants;
import com.test.taxiservice.apigw.service.APIAuthenticatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Constants.ApiGWUrl.APIGW)
public class ApiGWLoginServiceController
{
  @Autowired
  APIAuthenticatorService apiAuthenticatorService;

  @Autowired
  RestTemplate restTemplate;

  @Value("${loginservice.host}")
  private String loginServiceHost;

  @GetMapping(Constants.ApiGWUrl.LOGIN_PROFILE_URL)
  public String getProfile(HttpServletRequest request, HttpServletResponse response,
      @RequestParam(value = "mobileNumber", required = true) String mobileNumber){
    boolean isAuthenticated = apiAuthenticatorService.authenticateRequest(request, response, mobileNumber);
    System.out.println(loginServiceHost);
    if(isAuthenticated){
      String apiResponse = restTemplate.getForObject(loginServiceHost + Constants.ApiGWUrl.LOGIN_PROFILE_URL, String.class);
      return apiResponse;
    }
    else
    {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return null;
    }
  }
}

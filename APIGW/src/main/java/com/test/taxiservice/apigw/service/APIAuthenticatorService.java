package com.test.taxiservice.apigw.service;

import com.test.taxiservice.apigw.model.redis.AccessToken;
import com.test.taxiservice.apigw.repository.redis.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class APIAuthenticatorService
{
  @Autowired
  AccessTokenRepository accessTokenRepository;
  public  boolean authenticateRequest(HttpServletRequest request, HttpServletResponse response, String mobileNumber){
    String bearerToken = request.getHeader("Authorization");

    AccessToken token = accessTokenRepository.findById(mobileNumber).get();

    if(bearerToken.equals(token.accessToken)){
      return true;
    }

    System.out.println("bearerToken=" + bearerToken);

    return false;
  }
}

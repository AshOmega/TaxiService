package com.test.taxiservice.loginservice.controllers;

import com.test.taxiservice.loginservice.constants.Constants;
import com.test.taxiservice.loginservice.model.redis.AccessToken;
import com.test.taxiservice.loginservice.repository.redis.AccessTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Random;

@RestController
public class LoginServiceController
{
  @Autowired
  private AccessTokenRepository accessTokenRepository;

  private static final String BEARER_ = "Bearer ";
  private static final long EXPIRY_DURATION = 24 * 60 * 60 * 1000; // 24 hour

  @Value("${app.jwt.secret}")
  private String SECRET_KEY;

  @GetMapping(Constants.LOGIN_PROFILE_URL)
  public String getProfile(HttpServletRequest request, HttpServletResponse response){

    return "Successfully called endpoint-" + Constants.LOGIN_PROFILE_URL;
  }


  @GetMapping(Constants.CREATE_ACCESS_TOKEN)
  public String createToken(HttpServletRequest request, HttpServletResponse response,
      @RequestParam(value = "mobileNumber", required = true) String mobileNumber){

    Random random = new Random();

    String token = Jwts.builder()
        .setSubject(String.format("%s",mobileNumber))
        .setIssuer("TaxiService")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_DURATION))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .compact();

    AccessToken accessToken = new AccessToken(
        mobileNumber, BEARER_ + token, EXPIRY_DURATION);

    accessTokenRepository.save(accessToken);
    return String.valueOf(token);
  }



}

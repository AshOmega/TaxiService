package com.test.taxiservice.apigw.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGwController
{

  @GetMapping("/healthcheck")
  public String healthCheck(){
    return "Healthcheck successful";
  }


}

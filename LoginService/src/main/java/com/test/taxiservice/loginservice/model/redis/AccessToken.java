package com.test.taxiservice.loginservice.model.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value="AccessToken")
public class AccessToken implements Serializable
{
  @Id
  private String mobileNumber;
  private String accessToken;
  private long expiry;

  public AccessToken(String mobileNumber, String accessToken, long expiry)
  {
    this.mobileNumber = mobileNumber;
    this.accessToken = accessToken;
    this.expiry = expiry;
  }
}

package com.test.taxiservice.apigw.model.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value="AccessToken")
public class AccessToken implements Serializable
{
  @Id
  public String mobileNumber;
  public String accessToken;
  public long expiry;
}

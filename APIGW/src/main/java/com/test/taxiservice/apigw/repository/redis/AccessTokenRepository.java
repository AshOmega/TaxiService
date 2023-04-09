package com.test.taxiservice.apigw.repository.redis;

import com.test.taxiservice.apigw.model.redis.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, String>
{

}

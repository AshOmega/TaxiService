package com.test.taxiservice.loginservice.redis;


import com.test.taxiservice.loginservice.model.redis.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, String>
{

}

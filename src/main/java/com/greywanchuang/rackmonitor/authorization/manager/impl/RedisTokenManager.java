package com.greywanchuang.rackmonitor.authorization.manager.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.manager.TokenManager;
import com.greywanchuang.rackmonitor.authorization.model.TokenModel;
import com.greywanchuang.rackmonitor.entity.User;
import com.greywanchuang.rackmonitor.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 *
 * @author ScienJus
 * @date 2015/7/31.
 * @see com.greywanchuang.rackmonitor.authorization.manager.TokenManager
 */
@Component
@Named
public class RedisTokenManager implements TokenManager {

    @Autowired
    private StringRedisTemplate redis;

    @Inject
    public void setRedis(StringRedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public TokenModel createToken(User user) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel("" + user.getId(), token);
        //存储到redis并设置过期时间
        redis.boundValueOps(token).set(JSONObject.toJSONString(user), Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public User getCurrentUser(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
//        String[] param = authentication.split("_");
//        if (param.length != 2) {
//            return null;
//        }
//        //使用userId和源token简单拼接成的token，可以增加加密措施
//        String userId = new String(param[0]);
//        String token = param[1];
//        return new TokenModel(userId, authentication);
        String user1 = redis.boundValueOps(authentication).get();
        if (user1 == null || "".equals(user1)) {
            return null;
        } else {
            User user= JSON.parseObject(user1,User.class);
            return user;
        }
    }

    public boolean checkToken(String authentication) {
        String user = redis.boundValueOps(authentication).get();
        if (user == null) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(user).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(String userId) {
        redis.delete(userId);
    }
}
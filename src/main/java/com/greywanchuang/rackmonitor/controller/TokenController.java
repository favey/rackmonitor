package com.greywanchuang.rackmonitor.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.authorization.annotation.CurrentUser;
import com.greywanchuang.rackmonitor.authorization.manager.TokenManager;
import com.greywanchuang.rackmonitor.authorization.model.TokenModel;
import com.greywanchuang.rackmonitor.config.ResultModel;
import com.greywanchuang.rackmonitor.config.ResultStatus;
import com.greywanchuang.rackmonitor.entity.User;
import com.greywanchuang.rackmonitor.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 * @author ScienJus
 * @date 2015/7/30.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public ResponseEntity<ResultModel> login(@RequestBody Map<String,String> json) {
        Assert.notNull(json.get("userName"), "username can not be empty");
        Assert.notNull(json.get("password"), "password can not be empty");

        User user = userRepository.findByUserName(json.get("userName"));
        if ((user == null) ||  //未注册
                !user.getPwd().equals(json.get("password"))) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(new Long(user.getId()).toString());
        return new ResponseEntity<>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    @ApiOperation(value = "退出登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        tokenManager.deleteToken(new Long(user.getId()).toString());
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

}

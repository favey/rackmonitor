package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.Config;
import com.greywanchuang.rackmonitor.entity.Modifycfg;
import com.greywanchuang.rackmonitor.repository.ConfigRepository;
import com.greywanchuang.rackmonitor.repository.ModifycfgRepository;
import com.greywanchuang.rackmonitor.util.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(name = "/v1/system")
public class SystemSettingController {
    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private ModifycfgRepository modifycfgRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "添加系统配置", notes = "添加配置，后台通过该配置获取连接参数并决定通过RMC还是IPMI获取监控数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/config", method = RequestMethod.PUT)
    public String addConfig(@RequestBody Map<String, Object> reqMap) {
        Modifycfg modifycfg = new Modifycfg();
        modifycfg.setCategory(reqMap.get("category").toString());
        modifycfg.setIp(reqMap.get("ip").toString());
        modifycfg.setUser(reqMap.get("user").toString());
        modifycfg.setOperate("1");
        modifycfg.setPwd(reqMap.get("pwd").toString());
        modifycfg.setDeviceId((Integer) reqMap.get("device_id"));
        modifycfg.setDeviceType((Integer) reqMap.get("device_type"));
        modifycfg.setTimestamp((int) (System.currentTimeMillis() / 1000));
        modifycfgRepository.save(modifycfg);
        return Utils.success();
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "删除系统配置", notes = " 删除连接的设备配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/config/{id}", method = RequestMethod.DELETE)
    public String delConfig(@PathVariable(name = "id") int id) {
        Config config = configRepository.findOne(id);
        Modifycfg modifycfg = new Modifycfg();
        modifycfg.setOperate("0");
        modifycfg.setTimestamp((int) (System.currentTimeMillis() / 1000));
        modifycfg.setPwd(config.getPwd());
        modifycfg.setUser(config.getUser());
        modifycfg.setCategory(config.getCategory());
        modifycfg.setIp(config.getIp());
        modifycfg.setDeviceType(config.getDeviceType());
        modifycfg.setDeviceId(config.getDeviceId());
        modifycfgRepository.save(modifycfg);
        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取所有监控设备的配置数据", notes = " 获取所有监控设备的配置数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/configs", method = RequestMethod.GET)
    public String getConfigs() {
        JSONArray jsonArray = new JSONArray();
        List<Config> configList = configRepository.findAll();
        jsonArray.addAll(configList);
        return jsonArray.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取指定监控设备的配置数据", notes = " 获取指定监控设备的配置数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    public String getConfig(@PathVariable(name = "id") int id) {
       Config config=configRepository.findOne(id);
        return JSONObject.toJSONString(config);
    }

}

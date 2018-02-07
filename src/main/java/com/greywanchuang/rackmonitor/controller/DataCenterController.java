package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.repository.PropertyRepository;
import com.greywanchuang.rackmonitor.repository.TargetReposiroty;
import com.greywanchuang.rackmonitor.util.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1")
public class DataCenterController {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private TargetReposiroty targetReposiroty;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心基本信息", notes = "获取数据中心基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/dc_basic_info", method = RequestMethod.GET)
    public String getBasicInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avgTemp", "50 C");
        jsonObject.put("power", "1240 kw");
        jsonObject.put("rHumidity", "58%");
        jsonObject.put("compute", "1200 Ghz");
        jsonObject.put("memory", "800TB");
        jsonObject.put("storage", "500 PB");
        jsonObject.put("smoke", "Critical");
        jsonObject.put("leaking", "Normal");
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心pue信息", notes = "获取数据中心pue信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/dc_pue", method = RequestMethod.GET)
    public String getPUEData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pue","1.56");
        JSONArray jsonArray =new JSONArray();

        for(int j=0;j>-7;j--)
        {
            JSONObject json=new JSONObject();
            json.put(Utils.getPreDate(j),1.5);
            jsonArray.add(json);
        }
        jsonObject.put("history",jsonArray);
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心pue信息", notes = "获取数据中心pue信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/dc_statistics", method = RequestMethod.GET)
    public String getDCStatistics()
    {
        JSONObject jsonObject=new JSONObject();

        return jsonObject.toJSONString();
    }



}

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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


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
        jsonObject.put("space","28%");
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
    @RequestMapping(value = "/dc_pue/{date_range}", method = RequestMethod.GET)
    public String getPUEData(@PathVariable int date_range, HttpServletResponse resp) {
        int daterange=0;
        if(date_range==1)
        {
            daterange=7;//week
        }
        else if(date_range==2)
        {
            daterange=15; //half month
        }
        else if(date_range==3)
        {
            daterange=30;
        }
        else
        {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "Wrong Date Range";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pue","1.56");
        JSONArray jsonArray =new JSONArray();

        for(int j=0;j>0-daterange;j--)
        {
            JSONObject json=new JSONObject();
            json.put("date",Utils.getPreDate(j));
            json.put("pue",1.5);
            jsonArray.add(json);
        }
        jsonObject.put("history",jsonArray);
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心能耗统计信息", notes = "获取数据中心能耗统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/dc_ec_statistics", method = RequestMethod.GET)
    public String getDCECStatistics()
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("server","1200 KW");
        jsonObject.put("aircon","1000 KW");
        jsonObject.put("pdu","500 KW");
        jsonObject.put("fan","5 KW");
        jsonObject.put("others","10 KW");

        JSONObject json=new JSONObject();
        json.put("power",jsonObject);
        json.put("graph","");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心统计信息", notes = "获取数据中心统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/dc_statistics", method = RequestMethod.GET)
    public String getDCStatistics()
    {
        JSONObject jsonObject=new JSONObject();

        JSONObject infoJson=new JSONObject();
        infoJson.put("computedPower","1200 KW");   // 预估用电量
        infoJson.put("designMaxPower","2000 KW");  // 设计最大供电
        infoJson.put("size","1200");    // 面积
        jsonObject.put("info",infoJson);

        JSONObject equipmentsJson=new JSONObject();
        equipmentsJson.put("cabinets",4);
        equipmentsJson.put("server",40);
        equipmentsJson.put("pdu",10);
        equipmentsJson.put("aircon",5);
        equipmentsJson.put("ups",10);
        jsonObject.put("equipments",equipmentsJson);

        JSONObject powerJson=new JSONObject();
        powerJson.put("upsLoad","10 KW");
        powerJson.put("pduLoad","8 KW");
        powerJson.put("pwSquare","2 KW");
        jsonObject.put("power",powerJson);

        JSONObject airJson=new JSONObject();
        airJson.put("oversupply","68%");
        airJson.put("util","N/A");
        airJson.put("roomAir","N/A");
        airJson.put("airSupply","N/A");
        jsonObject.put("air",airJson);


        return  jsonObject.toJSONString();
    }

}

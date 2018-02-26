package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.SystemProperty;
import com.greywanchuang.rackmonitor.repository.PropertyRepository;
import com.greywanchuang.rackmonitor.repository.SystemPropertyRepository;
import com.greywanchuang.rackmonitor.repository.TargetReposiroty;
import com.greywanchuang.rackmonitor.util.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/v1")
public class DataCenterController {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private TargetReposiroty targetReposiroty;

    @Autowired
    private SystemPropertyRepository systemPropertyRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心基本信息", notes = "获取数据中心基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/dc_basic_info", method = RequestMethod.GET)
    public String getBasicInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avgTemp", "50");
        jsonObject.put("power", "1240");
        jsonObject.put("rHumidity", "58");
        jsonObject.put("compute", "1200");
        jsonObject.put("memory", "800");
        jsonObject.put("storage", "500");
        jsonObject.put("space","28");
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
        return Utils.error("Wrong Date Range");
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
        jsonObject.put("server","1200");
        jsonObject.put("aircon","1000");
        jsonObject.put("pdu","500");
        jsonObject.put("fan","5");
        jsonObject.put("others","10");

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
        infoJson.put("computedPower","1200");   // 预估用电量
        infoJson.put("designMaxPower","2000");  // 设计最大供电
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
        powerJson.put("upsLoad","10");
        powerJson.put("pduLoad","8");
        powerJson.put("pwSquare","2");
        jsonObject.put("power",powerJson);

        JSONObject airJson=new JSONObject();
        airJson.put("oversupply","68%");
        airJson.put("util","N/A");
        airJson.put("roomAir","N/A");
        airJson.put("airSupply","N/A");
        jsonObject.put("air",airJson);


        return  jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑PUE阈值", notes = "编辑PUE阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/pue_threshold", method = RequestMethod.POST)
    public String editPUEThreshold(@RequestBody Map<String, Object> reqMap, HttpServletResponse reps) {
        if (reqMap.size() != 4) {
            reps.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Utils.error("Need 4 parameters!");
        }

        List<SystemProperty> systemProperties = systemPropertyRepository.findAllByPropertyName("pue_%");
        systemProperties.forEach(systemProperty -> {
            if (reqMap.keySet().contains(systemProperty.getProp())) {
                systemProperty.setVal(reqMap.get(systemProperty.getProp()).toString());
                systemPropertyRepository.save(systemProperty);
            }
        });

        return "Sucess";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取PUE阈值", notes = "获取PUE阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/pue_threshold", method = RequestMethod.GET)
    public String getPUEThreshold() {
        List<SystemProperty> systemProperties = systemPropertyRepository.findAllByPropertyName("pue_%");
        JSONObject jsonObject = new JSONObject();
        systemProperties.forEach((SystemProperty systemProperty) -> {
            jsonObject.put(systemProperty.getProp(), systemProperty.getVal());
        });
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑温度阈值", notes = "编辑温度阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/temp_threshold", method = RequestMethod.POST)
    public String editTempThreshold(@RequestBody Map<String, Object> reqMap, HttpServletResponse reps) {
        if (reqMap.size() != 4) {
            reps.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Need 4 parameters!";
        }

        List<SystemProperty> systemProperties = systemPropertyRepository.findAllByPropertyName("temp_%");
        systemProperties.forEach(systemProperty -> {
            if (reqMap.keySet().contains(systemProperty.getProp())) {
                systemProperty.setVal(reqMap.get(systemProperty.getProp()).toString());
                systemPropertyRepository.save(systemProperty);
            }
        });

        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取温度阈值", notes = "获取温度阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/temp_threshold", method = RequestMethod.GET)
    public String getTempThreshold() {
        List<SystemProperty> systemProperties = systemPropertyRepository.findAllByPropertyName("temp_%");
        JSONObject jsonObject = new JSONObject();
        systemProperties.forEach((SystemProperty systemProperty) -> {
            jsonObject.put(systemProperty.getProp(), systemProperty.getVal());
        });
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑能耗阈值", notes = "编辑能耗阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/power_threshold", method = RequestMethod.POST)
    public String editPowerThreshold(@RequestBody Map<String, Object> reqMap, HttpServletResponse reps) {
        if (reqMap.size() != 4) {
            reps.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Utils.error("Need 4 parameters!");
        }

        List<SystemProperty> systemProperties = systemPropertyRepository.findAllByPropertyName("power_%");
        systemProperties.forEach(systemProperty -> {
            if (reqMap.keySet().contains(systemProperty.getProp())) {
                systemProperty.setVal(reqMap.get(systemProperty.getProp()).toString());
                systemPropertyRepository.save(systemProperty);
            }
        });

        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取能耗阈值", notes = "获取能耗阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/power_threshold", method = RequestMethod.GET)
    public String getPowerThreshold() {
        List<SystemProperty> systemProperties = systemPropertyRepository.findAllByPropertyName("power_%");
        JSONObject jsonObject = new JSONObject();
        systemProperties.forEach((SystemProperty systemProperty) -> {
            jsonObject.put(systemProperty.getProp(), systemProperty.getVal());
        });
        return jsonObject.toJSONString();
    }

    @Authorization
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取最新时间戳", notes = "获取最新时间戳")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public String getLatestTime() {
        List<Integer> timestamp = propertyRepository.findNewstTimstamp(1);
        JSONObject jsonObject = new JSONObject();
        if (jsonObject.size() == 0) {
            jsonObject.put("timestamp", Calendar.getInstance().getTimeInMillis());

        } else {
            jsonObject.put("timestamp", timestamp.get(0));
        }
        return jsonObject.toJSONString();
    }
}

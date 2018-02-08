package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.SystemProperty;
import com.greywanchuang.rackmonitor.repository.SystemPropertyRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1")
public class CommonDataController {

    @Autowired
    private SystemPropertyRepository systemPropertyRepository;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心平均温度", notes = "获取数据中心平均温度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/avg_temp", method = RequestMethod.GET)
    public String getAvgTemp() {
        JSONObject json = new JSONObject();
        json.put("avg_temp", "50");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体能耗", notes = "获取数据中心当前整体能耗，温度单位为摄氏温度（℃）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/ec", method = RequestMethod.GET)
    public String getTotalEnergyComsumption() {

        JSONObject json = new JSONObject();
        json.put("ec", "5000");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前绝对湿度", notes = "获取数据中心当前绝对湿度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/humidity", method = RequestMethod.GET)
    public String getRealHumidity() {

        JSONObject json = new JSONObject();
        json.put("humidity", "58%");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体计算能力", notes = "获取数据中心当前整体计算能力")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/cp", method = RequestMethod.GET)
    public String getComputeCapability() {

        JSONObject json = new JSONObject();
        json.put("cp", "1223 Ghz");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体内存容量", notes = "获取数据中心当前整体内存容量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/memory", method = RequestMethod.GET)
    public String getTotalMemory() {

        JSONObject json = new JSONObject();
        json.put("memory", "1230 TB");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体硬盘容量", notes = "获取数据中心当前整体硬盘容量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public String getTotalStorage() {
        JSONObject json = new JSONObject();
        json.put("storae", "2000 PB");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前PUE值", notes = "获取数据中心当前PUE值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/pue", method = RequestMethod.GET)
    public String getCurrentPUE() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前PUE历史值", notes = "获取数据中心当前PUE历史值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/pue_history", method = RequestMethod.GET)
    public String getPUEHistory() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前能耗的分布情况", notes = "获取数据中心当前能耗的分布情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/ec_detail", method = RequestMethod.GET)
    public String getECDetail() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心的烟雾预警信息", notes = "获取数据中心的烟雾预警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/smoke", method = RequestMethod.GET)
    public String getSmokeStatus() {

        JSONObject json = new JSONObject();
        json.put("status", "Normal");
        return json.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心的水浸预警信息", notes = "获取数据中心的水浸预警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/leaching", method = RequestMethod.GET)
    public String getLeachingStatus() {
        JSONObject json = new JSONObject();
        json.put("status", "Critical");
        return json.toJSONString();
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
            return "Need 4 parameters!";
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

        return "Sucess";
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

    @RequestMapping(value = "help", method = RequestMethod.GET)
    public String help() {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }
}

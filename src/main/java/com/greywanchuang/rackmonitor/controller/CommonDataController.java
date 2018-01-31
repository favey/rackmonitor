package com.greywanchuang.rackmonitor.controller;

import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class CommonDataController {

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心平均温度", notes = "获取数据中心平均温度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/avg_temp", method = RequestMethod.GET)
    public String getAvgTemp()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体能耗", notes = "获取数据中心当前整体能耗，温度单位为摄氏温度（℃）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/ec", method = RequestMethod.GET)
    public String getTotalEnergyComsumption()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前绝对湿度", notes = "获取数据中心当前绝对湿度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/humidity", method = RequestMethod.GET)
    public String getRealHumidity()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体计算能力", notes = "获取数据中心当前整体计算能力")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/cp", method = RequestMethod.GET)
    public String getComputeCapability()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体内存容量", notes = "获取数据中心当前整体内存容量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/memory", method = RequestMethod.GET)
    public String getTotalMemory()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前整体硬盘容量", notes = "获取数据中心当前整体硬盘容量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public String getTotalStorage()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前PUE值", notes = "获取数据中心当前PUE值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/pue", method = RequestMethod.GET)
    public String getCurrentPUE()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前PUE历史值", notes = "获取数据中心当前PUE历史值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/pue_history", method = RequestMethod.GET)
    public String getPUEHistory()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心当前能耗的分布情况", notes = "获取数据中心当前能耗的分布情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/ec_detail", method = RequestMethod.GET)
    public String getECDetail()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心的烟雾预警信息", notes = "获取数据中心的烟雾预警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/smoke", method = RequestMethod.GET)
    public String getSmokeStatus()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取数据中心的水浸预警信息", notes = "获取数据中心的水浸预警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/leaching", method = RequestMethod.GET)
    public String getLeachingStatus()
    {
        return "";
    }


    @RequestMapping(value = "help", method = RequestMethod.GET)
    public String help() {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }
}

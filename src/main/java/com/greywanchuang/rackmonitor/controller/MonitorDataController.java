package com.greywanchuang.rackmonitor.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorDataController {

    @ApiOperation(value = "getRackType",notes = "获取机架型号")
    @RequestMapping(name = "/rack_type",method = RequestMethod.GET)
    public String rackType() {
        return "";
    }

    @ApiOperation(value = "getRackInfo",notes = "获取机架信息")
    @RequestMapping(name = "/rack_info",method = RequestMethod.GET)
    public String rackInfo() {
        return "";
    }

    @ApiOperation(value = "getPowerInfo",notes = "获取电源信息")
    @RequestMapping(name = "/power",method = RequestMethod.GET)
    public String powerInfo() {
        return "";
    }

    @ApiOperation(value = "getPowerDetail",notes = "获取电源详细信息")
    @RequestMapping(name = "/power_detail",method = RequestMethod.GET)
    public String powerDetail() {
        return "";
    }

    @ApiOperation(value = "getServerInfo",notes = "获取服务器基本信息")
    @RequestMapping(name = "/server_info",method = RequestMethod.GET)
    public String serverInfo()
    {
        return "";
    }

    @ApiOperation(value = "getServerChipsets",notes = "获取服务器Chipsets信息")
    @RequestMapping(name = "/server_shipsets",method = RequestMethod.GET)
    public String serverChipsets()
    {
        return "";
    }

    @ApiOperation(value = "getServerStatics",notes = "获取服务器过去三十分钟内的温度信息")
    @RequestMapping(name = "/server_statics",method = RequestMethod.GET)
    public String serverStatics()
    {
        return "";
    }


    @ApiOperation(value = "getFanWall",notes = "获取机柜的风扇信息")
    @RequestMapping(name = "/fan_wall",method = RequestMethod.GET)
    public String fanWall() {
        return "";
    }

    @ApiOperation(value = "getRackServer",notes = "获取机架上服务器的基本信息")
    @RequestMapping(name = "/rack_server",method = RequestMethod.GET)
    public String rackServersInfo() {
        return "";
    }

    @ApiOperation(value = "getPowerEnergyComsuption",notes = "获取电源的能耗情况")
    @RequestMapping(name = "/power_energy",method = RequestMethod.GET)
    public String powerEnergyComsuption()
    {
        return "";
    }
}

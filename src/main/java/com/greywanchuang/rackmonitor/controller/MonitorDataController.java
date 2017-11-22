package com.greywanchuang.rackmonitor.controller;

import com.greywanchuang.rackmonitor.repository.PropertyRepository;
import com.greywanchuang.rackmonitor.repository.RelationPRepository;
import com.greywanchuang.rackmonitor.repository.TargetReposiroty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorDataController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RelationPRepository relationPRepository;

    @Autowired
    private TargetReposiroty targetReposiroty;


    @ApiOperation(value = "getRackType", notes = "获取机柜型号")
    @RequestMapping(name = "/rack_type", method = RequestMethod.GET)
    public String rackType() {
        return "";
    }

    @ApiOperation(value = "getRackDetail", notes = "获取机柜信息")
    @RequestMapping(name = "/rack_detail", method = RequestMethod.GET)
    public String rackDetail() {
        return "";
    }

    @ApiOperation(value = "getRackFrontPanel", notes = "获取机柜前面板信息")
    @RequestMapping(name = "/rack_front_panel", method = RequestMethod.GET)
    public String rackFrontPanel() {
        return "";
    }

    @ApiOperation(value = "getRackBackPanel", notes = "获取机柜背面板信息")
    @RequestMapping(name = "/rack_back_panel", method = RequestMethod.GET)
    public String rackBackPanel() {
        return "";
    }


    @ApiOperation(value = "getPowerDetail", notes = "获取电源详细信息")
    @RequestMapping(name = "/power_detail", method = RequestMethod.GET)
    public String powerDetail() {
        return "";
    }

    @ApiOperation(value = "getServerDetail", notes = "获取服务器基本信息")
    @RequestMapping(name = "/server_detail", method = RequestMethod.GET)
    public String serverDetail() {
        return "";
    }


    @ApiOperation(value = "getServerStatics", notes = "获取服务器过去三十分钟内的温度信息")
    @RequestMapping(name = "/server_statics", method = RequestMethod.GET)
    public String serverStatics() {
        return "";
    }


    @ApiOperation(value = "getRackServerDetail", notes = "获取机架上服务器的基本信息")
    @RequestMapping(name = "/rack_server_detail", method = RequestMethod.GET)
    public String rackServersDetail() {
        return "";
    }

    @ApiOperation(value = "getPowerEnergyComsuption", notes = "获取电源的能耗情况")
    @RequestMapping(name = "/comsumption", method = RequestMethod.GET)
    public String powerEnergyComsuption() {
        return "";
    }
}

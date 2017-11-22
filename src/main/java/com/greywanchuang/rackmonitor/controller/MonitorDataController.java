package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.entity.Property;
import com.greywanchuang.rackmonitor.repository.PropertyRepository;
import com.greywanchuang.rackmonitor.repository.RelationRepository;
import com.greywanchuang.rackmonitor.repository.TargetReposiroty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorDataController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RelationRepository relationPRepository;

    @Autowired
    private TargetReposiroty targetReposiroty;


    @ApiOperation(value = "getRackType", notes = "获取机柜型号")
    @RequestMapping(value = "type", method = RequestMethod.GET)
    public String rackType() {
        List<Property> properties=propertyRepository.findByTargetidAndName(970,"PartNumber");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("type",properties.get(0).getValue());
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "getRackDetail", notes = "获取机柜信息")
    @RequestMapping(value = "rack", method = RequestMethod.GET)
    public String rackDetail() {

        return "";
    }

    @ApiOperation(value = "getRackFrontPanel", notes = "获取机柜前面板信息")
    @RequestMapping(value = "front_panel", method = RequestMethod.GET)
    public String rackFrontPanel() {
        return "";
    }

    @ApiOperation(value = "getRackBackPanel", notes = "获取机柜背面板信息")
    @RequestMapping(value = "back_panel", method = RequestMethod.GET)
    public String rackBackPanel() {
        return "";
    }


    @ApiOperation(value = "getPowerDetail", notes = "获取电源详细信息")
    @RequestMapping(value = "power_info", method = RequestMethod.GET)
    public String powerDetail() {
        return "";
    }

    @ApiOperation(value = "getServerDetail", notes = "获取服务器基本信息")
    @RequestMapping(value = "server_detail", method = RequestMethod.GET)
    public String serverDetail() {
        return "";
    }


//    @ApiOperation(value = "getServerStatics", notes = "获取服务器过去三十分钟内的温度信息")
//    @RequestMapping(name = "/server_statics", method = RequestMethod.GET)
//    public String serverStatics() {
//        return "";
//    }


//    @ApiOperation(value = "getRackServerDetail", notes = "获取机架上服务器的基本信息")
//    @RequestMapping(name = "/rack_server_detail", method = RequestMethod.GET)
//    public String rackServersDetail() {
//        return "";
//    }

    @ApiOperation(value = "getPowerEnergyComsuption", notes = "获取电源的能耗情况")
    @RequestMapping(value = "comsumption", method = RequestMethod.GET)
    public String powerEnergyComsuption() {
        return "";
    }
}

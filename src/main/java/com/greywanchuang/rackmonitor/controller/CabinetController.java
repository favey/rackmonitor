package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.Cabinet;
import com.greywanchuang.rackmonitor.entity.CabinetGroup;
import com.greywanchuang.rackmonitor.repository.CabinetGroupRepository;
import com.greywanchuang.rackmonitor.repository.CabinetRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1/cabinet")
public class CabinetController {

    @Autowired
    private CabinetRepository cabinetRepository;

    @Autowired
    private CabinetGroupRepository cabinetGroupRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜信息", notes = "获取机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getCainet() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜组信息", notes = "获取机柜组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String getCainetGroupList(@PathVariable int id)
    {
        CabinetGroup cabinetGroup=cabinetGroupRepository.findById(id);
        return JSONObject.toJSONString(cabinetGroup);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜组", notes = "增加机柜组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public String createCainetGroup(@RequestBody Map<String,Object> reqMap) {
        CabinetGroup cabinetGroup=new CabinetGroup();
        cabinetGroup.setLabel(reqMap.get("label").toString());
        cabinetGroup.setPosition((Integer) reqMap.get("position"));
        cabinetGroupRepository.save(cabinetGroup);
        return "Sucess";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "移除机柜组", notes = "移除机柜组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public String removeCainetGroupList(@PathVariable int id)
    {
        cabinetGroupRepository.deleteById(id);
        return "Sucess";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜信息", notes = "增加机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String createCainet(@RequestBody Map<String,Object> reqMap)
    {
        Object cgid=reqMap.get("cgid");
        CabinetGroup cabinetGroup=cabinetGroupRepository.findById((Integer) cgid);
        Cabinet cabinet=new Cabinet();
        cabinet.setCabinetGroup(cabinetGroup);
        cabinet.setTypeName(reqMap.get("type").toString());
        cabinet.setModelNumer(reqMap.get("model").toString());
        cabinet.setSerialNumber(reqMap.get("serialNo").toString());
        cabinet.setSmpAddres(reqMap.get("smp").toString());
        cabinet.setSpace((Integer) reqMap.get("space"));
        cabinet.setWeight((Integer) reqMap.get("weight"));
        cabinet.setDoorSensorIP(reqMap.get("dsIP").toString());

        cabinetRepository.save(cabinet);

        return "Success";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑机柜信息", notes = "编辑机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String editCainet() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取所有机柜类型", notes = "获取所有机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public String getCainets() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜类型", notes = "增加机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public String createCainetType() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "删除机柜类型", notes = "删除机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/type", method = RequestMethod.DELETE)
    public String deleteCainetType(@RequestParam("id") int ctId) {
        return "";
    }


}

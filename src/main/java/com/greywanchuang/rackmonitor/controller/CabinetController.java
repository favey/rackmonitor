package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.Cabinet;
import com.greywanchuang.rackmonitor.entity.CabinetGroup;
import com.greywanchuang.rackmonitor.repository.CabinetGroupRepository;
import com.greywanchuang.rackmonitor.repository.CabinetRepository;
import com.greywanchuang.rackmonitor.util.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/cabinet")
public class CabinetController {

    @Autowired
    private CabinetRepository cabinetRepository;

    @Autowired
    private CabinetGroupRepository cabinetGroupRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜概况信息(平面图)", notes = "获取机柜概况信息(平面图)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/plane_view/{id}", method = RequestMethod.GET)
    public String getCainetPlaneView(@PathVariable int id) {
        Cabinet cabinet=cabinetRepository.findById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("label",cabinet.getLabel());
        jsonObject.put("power","1200 KW");
        jsonObject.put("temp","40 C");
        jsonObject.put("humidity","Normal");
        jsonObject.put("door","N/A");
        jsonObject.put("fan","Normal");
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜基本信息", notes = "获取机柜基本信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
    @Authorization
    @RequestMapping(value = "/basic_info/{id}", method = RequestMethod.GET)
    public String getCainetBasicInfo(@PathVariable int id) {
        Cabinet cabinet=cabinetRepository.findById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("totalPower","1200 KW");
        jsonObject.put("usage","20%");
        jsonObject.put("weight",cabinet.getWeight());
        jsonObject.put("humidity","Normal");
        jsonObject.put("door","N/A");
        jsonObject.put("fan","Normal");
        jsonObject.put("tCurrent","10 A");
        jsonObject.put("leaking","Normal");
        jsonObject.put("smoke","Normal");
        JSONObject tempJson=new JSONObject();
        tempJson.put("top","N/A");
        tempJson.put("middle","N/A");
        tempJson.put("bottom","N/A");
        tempJson.put("average","N/A");
        tempJson.put("btu","N/A");
        jsonObject.put("temp",tempJson);

        JSONObject infoJson=new JSONObject();
        infoJson.put("model",cabinet.getModelNumer());
        infoJson.put("sn",cabinet.getSerialNumber());
        infoJson.put("firmware","N/A");
        infoJson.put("mac","N/A");
        infoJson.put("netmask","N/A");
        jsonObject.put("info",infoJson);

        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取指定机柜组信息", notes = "获取指定机柜组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String getCainetGroup(@PathVariable int id) {
        CabinetGroup cabinetGroup = cabinetGroupRepository.findById(id);
        return JSONObject.toJSONString(cabinetGroup);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜组列表信息", notes = "获取机柜组列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group_list", method = RequestMethod.GET)
    public String getCainetGroupList() {
        List<CabinetGroup> cabinetGroupList=cabinetGroupRepository.findAll();
        return JSONObject.toJSONString(cabinetGroupList);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜组", notes = "增加机柜组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public String createCainetGroup(@RequestBody Map<String, Object> reqMap) {
        CabinetGroup cabinetGroup = new CabinetGroup();
        cabinetGroup.setLabel(reqMap.get("label").toString());
        cabinetGroup.setPosition((Integer) reqMap.get("position"));
        cabinetGroupRepository.save(cabinetGroup);
        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑机柜组", notes = "编辑机柜组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group/{id}", method = RequestMethod.POST)
    public String editCainetGroup(@PathVariable int id, @RequestBody Map<String, Object> reqMap) {
        CabinetGroup cabinetGroup = cabinetGroupRepository.findById(id);
        cabinetGroup.setLabel(reqMap.get("label").toString());
        cabinetGroup.setPosition((Integer) reqMap.get("position"));
        cabinetGroupRepository.save(cabinetGroup);
        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "移除机柜组", notes = "移除机柜组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public String removeCainetGroupList(@PathVariable int id) {
        cabinetGroupRepository.deleteById(id);
        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜信息", notes = "增加机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String createCainet(@RequestBody Map<String, Object> reqMap) {
        Object cgid = reqMap.get("cgid");
        CabinetGroup cabinetGroup = cabinetGroupRepository.findById((Integer) cgid);
        Cabinet cabinet = new Cabinet();
        cabinet.setCabinetGroup(cabinetGroup);

        cabinet.setTypeName(reqMap.get("type").toString());
        cabinet.setModelNumer(reqMap.get("model").toString());
        cabinet.setSerialNumber(reqMap.get("serialNo").toString());
        cabinet.setSmpAddres(reqMap.get("smp").toString());
        cabinet.setSpace((Integer) reqMap.get("space"));
        cabinet.setLabel(reqMap.get("label").toString());
        cabinet.setComputedPower((Double) reqMap.get("power"));
        cabinet.setWeight((Integer) reqMap.get("weight"));
        cabinet.setDoorSensorIP(reqMap.get("dsIP").toString());

        cabinetRepository.save(cabinet);

        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑机柜信息", notes = "编辑机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String editCainet(@PathVariable int id, @RequestBody Map<String, Object> reqMap) {
        Cabinet cabinet = cabinetRepository.findById(id);
        cabinet.setModelNumer(reqMap.get("model").toString());
        cabinet.setSerialNumber(reqMap.get("serialNo").toString());
        cabinet.setLabel(reqMap.get("label").toString());
        cabinet.setComputedPower((Double) reqMap.get("power"));
        cabinet.setWeight((Integer) reqMap.get("weight"));

        cabinetRepository.save(cabinet);

        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜PSU信息", notes = "获取机柜PSU信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/psu/{id}", method = RequestMethod.GET)
    public String getCabinetPSUInfo(@PathVariable int id)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status","N/A");
        jsonObject.put("health","N/A");
        jsonObject.put("input","N/A");
        jsonObject.put("output","N/A");
        jsonObject.put("efficiency","N/A");
        jsonObject.put("maxPower","N/A");
        jsonObject.put("mount",0);
        jsonObject.put("mode","N/A");
        jsonObject.put("firmware","N/A");

        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "移除机柜信息", notes = "移除机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeCainet(@PathVariable int id) {
        Cabinet cabinet = cabinetRepository.findById(id);
        cabinet.setStatus(1);
        cabinetRepository.save(cabinet);

        return Utils.success();
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

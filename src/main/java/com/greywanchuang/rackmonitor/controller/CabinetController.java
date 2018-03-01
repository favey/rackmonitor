package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.Cabinet;
import com.greywanchuang.rackmonitor.entity.CabinetGroup;
import com.greywanchuang.rackmonitor.entity.CabinetType;
import com.greywanchuang.rackmonitor.repository.CabinetGroupRepository;
import com.greywanchuang.rackmonitor.repository.CabinetRepository;
import com.greywanchuang.rackmonitor.repository.CabinetTypeRepository;
import com.greywanchuang.rackmonitor.util.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private CabinetTypeRepository cabinetTypeRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜概况信息(平面图)", notes = "获取机柜概况信息(平面图)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/plane_view/{id}", method = RequestMethod.GET)
    public String getCainetPlaneView(@PathVariable int id) {
        Cabinet cabinet = cabinetRepository.findById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("space", cabinet.getSpace());
        jsonObject.put("power", "1200");
        jsonObject.put("temp", "40");
        jsonObject.put("humidity", "N/A");
        jsonObject.put("door", "N/A");
        jsonObject.put("fan", "N/A");
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜基本信息", notes = "获取机柜基本信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
    @Authorization
    @RequestMapping(value = "/basic_info/{id}", method = RequestMethod.GET)
    public String getCainetBasicInfo(@PathVariable int id) {
        Cabinet cabinet = cabinetRepository.findById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalPower", "1200");
        jsonObject.put("usage", "20");
        jsonObject.put("weight", cabinet.getWeight());
        jsonObject.put("humidity", "Normal");
        jsonObject.put("door", "N/A");
        jsonObject.put("fan", "Normal");
        jsonObject.put("tCurrent", "10");
        jsonObject.put("leaking", "Normal");
        jsonObject.put("smoke", "Normal");

        JSONObject tempJson = new JSONObject();
        tempJson.put("top", "N/A");
        tempJson.put("middle", "N/A");
        tempJson.put("bottom", "N/A");
        tempJson.put("average", "N/A");
        tempJson.put("btu", "N/A");
        jsonObject.put("temp", tempJson);

        JSONObject infoJson = new JSONObject();
        infoJson.put("model", cabinet.getModelNumer());
        infoJson.put("sn", cabinet.getSerialNumber());
        infoJson.put("firmware", "N/A");
        infoJson.put("mac", "N/A");
        infoJson.put("netmask", "N/A");
        infoJson.put("ip", "N/A");
        jsonObject.put("info", infoJson);

        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取指定机柜组信息", notes = "获取指定机柜组信息,并返回其下所有状态不为1的机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String getCainetGroup(@PathVariable int id) {
        CabinetGroup cabinetGroup = cabinetGroupRepository.findById(id);
        List<Cabinet> cabinets = cabinetRepository.findAllByCgroupIdAndStatus(id, 0);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(cabinetGroup);
        JSONArray array = new JSONArray();
        cabinets.forEach(cabinet -> {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("space", cabinet.getSpace());
            jsonObj.put("power", "1200");
            jsonObj.put("temp", "40");
            jsonObj.put("humidity", "N/A");
            jsonObj.put("door", "N/A");
            jsonObj.put("fan", "N/A");
            jsonObj.put("id", cabinet.getId());
            jsonObj.put("label", cabinet.getLabel());
            jsonObj.put("type", cabinet.getTypeName());
            array.add(jsonObj);
        });
        jsonObject.put("cabinets", array);
        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜组列表信息", notes = "获取机柜组列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group_list", method = RequestMethod.GET)
    public String getCainetGroupList() {
        List<CabinetGroup> cabinetGroupList = cabinetGroupRepository.findAll();
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
    public String editCainetGroup(@PathVariable("id") int id, @RequestBody Map<String, Object> reqMap) {
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
        cabinet.setCgroupId(cabinetGroup.getId());

        cabinet.setTypeName(reqMap.get("type").toString());
        cabinet.setModelNumer(reqMap.get("model").toString());
        cabinet.setSerialNumber(reqMap.get("serialNo").toString());
        cabinet.setSmpAddres(reqMap.get("smp").toString());
        cabinet.setSpace(Integer.parseInt(reqMap.get("space").toString()));
        cabinet.setLabel(reqMap.get("label").toString());
        cabinet.setComputedPower(Integer.parseInt(reqMap.get("power").toString()));
        cabinet.setWeight(Double.parseDouble(reqMap.get("weight").toString()));
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
    public String editCainet(@PathVariable("id") int id, @RequestBody Map<String, Object> reqMap) {
        Cabinet cabinet = cabinetRepository.findById(id);
        cabinet.setModelNumer(reqMap.get("model").toString());
        cabinet.setSerialNumber(reqMap.get("serialNo").toString());
        cabinet.setLabel(reqMap.get("label").toString());
        cabinet.setComputedPower(Integer.parseInt(reqMap.get("power").toString()));
        cabinet.setWeight(Double.parseDouble(reqMap.get("weight").toString()));

        cabinetRepository.save(cabinet);

        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜风扇列表", notes = "获取机柜风扇列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/fans", method = RequestMethod.POST)
    public String getFans(@RequestBody Map<String, Object> reqMap, HttpServletResponse rsp) {
        Object cidObj = reqMap.get("cid");
        if (cidObj == null) {
            rsp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Utils.error("No Cabinet ID");
        }
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑机柜SMP信息", notes = "编辑机柜SMP信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/smp", method = RequestMethod.POST)
    public String editSMPAddress(@RequestBody Map<String, Object> reqMap, HttpServletResponse rsp) {
        int cid= (int) reqMap.get("cid");
        Cabinet cabinet = cabinetRepository.findById(cid);
        if (cabinet == null) {
            rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return Utils.error("Cabinet Not Exist!");
        }
        Object obj = reqMap.get("smp");
        if (obj == null) {
            rsp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Utils.error("Wrong Param!");

        }

        cabinet.setSmpAddres(obj.toString());
        return Utils.success();

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜PSU信息", notes = "获取机柜PSU（Power Supply Unit）的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/psu/{id}", method = RequestMethod.GET)
    public String getCabinetPSUInfo(@PathVariable int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "N/A");
        jsonObject.put("health", "N/A");
        jsonObject.put("input", "N/A");
        jsonObject.put("output", "N/A");
        jsonObject.put("efficiency", "N/A");
        jsonObject.put("maxPower", "N/A");
        jsonObject.put("mount", 0);
        jsonObject.put("mode", "N/A");
        jsonObject.put("firmware", "N/A");

        return jsonObject.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "查看机柜的PSU中电源模块信息", notes = "机柜中的PSU中包含多个电源模块，该功能允许用户查看机柜中PSU（Power Supply Unit）的电源模块信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/psu_list", method = RequestMethod.POST)
    public String getCabinetPSUList(@RequestBody Map<String, Object> reqMap) {
        Cabinet cabinet = cabinetRepository.findById((Integer) reqMap.get("cid"));
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (int i = 0; i < 10; i++) {
            jsonObject = new JSONObject();
            jsonObject.put("name", "#" + i);
            jsonObject.put("status", "On");
            jsonObject.put("voltage", "5");
            jsonObject.put("current", "2");
            jsonArray.add(jsonObject);
        }

        return jsonArray.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "移除机柜信息", notes = "移除机柜信息,实际上并不删除数据，只是标记数据为已删除")
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
        List<CabinetType> cabinetTypes = cabinetTypeRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(cabinetTypes);
        return jsonArray.toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜类型", notes = "增加机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public String createCainetType(@RequestBody Map<String, Object> reqMap) {
        CabinetType cabinetType = new CabinetType();
        cabinetType.setName(reqMap.get("name").toString());
        cabinetType.setMemo(reqMap.get("desc").toString());
        cabinetTypeRepository.save(cabinetType);
        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "删除机柜类型", notes = "删除机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public String deleteCainetType(@PathVariable("id") int ctId) {
        cabinetTypeRepository.deleteById(ctId);
        return Utils.success();
    }


}

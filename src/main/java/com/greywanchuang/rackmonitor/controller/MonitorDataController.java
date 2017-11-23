package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.domain.FanGroup;
import com.greywanchuang.rackmonitor.domain.Power;
import com.greywanchuang.rackmonitor.domain.Rack;
import com.greywanchuang.rackmonitor.domain.Server;
import com.greywanchuang.rackmonitor.entity.Property;
import com.greywanchuang.rackmonitor.entity.Relation;
import com.greywanchuang.rackmonitor.entity.Target;
import com.greywanchuang.rackmonitor.repository.PropertyRepository;
import com.greywanchuang.rackmonitor.repository.RelationRepository;
import com.greywanchuang.rackmonitor.repository.TargetReposiroty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        int timestamp = propertyRepository.findNewstTimstamp();
        List<Relation> relations = relationPRepository.findByParent(-1);
        Property property = propertyRepository.findByTargetidAndNameAndTimestamp(relations.get(0).getChild(), "PartNumber", timestamp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", property.getValue());
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "getRackDetail", notes = "获取机柜信息")
    @RequestMapping(value = "rack", method = RequestMethod.GET)
    public String rackDetail() {
        int timestamp = propertyRepository.findNewstTimstamp();
        List<Property> properties = propertyRepository.findAllByTimestampAndTargetid(timestamp, relationPRepository.findByParent(-1).get(0).getChild());
        Rack rack = new Rack();
        rack.compose(properties, rack);
        return JSONObject.toJSONString(rack);
    }

    @ApiOperation(value = "getRackFrontPanel", notes = "获取机柜前面板信息")
    @RequestMapping(value = "front_panel", method = RequestMethod.GET)
    public String rackFrontPanel() {
        int timestamp = propertyRepository.findNewstTimstamp();
        //获取Power信息
        StringBuffer powerTargetName = new StringBuffer("system/power1/");
        Target target = targetReposiroty.getByName(powerTargetName.toString());
        List<Property> properties = propertyRepository.findAllByTimestampAndTargetid(timestamp, target.getId());
        JSONObject jsonObject = new JSONObject();
        JSONObject powerJsonObject = new JSONObject();
        properties.forEach(property -> {
            if ("Status".equals(property.getName())) {
                powerJsonObject.put("status", property.getValue());
            } else if ("Health".equals(property.getName())) {
                powerJsonObject.put("health", property.getValue());
            } else if ("Input".equals(property.getName())) {
                powerJsonObject.put("input", property.getValue());
            } else if ("Output".equals(property.getName())) {
                powerJsonObject.put("output", property.getValue());
            } else if ("Efficiency".equals(property.getName())) {
                powerJsonObject.put("efficiency", property.getValue());
            }
        });
        jsonObject.put("power", powerJsonObject);

        //获取server信息
        StringBuffer serverTargetName = new StringBuffer("system/chassis1/");
        Target servertarget = targetReposiroty.getByName(serverTargetName.toString());
        List<Relation> relationList = relationPRepository.findByParent(servertarget.getId());
        JSONArray nodes = new JSONArray();
        relationList.forEach(relation -> {
            List<Property> propertyList = propertyRepository.findAllByTimestampAndTargetid(timestamp, relation.getChild());
            Target t = targetReposiroty.getById(relation.getChild());
            nodes.add(Server.conposeJson(propertyList, t.getName()));
        });
        jsonObject.put("servers", nodes);
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "getRackBackPanel", notes = "获取机柜背面板信息")
    @RequestMapping(value = "back_panel", method = RequestMethod.GET)
    public String rackBackPanel() {
        JSONArray fangroupsJson = new JSONArray();

        int timestamp = propertyRepository.findNewstTimstamp();

        for (int i = 1; i < 11; i++) {
            FanGroup fanGroup = new FanGroup();
            StringBuffer fanTargetName = new StringBuffer("system/cooling");
            fanTargetName.append(i).append("/");
            //获取风扇墙信息
            Target target = targetReposiroty.getByName(fanTargetName.toString());
            if (target == null) {
                continue;
            }
            List<Property> fanGProperties = propertyRepository.findAllByTimestampAndTargetid(timestamp, target.getId());
            fanGroup.composeGroup(fanGProperties);
            //获取风扇信息
            List<Relation> relationList = relationPRepository.findByParent(target.getId());
            relationList.forEach(relation -> {
                List<Property> fanProperties = propertyRepository.findAllByTimestampAndTargetid(timestamp, relation.getChild());
                fanGroup.conposeFan(fanProperties);
            });

            fangroupsJson.add(fanGroup);
        }


        return fangroupsJson.toJSONString();
    }


    @ApiOperation(value = "getPowerDetail", notes = "获取电源详细信息")
    @RequestMapping(value = "power_info", method = RequestMethod.GET)
    public String powerDetail() {
        StringBuffer serverTargetName = new StringBuffer("system/power1/");
        Target target = targetReposiroty.getByName(serverTargetName.toString());
        int timestamp = propertyRepository.findNewstTimstamp();
        Power power = new Power();
        //获取电源属性
        List<Property> properties = propertyRepository.findAllByTimestampAndTargetid(timestamp, target.getId());
        power.composeServer(properties);
        //获取各psu属性
        List<Relation> relations = relationPRepository.findByParent(target.getId());
        relations.forEach(relation -> {
            List<Property> propertyList = propertyRepository.findAllByTimestampAndTargetid(timestamp, relation.getChild());
            Target target1 = targetReposiroty.getById(relation.getChild());
            power.composePUSs(propertyList, target1.getName());
        });
        return JSONObject.toJSONString(power);
    }

    @ApiOperation(value = "getServerDetail", notes = "获取服务器基本信息")
    @RequestMapping(value = "server_detail", method = RequestMethod.GET)
    public String serverDetail(@RequestParam("id") String servername) {
        StringBuffer serverTargetName = new StringBuffer("system/chassis1/");
        serverTargetName.append(servername).append("/");
        Target target = targetReposiroty.getByName(serverTargetName.toString());
        int timestamp = propertyRepository.findNewstTimstamp();
        List<Property> properties = propertyRepository.findAllByTimestampAndTargetid(timestamp, target.getId());
        Server server = new Server();
        server.compose(properties);
        return JSONObject.toJSONString(server);
    }

    @ApiOperation(value = "getPowerEnergyComsuption", notes = "获取电源的能耗情况")
    @RequestMapping(value = "comsumption", method = RequestMethod.GET)
    public String powerEnergyComsuption() {
        JSONObject jsonObject = new JSONObject();
        int timestamp = propertyRepository.findNewstTimstamp();

        List<Integer> powerTargetIds = targetReposiroty.findPsuTargetIds();
        int psuPower = 0;
        for (Integer psuId : powerTargetIds) {
            List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

            for (Property property : powerPs) {
                psuPower += Integer.parseInt(property.getValue());
            }
        }
        jsonObject.put("psu", psuPower);

        List<Integer> fanTargetIds = targetReposiroty.findFanTargetIds();
        int fanPower = 0;
        for (Integer psuId : fanTargetIds) {
            List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

            for (Property property : powerPs) {
                fanPower += Integer.parseInt(property.getValue());
            }
        }
        jsonObject.put("fan", fanPower);

        List<Integer> serverTargetIds = targetReposiroty.findServerTargetIds();
        int serverPower = 0;
        for (Integer psuId : serverTargetIds) {
            List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

            for (Property property : powerPs) {
                serverPower += Integer.parseInt(property.getValue());
            }
        }
        jsonObject.put("server", serverPower);

        jsonObject.put("total", psuPower + fanPower + serverPower);
        return jsonObject.toJSONString();
    }
}

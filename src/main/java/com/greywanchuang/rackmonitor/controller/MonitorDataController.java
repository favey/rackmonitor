package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.domain.*;
import com.greywanchuang.rackmonitor.entity.Property;
import com.greywanchuang.rackmonitor.entity.Relation;
import com.greywanchuang.rackmonitor.entity.Target;
import com.greywanchuang.rackmonitor.repository.PropertyRepository;
import com.greywanchuang.rackmonitor.repository.RelationRepository;
import com.greywanchuang.rackmonitor.repository.TargetReposiroty;
import com.greywanchuang.rackmonitor.util.Utils;
import io.swagger.annotations.Api;
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
        int timestamp = propertyRepository.findNewstTimstamp(1).get(0);
        List<Relation> relations = relationPRepository.findByParent(-1);
        Property property = propertyRepository.findByTargetidAndNameAndTimestamp(relations.get(0).getChild(), "PartNumber", timestamp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", property.getValue());
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "getRackDetail", notes = "获取机柜信息")
    @RequestMapping(value = "rack_detail", method = RequestMethod.GET)
    public String rackDetail() {
        List<Integer> timestamp = propertyRepository.findNewstTimstamp(1);
        List<Property> properties = propertyRepository.findAllByTimestampAndTargetid(timestamp.get(0), relationPRepository.findByParent(-1).get(0).getChild());
        Rack rack = new Rack();
        rack.compose(properties, rack);
        return JSONObject.toJSONString(rack);
    }

    @ApiOperation(value = "getRackFrontPanel", notes = "获取机柜前面板信息")
    @RequestMapping(value = "front_panel", method = RequestMethod.GET)
    public String rackFrontPanel() {
        int timestamp = propertyRepository.findNewstTimstamp(1).get(0);
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

    @ApiOperation(value = "getPowerComsumption", notes = "获取过去二十分钟的电源能耗数据")
    @RequestMapping(value = "power_comsumption", method = RequestMethod.GET)
    public String getTwentyMinPower() {
        JSONArray powerJsons = new JSONArray();
        List<Integer> timestamps = propertyRepository.findNewstTimstamp(11);
        Target target = targetReposiroty.getByName("system/power1/");
        timestamps.forEach(timstamp -> {
            List<Property> propertyList = propertyRepository.findAllByTimestampAndTargetid(timstamp, target.getId());
            PowerComsumption powerComsumption = new PowerComsumption();
            powerComsumption.compose(propertyList);
            powerComsumption.setTimestamp(Utils.dateFommat(timstamp));
            powerJsons.add(powerComsumption);
        });

        return powerJsons.toJSONString();
    }

    @ApiOperation(value = "getRackBackPanel", notes = "获取机柜背面板信息")
    @RequestMapping(value = "back_panel", method = RequestMethod.GET)
    public String rackBackPanel() {
        JSONArray fangroupsJson = new JSONArray();

        List<Integer> timestamp = propertyRepository.findNewstTimstamp(1);

        for (int i = 1; i < 11; i++) {
            FanGroup fanGroup = new FanGroup();
            StringBuffer fanTargetName = new StringBuffer("system/cooling");
            fanTargetName.append(i).append("/");
            //获取风扇墙信息
            Target target = targetReposiroty.getByName(fanTargetName.toString());
            if (target == null) {
                continue;
            }
            List<Property> fanGProperties = propertyRepository.findAllByTimestampAndTargetid(timestamp.get(0), target.getId());
            fanGroup.composeGroup(fanGProperties);
            //获取风扇信息
            List<Relation> relationList = relationPRepository.findByParent(target.getId());
            relationList.forEach(relation -> {
                List<Property> fanProperties = propertyRepository.findAllByTimestampAndTargetid(timestamp.get(0), relation.getChild());
                fanGroup.conposeFan(fanProperties);
            });

            fangroupsJson.add(fanGroup);
        }


        return fangroupsJson.toJSONString();
    }


    @ApiOperation(value = "getPowerDetail", notes = "获取电源详细信息")
    @RequestMapping(value = "power_detail", method = RequestMethod.GET)
    public String powerDetail() {
        StringBuffer serverTargetName = new StringBuffer("system/power1/");
        Target target = targetReposiroty.getByName(serverTargetName.toString());
        int timestamp = propertyRepository.findNewstTimstamp(1).get(0);
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
        int timestamp = propertyRepository.findNewstTimstamp(1).get(0);
        List<Property> properties = propertyRepository.findAllByTimestampAndTargetid(timestamp, target.getId());
        Server server = new Server();
        server.compose(properties);
        return JSONObject.toJSONString(server);
    }

    @ApiOperation(value = "getPowerEnergyComsuption", notes = "获取电源的能耗情况")
    @RequestMapping(value = "comsumption", method = RequestMethod.GET)
    public String powerEnergyComsuption() {
        JSONObject jsonObject = new JSONObject();
        int timestamp = propertyRepository.findNewstTimstamp(1).get(0);

        List<Integer> powerTargetIds = targetReposiroty.findPsuTargetIds();
        int psuPower = 0;
        for (Integer psuId : powerTargetIds) {
            List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

            for (Property property : powerPs) {
                psuPower += Integer.parseInt(property.getValue());
            }
        }
        jsonObject.put("psus", psuPower);

        List<Integer> fanTargetIds = targetReposiroty.findFanTargetIds();
        int fanPower = 0;
        for (Integer psuId : fanTargetIds) {
            List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

            for (Property property : powerPs) {
                fanPower += Integer.parseInt(property.getValue());
            }
        }
        jsonObject.put("fans", fanPower);

        List<Integer> serverTargetIds = targetReposiroty.findServerTargetIds();
        int serverPower = 0;
        for (Integer psuId : serverTargetIds) {
            List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

            for (Property property : powerPs) {
                serverPower += Integer.parseInt(property.getValue());
            }
        }
        jsonObject.put("servers", serverPower);

        jsonObject.put("total", psuPower + fanPower + serverPower);
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "getPowerEnergyComsuptions", notes = "获取过去三十分钟的能耗情况数据，共16组")
    @RequestMapping(value = "comsumptions", method = RequestMethod.GET)
    public String thirtyMinsComsuptions() {
        JSONArray comsumptionJsons = new JSONArray();
        List<Integer> timestamps = propertyRepository.findNewstTimstamp(16);
        timestamps.forEach(timestamp -> {
            JSONObject jsonObject = new JSONObject();

            List<Integer> powerTargetIds = targetReposiroty.findPsuTargetIds();
            int psuPower = 0;
            for (Integer psuId : powerTargetIds) {
                List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

                for (Property property : powerPs) {
                    psuPower += Integer.parseInt(property.getValue());
                }
            }
            jsonObject.put("psus", psuPower);

            List<Integer> fanTargetIds = targetReposiroty.findFanTargetIds();
            int fanPower = 0;
            for (Integer psuId : fanTargetIds) {
                List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

                for (Property property : powerPs) {
                    fanPower += Integer.parseInt(property.getValue());
                }
            }
            jsonObject.put("fans", fanPower);

            List<Integer> serverTargetIds = targetReposiroty.findServerTargetIds();
            int serverPower = 0;
            for (Integer psuId : serverTargetIds) {
                List<Property> powerPs = propertyRepository.findAllByNameAndTimestampAndTargetid("Power", timestamp, psuId);

                for (Property property : powerPs) {
                    serverPower += Integer.parseInt(property.getValue());
                }
            }
            jsonObject.put("servers", serverPower);
            jsonObject.put("timestamp", Utils.dateFommat(timestamp));

            comsumptionJsons.add(jsonObject);
        });

        return comsumptionJsons.toJSONString();
    }

    @ApiOperation(value = "/server_temps", notes = "获取服务器过去三十分钟的内外温度数据，共十六组")
    @RequestMapping(value = "server_temps", method = RequestMethod.GET)
    public String serverTemps(@RequestParam("id") String serverName) {
        JSONArray tempsJson = new JSONArray();
        List<Integer> timestamps = propertyRepository.findNewstTimstamp(16);
        StringBuffer serverTargetName = new StringBuffer("system/chassis1/");
        serverTargetName.append(serverName).append("/");
        Target target = targetReposiroty.getByName(serverTargetName.toString());
        timestamps.forEach(timestamp -> {
            JSONObject json = new JSONObject();
            List<Property> tempsList = propertyRepository.findPowerTemps(timestamp, target.getId());
            tempsList.forEach(property -> {
                if ("ExTemp".equals(property.getName())) {
                    json.put("extemp", property.getValue());
                } else {
                    json.put("entemp", property.getValue());
                }
            });
            json.put("timestamp", Utils.dateFommat(timestamp));
            tempsJson.add(json);
        });

        return tempsJson.toJSONString();
    }

    @ApiOperation(value = "/server_comsumption", notes = "获取服务器过去三十分钟的能耗数据，共十六组")
    @RequestMapping(value = "server_comsumption", method = RequestMethod.GET)
    public String serverComsumption(@RequestParam("id") String serverName) {
        JSONArray tempsJson = new JSONArray();
        List<Integer> timestamps = propertyRepository.findNewstTimstamp(16);
        StringBuffer serverTargetName = new StringBuffer("system/chassis1/");
        serverTargetName.append(serverName).append("/");
        Target target = targetReposiroty.getByName(serverTargetName.toString());
        timestamps.forEach(timestamp -> {
            JSONObject json = new JSONObject();
            System.out.println("targetid: " + target.getId() + "   timestamp: " + timestamp);
            Property property = propertyRepository.findByTargetidAndNameAndTimestamp(target.getId(), "Power", timestamp);
            if (property == null) {
                return;
            }
            json.put("power", property.getValue());
            json.put("timestamp", Utils.dateFommat(timestamp));
            tempsJson.add(json);
        });

        return tempsJson.toJSONString();
    }

    @RequestMapping(value = "help", method = RequestMethod.GET)
    public String help() {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }
}

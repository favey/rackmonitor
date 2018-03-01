package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.Cabinet;
import com.greywanchuang.rackmonitor.entity.Server;
import com.greywanchuang.rackmonitor.repository.CabinetRepository;
import com.greywanchuang.rackmonitor.repository.ServerRepository;
import com.greywanchuang.rackmonitor.util.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/v1/server")
public class ServerController {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private CabinetRepository cabinetRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取服务器信息", notes = "获取指定服务器信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String serverInfo(@PathVariable("id") int id, HttpServletResponse rsp) {
        Server server = serverRepository.findById(id);
        if (server == null) {
            rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return Utils.error(  "Server Not Exist");
        }

        return composeServerInfo(server).toJSONString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取服务器列表信息", notes = "获取指定机柜下所有服务器信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getServerList(@RequestBody Map<String, Object> reqMap, HttpServletResponse rsp) {
        Object cidObj = reqMap.get("cid");
        if (cidObj == null) {
            rsp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Utils.error("No Cabinet ID");
        } else {
            List<Server> servers = serverRepository.getAllByCabinetIdAndStatus((Integer) cidObj, 0);
            JSONArray jsonArray = new JSONArray();
            servers.forEach(server -> {
                jsonArray.add(composeServerInfo(server));
            });
            return jsonArray.toJSONString();
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "添加服务器", notes = "添加服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String createServerInfo(@RequestBody Map<String, Object> reqMap, HttpServletResponse rsp) {
        Object cid = reqMap.get("cid");
        if (cid == null) {
            rsp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Utils.error("No cabinet ID param!");
        }
        Cabinet cabinet = cabinetRepository.findById((Integer) cid);
        if (cabinet == null) {
            rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return Utils.error(  "Cabinet Not Exist");
        }

        try {
            Server server = new Server();
            server.setLabel(reqMap.get("label").toString());
            server.setCabinetId(cabinet.getId());
            server.setDescription(reqMap.get("description").toString());
            server.setHeight((Integer) reqMap.get("height"));
            server.setWeight((Double) reqMap.get("weight"));
            server.setHostname(reqMap.get("hostname").toString());
            server.setIp(reqMap.get("ip").toString());
            server.setIpmi(reqMap.get("ipmi").toString());
            server.setPosition((Integer) reqMap.get("position"));
            server.setSerialNo(reqMap.get("serialNo").toString());
            server.setUserName(reqMap.get("userName").toString());
            server.setPassword(reqMap.get("password").toString());

            serverRepository.save(server);
        } catch (Exception ex) {
            ex.printStackTrace();
            rsp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return Utils.error("Intenal error");
        }
        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "移除服务器", notes = "移除服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeServerInfo(@PathVariable int id, HttpServletResponse rsp) {
        Server server = serverRepository.findById(id);
        if (server == null) {
            rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return Utils.error(  "Server Not Exist");
        }
        else {
            server.setStatus(1);
            serverRepository.save(server);
            return Utils.success();
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑服务器信息", notes = "编辑服务器信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String editServerInfo(@PathVariable int id, @RequestBody Map<String, Object> reqMap, HttpServletResponse rsp) {
        Server server = serverRepository.findById(id);
        if (server == null) {
            rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return Utils.error(  "Server Not Exist");
        }
        Object cid = reqMap.get("cid");
        if (cid != null && (Integer) cid != server.getCabinetId()) {
            server.setCabinetId((Integer) cid);
        }

        server.setLabel(reqMap.get("label").toString());
        server.setDescription(reqMap.get("description").toString());
        server.setHeight((Integer) reqMap.get("height"));
        server.setWeight((Double) reqMap.get("weight"));
        server.setHostname(reqMap.get("hostname").toString());
        server.setIp(reqMap.get("ip").toString());
        server.setIpmi(reqMap.get("ipmi").toString());
        server.setPosition((Integer) reqMap.get("position"));
        server.setSerialNo(reqMap.get("serialNo").toString());
        server.setUserName(reqMap.get("userName").toString());
        server.setPassword(reqMap.get("password").toString());

        serverRepository.save(server);
        return Utils.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "重启服务器", notes = "重启服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/reboot/{id}", method = RequestMethod.POST)
    public String rebootServer(@PathVariable int id) {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "远程启动服务器", notes = "远程启动服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/start/{id}", method = RequestMethod.POST)
    public String startServer(@PathVariable int id) {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "存储率使用统计", notes = "存储率使用统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public String storageInfo() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "能耗统计信息", notes = "能耗统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/energe", method = RequestMethod.GET)
    public String energeConsumption() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "温度统计信息", notes = "温度统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/temp", method = RequestMethod.GET)
    public String tempStatistics() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "内存使用率统计信息", notes = "内存使用率统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/memory", method = RequestMethod.GET)
    public String memoryStatistics() {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "CPU使用率统计信息", notes = "CPU使用率统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/cpu", method = RequestMethod.GET)
    public String cpusStatistics() {
        return "";
    }

    /**
     * @param server
     * @return
     */
    private JSONObject composeServerInfo(Server server) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("label", server.getLabel());
        jsonObject.put("status", "N/A");
        jsonObject.put("description", server.getDescription());
        jsonObject.put("location", "China");
        jsonObject.put("client", "admin");
        jsonObject.put("model", "N/A");
        jsonObject.put("sn", server.getSerialNo());
        jsonObject.put("compute", "N/A");
        jsonObject.put("memory", "N/A");
        jsonObject.put("storage", "N/A");
        jsonObject.put("intTemp", "N/A");
        jsonObject.put("extTemp", "N/A");
        jsonObject.put("power", "N/A");

        JSONObject netJson = new JSONObject();
        netJson.put("ip", server.getIp());
        netJson.put("hostname", server.getHostname());
        netJson.put("mac", "N/A");
        netJson.put("mask", "255.255.255.0");
        netJson.put("gateway", "N/A");
        jsonObject.put("net", netJson);

        JSONObject bmcJson = new JSONObject();
        bmcJson.put("reset", "N/A");
        jsonObject.put("bmc", bmcJson);

        return jsonObject;
    }

    /**
     *  判断对象是否存在
     * @param rsp
     * @param obj
     */
//    private String isObjectExist(HttpServletResponse rsp,Object obj)
//    {
//        if (obj == null) {
//            rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return Utils.error(  "Request Object Not Exist");
//        }
//        return "";
//    }

}

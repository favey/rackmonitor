package com.greywanchuang.rackmonitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.entity.Server;
import com.greywanchuang.rackmonitor.repository.ServerRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/v1/server")
public class ServerController {

    @Autowired
    private ServerRepository serverRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取服务器信息", notes = "获取服务器信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String serverInfo(@PathVariable int id)
    {
        Server server=serverRepository.findById(id);
        if(server!=null) {
            return JSONObject.toJSONString(server);
        }
        else
        {
            return "";
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "添加服务器", notes = "添加服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String createServerInfo(@RequestBody Map<String,Object> reqMap)
    {

        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "移除服务器", notes = "移除服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeServerInfo(@RequestParam int id)
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑服务器信息", notes = "编辑服务器信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String editServerInfo(@RequestParam int id)
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "重启服务器", notes = "重启服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/reboot", method = RequestMethod.POST)
    public String rebootServer(@RequestParam int id)
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "远程启动服务器", notes = "远程启动服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String startServer(@RequestParam int id)
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "存储率使用统计", notes = "存储率使用统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public String storageInfo()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "能耗统计信息", notes = "能耗统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/energe", method = RequestMethod.GET)
    public String energeConsumption()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "温度统计信息", notes = "温度统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/temp", method = RequestMethod.GET)
    public String tempStatistics()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "内存使用率统计信息", notes = "内存使用率统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/memory", method = RequestMethod.GET)
    public String memoryStatistics()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "CPU使用率统计信息", notes = "CPU使用率统计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/cpu", method = RequestMethod.GET)
    public String cpusStatistics()
    {
        return "";
    }

}

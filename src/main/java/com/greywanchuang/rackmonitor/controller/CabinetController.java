package com.greywanchuang.rackmonitor.controller;

import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/cabinet")
public class CabinetController {

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜信息", notes = "获取机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getCainet()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取机柜组信息", notes = "获取机柜组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String getCainetGroupList()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜组", notes = "增加机柜组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public String createCainetGroupList()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "移除机柜组", notes = "移除机柜组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/group", method = RequestMethod.DELETE)
    public String removeCainetGroupList()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜信息", notes = "增加机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public String createCainet()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "编辑机柜信息", notes = "编辑机柜信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String editCainet()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "获取所有机柜类型", notes = "获取所有机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public String getCainets()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "增加机柜类型", notes = "增加机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public String createCainetType()
    {
        return "";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value = "删除机柜类型", notes = "删除机柜类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header"),
    })
    @Authorization
    @RequestMapping(value = "/type", method = RequestMethod.DELETE)
    public String deleteCainetType(@RequestParam("id")int ctId)
    {
        return "";
    }



}

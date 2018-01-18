package com.greywanchuang.rackmonitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login() {
        return "";
    }

    @RequestMapping(value = "/retreive", method = RequestMethod.POST)
    public String retreivePwd() {
        return "";
    }

    @RequestMapping(value = "/mail_sender", method = RequestMethod.POST)
    public String editSMTPServer() {
        return "";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String resetPwd() {
        return "";
    }
}

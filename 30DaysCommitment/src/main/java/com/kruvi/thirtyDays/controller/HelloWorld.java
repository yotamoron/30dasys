package com.kruvi.thirtyDays.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yotam on 4/10/15.
 */
@Controller
public class HelloWorld {

    private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);

    public HelloWorld() {
        log.info("Started");
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, World!";
    }
}

package com.javadeveloperzone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = {"/","/RespondToConsultation"})
    public String index() {
        return "forward:index.html"; // can't get this to work
    }
}
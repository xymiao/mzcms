package com.xymiao.cms.web.controller;

import com.xymiao.cms.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roadmap")
public class RoadMapController extends BaseController {
    @RequestMapping("")
    public String index() {
        logger.info("进入路线图页面");
        return "default/roadmap";
    }
}

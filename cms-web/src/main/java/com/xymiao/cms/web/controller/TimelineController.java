package com.xymiao.cms.web.controller;

import com.xymiao.cms.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimelineController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(TimelineController.class);
    @RequestMapping("timeline.html")
    public String index(Model model) {
        logger.info("进入时间线");

        return RETURN_THEME + "timeline";
    }

}

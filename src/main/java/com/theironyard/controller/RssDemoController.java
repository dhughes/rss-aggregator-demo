package com.theironyard.controller;

import com.theironyard.service.RssDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RssDemoController {

    @Autowired
    RssDemoService rssDemoService;

    @RequestMapping(path = "/")
    public String home(Model model, @PageableDefault(size = 25, sort = "publishedDate") Pageable pageable){

        rssDemoService.loadFeeds();

        model.addAttribute("entries", rssDemoService.getPageOfEntries(pageable));

        return "home";
    }
}

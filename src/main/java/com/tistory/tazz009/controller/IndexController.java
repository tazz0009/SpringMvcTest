package com.tistory.tazz009.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value="/index.htm", method=RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("theModelKey", "Spring says HI!");
        return "index";
    }
	
}

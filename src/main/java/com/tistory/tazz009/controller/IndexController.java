package com.tistory.tazz009.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = "/index.htm")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("/WEB-INF/views/index.jsp");
        mav.addObject("theModelKey", "Spring says HI!");
        return mav;
    }
	
}

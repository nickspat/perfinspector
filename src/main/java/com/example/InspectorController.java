package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InspectorController {
	
	@RequestMapping("/home")
    public String greeting() {
        
        return "index";
    }
	
	@RequestMapping("/responsegauge")
	public String responseSpeed(){
		return "responsegauge";
	}
}

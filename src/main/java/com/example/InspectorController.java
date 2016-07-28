package com.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InspectorController {
	
	@Autowired
	HttpUtils httpUtilsObj;
	
	@RequestMapping("/home")
    public String greeting() {
        
        return "index";
    }
	
	@RequestMapping("/responsegauge")
	public String responseSpeedHome(HttpServletRequest request, Model model){
		
		String stringurl = request.getParameter("urltodiagnose");
		if (stringurl == null || stringurl == ""){
			model.addAttribute("urltodiagnose", stringurl);
			model.addAttribute("responsetime","");
			model.addAttribute("responseHeaderMap",null);
		}
		else{
			model.addAttribute("urltodiagnose", stringurl);
			model.addAttribute("responsetime", httpUtilsObj.getResponseTime(stringurl) );
			model.addAttribute("responseHeaderMap", httpUtilsObj.getHttpResponse(stringurl));
		}	
		return "responsegauge";
	}
	
	/*
	//value ="/responsegauge/measure", method = RequestMethod.GET, produces = "application/json"
	@RequestMapping("/responsegauge/measure")
	public @ResponseBody String returnPerformanceData(HttpServletRequest request){
		Object stringurl= request.getAttribute("urltodiagnose");
		if (stringurl == null || stringurl == "")
			return null;
		return httpUtilsObj.getResponseTime(stringurl.toString());
		
	}
	*/
}

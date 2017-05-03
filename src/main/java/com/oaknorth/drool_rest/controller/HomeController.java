package com.oaknorth.drool_rest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oaknorth.drool_rest.models.Employee;
import com.oaknorth.drool_rest.services.RulesService;

@Controller
public class HomeController {
	
	@Autowired
	RulesService ruleService;

	@RequestMapping(value="/home")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		Employee e1 = new Employee();
    	e1.setFirstName("Test");
    	e1.setLastName("Test");
    	e1.setAge(30);
    	e1.setExperience(3.3);
    	e1.setId(1);
    	
    	System.out.println("Employee before Rules fired ! " + e1);
    	
    	Employee e2 = ruleService.applyRules(e1);
    	
    	System.out.println("Employee after Rules fired ! " + e2);
		return new ModelAndView("home");
	}
}

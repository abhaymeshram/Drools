package com.oaknorth.drool_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oaknorth.drool_rest.models.Employee;
import com.oaknorth.drool_rest.services.RulesService;

@RestController
public class RulesRestController {

	@Autowired
	RulesService ruleService;
	
	@RequestMapping(value = "/applyRules", method = RequestMethod.POST)
	public Employee applyRules(@RequestBody Employee employee){
		if(null != employee)
			return ruleService.applyRules(employee);
		return employee;
		
	}
}

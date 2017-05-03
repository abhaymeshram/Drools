package com.oaknorth.drool_rest.services;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;

import com.oaknorth.drool_rest.models.Employee;
import com.oaknorth.drool_rest.utils.CommonUtils;

@Service
public class RulesService {
	
	private KieSession kSession = null;
	
	public Employee applyRules(Employee employee){
		
		if(kSession == null)
			kSession = CommonUtils.createKession();
		
    	if(null != employee ){
    		System.out.println("####### Rules Fired #######");
    		FactHandle factHandle = kSession.insert(employee);
            kSession.fireAllRules();
            kSession.delete(factHandle);
    	}
		return employee;
	}
	
}

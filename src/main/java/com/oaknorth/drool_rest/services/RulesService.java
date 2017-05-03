package com.oaknorth.drool_rest.services;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.oaknorth.drool_rest.models.Employee;

@Service
public class RulesService {
	
	public Employee applyRules(Employee employee){
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	if(null != employee ){
    		System.out.println("####### Rules Fired #######");
    		kSession.insert(employee);
            kSession.fireAllRules();
            kSession.dispose();
    	}
		return employee;
	}
	
}

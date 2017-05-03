package com.oaknorth.drool_rest.utils;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class CommonUtils {
	
	public static KieSession createKession(){
		System.out.println("Kie Model created!");
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");    	
    	return kSession;
	}

}

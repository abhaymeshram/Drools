package com.cis.drool_rest.services;

import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.cis.drool_rest.models.FeeDataModel;
import com.cis.drool_rest.utils.CommonUtils;

@Service
public class RulesService {
	
	
	public FeeDataModel applyRules(FeeDataModel feeDataModel) throws Exception{
		System.out.println("Input DataModel: " + feeDataModel);
		KieSession kSession = CommonUtils.createKession();
		
		if(kSession != null){
			if(null != feeDataModel ){
	    		System.out.println("####### Rules Fired #######");
	    		kSession.insert(feeDataModel);
	            kSession.fireAllRules();
	            kSession.dispose();
	            System.out.println("Output DataModel: " + feeDataModel);
	    	}
	    	
		}
		return feeDataModel;
	}
	
	public String update(){
		try {
			CommonUtils.initialize();
			return "Rules updated Successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception while updating rules. Exception: " + e.toString();
		}
	}
	
}

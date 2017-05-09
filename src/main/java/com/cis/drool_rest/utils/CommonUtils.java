package com.cis.drool_rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class CommonUtils {

	private static KieContainer kieContainer = null;

		public static KieSession createKession() throws Exception {
			System.out.println("Creating KieSession!");
			/*
			 * KieServices ks = KieServices.Factory.get(); KieContainer kContainer =
			 * ks.getKieClasspathContainer(); KieSession kSession =
			 * kContainer.newKieSession("ksession-rules"); return kSession;
			 */
			if(null == kieContainer){
				initialize();
			}
			return kieContainer.newKieSession();
		}

		public static void initialize() throws Exception {
			KieServices kieServices = KieServices.Factory.get();
			KieFileSystem kfs = kieServices.newKieFileSystem();
			try {
				Files.list(Paths.get("C:/Users/asus/git/Drools/src/main/resources/rules/")).forEach(f -> {
				FileInputStream fis;
					try {
						fis = new FileInputStream(f.toFile());
						kfs.write("src/main/resources/" + f.getFileName(),
								kieServices.getResources().newInputStreamResource(fis));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
	
			KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
			Results results = kieBuilder.getResults();
			if (results.hasMessages(Message.Level.ERROR)) {
				System.out.println(results.getMessages());
				throw new IllegalStateException(results.getMessages().toString());
			}
			kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
			System.out.println("Kie Container Created!");
		}
}

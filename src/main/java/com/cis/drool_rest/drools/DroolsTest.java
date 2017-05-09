package com.cis.drool_rest.drools;

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

import com.cis.drool_rest.models.Employee;
import com.cis.drool_rest.utils.TrackingAgendaEventListener;

/*
 * put sample.drl file in any location and put that location name against drlFileLocation
 */

public class DroolsTest {

	private static KieContainer kieContainer = null;
	private static String drlFileLocation = "D:/rules";

	public static KieSession getKieSession() throws IOException {
		if (kieContainer == null) {
			KieServices kieServices = KieServices.Factory.get();
			KieFileSystem kfs = kieServices.newKieFileSystem();
			Files.list(Paths.get(drlFileLocation)).forEach(
					f -> {
						FileInputStream fis;
						try {
							fis = new FileInputStream(f.toFile());
							kfs.write("src/main/resources/" + f.getFileName(),
									kieServices.getResources()
											.newInputStreamResource(fis));
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
			KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
			Results results = kieBuilder.getResults();
			if (results.hasMessages(Message.Level.ERROR)) {
				System.out.println(results.getMessages());
				throw new IllegalStateException("### errors ###");
			}
			kieContainer = kieServices.newKieContainer(kieServices
					.getRepository().getDefaultReleaseId());
		}
		return kieContainer.newKieSession();
	}

	public static final void main(String[] args) {
		try {
			TrackingAgendaEventListener ad = new TrackingAgendaEventListener();
			for (int i = 0; i < 5; i++) {
				KieSession kieSession = getKieSession();
				kieSession.addEventListener(ad);
				System.out.println(kieSession);
				Employee e1 = new Employee();
				e1.setFirstName("Abhay");
				e1.setAge(i + 5);
				e1.setActive(true);
				e1.setSampleDate("2007-12-03");
				System.out.println(e1);
				kieSession.insert(e1);
				kieSession.getAgenda().getAgendaGroup("ag1").setFocus();
				// kieSession.getAgenda().getActivationGroup("A1");
				kieSession.fireAllRules();
				System.out.println(e1);
			}
			System.out.println(ad.matchsToString());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}

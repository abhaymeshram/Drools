# Drools

Apply Rules Rest Service

URL : http://localhost:8080/drool-rest/applyRules
Method : POST
Content-Type: application/json

1) Body for experience less than 3 ## please check the value of experience, designation and salary ##

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": null,
  "salary": 0,
  "experience": 2.9
}

Rules Response : 

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": "Software Engineer",
  "salary": 30000,
  "experience": 2.9
}

2) Body for experience greater than 3 ## please check the value of experience, designation and salary ##

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": null,
  "salary": 0,
  "experience": 3.1
}

Rules Response : 

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": "Senior Software Engineer",
  "salary": 50000,
  "experience": 3.1
}


DataBase config for rules:

Template:
template header
name
experience
salary
templateid
name
experience
salary
templateid
groupName
enabled
order
ruleTemplateId
ruleName

template "tow"

rule "@{ruleName}"
salience @{order}
enabled @{enabled}
ruleflow-group "@{groupName}"
activation-group "@{groupName}"
when
	cc: com.cis.cibbpm.rules.model.Customer(experience < @{experience}, salary > @{salary}, name == "@{name}")
then
	System.out.println("group.... @{row.rowNumber}");
	cc.setTemplateid("@{templateid}");
end

end template

Sample java code to build rules engine

package com.cis.cibbpm.rules.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cis.cibbpm.rules.model.Customer;
import com.cis.cibbpm.rules.model.QIBGroupInfo;

public class CommonUtil implements Constants {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static KieContainer kieContainer = null;
	public static final String drlFile = "customer.drl";

	public static KieSession createKession() throws Exception {
		return kieContainer.newKieSession();
	}

	public static void initialize(List<Customer> list, String template) throws Exception {

		// Collection<QIBGroupInfo> qibGroupInfos = extractRulesDataFromDB();
		// extractResultSetsFromDB();

		// Collection<QIBGroupInfo> qibGroupInfos = extractRulesDataFromObjects();

		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		 ObjectDataCompiler converter = new ObjectDataCompiler();
//		ResultSetGenerator converter = new ResultSetGenerator();
		InputStream fis = CommonUtil.class.getClassLoader().getResourceAsStream(drlFile);

		String drl2 = converter.compile(list, template);
		logger.info(drl2);
		try {
			kfs.write("src/main/resources/simple.drl",
					kieServices.getResources().newReaderResource(new StringReader(drl2)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
		Results results = kieBuilder.getResults();
		if (results.hasMessages(Message.Level.ERROR)) {
			logger.error(results.getMessages().toString());
			throw new IllegalStateException(results.getMessages().toString());
		}
		kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		logger.info("Kie Container Created!");
	}
	
	
	public static Customer applyRules(Customer payloadModel) throws Exception {
		logger.info("Input DataModel: " + payloadModel);
		KieSession kSession = CommonUtil.createKession();

		if (kSession != null) {
			if (null != payloadModel) {
				logger.info("####### Rules Fired #######");
				kSession.insert(payloadModel);
				kSession.getAgenda().getAgendaGroup("TemplateLoad").setFocus();
				kSession.fireAllRules();
				kSession.dispose();
				logger.info("Result output : " + payloadModel);
			}

		}
		return payloadModel;
	}
	
	
	public static void main(String[] args) throws Exception {
		List<Customer> list = new ArrayList<Customer>();
		Customer cs = new Customer("Abhay", 3.2, 20000.0, "T01", "TemplateLoad", true, 1, "Salary less than 20000", "");
		Customer cs1 = new Customer("Abhay", 3.2, 20000.0, "T02", "TemplateLoad", true, 12, "Salary less than 200001", "");

		list.add(cs);
		list.add(cs1);
		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		 ObjectDataCompiler converter = new ObjectDataCompiler();
//		ResultSetGenerator converter = new ResultSetGenerator();
		InputStream fis = CommonUtil.class.getClassLoader().getResourceAsStream("customer.drl");
		//String drl2 = converter.compile(list, fis);
		
		String template = "template header\n" + 
				"name\n" + 
				"experience\n" + 
				"salary\n" + 
				"templateid\n" + 
				"name\n" + 
				"experience\n" + 
				"salary\n" + 
				"templateid\n" + 
				"groupName\n" + 
				"enabled\n" + 
				"order\n" + 
				"ruleTemplateId\n" + 
				"ruleName\n" + 
				"\n" + 
				"package org.drools.examples.templates\n" + 
				"import com.cis.cibbpm.rules.model.Customer\n" + 
				"\n" + 
				"template \"one\"\n" + 
				"\n" + 
				"rule \"@{ruleName}\"\n" + 
				"salience @{order}\n" + 
				"enabled @{enabled}\n" + 
				"ruleflow-group \"@{groupName}\"\n" + 
				"activation-group \"@{groupName}\"\n" + 
				"when\n" + 
				"	cc: Customer(experience < @{experience}, salary > @{salary}, name == \"@{name}\")\n" + 
				"then\n" + 
				"	System.out.println(\"group.... @{row.rowNumber}\");\n" + 
				"	cc.setTemplateid(\"@{templateid}\");\n" + 
				"end\n" + 
				"\n" + 
				"end template";
		InputStream in = new ByteArrayInputStream(template.getBytes("UTF-8"));
		String drl2 = converter.compile(list, in);
		System.out.println(drl2);
		//initialize(list,template);
		
		/*
		 * System.out.println(drl2); Customer payload = new Customer();
		 * payload.setSalary(200000); payload.setExperience(3.0);
		 * payload.setName("Abhay"); System.out.println("Output is:" +
		 * CommonUtil.applyRules(payload));
		 */
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static Collection<QIBGroupInfo> extractRulesDataFromObjects() {
		Collection<QIBGroupInfo> qibGroupInfos = new ArrayList<>();
		qibGroupInfos.add(new QIBGroupInfo(1, "g1", 11, false, true, "eid1", 1, 1, 10, 500));
		qibGroupInfos.add(new QIBGroupInfo(2, "g2", 11, true, true, "eid2", 1, 1, 10, 500));
		qibGroupInfos.add(new QIBGroupInfo(3, "g3", 11, true, true, "eid1", 1, 1, 400, 5000));
		qibGroupInfos.add(new QIBGroupInfo(1, "g1", 12, true, true, "eid1", 1, 1, 10, 500));
		return qibGroupInfos;
	}

	private static Collection<QIBGroupInfo> extractRulesDataFromDB() throws ClassNotFoundException, SQLException {
		String jdbcUrl = MYSQL_URL;
		Class.forName(JDBC_MYSQL_DRIVER);
		Connection conn = DriverManager.getConnection(jdbcUrl, MYSQL_USER, MYSQL_PASSWORD);

		PreparedStatement preparedStmt = conn.prepareStatement(
				"select groupId, name, rimNumber, verifierGroup, isActive, entitlementUuid, affrimationCount, sequence, minLimit, maxLimit from QIB_GROUP_INFO");

		ResultSet resultSet = preparedStmt.executeQuery();
		Collection<QIBGroupInfo> qibGroupInfos = new ArrayList<>();
		while (resultSet.next()) {
			qibGroupInfos.add(new QIBGroupInfo(resultSet.getLong("groupId"), resultSet.getString("name"),
					resultSet.getLong("rimNumber"), resultSet.getBoolean("verifierGroup"),
					resultSet.getBoolean("isActive"), resultSet.getString("entitlementUuid"),
					resultSet.getInt("affrimationCount"), resultSet.getInt("sequence"), resultSet.getLong("minLimit"),
					resultSet.getLong("maxLimit")));
		}
		return qibGroupInfos;
	}

	private static ResultSet extractResultSetsFromDB() throws SQLException, ClassNotFoundException {
		String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbcUrl, "root", "mysql");

		PreparedStatement preparedStmt = conn.prepareStatement(
				"select groupId, name, rimNumber, IF(verifierGroup, 'true', 'false') verifierGroup , IF(isActive, 'true', 'false') isActive , entitlementUuid, affrimationCount, sequence, minLimit, maxLimit from QIB_GROUP_INFO");

		return preparedStmt.executeQuery();
	}
}

package com.cis.cibbpm.rules.model;

import java.io.Serializable;

public class Customer implements Serializable{

	private String name;
	private double experience;
	private double salary;
	private String templateid;
	private String groupName;
	private boolean enabled;
	private int order;
	private String ruleName;
	private String ruleTemplateId;
	
	public String getRuleTemplateId() {
		return ruleTemplateId;
	}

	public void setRuleTemplateId(String ruleTemplateId) {
		this.ruleTemplateId = ruleTemplateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", experience=" + experience + ", salary=" + salary + ", templateid="
				+ templateid + ", groupName=" + groupName + ", enabled=" + enabled + ", order=" + order + ", ruleName="
				+ ruleName + ", ruleTemplateId=" + ruleTemplateId + "]";
	}

	public Customer(String name, double experience, double salary, String templateid, String groupName, boolean enabled,
			int order, String ruleName, String ruleTemplateId) {
		super();
		this.name = name;
		this.experience = experience;
		this.salary = salary;
		this.templateid = templateid;
		this.groupName = groupName;
		this.enabled = enabled;
		this.order = order;
		this.ruleName = ruleName;
		this.ruleTemplateId = ruleTemplateId;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}


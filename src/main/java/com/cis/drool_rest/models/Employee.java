package com.cis.drool_rest.models;

import java.time.LocalDate;

public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String designation;
	private double salary;
	private double experience;
	private boolean active;
	private String sampleDate;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(String sampleDate) {
		this.sampleDate = sampleDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	
	public boolean isDateBefore(){
		System.out.println("In Date checker");
		LocalDate today = LocalDate.now();
		LocalDate conditionSatisfiedDate  = LocalDate.parse(this.sampleDate);
		if(conditionSatisfiedDate.isBefore(today)){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", age=" + age + ", designation="
				+ designation + ", salary=" + salary + ", experience="
				+ experience + ", active=" + active + ", sampleDate="
				+ sampleDate + "]";
	}

}

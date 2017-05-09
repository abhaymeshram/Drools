package com.cis.drool_rest.models;

import java.util.Date;

public class FeeDataModel {
	
	private int feeType;
	private int parentID;
	private String active;
	private String conditional;
	private boolean isConditionSatisfied;
	private Date conditionSatisfiedDate;
	private String conditionDesc;
	private int feeStatus;
	private Date lastAppDate;
	private Date nextAppDate;
	private int feeFreq;
	private int feeStructType;
	private int paidBy;
	private int payableTo;
	private boolean isPassThrough;
	private int ractiveFee;
	private int rConditionalFee;
	private int rFeeCanBeApplied;
	
	
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public boolean isConditionSatisfied() {
		return isConditionSatisfied;
	}
	public void setConditionSatisfied(boolean isConditionSatisfied) {
		this.isConditionSatisfied = isConditionSatisfied;
	}
	public Date getConditionSatisfiedDate() {
		return conditionSatisfiedDate;
	}
	public void setConditionSatisfiedDate(Date conditionSatisfiedDate) {
		this.conditionSatisfiedDate = conditionSatisfiedDate;
	}
	public String getConditionDesc() {
		return conditionDesc;
	}
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}
	public int getFeeStatus() {
		return feeStatus;
	}
	public void setFeeStatus(int feeStatus) {
		this.feeStatus = feeStatus;
	}
	public Date getLastAppDate() {
		return lastAppDate;
	}
	public void setLastAppDate(Date lastAppDate) {
		this.lastAppDate = lastAppDate;
	}
	public Date getNextAppDate() {
		return nextAppDate;
	}
	public void setNextAppDate(Date nextAppDate) {
		this.nextAppDate = nextAppDate;
	}
	public int getFeeFreq() {
		return feeFreq;
	}
	public void setFeeFreq(int feeFreq) {
		this.feeFreq = feeFreq;
	}
	public int getFeeStructType() {
		return feeStructType;
	}
	public void setFeeStructType(int feeStructType) {
		this.feeStructType = feeStructType;
	}
	public int getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(int paidBy) {
		this.paidBy = paidBy;
	}
	public int getPayableTo() {
		return payableTo;
	}
	public void setPayableTo(int payableTo) {
		this.payableTo = payableTo;
	}
	public boolean isPassThrough() {
		return isPassThrough;
	}
	public void setPassThrough(boolean isPassThrough) {
		this.isPassThrough = isPassThrough;
	}
	public int getRactiveFee() {
		return ractiveFee;
	}
	public void setRactiveFee(int ractiveFee) {
		this.ractiveFee = ractiveFee;
	}
	public int getrConditionalFee() {
		return rConditionalFee;
	}
	public void setrConditionalFee(int rConditionalFee) {
		this.rConditionalFee = rConditionalFee;
	}
	public int getrFeeCanBeApplied() {
		return rFeeCanBeApplied;
	}
	public void setrFeeCanBeApplied(int rFeeCanBeApplied) {
		this.rFeeCanBeApplied = rFeeCanBeApplied;
	}
	
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getConditional() {
		return conditional;
	}
	public void setConditional(String conditional) {
		this.conditional = conditional;
	}
	@Override
	public String toString() {
		return "FeeDataModel [feeType=" + feeType + ", parentID=" + parentID + ", active=" + active + ", conditional="
				+ conditional + ", isConditionSatisfied=" + isConditionSatisfied + ", conditionSatisfiedDate="
				+ conditionSatisfiedDate + ", conditionDesc=" + conditionDesc + ", feeStatus=" + feeStatus
				+ ", lastAppDate=" + lastAppDate + ", nextAppDate=" + nextAppDate + ", feeFreq=" + feeFreq
				+ ", feeStructType=" + feeStructType + ", paidBy=" + paidBy + ", payableTo=" + payableTo
				+ ", isPassThrough=" + isPassThrough + ", ractiveFee=" + ractiveFee + ", rConditionalFee="
				+ rConditionalFee + ", rFeeCanBeApplied=" + rFeeCanBeApplied + "]";
	}
	
}

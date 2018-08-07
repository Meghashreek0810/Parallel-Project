package com.cg.pwa.bean;

public class Account {
	
	private String customerName;
	private String mobileNo;
	private String emailId;
	private double balance;
	@Override
	public String toString() {
		return "Account [customerName=" + customerName + ", mobileNo="
				+ mobileNo + ", emailId=" + emailId + ", balance=" + balance
				+ "]";
	}
	public String getCustomerName() {
		return customerName;
	}
	public Account(String customerName, String phoneNo, String emailId,
			double initialBalance) {
		super();
		this.customerName = customerName;
		this.mobileNo = phoneNo;
		this.emailId = emailId;
		this.balance = initialBalance;
	}
	public Account() {
		
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String phoneNo) {
		this.mobileNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double initialBalance) {
		this.balance = initialBalance;
	}
}

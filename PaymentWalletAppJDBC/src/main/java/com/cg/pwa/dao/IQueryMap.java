package com.cg.pwa.dao;

public interface IQueryMap {
	
	public String addAccount="insert into account(mobileNo,emailId,customerName,balance) values(?,?,?,?)";
	
	public String showBalance="select balance from account where mobileNo=?";
	
	public String updateBalance="update account set balance=? where mobileNo=? ";
	
	public String transaction="select * from account where mobileNo=?";
	
	

}

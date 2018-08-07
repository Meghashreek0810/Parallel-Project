package com.cg.pwa.dao;

import com.cg.pwa.bean.Account;
import com.cg.pwa.exception.AccountException;

public interface IAccountDao {
		
	Account createAccount(Account acc) throws AccountException;
	
	double showBalance(String mobileNo) throws AccountException;
	
	double depositAmount(String mobileNo,double depositAmount) throws AccountException;
	
	double withdrawAmount(String mobileNo,double withdrawAmount) throws AccountException;
	
	double fundTransfer(String mobileNo1,String mobileNo2,double amount) throws AccountException;
	
	Account printTransaction(String mobileNo) throws AccountException;

}

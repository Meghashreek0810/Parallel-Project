package com.cg.pwa.service;

import com.cg.pwa.bean.Account;
import com.cg.pwa.dao.AccountDao;
import com.cg.pwa.dao.IAccountDao;
import com.cg.pwa.exception.AccountException;

public class AccountService implements IAccountService {
	
	IAccountDao accountDao=new AccountDao();

	
	public boolean validateMobileNo(String mobileNo) throws AccountException {

		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("mobile number should be 10 digits");
		}

		return true;
	}
	
	

	public boolean validateName(String name) throws AccountException {
		if(!name.matches("[a-z]{3,}")){
		throw new AccountException("name should contain only alphabets");	
		}
		return true;
	}

	public boolean validateEmailId(String emailId) throws AccountException {
		if(!emailId.matches("[a-z0-9]+@[a-z]+\\.com")){
			throw new AccountException("enter valid email id");
		}
		return true;
	}

	public boolean validatetAmount(double amount) throws AccountException {
		if(!(amount>0)){
			throw new AccountException("enter amount greater than 0");
		}
		return true;
	}

	

	public Account createAccount(Account acc) throws AccountException {
		if(!(validateName(acc.getCustomerName()) && validatetAmount(acc.getBalance()) && validateEmailId(acc.getEmailId())
				&& validateMobileNo(acc.getMobileNo())) ){
			throw new AccountException("account cannot created successfully");
			
		}
	
		return accountDao.createAccount(acc);
		
		
	}

	public double showBalance(String mobileNo) throws AccountException {
		if(!validateMobileNo(mobileNo)){
			throw new AccountException("cant show balance");
			
		
		
		}
		
		return accountDao.showBalance(mobileNo);
	}
	
	public double depositAmount(String mobileNo, double depositAmount)
			throws AccountException {
		if(!(validateMobileNo(mobileNo) && validatetAmount(depositAmount))){
			throw new AccountException("cannot deposit amount");
		}
		
		return accountDao.depositAmount(mobileNo, depositAmount);
	}

	
	
	public double withdrawAmount(String mobileNo, double withdrawAmount)
			throws AccountException {
		if(!(validateMobileNo(mobileNo) && validatetAmount(withdrawAmount))){
			throw new AccountException("cannot withdraw amount");
		}
	
		return accountDao.withdrawAmount(mobileNo, withdrawAmount);
	}

	
	
public double fundTransfer(String mobileNo1, String mobileNo2, double amount)
			throws AccountException {
		if(!(validateMobileNo(mobileNo1) && validateMobileNo(mobileNo2)&& validatetAmount(amount))){
			throw new AccountException("fund transfer not possible");
		}
		
		return accountDao.fundTransfer(mobileNo1, mobileNo2, amount);
	}

	public Account printTransaction(String mobileNo) throws AccountException {
		if(!(validateMobileNo(mobileNo))){
			throw new AccountException("transaction details not available");
		}
		return accountDao.printTransaction(mobileNo);
	}

}

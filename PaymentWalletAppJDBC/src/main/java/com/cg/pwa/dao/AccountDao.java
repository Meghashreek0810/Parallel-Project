package com.cg.pwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.cg.pwa.bean.Account;
import com.cg.pwa.db.AccountDb;
import com.cg.pwa.exception.AccountException;
import com.sun.webkit.ContextMenu.ShowContext;


public class AccountDao implements IAccountDao {

	


	public Account createAccount(Account acc) throws AccountException {
	
		Connection con=AccountDb.getConnection();
		PreparedStatement stat;
		
		try {
			
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMap.addAccount);
			stat.setString(1, acc.getMobileNo());
			stat.setString(2, acc.getEmailId());
			stat.setString(3, acc.getCustomerName());
			stat.setDouble(4, acc.getBalance());
			int res=stat.executeUpdate();
			System.out.println(res+" row inserted");
			if(res==1){
				con.commit();
				return acc;
			}
			else{
				throw new AccountException("Account cannot be created");
				
			}
		}
		
		 catch (SQLException e) {
				
				throw new AccountException(e.getMessage());
				
			}

		
	}


	public double showBalance(String mobileNo) throws AccountException {
		
		Connection con=AccountDb.getConnection();
		PreparedStatement stat;
		
		try {
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMap.showBalance);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			if(rs.next()){
			Double balance=rs.getDouble(1);
			return balance;
			}
			else{
				throw new AccountException("account doesnot exists");
			}
			

		} catch (SQLException e) {
			
		throw new AccountException(e.getMessage());
		}
		
	}

	
	public double depositAmount(String mobileNo, double depositAmount)throws AccountException {
		
		Connection con=AccountDb.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		PreparedStatement stat2;
		
		
		try {
			con.setAutoCommit(false);
		double oldBalance=showBalance(mobileNo);
		stat=con.prepareStatement(IQueryMap.updateBalance);
		double updatedBalance=oldBalance+depositAmount;
		stat.setDouble(1, updatedBalance);
		stat.setString(2, mobileNo);
		int rs=stat.executeUpdate();
		if(rs==1)
				{
					con.commit();
					return updatedBalance;
			
				}
				else
				{
					throw new AccountException("balance cannot be updated");
						
				}
			
			}
			catch (SQLException e) 
			{
				throw new AccountException(e.getMessage());
			}
			
	}

	
	public double withdrawAmount(String mobileNo, double withdrawAmount)throws AccountException {
		
		
		Connection con=AccountDb.getConnection();
		PreparedStatement stat;
		
		
		try {
			con.setAutoCommit(false);
			Double oldBalance=showBalance(mobileNo);
			if(withdrawAmount<oldBalance)
				
			{	
			
				stat=con.prepareStatement(IQueryMap.updateBalance);
				Double newBalance=oldBalance-withdrawAmount;
				stat.setDouble(1, newBalance);
				stat.setString(2, mobileNo);
				int rs=stat.executeUpdate();
				if(rs==1)
				{
					con.commit();
					return newBalance;
				}
				else
				{
					throw new AccountException("balance cannot be updated");
				}
			
			}
			else
			{
				throw new AccountException("enter withdraw amount less than available balance");
			}
		}
		catch (SQLException e) 
		{
			throw new AccountException(e.getMessage());
		}
	}


	public double fundTransfer(String mobileNo1, String mobileNo2, double amount) throws AccountException {

		
		if(showBalance(mobileNo2)!=0) {
		double balance1=withdrawAmount(mobileNo1, amount);
		depositAmount(mobileNo2, amount);
		return balance1;
		}
		else {
			throw new AccountException("fund transfer not possible");
		}
	
		
	}

	
	public Account printTransaction(String mobileNo) throws AccountException {

		
		Account account=new Account();
		Connection con=AccountDb.getConnection();
		PreparedStatement stat;
		
		try {
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMap.transaction);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			if(rs.next()){
				con.commit();
				account.setMobileNo(rs.getString(1));
				account.setCustomerName(rs.getString(2));
				account.setEmailId(rs.getString(3));
				account.setBalance(rs.getDouble(4));
				return account;
				
			}
			else
			{
				throw new AccountException("mobile number doesnot exist");
			}
		   }
		catch (SQLException e) 
		{
			throw new AccountException("Account doesnot exist");
		}
		
		
	}
	
	
}

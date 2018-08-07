package com.cg.pwa.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.pwa.bean.Account;
import com.cg.pwa.exception.AccountException;
import com.cg.pwa.service.AccountService;
import com.cg.pwa.service.IAccountService;

public class AccountTest {

	
	IAccountService accountService = new AccountService();
	
	
	@Test
	public void testCreateAccountForName() {

		Account account = new Account();
		account.setCustomerName("john896");
		account.setMobileNo("0123456789");
		account.setBalance(5000);
		account.setEmailId("john@gmail.com");
		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("name should contain only alphabets", e.getMessage());

		}
	}
	
	
	
	@Test
	public void testCreateAccountForMobile() {

		Account account = new Account();
		account.setCustomerName("john");
		account.setMobileNo("01234");
		account.setBalance(5000);
		account.setEmailId("john@gmail.com");
		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("mobile number should be 10 digits", e.getMessage());
		}

	}
	
	
	@Test
	public void testCreateAccountForEmail() {
		Account account = new Account();
		account.setCustomerName("john");
		account.setMobileNo("0123456789");
		account.setBalance(5000);
		account.setEmailId("johngmail.com");
		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("enter valid email id", e.getMessage());
		}

	}
	
	
	
	@Test
	public void testCreateAccountForAmount() {
		Account account = new Account();
		account.setCustomerName("john");
		account.setMobileNo("0123456789");
		account.setBalance(0);
		account.setEmailId("john@gmail.com");
		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("enter amount greater than 0", e.getMessage());
		}

	}
	
	
	
	 @Test
	public void testCreateAccount() {

		Account account = new Account();
		account.setCustomerName("mary");
		account.setMobileNo("8965741236");
		account.setBalance(9000);
		account.setEmailId("mary@gmail.com");

		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("account created successfully", e.getMessage());
		}

	}
	
	
	// test cases for showBalance method

		@Test
		public void testShowBalanceForMobile() {

			Account account = new Account();
			account.setMobileNo("98766");
			try {

				accountService.showBalance(account.getMobileNo());
			} catch (AccountException e) {

				assertEquals("mobile number should be 10 digits", e.getMessage());
			}
		}

	
	@Test
	public void testShowBalanceForAccount() {

		Account account = new Account();
		account.setMobileNo("9876699999");
		try {
			accountService.showBalance(account.getMobileNo());

		} catch (AccountException e) {
			
			assertEquals("account doesnot exists", e.getMessage());
		}
	}
	
	
	@Test
	public void testShowBalance() {

		Account account = new Account();
		account.setMobileNo("9945632145");
		try {
			double amt = accountService.showBalance(account.getMobileNo());
			assertEquals(10000, amt, 0);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// test cases for depositAmount method

		@Test
		public void testDepositAmountForMobile() {

			Account account = new Account();
			account.setMobileNo("9945");
			double amount = 120;

			try {
				accountService.depositAmount(account.getMobileNo(), amount);
			} catch (AccountException e) {
				assertEquals("mobile number should be 10 digits", e.getMessage());
			}
		}
	
	@Test
	public void testDepositAmountForAccount() {

		Account account = new Account();
		account.setMobileNo("0123456789");
		double amount = 120;
		try {
			accountService.depositAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("account doesnot exists", e.getMessage());
		}
	}

	@Test
	public void testDepositAmountForAmount() {

		Account account = new Account();
		account.setMobileNo("9535597905");
		double amount = -100;
		try {
			accountService.depositAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("enter amount greater than 0", e.getMessage());
		}
	}
	
	
	@Test
	public void testDepositAmount() {

		Account account = new Account();
		account.setMobileNo("9945876321");
		double amount = 1000;
		try {
			double balance = accountService.depositAmount(
					account.getMobileNo(), amount);
			assertEquals(13000, balance, 0.0);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// test cases for withdrawAmount method

		@Test
		public void testWithdrawAmountForMobile() {

			Account account = new Account();
			account.setMobileNo("0123");
			double amount = 500;
			;
			try {
				double balance = accountService.withdrawAmount(
						account.getMobileNo(), amount);
			} catch (AccountException e) {
				assertEquals("mobile number should be 10 digits", e.getMessage());
			}
		}
	
	
	@Test
	public void testWithdrawAmountForAccount() {

		Account account = new Account();
		account.setMobileNo("0123456789");
		double amount = 500;
		try {
			double balance = accountService.withdrawAmount(
					account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("account doesnot exists", e.getMessage());
		}
	}
	
	
	@Test
	public void testWithdrawAmountForAmount1() {

		Account account = new Account();
		account.setMobileNo("9535597985");
		double amount = -100;
		try {
			double balance = accountService.withdrawAmount(
					account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("enter amount greater than 0", e.getMessage());
		}

	}

	@Test
	public void testWithdrawAmountForAmount2() {

		Account account = new Account();
		account.setMobileNo("9535597985");
		double amount = 5000;
		try {
			double balance = accountService.withdrawAmount(
					account.getMobileNo(), amount);

		} catch (AccountException e) {
			
			assertEquals("enter withdraw amount less than available balance",e.getMessage());
		}
	}

	
	 @Test
	public void testWithdrawAmount() {

		Account account = new Account();
		account.setMobileNo("9987698536");
		double amount = 100;
		try {
			double balance = accountService.withdrawAmount(
					account.getMobileNo(), amount);
			assertEquals(1900, balance, 0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}

	}
	
	
	// test cases for fundTrnasfer method

		@Test
		public void testFundTransferForMobile1() {

			Account account1 = new Account();
			account1.setMobileNo("1234");
			Account account2 = new Account();
			account2.setMobileNo("1234567890");
			double amount = 200;
			try {
				accountService.fundTransfer(account1.getMobileNo(),
						account2.getMobileNo(), amount);
			} catch (AccountException e) {

				assertEquals("mobile number should be 10 digits", e.getMessage());
			}

		}

	
	@Test
	public void testFundTransferForMobile2() {

		Account account1 = new Account();
		account1.setMobileNo("1234567890");
		Account account2 = new Account();
		account2.setMobileNo("12345");
		double amount = 200;
		try {
			accountService.fundTransfer(account1.getMobileNo(),
					account2.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("mobile number should be 10 digits", e.getMessage());
		}

	}

	@Test
	public void testFundTransferForAccount1() {

		Account account1 = new Account();
		account1.setMobileNo("1234567890");
		Account account2 = new Account();
		account2.setMobileNo("9535597905");
		double amount = 200;
		try {
			accountService.fundTransfer(account1.getMobileNo(),
					account2.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("account doesnot exists", e.getMessage());
		}

	}
	
	
	@Test
	public void testFundTransferForAccount2() {

		Account account1 = new Account();
		account1.setMobileNo("8763549869");
		Account account2 = new Account();
		account2.setMobileNo("0123456789");
		double amount = 200;
		try {
			accountService.fundTransfer(account1.getMobileNo(),
					account2.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("account doesnot exists", e.getMessage());
		}

	}
	
	@Test
	public void testFundTransferForBAmount() {

		Account account1 = new Account();
		account1.setMobileNo("9535597985");
		Account account2 = new Account();
		account2.setMobileNo("8763549869");
		double amount = -100;
		try {
			accountService.fundTransfer(account1.getMobileNo(),
					account2.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("enter amount greater than 0", e.getMessage());
		}

	}
	
	

	@Test
	public void testFundTransferForWAmount() {

		Account account1 = new Account();
		account1.setMobileNo("9535597985");
		Account account2 = new Account();
		account2.setMobileNo("8763549869");
		double amount = 8000;
		try {
			accountService.fundTransfer(account1.getMobileNo(),
					account2.getMobileNo(), amount);
		} catch (AccountException e) {
			
			assertEquals("enter withdraw amount less than available balance",
					e.getMessage());
		}

	}
	
	
	
	 @Test
	public void testFundTransfer() {

		Account account1 = new Account();
		account1.setMobileNo("8763549869");
		Account account2 = new Account();
		account2.setMobileNo("9535597985");
		double amount = 100;

		try {
			double balance = accountService.fundTransfer(
					account1.getMobileNo(), account2.getMobileNo(), amount);
			assertEquals(2300, balance, 0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}

	}
	
	// test cases for printTransaction method

		@Test
		public void testPrintTransactionForMobile() {
			Account account = new Account();
			account.setMobileNo("01234");
			try {
				accountService.printTransaction(account.getMobileNo());

			} catch (AccountException e) {

				assertEquals("mobile number should be 10 digits", e.getMessage());
			}
		}
	
	@Test
	public void testPrintTransactionForAccount() {
		Account account = new Account();
		account.setMobileNo("9999999999");
		try {
			accountService.printTransaction(account.getMobileNo());

		} catch (AccountException e) {

			assertEquals("account doesnot exists", e.getMessage());
		}
	}
	
	@Test
	public void testPrintTransactionForName() {
		Account account = new Account();
		account.setMobileNo("9999999999");
		try {
			Account acc1 = accountService.printTransaction(account.getMobileNo());
			
			
			assertEquals("megha", acc1.getCustomerName());
			
		} catch (AccountException e) {
			
			System.out.println(e.getMessage());

		}
	}
	
	@Test
	public void testPrintTransactionForBalance() {
		Account account = new Account();
		account.setMobileNo("9945632145");
		try {
			Account account1 = accountService.printTransaction(account
					.getMobileNo());
			
			assertEquals(10000, account1.getBalance(), 0);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}
	}

	@Test
	public void testPrintTransaction() {
		Account account = new Account();
		account.setMobileNo("9535597985");
		try {
			Account account1 = accountService.printTransaction(account.getMobileNo());
			
			assertNotNull(account1);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

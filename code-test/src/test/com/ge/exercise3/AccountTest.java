package com.ge.exercise3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AccountTest {

	Account checkingAccount;
	Account savingsAccount;

	@Before
	public void setUp() {
		checkingAccount = new Account("001", "Checking");
		savingsAccount = new Account("002", "Savings");
	}


	//MODIFICATION
	//ADDED TEST CASES FOR 
	//PREVENT CHECKING ACCOUNTS FROM BEING OVERDRAWN BY MORE THAT $100
	//PREVENT SAVINGS ACCOUNTS FROM EVER HAVING A NEGATIVE BALANCE
	@Test
	public void depositAndWithdrawTest() {

		checkingAccount.setBalance(0.0f);
		checkingAccount.deposit(100.0f);
		assertEquals(100.0f, checkingAccount.getBalance(), 0.01);
		checkingAccount.withdraw(100.0f);
		assertEquals(0.0f, checkingAccount.getBalance(), 0.01);

		//Modification
		//can overdraw till -100$
		checkingAccount.withdraw(100.0f);
		assertEquals(-100.0f, checkingAccount.getBalance(), 0.01);

		//Modification
		//will fails as balance is already -100$
		assertEquals(false,checkingAccount.withdraw(1.0f));

		//Modification
		//Checking for savings
		savingsAccount.setBalance(0.0f);
		savingsAccount.deposit(100.0f);

		assertEquals(100.0f, savingsAccount.getBalance(), 0.01);

		//Modification
		//Checking for savings
		savingsAccount.withdraw(100.0f);
		assertEquals(00.0f, savingsAccount.getBalance(), 0.01);

		//Modification
		//will fails as balance cannot be negative
		assertEquals(false,checkingAccount.withdraw(1.0f));
	}

	//MODIFICATION
	@Test
	public void valueNextMonthTest() {
		checkingAccount = new Account("003", "Checking", 100.0f);
		assertEquals(100.0f, checkingAccount.valueNextMonth(), 0.01f);

		savingsAccount = new Account("004", "Savings", 100.0f);
		assertEquals(101.0f, savingsAccount.valueNextMonth(), 0.01f);

		checkingAccount.setMonthlyFee(10.0f);
		assertEquals(90.0f, checkingAccount.valueNextMonth(), 0.01f);

		//Modification
		//Interest rate formulae was inappropriate
		//savingsAccount.valueNextMonth() will return 101.5
		savingsAccount.setMonthlyInterestRate(1.05f);
		assertEquals(101.05f, savingsAccount.valueNextMonth(), 0.01f);
	}

	
	@Test
	public void toStringTest() {
		savingsAccount = new Account("005", "Savings", 0.0f);
		assertEquals("No fee savings account #005", savingsAccount.toString());

		checkingAccount = new Account("006", "Checking", 0.0f);
		assertEquals("No fee checking account #006", checkingAccount.toString());

		checkingAccount.setMonthlyFee(10.0f);
		assertEquals("Checking account #006", checkingAccount.toString());

		savingsAccount.setMonthlyInterestRate(1.02f);
		assertEquals("High interest no fee savings account #005", savingsAccount.toString());
	}


}
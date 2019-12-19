package com.ge.exercise3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BankTest {

	Bank bank;

	@Before
	public void setUp() {
		bank = new Bank();
	}

	//MODIFICATION TO TEST CASE 
	//CHANGED ADDACCOUNT TO BOOLEAN SO KNOW IF IT WAS SUCCESSFULLY ADDED OR NOT. 
	@Test
	public void addAccountTest() {
		Account account = new Account("001");
		assertEquals(true,bank.addAccount(account));
		assertEquals(false,bank.addAccount(account));
		assertEquals(1, bank.numAccounts());
	}

	@Test
	public void getAccountTest() {
		Account account = new Account("002", "Checking");
		bank.addAccount(account);
		assertEquals(account, bank.getAccount("002"));
	}

	@Test
	public void depositToAccountTest() {
		Account account = new Account("003", "Checking", 100.0f);
		bank.addAccount(account);
		bank.depositToAccount("003", 100.0f);
		assertEquals(200.0f, account.getBalance(), 0.01);
	}

	@Test
	public void withdrawFromAccountTest() {
		Account account = new Account("004", "Checking", 100.0f);
		bank.addAccount(account);
		bank.withdrawFromAccount("004", 100.0f);
		assertEquals(0.0f, account.getBalance(), 0.01);
	}

	@Test
	//MODIFICATION
	//CHECKING CAN OVERDRAW TILL $100
	public void withdrawFromCheckingsOverDrawnAccountTest() {
		Account account = new Account("004", "Checking", 100.0f);
		bank.addAccount(account);

		bank.withdrawFromAccount("004", 100.0f);
		assertEquals(0.0f, account.getBalance(), 0.01);
		//CAN DRAW TILL -100$
		bank.withdrawFromAccount("004", 100.0f);
		assertEquals(-100.0f, account.getBalance(), 0.01);
		//FALSE BECAUSE CANNOT OVER DRAW OVER -100$

		assertEquals(false,	bank.withdrawFromAccount("004", 100.0f));
	}

	//MODIFICATION
	//SAVIN CANNOT GO BELOW 0
	@Test
	public void withdrawFromSavingNegativeTest() {
		Account account = new Account("005", "Savings", 100.0f);
		bank.addAccount(account);

		//CAN WITHDRAW TILL 0
		bank.withdrawFromAccount("005", 100.0f);
		assertEquals(0.0f, account.getBalance(), 0.01);


		//FALSE BECAUSE CANNOT WITHRAW BELOW NEGATIVE
		assertEquals(false,	bank.withdrawFromAccount("004", 10.0f));
	}


	//MODIFICATION
	//GET SUM OF CURRENT HOLDINGS
	@Test
	public void getSumofCurrentHoldings() {

		Account account = new Account("001", "Checking", 100.0f);
		Account account1 = new Account("002", "Checking", 100.0f);
		Account account2 = new Account("003", "Checking", 100.0f);
		Account account3 = new Account("004", "Checking", 100.0f);
		Account account4 = new Account("005", "Checking", 100.0f);
		Account account5 = new Account("006", "Checking", 100.0f);
		bank.addAccount(account);
		bank.addAccount(account1);
		bank.addAccount(account2);
		bank.addAccount(account3);
		bank.addAccount(account4);
		bank.addAccount(account5);

		assertEquals(600.00f,bank.getSumOfCurrentAccounts(), 0.01);

	}
	
	//MODIFICATION
	@Test
	public void getProfitOrLossPredictor() {

		Account account = new Account("001", "Savings", 200.0f);
		account.setMonthlyInterestRate(1.0f);
		account.setMonthlyFee(10);
		bank.addAccount(account);

		Account account1 = new Account("002", "Checking", 300.0f);
		account1.setMonthlyInterestRate(1.0f);
		account1.setMonthlyFee(10);
		bank.addAccount(account1);

		assertEquals("Profit",bank.profitOrLossPredictor());
	}

}
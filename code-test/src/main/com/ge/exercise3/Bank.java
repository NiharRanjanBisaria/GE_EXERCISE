package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Bank {

	private static final Logger logger = LogManager.getLogger(Bank.class);
	private Map<String, Account> accountMap;

	public Bank() {
		accountMap = new HashMap<>();
	}


	public Account getAccount(String accountNumber) {
		return accountMap.get(accountNumber);
	}

	//MODIFICATION 
	//CHECKING IF ACCOUNT EXISTS BEFORE SO THAT ALREADY PRESENT ACCOUNT IS NOT OVERRIDDEN 
	//CHANGING RETURN TYPE TO BOOLEAN SO THAT WE KNOW IF THE ACCOUNT WAS SUCCESSFULLY ADDED OR NOT 
	public boolean addAccount(Account account) {
		if(accountMap.containsKey(account.getAccountNumber())) {
			return false;
		}
		accountMap.put(account.getAccountNumber(), account);
		return true;
	}

	//MODIFICATION 
	public boolean depositToAccount(String accountNumber, float amount) {
		if(accountMap.containsKey(accountNumber)) {
			getAccount(accountNumber).deposit(amount);
			return true;
		}
		return false;
	}

	public boolean withdrawFromAccount(String accountNumber, float amount) {
		if(accountMap.containsKey(accountNumber)) {
			return getAccount(accountNumber).withdraw(amount);
		} 
		return false;
	}

	public int numAccounts() {

		return accountMap.size();

	}

	//MODIFICATION
	//OVERRIDEN HASHCODE AND EQUALS METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountMap == null) ? 0 : accountMap.hashCode());
		return result;
	}

	//MODIFICATION
	//OVERRIDEN HASHCODE AND EQUALS METHODs
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (accountMap == null) {
			if (other.accountMap != null)
				return false;
		} else if (!accountMap.equals(other.accountMap))
			return false;
		return true;
	}

	//MODIFICATION
	//SUM OF CURRENT HOLDINGS
	//SUM OF ALL ACCOUNT
	public double getSumOfCurrentAccounts() {
		float result = 0;
		for( Map.Entry<String, Account> iterator: accountMap.entrySet()) {
			result += iterator.getValue().getBalance(); 
		}
		return result;
	}


	//MODIFICATION
	//PROFIT OR LOSS PREDICTOR
	public String profitOrLossPredictor() {
		float fessCollected = 0;
		float interestPaid = 0;
		for( Map.Entry<String, Account> iterator: accountMap.entrySet()) {
			fessCollected += iterator.getValue().getMonthlyFee();
			interestPaid += (iterator.getValue().getMonthlyInterestRate()/100)*iterator.getValue().getBalance();
		}

		if(fessCollected > interestPaid) {
			return "Profit";
		}
		else if(fessCollected < interestPaid) {
			return "Loss";
		}
		else return "No Profit, No Loss";

	}
}

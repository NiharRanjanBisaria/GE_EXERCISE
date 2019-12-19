package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account {

	private static final Logger logger = LogManager.getLogger(Account.class);

	//MODIFICATION
	//MONTHLYINTERESTRATE && MONTHLYFEE SHOULDN'T BE STATIC BECAUSE IT IS NOT SAME 
	//FOR ALL KIND OF ACCOUNT
	private float monthlyInterestRate;
	private float monthlyFee;

	private String accountNumber;
	private String accountType;
	private float balance;

	public Account(String accountNumber, String accountType, float balance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;


		//IN THE PROBLEM STATEMENT IT IS GIVEN THAT  
		//CHECKING ACCOUNTS DEFAULT TO NO INTEREST AND NO FEES
		//SAVINGS ACCOUNTS DEFAULT TO 1% INTEREST AND NO FEES
		if (this.getAccountType().equals(TypesOfAccount.SAVINGS)) {
			this.monthlyInterestRate = 1.0f;
		}
	}

	//MODIFICATION
	//CONSTRUCTORS WERE WRONGLY CALLED
	public Account(String accountNumber, String accountType) {
		this(accountNumber, accountType, 0.0f);
	}

	//MODIFICATION
	//CONSTRUCTORS WERE WRONGLY CALLED
	public Account(String accountNumber) {
		this(accountNumber,TypesOfAccount.SAVINGS, 0.0f);
	}

	//Modification
	///Value NextMonth formulae is not appropriate 
	public float valueNextMonth() {
		float balance = this.getBalance();
		float interstRate = this.getMonthlyInterestRate() / 100;    	
		return balance + (balance*interstRate) - this.getMonthlyFee();
	}


	@Override
	public String toString() {

		//MODIFICATION
		if (this.getAccountType().equals(TypesOfAccount.CHECKING)) {
			if (monthlyFee == 0.0f) {
				return "No fee checking account #" + accountNumber;
			} else {
				return "Checking account #" + accountNumber;
			}
		} else {
			if (monthlyInterestRate > 1.01) {
				if (monthlyFee == 0.0f) {
					return "High interest no fee savings account #" + accountNumber;
				} else {
					return "High interest savings account #" + accountNumber;
				}
			} else {
				if (monthlyFee == 0.0f) {
					return "No fee savings account #" + accountNumber;
				} else {
					return "Savings account #" + accountNumber;
				}
			}
		}
	}

	//MODIFICATION 
	//MADE THE METHOD SYNCHRONIZATION IN CASE OF MULTIPLE THREADS ACCESSING IT
	public synchronized void deposit(float amount) {
		balance += amount;
	}

	//MODIFICATION 
	//MADE THE METHOD SYNCHRONIZATION IN CASE OF MULTIPLE THREADS ACCESSING IT
	public synchronized boolean withdraw(float amount) {
		//MODIFICATION 
		//PREVENT CHECKING ACCOUNTS FROM BEING OVERDRAWN BY MORE THAT $100
		if (this.getAccountType().equals(TypesOfAccount.CHECKING)){
			if((balance - amount) >= -100.00f) {
				balance -= amount;
				return true;
			}
			else {
				return false;
			}
		}else {
			//MODIFICATION 
			//PREVENT SAVINGS ACCOUNTS FROM EVER HAVING A NEGATIVE BALANCE
			if((balance - amount) >= 00.00f) {
				balance -= amount;
				return true;
			}
			else {
				return false;
			}
		}
	}

	public float getMonthlyInterestRate() {
		return monthlyInterestRate;
	}

	public  void setMonthlyInterestRate(float monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public float getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return this.accountType;
	}

	//MODIFICATION
	//OVERRIDEN HASHCODE AND EQUALS METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + Float.floatToIntBits(balance);
		return result;
	}

	//MODIFICATION
	//OVERRIDEN HASHCODE AND EQUALS METHODS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		return true;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getBalance() {
		return balance;
	}

	void setBalance(float balance) {
		this.balance = balance;
	}
}

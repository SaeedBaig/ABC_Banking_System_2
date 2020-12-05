package com.bean;

public class Account
{
	private long accountID;
	private double balance;
	private long CustomerID;
	private double transactionFee;
	private int numTransactions;
	private AccountType accountType; 
	
	public Account(long id, double balance, long CustomerID, int transactionNum, double transactionFee, AccountType accountType)
	{
		this.accountID = id;
		this.balance = balance;
		this.CustomerID = CustomerID;
		this.numTransactions = transactionNum;
		this.transactionFee = transactionFee;
		this.accountType = accountType;
	}
	
	public Account(double balance, long CustomerID, double transactionFee, AccountType accountType)
	{
		this.balance = balance;
		this.CustomerID = CustomerID;
		this.numTransactions = 0;
		this.transactionFee = transactionFee;
		this.accountType = accountType;
	}

	public Account() {
	}
	
	
	@Override
	public String toString() {
		return accountID + " : Account balance=" + balance + ",CustomerID=" + CustomerID + ", numTransactions=" + numTransactions + ", transactionFee=" + transactionFee + ", Type=" + this.accountType.toString();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(long customerID) {
		CustomerID = customerID;
	}

	public double getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(double transactionFee) {
		this.transactionFee = transactionFee;
	}

	public int getNumTransactions() {
		return numTransactions;
	}

	public void setNumTransactions(int numTransactions) {
		this.numTransactions = numTransactions;
	}

	public AccountType getAccountType() 
	{
		return this.accountType;
	}

	public void setAccountType(AccountType accountType) 
	{
		this.accountType = accountType;
	}
	
	public void setID(long id)
	{
		this.accountID = id;
	}
	
	public long getID()
	{
		return this.accountID;
	}
	
	public void incrementNumTransactions()
	{
		numTransactions++;
	}
}
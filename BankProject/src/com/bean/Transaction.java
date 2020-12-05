package com.bean;

import java.util.Date;

public class Transaction
{
	private long transactionID;
	private String date;
	private double amount;
	private long accountNumber;

	public Transaction(long id, String date, double amount, long accountNumber) 
	{
		this.transactionID = id;
		this.date = date;
		this.amount = amount;
		this.accountNumber = accountNumber;
	}
    
    public Transaction(String date, double amount, long accountNumber) 
	{
		this.date = date;
		this.amount = amount;
		this.accountNumber = accountNumber;
	}

	public Transaction() {

	}

	@Override
	public String toString() {
		return  transactionID + ", date=" + date + ", amount=" + amount
				+ ", accountNumber=" + accountNumber + "]";
	}

	public void setID(long id)
	{
		this.transactionID = id;
	}
	
	public long getID()
	{
		return this.transactionID;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String newDate) {
		this.date = newDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
}
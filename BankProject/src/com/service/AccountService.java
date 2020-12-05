package com.service;

import java.util.ArrayList;

import com.bean.Account;
import com.bean.Transaction;
import com.dao.AccountDAO;
import com.dao.TransactionDAO;

public class AccountService {
	
	private AccountDAO dao;
	
	public AccountService()
	{
		dao = new AccountDAO();
	}
	
	//Get all Accounts
	public ArrayList<Account> getAllAccounts() {
		
		return dao.getAll();
	}
	
	
	//Get all Accounts by CustomerID
	public ArrayList<Account> getAllAccountsByCustomerID(long CustomerID) {
		
		return dao.getAllByCustomerID(CustomerID);
	}
	
	//Get Account details based on the AccountNumber passed as parameter
	public Account getAccountDetails(long AccountNumber) {
		
		return dao.get(AccountNumber);
	}
	
	//Add new Account to the application
	public long addAccount(Account a) {
		
		return dao.add(a);
	}
	
	//Delete the Account which is passed as parameter
	public boolean deleteAccount(long AccountNumber) 
	{
		new TransactionDAO().deleteAllByCustomerID(AccountNumber);
		return dao.delete(AccountNumber);
	}
	
}
	
package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.bean.Account;
//import com.bean.Customer;
import com.bean.Transaction;
import com.dao.TransactionDAO;
import com.dao.AccountDAO;
import com.bean.AccountType;


public class TransactionService {
	
	private TransactionDAO TransactionDAO;
	private AccountDAO AccountDAO;
	
	public TransactionService()
	{
		TransactionDAO = new TransactionDAO();
		AccountDAO = new AccountDAO();
	}
	
	//Get all Accounts
	public ArrayList<Transaction> getAllTransactions() 
	{	
		return TransactionDAO.getAll();
	}
	
	public ArrayList<Transaction> getAllByAccountID(long id)
	{
		return TransactionDAO.getAllByAccountID(id);
	}
	
	public double DepositAmount (long AccountNo, double Deposit) 
	{
		  Account account = AccountDAO.get(AccountNo);
		  Date date = new Date();
		  double balance = account.getBalance();
		  double newBalance = balance + Math.abs(Deposit);
		  account.setBalance(newBalance);
		  account.incrementNumTransactions();
		  AccountDAO.update(AccountNo, account);
		  TransactionDAO.add(new Transaction(date.toString(), Deposit, AccountNo));
		  return newBalance;
	}
		  

	
	public double WithdrawAmount (long Accountno, double withdrawAmount) 
	{
		withdrawAmount = Math.abs(withdrawAmount);
		Account account = AccountDAO.get(Accountno);
		double balance = account.getBalance();
		
		if (balance <= 0.0)
		{
			return 0;
		}
		
		Date date = new Date();
        double Overdraft_amount = 0.0;
        double newAccountLimit = 0.0;
        double newBalance= 0.0;
        double transactionFee = 0.0;
        
		if( account.getAccountType() == AccountType.CashCredit) 
		{
			if (withdrawAmount > balance + balance * 0.1)
			{
				return balance;
			}
		}
		
		if ( account.getAccountType()  == AccountType.RegularCurrent) 
		{
			if (withdrawAmount > balance)
			{
				return balance;
			}
			transactionFee = (account.getNumTransactions()/100)*100 ;
		}
		
		newBalance = balance - withdrawAmount - transactionFee;
		account.setBalance(newBalance);
		account.incrementNumTransactions();
		AccountDAO.update(Accountno, account);
		TransactionDAO.add(new Transaction(date.toString(), -1 * withdrawAmount, Accountno));
		return newBalance;
	}
     
	
	//Add new Transaction to the application
	public long addTransaction(long Accountno, Transaction t) {
		Account account = AccountDAO.get(Accountno);
		int numTransactions = account.getNumTransactions() + 1 ;
		TransactionDAO dao =new TransactionDAO();
		AccountDAO.updateNumTransactions(Accountno, numTransactions);
		return dao.add(t); 	
	}
		
	public double TransactionCount (long AccountNo) {
		Account account = AccountDAO.get(AccountNo);
		TransactionDAO dao =new TransactionDAO();
		return dao.getAllByAccountID(account.getID()).size();
	};


	public void generateTransactionStatement(Long AccountNo,TransactionDAO[] Trans ) {
		Account account = AccountDAO.get(AccountNo);
		TransactionDAO dao =new TransactionDAO();
		ArrayList<Transaction> Resultlist = dao.getAllByAccountID(account.getID());
			System.out.println("===========GENERATE STATEMENT========");
			for (Transaction tr: Resultlist ){
			System.out.println(tr.getAccountNumber()+ " " + tr.getDate() + " " + tr.getAmount());
					System.out.println(" ");
				}
		
	}
}
package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.Account;
import com.bean.AccountType;
import com.bean.Customer;
import com.bean.Gender;
import com.bean.Transaction;
import com.util.DBUtil;

public class AccountDAO
{
	private DBUtil dbUtil;
	
	public AccountDAO()
	{
		dbUtil = DBUtil.getInstance();
	}

	public long add(Account account)
	{
		String query = "INSERT INTO Account (customer_id, balance, transaction_count, transaction_fee, account_type) VALUES (?, ?, ?, ?, ?)";
		dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) account.getCustomerID());
			ps.setDouble(2, account.getBalance());
			ps.setInt(3, account.getNumTransactions());
			ps.setDouble(4, account.getTransactionFee());
			ps.setString(5, account.getAccountType().toString());
		    ps.executeUpdate();
		    
			return true;
		});
		
		return getID();
	}
	
	
	public long add() 
	{
		String query = "INSERT INTO Account (customer_id, balance, transaction_count, transaction_fee, account_type) VALUES ('', '', '', '', '')";
		dbUtil.execute(query, (PreparedStatement ps) -> {
		
		    ps.executeUpdate();    
			return true;
		});
		
		return getID();
	}
	
	public boolean delete(long id) 
	{
		String query = "DELETE FROM Account WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
		    
			ps.setInt(1, (int) id);
			ps.executeUpdate();
			return true;
		});
	}
	
	
	public boolean update(long id, Account newAccount) 
	{
		String query = "UPDATE Account SET customer_id = ?, balance = ?, transaction_count = ?, transaction_fee = ?, account_type = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) newAccount.getCustomerID());
			ps.setDouble(2, newAccount.getBalance());
			ps.setInt(3, newAccount.getNumTransactions());
			ps.setDouble(4, newAccount.getTransactionFee());
			ps.setString(5, newAccount.getAccountType().toString());
			ps.setInt(6, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateBalance(long id, double newBalance)
	{
		String query = "UPDATE Account SET balance = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setDouble(1, newBalance);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateCustomerID(long id, long newID)
	{
		String query = "UPDATE Account SET customer_id = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) newID);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateTransactionFee(long id, double newFee)
	{
		String query = "UPDATE Account SET transaction_fee = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setDouble(1, newFee);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateNumTransactions(long id, int newNumber)
	{
		String query = "UPDATE Account SET transaction_count = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, newNumber);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateAccountType(long id, AccountType newType)
	{
		String query = "UPDATE Account SET account_type = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newType.toString());
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public Account get(long id) 
	{
		String query = "SELECT * FROM Account WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				return new Account((long) rs.getInt("id"),
								   rs.getDouble("balance"),
								   (long) rs.getInt("customer_id"),
								   rs.getInt("transaction_count"),
								   rs.getDouble("transaction_fee"),
								   convertAccountType(rs.getString("account_type")));
			}
			return null;
		});
	}
	
	public ArrayList<Account> getAllByCustomerID(long customerID)
	{
		String query = "SELECT * FROM Account WHERE customer_id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) customerID);
			ArrayList<Account> accounts = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				accounts.add(new Account((long) rs.getInt("id"),
										 rs.getDouble("balance"),
										 (long) rs.getInt("customer_id"),
										 rs.getInt("transaction_count"),
										 rs.getDouble("transaction_fee"),
										 convertAccountType(rs.getString("account_type"))));
			}
			return accounts;
		});
	}

	public ArrayList<Account> getAll() 
	{
		String query = "SELECT * FROM Account";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ArrayList<Account> accounts = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				accounts.add(new Account((long) rs.getInt("id"),
										 rs.getDouble("balance"),
										 (long) rs.getInt("customer_id"),
										 rs.getInt("transaction_count"),
										 rs.getDouble("transaction_fee"),
										 convertAccountType(rs.getString("account_type"))));
			}
			return accounts;
		});
	}
	
	
	private long getID()
	{
		return dbUtil.execute("SELECT MAX(id) FROM Account", (PreparedStatement ps) -> {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				return rs.getInt("MAX(id)");
			}
			return 0;
		});
	}
	
	private AccountType convertAccountType(String type)
	{
		if (type.equals("CashCredit"))
		{
			return AccountType.CashCredit;
		}
		return AccountType.RegularCurrent;
	}
}
package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bean.Account;
import com.bean.AccountType;
import com.bean.Transaction;
import com.util.DBUtil;

public class TransactionDAO 
{
	private DBUtil dbUtil;
	private SimpleDateFormat dateFormat;
	
	public TransactionDAO()
	{
		dbUtil = DBUtil.getInstance();
		dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	}
	
	
	public long add(Transaction transaction)
	{
		String query = "INSERT INTO \"Transaction\" (account_id, transaction_date, amount) VALUES (?, ?, ?)";
		dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) transaction.getAccountNumber());
			ps.setString(2, transaction.getDate());
			ps.setDouble(3, transaction.getAmount());
		    ps.executeUpdate();
		    
			return true;
		});
		
		return getID();
	}
	
	
	public long add() 
	{
		String query = "INSERT INTO \"Transaction\" (account_id, transaction_date, amount) VALUES ('', '', '')";
		dbUtil.execute(query, (PreparedStatement ps) -> {
		
		    ps.executeUpdate(); 
			return true;
		});
		
		return getID();
	}
	
	
	public boolean delete(long id) 
	{
		String query = "DELETE FROM \"Transaction\" WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
		    
			ps.setInt(1, (int) id);
			ps.executeUpdate();
			return true;
		});
	}
	
	public boolean deleteAllByCustomerID(long id)
	{
		String query = "DELETE FROM \"Transaction\" WHERE account_id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
		    
			ps.setInt(1, (int) id);
			ps.executeUpdate();
			return true;
		});
	}
	
	public boolean update(long id, Transaction newTransaction) 
	{
		String query = "UPDATE \"Transaction\" SET account_id = ?, transaction_date = ?, amount = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) newTransaction.getAccountNumber());
			ps.setString(2, newTransaction.getDate().toString());
			ps.setDouble(3, newTransaction.getAmount());
			ps.setInt(4, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateDate(long id, String newDate)
	{
		String query = "UPDATE \"Transaction\" SET transaction_date = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newDate);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateAmount(long id, long newAmount)
	{
		String query = "UPDATE \"Transaction\" SET amount = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setDouble(1, newAmount);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateAccountNumber(long id, long newID)
	{
		String query = "UPDATE \"Transaction\" SET account_id = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) newID);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	
	public Transaction get(long id) 
	{
		String query = "SELECT * FROM \"Transaction\" WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				return new Transaction((long) rs.getInt("id"),
										rs.getString("transaction_date"),
						   				rs.getDouble("amount"),
						   				(long) rs.getInt("account_id"));
			}
			return null;
		});
	}
	
	
	public ArrayList<Transaction> getAllByAccountID(long accountID)
	{
		String query = "SELECT * FROM \"Transaction\" WHERE account_id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) accountID);
			ArrayList<Transaction> transactions = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				transactions.add(new Transaction((long) rs.getInt("id"),
										rs.getString("transaction_date"),
						   				rs.getDouble("amount"),
						   				(long) rs.getInt("account_id")));
			}
			return transactions;
		});
	}
	
	
	public ArrayList<Transaction> getAll() 
	{
		String query = "SELECT * FROM \"Transaction\"";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ArrayList<Transaction> transactions = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				transactions.add(new Transaction((long) rs.getInt("id"),
										rs.getString("transaction_date"),
						   				rs.getDouble("amount"),
						   				(long) rs.getInt("account_id")));
			}
			return transactions;
		});
	}
	
	private long getID()
	{
		return dbUtil.execute("SELECT MAX(id) FROM \"Transaction\"", (PreparedStatement ps) -> {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				return rs.getInt("MAX(id)");
			}
			return 0;
		});
	}
}
package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.bean.Customer;
import com.bean.Gender;
import com.util.DBUtil;

public class CustomerDAO
{
	private DBUtil dbUtil;
	
	public CustomerDAO()
	{
		dbUtil = DBUtil.getInstance();
	}

	public long add(Customer customer)
	{
		String query = "INSERT INTO Customer (name, email, address, phone, gender) VALUES (?, ?, ?, ?, ?)";
		dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getAddress());
			ps.setString(4, customer.getPhone());
			ps.setString(5, customer.getGender().toString());
		    ps.executeUpdate();
		    
			return true;
		});
		
		return getID();
	}
	
	public long add() 
	{
		String query = "INSERT INTO Customer (name, email, address, phone, gender) VALUES ('', '', '', '', '')";
		dbUtil.execute(query, (PreparedStatement ps) -> {
		    
			ps.executeUpdate();
			return true;
		});
		
		return getID();
	}

	public boolean delete(long id) 
	{
		String query = "DELETE FROM Customer WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
		    
			ps.setInt(1, (int) id);
			ps.executeUpdate();
			return true;
		});
	}
	
	
	public boolean update(long id, Customer newCustomer) 
	{
		String query = "UPDATE Customer SET name = ?, email = ?, address = ?, phone = ?, gender = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newCustomer.getName());
			ps.setString(2, newCustomer.getEmail());
			ps.setString(3, newCustomer.getAddress());
			ps.setString(4, newCustomer.getPhone());
			ps.setString(5, newCustomer.getGender().toString());
			ps.setInt(6, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateName(long id, String newName)
	{
		String query = "UPDATE Customer SET name = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newName);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateEmail(long id, String newEmail)
	{
		String query = "UPDATE Customer SET email = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newEmail);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updatePhone(long id, String newPhone)
	{
		String query = "UPDATE Customer SET Phone = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newPhone);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateAddress(long id, String newAddress)
	{
		String query = "UPDATE Customer SET address = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newAddress);
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	public boolean updateGender(long id, Gender newGender)
	{
		String query = "UPDATE Customer SET gender = ? WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setString(1, newGender.toString());
			ps.setInt(2, (int) id);
		    ps.executeUpdate();
		    
			return true;
		});
	}
	
	
	public Customer get(long id) 
	{
		String query = "SELECT * FROM Customer WHERE id = ?";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ps.setInt(1, (int) id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				return new Customer((long) rs.getInt("id"),
									rs.getString("name"), 
									rs.getString("email"), 
									rs.getString("address"), 
									rs.getString("phone"),
									convertGender(rs.getString("gender")));
			}
			return null;
		});
	}
	
	
	public ArrayList<Customer> getAll() 
	{
		String query = "SELECT * FROM Customer";
		return dbUtil.execute(query, (PreparedStatement ps) -> {
			
			ArrayList<Customer> customers = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				customers.add(new Customer((long) rs.getInt("id"),
											rs.getString("name"), 
											rs.getString("email"), 
											rs.getString("address"), 
											rs.getString("phone"),
											convertGender(rs.getString("gender"))));
			}
			return customers;
		});
	}
	
	private long getID()
	{
		return dbUtil.execute("SELECT MAX(id) FROM Customer", (PreparedStatement ps) -> {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				return rs.getInt("MAX(id)");
			}
			return 0;
		});
	}
	
	private Gender convertGender(String gender)
	{
		switch (gender)
		{
			case "Male":
				return Gender.Male;
			case "Female":
				return Gender.Female;
		}
		return Gender.Other;
	}
}
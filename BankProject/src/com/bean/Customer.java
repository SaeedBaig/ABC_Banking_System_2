package com.bean;


public class Customer
{
	private long customerID;
	private String name;
	private String email;
	private String address;
	private String phone;
	private Gender gender;
	
	public Customer(long id, String name, String email, String address, String phone, Gender gender)
	{
		this.customerID = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
	}
	
	public Customer(String name, String email, String address, String phone, Gender gender)
	{
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
	}
	
	public Customer() 
	{
		
	}
	
	@Override
	public String toString()
	{
		return this.customerID + " " + this.name + " " + this.email + " " + this.address + " " + this.phone;
	}
	
	//Setters
	public void setID(long id)
	{
		this.customerID = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	//Getters
	public long getID()
	{
		return this.customerID;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public String getPhone()
	{
		return this.phone;
	}
	
	public Gender getGender()
	{
		return this.gender;
	}	
}
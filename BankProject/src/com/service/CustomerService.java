package com.service;

import java.util.ArrayList;

import com.bean.Customer;
import com.bean.Gender;
import com.dao.CustomerDAO;

public class CustomerService {
	
	private CustomerDAO dao;
	
	public CustomerService()
	{
		dao = new CustomerDAO();
	}
	
	//Get all Customers
	public ArrayList<Customer> getAll() {
		
		return dao.getAll();
	}
	
	//Get Customer details based on the CustId passed as parameter
	public Customer get(long CustId) {
		
		return dao.get(CustId);
	}
	
	//Add new Customer to the application
	public long createNewCustomer(Customer e) {
		
		return dao.add(e);
	}
	
	//Delete the Customer which is passed as parameter
	public boolean delete(long CustId) {
		CustomerDAO dao=new CustomerDAO();
		return dao.delete(CustId);
	}
	
	//Update Name for the Customer passed in the parameter
	public String updateName(long CustId, String NewName) {
		String newCustomerName = null;
		CustomerDAO dao=new CustomerDAO();
		boolean t=dao.updateName(CustId, NewName);
		if(t==true) {
			Customer c = get(CustId);
			newCustomerName = c.getName();
		}
		return newCustomerName;
	}

	//Update Email for the Customer passed in the parameter
	public String updateEmail(long CustId, String NewEmail) {
		String newCustomerEmail = null;
		CustomerDAO dao=new CustomerDAO();
		boolean t=dao.updateEmail(CustId, NewEmail);
		if(t==true) {
			Customer c = get(CustId);
			newCustomerEmail = c.getEmail();
		}
		return newCustomerEmail;
	}
	
	//Update Address for the Customer passed in the parameter
	public String updateAddress(long CustId, String NewAddress) {
		String newCustomerAddress = null;
		CustomerDAO dao=new CustomerDAO();
		boolean t=dao.updateAddress(CustId, NewAddress);
		if(t==true) {
			Customer c = get(CustId);
			newCustomerAddress = c.getAddress();
		}
		return newCustomerAddress;
	}
	
	//Update Phone for the Customer passed in the parameter
	public String updatePhone(long CustId, String NewPhone) {
		String newCustomerPhone = null;
		CustomerDAO dao=new CustomerDAO();
		boolean t=dao.updatePhone(CustId, NewPhone);
		if(t==true) {
			Customer c = get(CustId);
			newCustomerPhone = c.getPhone();
		}
		return newCustomerPhone;
	}
	
	//Update Gender for the Customer passed in the parameter
	public Gender updateGender(long CustId, Gender NewGender) {
	 	Gender newCustomerGender = null;
		CustomerDAO dao=new CustomerDAO();
		boolean t=dao.updateGender(CustId, NewGender);
		if(t==true) {
			Customer c = get(CustId);
			newCustomerGender = c.getGender();
		}
		return newCustomerGender;
	}

}
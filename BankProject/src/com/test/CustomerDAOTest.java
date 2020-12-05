package com.test;

import com.bean.Customer;
import com.bean.Gender;
import com.dao.CustomerDAO;

public class CustomerDAOTest 
{
	public static void main(String[] args)
	{
		CustomerDAO cdao = new CustomerDAO();
		
		//cdao.add(new Customer("Bruno Mars", "bruno.mars@gmail", "Mars", "993724252", Gender.Other));
		
		
		cdao.getAll().forEach((c) -> System.out.println(c));
	}
}

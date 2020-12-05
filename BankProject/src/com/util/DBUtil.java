package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Callable;

public class DBUtil 
{
	private static DBUtil instance;
	private Connection connection;
	
	private final String DB_URL = "jdbc:sqlite:casestudy_db.db";
	private final String DB_USERNAME = ""; // "root";
	private final String DB_PASSWORD = "";
	
	private DBUtil()
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		}
		catch(SQLException e)
		{
			System.out.println("Saeed ERROR:"+e.getMessage());
		}
	}
	
	//Singleton class implementation
	public static DBUtil getInstance()
	{
		if (instance == null)
		{
			return new DBUtil();
		}
		return instance;
	}
	
	public interface QueryHandler<T>
	{
		public T run(PreparedStatement ps) throws SQLException;
	}
	
	public <T> T execute(String query, QueryHandler<T> handler)
	{
		try (PreparedStatement ps = connection.prepareStatement(query)) 
		{
			return handler.run(ps);
	    } 
	    catch (SQLException e) 
	    {
	    	System.out.println("SQL Execute Exception: " + e.getMessage());
	    }
		return null;
	}
}
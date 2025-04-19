package com.Bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AdminDAOImpl implements AdminDAO
{
	String url="jdbc:mysql://localhost:3307/teca_66_advance_java_project?user=root&password=2002";
	String select_query="select id from admin_details where email_id=? and password=?";

	@Override
	public boolean selectAdminDetailsByUsingEmailAndPassword(String emailid,String password) {
		
		try {		
			Connection con=DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(select_query);
			preparedstatement.setString(1,emailid);
			preparedstatement.setString(2,password);
			ResultSet rs=preparedstatement.executeQuery();
			if(rs.next())
			{
//				System.out.println("login successfull");
				return true;
			}
			else
			{
//				System.out.println("not a valid credentials");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}
}

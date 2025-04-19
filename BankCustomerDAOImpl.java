package com.Bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bank.Model.BankCustomerDetails;

public class BankCustomerDAOImpl implements BankCustomerDAO
{
	private static final String url="jdbc:mysql://localhost:3307/teca_66_advance_java_project?user=root&password=2002";
	private static final String query="insert into Bank_Customer_Details (Name, Emailid, mobile_number, aadhar_number, pan_number, date_of_birth, address, amount, age, gender,status) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String select_query="select * from Bank_Customer_Details";
	private static final String updated_accountnumber_and_pin_query="update Bank_Customer_Details set account_number=?,pin=?,status=? where Id=?";
	private static final String deletion_query="delete from Bank_Customer_Details where Id=?";
	private static final String customer_login="select * from Bank_Customer_Details where Emailid=? and pin=?";
	private static final String update_amount="update Bank_Customer_Details set amount=? where account_number=?";
	@Override
	public void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails) 
	{
		try
		{
			//Class.forName("com.mysql.cj.jdbc.DriverManager");
			Connection con=DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(query);
			preparedstatement.setString(1,bankCustomerDetails.getName());
			
			preparedstatement.setString(2,bankCustomerDetails.getEmailid());
			preparedstatement.setLong(3,bankCustomerDetails.getMobileNumber());
			preparedstatement.setLong(4,bankCustomerDetails.getAadharNumber());
			preparedstatement.setString(5,bankCustomerDetails.getPanNumber());
			preparedstatement.setDate(6,bankCustomerDetails.getDateOfBirth());
			preparedstatement.setString(7,bankCustomerDetails.getAddress());
			preparedstatement.setDouble(8,bankCustomerDetails.getAmount());
			preparedstatement.setInt(9,bankCustomerDetails.getAge());
			preparedstatement.setString(10,bankCustomerDetails.getGender());
			preparedstatement.setString(11, "Pending");
			int result=preparedstatement.executeUpdate();
			
			if(result>0)
			{
				System.out.println("registration successful");
			}
			else
			{
				System.out.println("invalid data");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public List<BankCustomerDetails> getAllBankCustomerDetails() {
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(select_query);
			ResultSet rs=preparedstatement.executeQuery();
			List<BankCustomerDetails> listofbankcustomerdetails=new ArrayList<>();
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					BankCustomerDetails bankcustomerdetails=new BankCustomerDetails();
					bankcustomerdetails.setId(rs.getInt("Id"));
					bankcustomerdetails.setName(rs.getString("Name"));					
					bankcustomerdetails.setEmailid(rs.getString("Emailid"));
					bankcustomerdetails.setAadharNumber(rs.getLong("aadhar_number"));
					bankcustomerdetails.setMobileNumber(rs.getLong("mobile_number"));
					bankcustomerdetails.setPanNumber(rs.getString("pan_number"));
					bankcustomerdetails.setStatus(rs.getString("status"));
					listofbankcustomerdetails.add(bankcustomerdetails);					
				}
				return listofbankcustomerdetails;
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public void updateAccountNumberandPinByUsingId(BankCustomerDetails bankcustomerdetails) 
	{
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(updated_accountnumber_and_pin_query);
			preparedstatement.setInt(1, bankcustomerdetails.getAccountNumber());
			preparedstatement.setInt(2,bankcustomerdetails.getPin());
			preparedstatement.setString(3,"Accepted");
			preparedstatement.setInt(4,bankcustomerdetails.getId());
			int result =preparedstatement.executeUpdate();
			if(result>0)
			{
				System.out.println("updated");
			}
			else
			{
				System.out.println("not updated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteCustomerDetailsUsingId(BankCustomerDetails bankcustomerdetails) 
	{
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(deletion_query);
			preparedstatement.setInt(1, bankcustomerdetails.getId());
			int result =preparedstatement.executeUpdate();
			if(result>0)
			{
				System.out.println("deleted");
			}
			else
			{
				System.out.println("no records found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public BankCustomerDetails selectCustomerDetailsUsingEmailidandPin(String emailid, int pin) {
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(customer_login);
			preparedstatement.setString(1, emailid);
			preparedstatement.setInt(2,pin);
			
			ResultSet rs=preparedstatement.executeQuery();
			if (rs.next())
			{
				BankCustomerDetails bankcustomerdetails=new BankCustomerDetails();
				bankcustomerdetails.setGender(rs.getString("gender"));
				bankcustomerdetails.setName(rs.getString("Name"));
				bankcustomerdetails.setAmount(rs.getDouble("amount"));
				bankcustomerdetails.setAccountNumber(rs.getInt("account_number"));
				bankcustomerdetails.setPin(rs.getInt("pin"));
				return bankcustomerdetails;
			}
			else
			{
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public boolean updateBalanceAmountByUsingAccountNumber(double updatedbalance, int accountnumber) {
		
			
			try {
				Connection con = DriverManager.getConnection(url);
				PreparedStatement preparestatement=con.prepareStatement(update_amount);
				preparestatement.setDouble(1,updatedbalance);
				preparestatement.setInt(2, accountnumber);
				int result=preparestatement.executeUpdate();
				if(result>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
	}

}

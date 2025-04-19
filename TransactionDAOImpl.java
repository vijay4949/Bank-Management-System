package com.Bank.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.Bank.Model.TransactionDetails;

public class TrancationDAOImpl implements TransactionDAO
{
	//transaction_id, transaction_type, transaction_date, transaction_time, balance_amount, transaction_amount, account_number
	private final String url="jdbc:mysql://localhost:3307/teca_66_advance_java_project?user=root&password=2002";
	private final String insert_query="insert into transaction_details (transaction_type,transaction_date, transaction_time, balance_amount, transaction_amount, account_number) values(?,?,?,?,?,?)";
	private final String select_query="select * from transaction_details where account_number=?";

	@Override
	public void insertTransactionDetails(TransactionDetails transactiondetails) {
		// TODO Auto-generated method stub
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(insert_query);
			preparedstatement.setString(1, transactiondetails.getTransactiontype());
			preparedstatement.setDate(2, Date.valueOf(transactiondetails.getTransactiondate()));
			preparedstatement.setTime(3, Time.valueOf(transactiondetails.getTransactiontime()));
			preparedstatement.setDouble(4,transactiondetails.getBalanceamount());
			preparedstatement.setDouble(5,transactiondetails.getTransactionamount());
			preparedstatement.setInt(6,transactiondetails.getAccountnumber());
			int result=preparedstatement.executeUpdate();
			if(result>0)
			{
				System.out.println("record inserted successfully");
				
			}
			else
			{
				System.out.println("problem");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<TransactionDetails> ListOfAllTheAccountTransactions(int accountnumber) {
		List<TransactionDetails> listoftransactions=null;
		
		try {
			Connection con = DriverManager.getConnection(url);
			PreparedStatement preparedstatement=con.prepareStatement(select_query);
			preparedstatement.setInt(1, accountnumber);
			ResultSet rs= preparedstatement.executeQuery();
			listoftransactions=new ArrayList<>();
			while(rs.next())
			{
				//transaction_id, transaction_type, transaction_date, transaction_time, balance_amount, transaction_amount, account_number
				TransactionDetails transactiondetails=new TransactionDetails();
				transactiondetails.setAccountnumber(accountnumber);
				transactiondetails.setBalanceamount(rs.getDouble("balance_amount"));
				transactiondetails.setTransactionamount(rs.getDouble("transaction_amount"));
				transactiondetails.setTransactionid(rs.getInt("transaction_id"));
				transactiondetails.setTransactiontype(rs.getString("transaction_type"));
				transactiondetails.setTransactiondate(rs.getDate("transaction_date").toLocalDate());
				transactiondetails.setTransactiontime(rs.getTime("transaction_time").toLocalTime());
				listoftransactions.add(transactiondetails);
			}
			return listoftransactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return listoftransactions;
		}
		
		
		
		
		
	}
	

}

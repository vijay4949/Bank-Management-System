package com.Bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.Bank.DAO.AdminDAO;
import com.Bank.DAO.AdminDAOImpl;
import com.Bank.DAO.BankCustomerDAO;
import com.Bank.DAO.BankCustomerDAOImpl;
import com.Bank.Model.BankCustomerDetails;

public class AdminServiceImpl implements AdminService
{
	BankCustomerDAO bankCustomerDAO=new BankCustomerDAOImpl();

	@Override
	public void adminLogin() 
	{
		AdminDAO admindao=new AdminDAOImpl();
		Scanner sc=new Scanner(System.in);
		System.out.println("enter email id");
		String emailid=sc.next();
		System.out.println("enter password");
		String password=sc.next();
		if(admindao.selectAdminDetailsByUsingEmailAndPassword(emailid, password))
		{
			System.out.println("enter \n 1.To get all Account Request Details \n 2.To get all User Details \n 3.To get all Account Closing Request Details");
			switch(sc.nextInt())
			{
				case 1:
					System.out.println("All Account Request Details");
					allPendingDetails();
					break;
				case 2:
					System.out.println("All User Details");
					allUserDetails();
					break;
				case 3:
					System.out.println("All Account Closing Request Details");
					break;
				default:
					System.out.println("Invalid Choice");
					break;
							
			}
			
		}else
		{
			System.out.println("Invalid Credentials");
		}
		
		
	}

	@Override
	public void allUserDetails() {
		
		List<BankCustomerDetails> allCustomerDetails=bankCustomerDAO.getAllBankCustomerDetails();
		
		allCustomerDetails.forEach((customerdetails)->{
			System.out.println("S.No "+(allCustomerDetails.indexOf(customerdetails)+1));
			System.out.println("Customer Name : "+customerdetails.getName());
			System.out.println("Customer Email : "+customerdetails.getEmailid());
			String mobilestr=""+customerdetails.getMobileNumber();
			System.out.println("Customer Mobile Numner : "+mobilestr.substring(0,3)+"****"+mobilestr.substring(7));
			String aadharstr=""+customerdetails.getAadharNumber();
			System.out.println("Aadhar Number : "+aadharstr.substring(0,3)+"******"+aadharstr.substring(9));
			
			System.out.println("************************=======================================***********************************");
		}
		);
	}

	@Override
	public void allPendingDetails() {
		List<BankCustomerDetails> allCustomerDetails=bankCustomerDAO.getAllBankCustomerDetails();
		List<BankCustomerDetails> allpendingdetailslist=new ArrayList<BankCustomerDetails>();
		
		allCustomerDetails.forEach((customerdetails)->
		{
			if(customerdetails.getStatus().equalsIgnoreCase("Pending"))
			{
				BankCustomerDetails bankcustomerdetails2=new BankCustomerDetails();
				bankcustomerdetails2.setId(customerdetails.getId());
				bankcustomerdetails2.setName(customerdetails.getName());
				bankcustomerdetails2.setEmailid(customerdetails.getEmailid());
				allpendingdetailslist.add(bankcustomerdetails2);
				System.out.println("S.No "+(allpendingdetailslist.indexOf(bankcustomerdetails2)+1));
				System.out.println("Customer Name : "+customerdetails.getName());
				System.out.println("Customer Email : "+customerdetails.getEmailid());
				System.out.println("customer mobile number : "+customerdetails.getMobileNumber());
				System.out.println("customer status : "+customerdetails.getStatus());
			}			
		});
		System.out.println("Enter S.No to Select The Customer Details");
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		BankCustomerDetails selected=allpendingdetailslist.get(num-1);
		System.out.println(selected);
		
		acceptPendingDetails(selected);
		
		
		
		
	}

	@Override
	public void acceptPendingDetails(BankCustomerDetails bankcustomerdetails) {
		Random random=new Random();
		int accountNumber=random.nextInt(10000000);
		if(accountNumber<1000000)
		{
			accountNumber+=1000000;
		}
//		System.out.println(accountNumber);
		int pin=random.nextInt(10000);
		if(pin<1000)
		{
			pin+=1000;
		}
		bankcustomerdetails.setAccountNumber(accountNumber);
		bankcustomerdetails.setPin(pin);
		bankCustomerDAO.updateAccountNumberandPinByUsingId(bankcustomerdetails);
	}

	

	
	

}

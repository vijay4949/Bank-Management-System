package com.Bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.Bank.DAO.BankCustomerDAO;
import com.Bank.DAO.BankCustomerDAOImpl;
import com.Bank.DAO.TrancationDAOImpl;
import com.Bank.Exception.BankCustomerException;
import com.Bank.Model.BankCustomerDetails;
import com.Bank.Model.TransactionDetails;
import com.Bank.DAO.TransactionDAO;


public class BankCustomerServiceImpl implements BankCustomerService 
{
	TransactionDAO transactiondao=new TrancationDAOImpl();

	BankCustomerDAO bankCustomerDAO=new BankCustomerDAOImpl();
	List<BankCustomerDetails> listofbankcustomerdetails=bankCustomerDAO.getAllBankCustomerDetails();
	private BankCustomerDetails loginpersondetails;

	Scanner scanner =new Scanner(System.in);
	@Override
	public void bankCustomerDetails() 
	{
		BankCustomerDetails bankCustomerDetails=new BankCustomerDetails();
		// name
		System.out.println("Enter Customer name :");
		String name=scanner.next();
		bankCustomerDetails.setName(name);
		
		
		// email id
		boolean emailidstatus=true;
		while (emailidstatus)
		{
			boolean emailflag=true;
			System.out.println("enter emailid");
			String emailid=scanner.next();
			try
			{
				for (BankCustomerDetails bankCustomerDetails2 : listofbankcustomerdetails) 
				{
					if(bankCustomerDetails2.getEmailid().equals(emailid))
					{
						emailflag=false;
					}
				}
				if(!emailflag)
				{
					throw new BankCustomerException("Already Emailid Existed");
				}
				else
				{
					bankCustomerDetails.setEmailid(emailid);
					emailidstatus=false;
				}
			}
			catch(BankCustomerException e)
			{
				System.out.println(e.getMsg());
			}	
		}
			
		
		// mobile number
		boolean mobilestatus=true;
		while(mobilestatus)
		{
			boolean mobileflag=true;
			System.out.println("enter customer mobile number :");
			long mobileNumber=scanner.nextLong();
			try
			{
				for (BankCustomerDetails bankCustomerDetails2 : listofbankcustomerdetails) 
				{
					if(bankCustomerDetails2.getMobileNumber()==mobileNumber)
					{
						mobileflag=false;
					}
					
				}
				if(!mobileflag)
				{
					throw new BankCustomerException("Mobile Number Already Exists");
				}
				else
				{
					bankCustomerDetails.setMobileNumber(mobileNumber);
					mobilestatus=false;
				}
			}catch(BankCustomerException b)
			{
				System.out.println(b.getMsg());
			}
		}
		
		// mobile number
		boolean aadharStatus=true;
		while(aadharStatus)
		{
			System.out.println("enter customer Aadhar number :");
			long aadharNumber=scanner.nextLong();
			boolean aadharflag=true;
			try
			{
				for (BankCustomerDetails bankCustomerDetails2: listofbankcustomerdetails)
				{
					if(bankCustomerDetails2.getAadharNumber()==aadharNumber)
					{
						aadharflag=false;
					}
				}
				if(!aadharflag)
				{
					throw new BankCustomerException("aadhar number already exists");
				}
				else
				{
					bankCustomerDetails.setAadharNumber(aadharNumber);
					aadharStatus=false;
				}
			}
			catch(BankCustomerException e)
			{
				System.out.println(e.getMsg());
			}
		}
		
		// pan number
		boolean panStatus=true;
		while(panStatus)
		{
			boolean panflag=true;
			System.out.println("Enter customer pan number (ABCDE1234Y");
			String pan=scanner.next();
			try
			{
				for (BankCustomerDetails bankCustomerDetails2:listofbankcustomerdetails)
				{
					if(bankCustomerDetails2.getPanNumber().equals(pan))
					{
						panflag=false;
					}
				}
				if(!panflag)
				{
					throw new BankCustomerException("pan number already exists");
				}
				else
				{
					bankCustomerDetails.setPanNumber(pan);
					panStatus=false;
				}
			}catch(BankCustomerException e)
			{
				System.out.println(e.getMsg());
			}
		}
		
		//date of birth
		System.out.println("Enter customer Date of Birth (YYYY-mm-dd):");
		String dateOfBirth=scanner.next();
		bankCustomerDetails.setDateOfBirth(Date.valueOf(dateOfBirth));
		// customer address
		System.out.println("Enter Customer Address :");
		String address=scanner.next();
		bankCustomerDetails.setAddress(address);
		// customer gender
		System.out.println("Enter the customer gender :");
		String gender=scanner.next();
		bankCustomerDetails.setGender(gender);
		
		System.out.println("Enter customer Age :");
		int age=scanner.nextInt();
		bankCustomerDetails.setAge(age);
		
		System.out.println("Enter Amount");
		double amount=scanner.nextDouble();
		bankCustomerDetails.setAmount(amount);
		
		bankCustomerDAO.insertBankCustomerDetails(bankCustomerDetails);
		
		
	}
	@Override
	public void customerLogin() {
		System.out.println("enter emailid");
		String emailid=scanner.next();
		System.out.println("enter pin");
		int pin=scanner.nextInt();
		 loginpersondetails=bankCustomerDAO.selectCustomerDetailsUsingEmailidandPin(emailid, pin);
		if(loginpersondetails!=null)
		{
			Boolean status=true;
			while(status)
			{
				Random random=new Random();
				//String [] arr= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9"	};
				String [] arr=new String[61];
				int index=0;
				while(index<arr.length)
				{
					for (char j='A';j<='Z';j++)
					{
						arr[index]=String.valueOf(j);
						index++;
					}
					for (char j='a';j<='z';j++)
					{
						arr[index]=String.valueOf(j);
						index++;
					}
					for (int j=1;j<=9;j++)
					{
						arr[index]=String.valueOf(j);
						index++;
					}
				}
//				System.out.println(Arrays.toString(arr));
				String captha = "";
				for (int i=0;i<6;i++)
				{
					int ind=random.nextInt(arr.length);
					captha+=arr[ind];
				}
				System.out.println("Your Captha is :"+captha);
				System.out.println("enter captha ");
				String enteredcaptha=scanner.next();
				
				if (captha.equals(enteredcaptha))
				{
					status=false;
//					System.out.println("login successfull");

					if(loginpersondetails.getGender().equalsIgnoreCase("male"))
					{
						System.out.println("hello mr "+loginpersondetails.getName());
					}
					else
					{
						System.out.println("hello miss "+loginpersondetails.getName());
					}
					bankCustomerOperations();
				}
				else
				{
					System.out.println("invalid captha  ");
				}
				
			}
		}
		else
		{
			System.out.println("invalid credentials...");
		}
		
		
	}
	@Override
	public void bankCustomerOperations()
	{
		System.out.println("enter \n 1. for credit \n 2. for debit \n 3. for check balance \n 4. for check Statement \n 5. for Update Password \n 6.for Mobile To Mobile Transaction");
		switch(scanner.nextInt())
		{
			case 1:
				System.out.println("credit");
				credit();
				break;
			case 2:
				System.out.println("debit");
				Debit();
				break;
			case 3:
				System.out.println("check balance");
				checkBalance();
				break;
			case 4:
				System.out.println("check statement");
				Statement();
				break;
			case 5:
				System.out.println("update pin ");
				break;
			case 6:
				System.out.println("mobile to mobile Transaction");
				break;
			default:
				System.out.println("Invalid choice");
			
		}
	}
	@Override
	public void Debit()
	{
		System.out.println("enter amount to Debit");
		double useramount=scanner.nextDouble();
		if(useramount>=0)
		{
			double balanceamount=loginpersondetails.getAmount();
			int accountnumber=loginpersondetails.getAccountNumber();
			if(balanceamount>=useramount)
			{
				double remaining=balanceamount-useramount;
				System.out.println(remaining);
				if(bankCustomerDAO.updateBalanceAmountByUsingAccountNumber(remaining, accountnumber))
				{
					System.out.println("amount Debited");
					TransactionDetails transactiondetails=new TransactionDetails();
					transactiondetails.setAccountnumber(accountnumber);
					transactiondetails.setBalanceamount(remaining);
					transactiondetails.setTransactionamount(useramount);
					transactiondetails.setTransactiondate(LocalDate.now());
					transactiondetails.setTransactiontime(LocalTime.now());
					transactiondetails.setTransactiontype("Debit");
					transactiondao.insertTransactionDetails(transactiondetails);
//					System.out.println(transactiondetails);
//					
					
				}
				else
				{
					System.out.println("server 404 ");
				}
			}
			else
			{
				System.out.println("Insuffient Balance");
			}
		}
		else
		{
			System.out.println("Invalid Amount");
		}
	}
	@Override
	public void Statement() {
		int accountnumber=loginpersondetails.getAccountNumber();
		List<TransactionDetails> listoftransactions= transactiondao.ListOfAllTheAccountTransactions(accountnumber);
		System.out.println("transactionid \t account number \t balance \t transaction amount \t transaction type");
		System.out.println("===================================================================================================================");
		listoftransactions.forEach((transactiondetails)->{
			System.out.print(transactiondetails.getTransactionid()+"\t\t   ");
			System.out.print(transactiondetails.getAccountnumber()+"\t\t  ");
			System.out.print(transactiondetails.getBalanceamount()+"\t\t");
			System.out.print(transactiondetails.getTransactionamount()+"\t\t   ");
			System.out.println(transactiondetails.getTransactiontype());
			System.out.println("================================================================================================================");
			
		});
			
		
		
	}
	@Override
	public void credit() {
		int accountnumber=loginpersondetails.getAccountNumber();
		System.out.println("enter amount to credit");
		double creditamount=scanner.nextDouble();
		double updatedbalance=loginpersondetails.getAmount()+creditamount;
		if(bankCustomerDAO.updateBalanceAmountByUsingAccountNumber(updatedbalance, accountnumber))
		{
			System.out.println("amount Credited");
			TransactionDetails transactiondetails=new TransactionDetails();
			transactiondetails.setAccountnumber(accountnumber);
			transactiondetails.setBalanceamount(updatedbalance);
			transactiondetails.setTransactionamount(creditamount);
			transactiondetails.setTransactiondate(LocalDate.now());
			transactiondetails.setTransactiontime(LocalTime.now());
			transactiondetails.setTransactiontype("Credit");
			transactiondao.insertTransactionDetails(transactiondetails);
//			System.out.println(transactiondetails);
//			
			
		}
		else
		{
			System.out.println("Server 404");
		}

	}
	@Override
	public void checkBalance() {
		System.out.println("enter pin");
		int userpin=scanner.nextInt();
//		System.out.println(loginpersondetails.getPin());
		if(loginpersondetails.getPin()==userpin)
		{
			System.out.println("your balance is "+loginpersondetails.getAmount());
		}
		else
		{
			System.out.println("Invalid pin ");
		}

	}

}

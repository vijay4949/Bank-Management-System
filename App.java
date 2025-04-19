package com.Bank;

import java.util.Scanner;

import com.Bank.service.AdminService;
import com.Bank.service.AdminServiceImpl;
import com.Bank.service.BankCustomerService;
import com.Bank.service.BankCustomerServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Scanner scan=new Scanner(System.in);
    	boolean status =true;
    	BankCustomerService bankcustomerservice=new BankCustomerServiceImpl();
    	AdminService adminservice=new AdminServiceImpl();
    	while(status)
    	{
    		System.out.println("Enter \n 1 for Customer Registration \n 2 for Customer Login \n 3 for Admin Login");
	    	switch(scan.nextInt())
	    	{
	    		case 1:
	    			System.out.println("Customer Registration");
	    			bankcustomerservice.bankCustomerDetails();
	    			break;
	    		case 2:
	    			System.out.println("Customer Login");
	    			bankcustomerservice.customerLogin();
	    			break;
	    		case 3:
	    			System.out.println("Admin Login");
	    			adminservice.adminLogin();
	    			break;
	    		default:
	    			System.out.println("Invalid Request");
	    			break;
	    	}
	    	System.out.println("Do You want to Continue \n YES \t No");
	    	if(!(scan.next().equalsIgnoreCase("yes")))
	    	{
	    		System.out.println("**************Thank You ****************");
	    		status =false;
	    	}
    	}


    }
}

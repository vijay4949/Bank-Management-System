package com.Bank.service;

import com.Bank.Model.BankCustomerDetails;

public interface AdminService 
{
	void adminLogin();
	void allUserDetails();
	void allPendingDetails();
//	void acceptPendingDetails();
	//void getCustomerDetails();
	void acceptPendingDetails(BankCustomerDetails bankcustomerdetails);

}

package com.Bank.DAO;

import java.util.List;

import com.Bank.Model.BankCustomerDetails;

public interface BankCustomerDAO 
{
	void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails);
	List<BankCustomerDetails> getAllBankCustomerDetails();
//	void updateAccountNumberandPinByUsingId();
	void updateAccountNumberandPinByUsingId(BankCustomerDetails bankcustomerdetails);
	void deleteCustomerDetailsUsingId(BankCustomerDetails bankcustomerdetails);
	BankCustomerDetails selectCustomerDetailsUsingEmailidandPin(String emailid,int pin);
	boolean updateBalanceAmountByUsingAccountNumber(double remaining,int accountnumber);
}


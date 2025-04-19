package com.Bank.DAO;

import java.util.List;

import com.Bank.Model.TransactionDetails;

public interface TransactionDAO 
{
	void insertTransactionDetails(TransactionDetails transactiondetails);
	List<TransactionDetails> ListOfAllTheAccountTransactions(int accountnumber);
}

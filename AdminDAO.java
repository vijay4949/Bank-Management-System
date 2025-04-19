package com.Bank.DAO;

public interface AdminDAO {
	boolean selectAdminDetailsByUsingEmailAndPassword(String emailid,String password);
	
}

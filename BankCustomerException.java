package com.Bank.Exception;

public class BankCustomerException extends RuntimeException
{
	private String msg;
	BankCustomerException()
	{
		
	}
	

	public BankCustomerException(String msg) {
		super();
		this.msg = msg;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

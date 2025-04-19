package com.Bank.Model;

import java.sql.Date;

public class BankCustomerDetails 
{
	private int id;
	private String name;
	private String emailid;
	private long mobileNumber;
	private long aadharNumber;
	private String panNumber;
	private int accountNumber;
	private int pin;
	private Date dateOfBirth;
	private String address;
	private double amount;
	private int age;
	private String gender;
	private String Status; 
	
	public BankCustomerDetails()
	{
		
	}
	
	

	public BankCustomerDetails(int id, String name, String emailid, long mobileNumber, long aadharNumber,
			String panNumber, int accountNumber, int pin, Date dateOfBirth, String address, double amount, int age,
			String gender, String status) {
		super();
		this.id = id;
		this.name = name;
		this.emailid = emailid;
		this.mobileNumber = mobileNumber;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.amount = amount;
		this.age = age;
		this.gender = gender;
		Status = status;
	}



	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "BankCustomerDetails [id=" + id + ", name=" + name + ", emailid=" + emailid + ", mobileNumber="
				+ mobileNumber + ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + ", accountNumber="
				+ accountNumber + ", pin=" + pin + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", amount="
				+ amount + ", age=" + age + ", gender=" + gender + ", Status=" + Status + "]";
	}
	
	
}

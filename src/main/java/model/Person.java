package model;

import java.sql.Date;

public class Person {

	protected String name;
	protected String lastname;
	protected String bloodType;
	protected Date birthday;
	protected String phoneNumber;
	protected String email; // CONVERTER TO DO 
	protected String adress;
	protected Boolean isActive;
	protected String qrdetail;
	
	protected Person(String name, String lastname, String bloodType, Date birthday, String phoneNumber,
			String email, String adress,Boolean isActive) {
		this.name = name;
		this.lastname = lastname;
		this.bloodType = bloodType;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.adress = adress;
		this.isActive = isActive;
	}

	public Person() {
		name = "";
		lastname = "";
		bloodType = "";
		birthday = null;
		phoneNumber = "";
		email = "";
		adress = "";
		isActive = true;
		qrdetail = "";
	}

	public String getQrdetail() {
		return qrdetail;
	}

	public void setQrdetail(String qrdetail) {
		this.qrdetail = qrdetail;
	}
}

package model;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import controller.QrGenerator;
import service.MySqlDaoClient;
import service.Idaos.InterfaceClientDao;



public class Client extends Person{
	private int id;
	private int idrate;
	private Date firstClassDate;
	private Date lastClassDate;
	private String qrdetial;
	
	@Autowired
	QrGenerator qrgenerator;
	
	public Client() {
		super();
		id = 0;
		idrate = 0;
		firstClassDate = null;
		lastClassDate = null;		
	}
	
	// CONSTRUCTOR PARA TRAER DE BDD (CON ID Y QRDETAIL YA GENERADOS EN PERCISTENCIA)
	public Client(String name, String lastname, String bloodType, Date birthday, String phoneNumber,
			String email, String adress,Boolean isActive, int id, int idrate, Date firstClassDate, Date lastClassDate, String qrdetail) {
		super(name,lastname,bloodType,birthday,phoneNumber,email,adress,isActive);
		this.id = id;
		this.idrate = idrate;
		this.firstClassDate = firstClassDate;
		this.lastClassDate = lastClassDate;
		super.qrdetail = qrdetail;
	}
	
	
	// CONSTRUCTOR DE CREACION PARA ALMACENAR EN BDD (SIN ID Y QRDETAIL QUE SE GENERAN EN PERSISTENCIA)
	public Client(String name, String lastname, String bloodType, Date birthday, String phoneNumber,
			String email, String adress,Boolean isActive, int idrate, Date firstClassDate, Date lastClassDate) {
		super(name,lastname,bloodType,birthday,phoneNumber,email,adress,isActive);
		this.idrate = idrate;
		this.firstClassDate = firstClassDate;
		this.lastClassDate = lastClassDate;
	}
	
	
	public String setQrDetail(int id , String name , String lastname) {
		Integer i = new Integer(id);
		this.qrdetail = i.toString() + name + lastname;
		try {
			this.qrgenerator.mapQRDetToQR(qrdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getId() {
		return id;
	}

	public int getIdRate() {
		return this.idrate;
	}
	
	public Date getFirstClassDate() {
		return firstClassDate;
	}

	public Date getLastClassDate() {
		return lastClassDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}
	
	
	// super getters
	public String getName() {
		return super.name;
	}
	
	public String getLastname() {
		return super.lastname;
	}
	
	public String getBloodType() {
		return super.bloodType;
	}
	
	public Date getBirthday() {		
		return super.birthday;
	}
	
	public String getPhoneNumber() {
		return super.phoneNumber;
	}
	
	public String getEmail() {
		return super.email;
	}
	
	public String getAdress() {
		return super.adress;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIdRate(int idrate) {
		this.idrate = idrate;
	}
	
	public void setFirstClassDate(Date firstClassDate) {
		this.firstClassDate = firstClassDate;
	}

	public void setLastClassDate(Date lastClassDate) {
		this.lastClassDate = lastClassDate;
	}

	public void setName(String name) {
		super.name = name;
	}
	
	public void setLastname(String lastname) {
		super.lastname = lastname;
	}
	
	public void setBloodtype(String bloodtype) {
		super.bloodType = bloodtype;
	}
	
	public void setBirthday(Date birthday) {
		super.birthday = birthday;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		super.phoneNumber = phoneNumber;
	}
	
	public void setEmail(String email) {
		super.email = email;
	}
	
	public void setAdress(String adress) {
		super.adress = adress;
	}

	public void setActive(Boolean status) {
		super.isActive = status;
	}
	
}

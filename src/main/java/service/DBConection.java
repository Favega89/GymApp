package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
	private Connection myConn;
	private static DBConection instance;
	
	
	//Singleton
	public static DBConection getInstance(){
		if(instance == null) {
			instance = new DBConection();
		}
		return instance;
	}
	
	public DBConection() {
		try{
			this.checkDriver();
		}catch(ClassNotFoundException e) {
				e.printStackTrace();
		}		
	}
	
	private void checkDriver() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		}catch(ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
			throw e;
		}
	}
	
	public void Conect() throws SQLException {
		try {
            setMyConection(DriverManager.getConnection("jdbc:mysql://localhost:3306/gymdbv1?autoReconnect=true&useSSL=false" , "root" , "1234" ));
		}catch(SQLException e) {
			System.err.print("SQLE: " + e.getMessage());
		}
	}
	
	 public void Disconect() throws Exception{
	        try{
	            if (this.getMyConection() != null){
	                this.getMyConection().close();
	            }
	        }catch (Exception e){
	            throw e;
	        }
	    }
	    public Connection getMyConection() {
	        return myConn;
	    }
	    private void setMyConection(Connection myConection) {
	        this.myConn = myConection;
	    }
}

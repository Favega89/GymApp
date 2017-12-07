package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import model.Client;
import service.Idaos.InterfaceClientDao;

@Service
public class MySqlDaoClient implements InterfaceClientDao{
	private DBConection miConeccion = DBConection.getInstance(); // DATABASE CONECCION INSTANCE.

	 private final String sGetAll = "select * from CLIENTE";
	 private final String sGetOne = "select * from CLIENTE where nombre = ?";
	 private final String sGetById = "select * from CLIENTE where ID_USUARIO = ?";
	 private final String sAdd = "INSERT INTO CLIENTE(IDRATE,"
	 		+ "FIRSTNAME,LASTNAME,INITIAL_DATE,FINAL_DATE,BIRTHDAY,EMAIL,"
	 		+ "PHONE_NUMBER,BLOODTYPE,ADRESS,ISACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	 private final String sModify = "update CLIENTE set IDRATE = ? , FIRSTNAME = ? , LASTNAME = ? ,"
	 		+ " INITIAL_DATE = ? , FINAL_DATE = ? , BIRTHDAY = ? , EMAIL = ? , PHONE_NUMBER = ? , "
	 		+ " BLOODTYPE = ? , ADRESS = ? , ISACTIVE = ? where ID_USUARIO = ?";
	 private final String sUnactives = "select * from Cliente where isActive = true";
	// private final String sDelete = "delete from CLIENT where ID = ?";

	@Override
	public void add(Client n) {
		try {
			miConeccion.Conect();
           PreparedStatement p = (PreparedStatement) miConeccion.getMyConection().prepareStatement(sAdd,Statement.RETURN_GENERATED_KEYS);	
           	p.setInt(1,n.getIdRate());
			p.setString(2, n.getName());
			p.setString(3, n.getLastname());
			p.setDate(4, n.getFirstClassDate());
			p.setDate(5, n.getLastClassDate());
			p.setDate(6, n.getBirthday());
			p.setString(7, n.getEmail());
			p.setString(8, n.getPhoneNumber());
			p.setString(9, n.getBloodType());
			p.setString(10,n.getAdress());
			p.setBoolean(11, n.getIsActive());
			p.executeUpdate(); 
			ResultSet rs = p.getGeneratedKeys();
			rs.next();
			int key = rs.getInt(1);
			this.getbyId(key);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				miConeccion.Disconect();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public Client getbyId(int i) {
		Client c = new Client();
		try {
			PreparedStatement st = (PreparedStatement)miConeccion.getMyConection().prepareStatement(sGetById);
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			if(rs == null) {
				//TODO en controlladora
			} 
			c.setId(rs.getInt("ID_USUARIO"));
			c.setIdRate(rs.getInt("IDRATE"));
			c.setName(rs.getString("FIRSTNAME"));
			c.setLastname(rs.getString("LASTNAME"));
			c.setFirstClassDate(rs.getDate("INITIAL_DATE"));
			c.setLastClassDate(rs.getDate("FINAL_DATE"));
			c.setBirthday(rs.getDate("BIRTHDAY"));
			c.setEmail(rs.getString("EMAIL"));
			c.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			c.setBloodtype(rs.getString("BLOODTYPE"));
			c.setAdress(rs.getString("ADRESS"));
			c.setActive(rs.getBoolean("isActive"));
			c.setQrDetail(rs.getInt("ID_USUARIO"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"));
		}catch (SQLException e) {
           e.printStackTrace();
       } 
		return c;
	}

	@Override
	public void modify(Integer id,Client n) {
		int auxId = (int)id;
		PreparedStatement ps = null;
		try {
			miConeccion.Conect();
			ps = (PreparedStatement)miConeccion.getMyConection().prepareStatement(sModify);
			ps.setInt(1,n.getIdRate());
			ps.setString(2, n.getName());
			ps.setString(3, n.getLastname());
			ps.setDate(4, n.getFirstClassDate());
			ps.setDate(5, n.getLastClassDate());
			ps.setDate(6, n.getBirthday());
			ps.setString(7, n.getEmail());
			ps.setString(8, n.getPhoneNumber());
			ps.setString(9, n.getBloodType());
			ps.setString(10,n.getAdress());
			ps.setBoolean(11, n.getIsActive());
			ps.setInt(12,auxId);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			try {
               miConeccion.Disconect();
           } catch (Exception e) {
               e.printStackTrace();
           }
		}
	}

	@Override
	public void delete(Client a, Integer i) {
	/*	try {
			miConeccion.Conect();
			PreparedStatement st = (PreparedStatement) miConeccion.getMyConection().prepareStatement(sDelete);
		}catch(SQLException e) {
			
		}
	*/
	}

	@Override
	public ArrayList<Client> getAll() {
		ArrayList<Client> myClientsCollection = new ArrayList<Client>();
		try {
			miConeccion.Conect();
			PreparedStatement st = (PreparedStatement) miConeccion.getMyConection().prepareStatement(sGetAll);
			ResultSet result = st.executeQuery();
			if(result == null) {
				// TO DO en controlladora
			}
			while(result.next()) {
				Client c = new Client();
				c.setId(result.getInt("ID_USUARIO"));
				c.setIdRate(result.getInt("IDRATE"));
				c.setName(result.getString("FIRSTNAME"));
				c.setLastname(result.getString("LASTNAME"));
				c.setFirstClassDate(result.getDate("INITIAL_DATE"));
				c.setLastClassDate(result.getDate("FINAL_DATE"));
				c.setBirthday(result.getDate("BIRTHDAY"));
				c.setEmail(result.getString("EMAIL"));
				c.setPhoneNumber(result.getString("PHONE_NUMBER"));
				c.setBloodtype(result.getString("BLOODTYPE"));
				c.setAdress(result.getString("ADRESS"));
				c.setActive(result.getBoolean("isActive"));
				myClientsCollection.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				miConeccion.Disconect();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return myClientsCollection;
	}
	public ArrayList<Client> getAllUnactives() {
		ArrayList<Client> myClientsCollection = new ArrayList<Client>();
		try {
			miConeccion.Conect();
			PreparedStatement st = (PreparedStatement) miConeccion.getMyConection().prepareStatement(sUnactives);
			ResultSet result = st.executeQuery();
			if(result == null) {
				// TO DO en controlladora
			}
			while(result.next()) {
				Client c = new Client();
				c.setId(result.getInt("ID_USUARIO"));
				c.setIdRate(result.getInt("IDRATE"));
				c.setName(result.getString("FIRSTNAME"));
				c.setLastname(result.getString("LASTNAME"));
				c.setFirstClassDate(result.getDate("INITIAL_DATE"));
				c.setLastClassDate(result.getDate("FINAL_DATE"));
				c.setBirthday(result.getDate("BIRTHDAY"));
				c.setEmail(result.getString("EMAIL"));
				c.setPhoneNumber(result.getString("PHONE_NUMBER"));
				c.setBloodtype(result.getString("BLOODTYPE"));
				c.setAdress(result.getString("ADRESS"));
				c.setActive(result.getBoolean("isActive"));
				myClientsCollection.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				miConeccion.Disconect();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return myClientsCollection;
	}
	@Override// PROBABLEMENTE SEA INNECESARIO (ES = a GET ALL)
	public ArrayList<Client> get(String s) {
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			miConeccion.Conect();
			PreparedStatement st = (PreparedStatement)miConeccion.getMyConection().prepareStatement(sGetOne);
			st.setString(1, s);
			ResultSet rs = st.executeQuery();
			if(rs == null) {
				//TODO en controlladora
			}
			while(rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt("ID_USUARIO"));
				c.setIdRate(rs.getInt("IDRATE"));
				c.setName(rs.getString("FIRSTNAME"));
				c.setLastname(rs.getString("LASTNAME"));
				c.setFirstClassDate(rs.getDate("INITIAL_DATE"));
				c.setLastClassDate(rs.getDate("FINAL_DATE"));
				c.setBirthday(rs.getDate("BIRTHDAY"));
				c.setEmail(rs.getString("EMAIL"));
				c.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				c.setBloodtype(rs.getString("BLOODTYPE"));
				c.setAdress(rs.getString("ADRESS"));
				c.setActive(rs.getBoolean("isActive"));
				clients.add(c);
			}
		}catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
               miConeccion.Disconect();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
		return clients;
	}
	
	
}

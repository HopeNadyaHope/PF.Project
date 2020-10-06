package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lobanok.dao.RegistrationDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.RegistrationData;

public class RegistrationDAOImpl implements RegistrationDAO {
	
	private final ConnectionPool pool = ConnectionPool.getInstance();
	
	private final String FIND_ROLE_ID = "SELECT roles.id FROM roles WHERE roles.role=?";	
	private final String ADD_USER = "INSERT INTO users(login, password, name, surname, age, sex, email, roles_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";	
	private final String FIND_LOGIN ="SELECT users.login FROM users WHERE login=?";

	
	@Override
	public boolean registration(RegistrationData regData)  throws DAOException{		
		boolean registration;
		registration = false;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			con = pool.takeConnection();			
			
			ps = con.prepareStatement(FIND_ROLE_ID); ///////не уверена что именно так нужно опеределять роль
			ps.setString(1, regData.getRole());
			result = ps.executeQuery();	
			if(!result.next()) {
				return false;///////////////////////////
			}	
			int role_id;
			role_id = result.getInt("id");
			
			ps = con.prepareStatement(ADD_USER); 
			ps.setString(1, regData.getLogin());
			ps.setString(2, regData.getPassword());
			ps.setString(3, regData.getName());
			ps.setString(4, regData.getSurname());
			ps.setInt(5, regData.getAge()); 
			ps.setString(6, regData.getSex());
			ps.setString(7, regData.getEmail());
			ps.setInt(8, role_id);
			if(ps.executeUpdate() == 1) {
				registration = true;
			}			
		}catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
		
		return registration;
	}
	
	
	public boolean findLogin(String login)  throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try { 
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_LOGIN);			
			ps.setString(1, login);
			result = ps.executeQuery();
			
			if(result.next()) {
               return true;
			}
			
		}catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps, result);
        }
		return false;
	}
}

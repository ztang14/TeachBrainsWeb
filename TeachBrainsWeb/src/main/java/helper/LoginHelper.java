package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;
import model.DAOImpl;



public class LoginHelper 
{
	DAOImpl dao = new DAOImpl();
	Connection con = dao.getConnection();
	
	public String checkLogin(String userName)
	{
		String query = "SELECT password FROM login where username = ?";
		String password = null;
		
		try {
			
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, userName);
			ResultSet rs = dao.selectData(psmt);
			
			if(rs.next())
			{
				password = rs.getString(1);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		return password;
	}
	
	public boolean registerUser(User user)
	{
		String query = "INSERT INTO login(username, password, email)"
						+ " values(?,?,?)";
		
		
		try {
			
			PreparedStatement psmt = con.prepareStatement(query);
			
			psmt.setString(1, user.getUsername());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getEmail());
			
			if(dao.insertData(psmt) > 0)
				return true;
			
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}

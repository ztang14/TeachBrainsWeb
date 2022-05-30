package model;

import java.sql.*;

public class DAOImpl {

	
	private static Connection con = null;
	private static Statement smt = null;
	private static PreparedStatement psmt = null;
	private static String query = null;
	private static ResultSet rs = null;
	
	public DAOImpl()
	{
		generateConnection();
	}
	
	public void generateConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechBrains", "root", "root");
			
			smt = con.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return con;
	}
	
	public int insertData(PreparedStatement psmt)
	{
		
		int result = 0;
		try {
				
			result = psmt.executeUpdate();
			System.out.println("Inserted rows : " + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int updateData(PreparedStatement psmt)
	{
		int result = 0;
		try {
				
			result = psmt.executeUpdate();
			System.out.println("Updated rows : " + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public void deleteData(int id)
	{
		query = "DELETE FROM paypal where id = ?";
		
		try {
			
			psmt = con.prepareStatement(query);
			
			psmt.setInt(1, id);
			
			System.out.println("Deleted rows : " + psmt.executeUpdate());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet selectData(PreparedStatement psmt)
	{
		
		try {
			
			rs = psmt.executeQuery();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void closeConnection()
	{
		try {
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


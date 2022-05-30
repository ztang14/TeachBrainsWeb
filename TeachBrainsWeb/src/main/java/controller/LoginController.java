package controller;


import entities.User;
import helper.LoginHelper;

public class LoginController 
{

	LoginHelper helper = new LoginHelper();
	String result = null;
	
	
	public boolean login(String userName, String password)
	{
		String result = helper.checkLogin(userName);
		if(result != null && result.equals(password))
		{
			return true;	
		}
		else
			return false;

	}
	
	public boolean register(String email, String username, String password, String cfPassword) 
	{
		if(password.equals(cfPassword))
		{
			User user = new User();
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			
			if(helper.registerUser(user))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		else
		{
			return false;
		}
	}	
		
	public boolean sellerLogin(String userName, String password)
		{
			if(userName == "root" && password == "root")
			{
			   return true;
						
			}
			else
			{
			   return false;
			}
		}
		
}

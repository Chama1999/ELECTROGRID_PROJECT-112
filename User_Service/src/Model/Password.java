package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Password {
	
	//creating connection
		public Connection connect()
		{
			Connection con = null;

			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid","root", "");
				//For testing
				System.out.print("Successfully connected");
				 }
				catch(Exception e)
				 {
				 	e.printStackTrace();
				  }

			return con;
			}
				
		public String ForgotPassword(String userId,String password) 
		{
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for validation"; 
				}
							
				String query = "UPDATE user SET password=? WHERE userId=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				// binding values
				preparedStmt.setString(1,password);//newpassword to be set is passed
				preparedStmt.setString(2,userId);//usercode sent through email is passed
		
				preparedStmt.execute();
				con.close();
				return "Password changed Successfully";
				
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		return "Error while changing the Password";
		}
		
		//validate the user login details
		public String validateUserLogin(String userId ,String username, String password) 
		{
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for validation"; 
				}
							
				String query = "select userId ,username, password from user";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
							
				while(rs.next())
				{
					String UserId = rs.getString("userId");
					String userN = rs.getString("username");
					String pass = rs.getString("password");
					
								
					if(userId.equals(UserId) && username.equals(userN) && password.equals(pass))
					{
						
							return "Welcome "+ username;
					}		
				}
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
				return "UserId ,Username or Password is incorrect";
			}

}

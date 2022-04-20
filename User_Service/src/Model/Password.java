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
				
		public String ForgotPassword(String pincode,String password) 
		{
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for validation"; 
				}
							
				String query = "UPDATE user SET password=? WHERE pincode=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				// binding values
				preparedStmt.setString(1,password);//newpassword to be set is passed
				preparedStmt.setString(2,pincode);//usercode sent through email is passed
		
				preparedStmt.execute();
				con.close();
				return "Password reseted Successfully";
				
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		return "Error while Reseting the Password";
		}
		
		//validate the user login details
		public String validateUserLogin(String userName, String Password) 
		{
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for validation"; 
				}
							
				String query = "select username, password from user";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
							
				while(rs.next())
				{
					String userN = rs.getString("username");
					String pass = rs.getString("password");
					
								
					if(userName.equals(userN) && Password.equals(pass))
					{
						
							return "Welcome "+ userName;
					}		
				}
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
				return "incorrect Username or password";
			}

}

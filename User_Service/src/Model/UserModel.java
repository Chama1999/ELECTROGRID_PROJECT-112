package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserModel {

	//creating connection for the database
			public Connection connect()
			{
				Connection con = null;

				try
				{
				 Class.forName("com.mysql.jdbc.Driver");
				 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid","root", "");
				 
				 //For testing whether it is connected with the database
				 
				 System.out.print("Successfully connected with the database");
			 	}
				catch(Exception e)
			 	{
			 		e.printStackTrace();
				 }

			return con;
			}
			
			
			public String RegisterUser(String accountNo,String name,String address,String NIC, String email, String phone,String userType, String username, String password) {
			   	
				String output = "";
						try
						 { Connection con = connect();
						 if (con == null)
						 {
						 return "Error while connecting to the database";
						 }
				    		
				    	    String sql = "insert into user(`userId`,`accountNo`,`name`,`address`,`NIC`,`email`,`phone`,`userType`,`username`,`password`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
				    	    PreparedStatement preparedStmt = con.prepareStatement(sql);
							 // binding values
							 preparedStmt.setInt(1, 0);
							 preparedStmt.setString(2, accountNo);
							 preparedStmt.setString(3, name);
							 preparedStmt.setString(4, address);
							 preparedStmt.setString(5, NIC);
							 preparedStmt.setString(6, email); 
							 preparedStmt.setString(7, phone);
							 preparedStmt.setString(8, userType);
							 preparedStmt.setString(9, username); 
							 preparedStmt.setString(10, password); 
							//execute the statement
							 preparedStmt.execute();
							 con.close();
							 output = "Inserted successfully";
				    		
				    	}
					catch (Exception e)
					{
					output = "Error while inserting";
					System.err.println(e.getMessage());
					}
			return output;
			}
			
}

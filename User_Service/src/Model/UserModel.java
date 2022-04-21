package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

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
			
			
			public String RegisterUser(String accountNo,String pincode,String name,String address,String NIC, String email, String phone, String username, String password) {
			   	
				String output = "";
						try
						 { Connection con = connect();
						 if (con == null)
						 {
						 return "Error while connecting to the database";
						 }
				    		
				    	    String sql = "insert into user(`userId`,`accountNo`,`pincode`,`name`,`address`,`NIC`,`email`,`phone`,`username`,`password`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
				    	    PreparedStatement preparedStmt = con.prepareStatement(sql);
							 // binding values
							 preparedStmt.setInt(1, 0);
							 preparedStmt.setString(2, accountNo);
							 preparedStmt.setString(3, pincode);
							 preparedStmt.setString(4, name);
							 preparedStmt.setString(5, address);
							 preparedStmt.setString(6, NIC);
							 preparedStmt.setString(7, email); 
							 preparedStmt.setString(8, phone);
							 preparedStmt.setString(9, username); 
							 preparedStmt.setString(10, password); 
							//execute the statement
							 preparedStmt.execute();
							 con.close();
							 output = "User data Inserted successfully";
				    		
				    	}
					catch (Exception e)
					{
					output = "Error while inserting";
					System.err.println(e.getMessage());
					}
			return output;
			}
		
			
			public String readUserDetails()
			
			{
				 String output = "";
				try
				 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for reading.";
				 }
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Account Number</th>"
				 +"<th>Pin code</th>"
				 +"<th>Name</th><th>Address</th><th>NIC</th>"
				 + "<th>Email</th>"
				 + "<th>Phone</th>"
				 + "<th>UserName</th>"
				 + "<th>Password</th>";
				 String query = "select * from user";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 // iterate through the rows in the result set
				 while (rs.next())
				 {
					 String userId= Integer.toString(rs.getInt("userId"));
					 String accountNo = rs.getString("accountNo");
					 String pincode = rs.getString("pincode");
					 String name = rs.getString("name");
					 String address = rs.getString("address");
					 String NIC = rs.getString("NIC");
					 String email = rs.getString("email");
					 String phone = rs.getString("phone");
					 String username = rs.getString("username");
					 String password = rs.getString("password");
					 // Add a row into the html table
					 output += "<tr><td>" + accountNo + "</td>";
					 output += "<td>" + pincode + "</td>";
					 output += "<td>" + name + "</td>";
					 output += "<td>" + address + "</td>";
					 output += "<td>" + NIC + "</td>";
					 output += "<td>" + email + "</td>"; 
					 output += "<td>" + phone + "</td>";
					 output += "<td>" + username + "</td>";
					 output += "<td>" + password + "</td>";
					 // buttons
					 output += "<input name='itemID' type='hidden' "
					 + " value='" + userId + "'>"
					 + "</form></td></tr>";
				 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
				 }
				catch (Exception e)
				 {
					 output = "Error while reading the user details"
					 		+ ".";
					 System.err.println(e.getMessage());
				 }
				return output;
			}
			
			 public String getUserDetails(String userID)
				
				{
					 String output = "";
					try
					 {
					 Connection con = connect();
					 if (con == null)
					 {
						 return "Error while connecting to the database for reading";
					 }
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Account No</th>"
					 + "<th>Pin code</th>"
					 +"<th>Name</th><th>NIC</th>"
					 + "<th>Email</th>"
					 + "<th>Phone</th>"
					 + "<th>UserName</th>"
					 + "<th>Password</th>";
					 String query = "select * from user where userId='"+userID+"'";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 // iterate through the rows in the result set
					 while (rs.next())
					 { 
						 String userId = Integer.toString(rs.getInt("userId"));
						 String accountNo = rs.getString("accountNo");
						 String pincode = rs.getString("pincode");
						 String name = rs.getString("name");
						 String NIC = rs.getString("NIC");
						 String email = rs.getString("email");
						 String phone = rs.getString("phone");
						 String username = rs.getString("username");
						 String password = rs.getString("password");
						 // Add a row into the html table
						 output += "<tr><td>" + accountNo + "</td>";
						 output += "<td>" + pincode + "</td>";
						 output += "<td>" + name + "</td>";
						 output += "<td>" + NIC + "</td>";
						 output += "<td>" + email + "</td>"; 
						 output += "<td>" + phone + "</td>";
						 output += "<td>" + username + "</td>";
						 output += "<td>" + password + "</td>";
						 // buttons
						 output += "<input name='itemID' type='hidden' "
						 + " value='" + userId + "'>"
						 + "</form></td></tr>";
					 }
					 con.close();
					 // Complete the html table
					 output += "</table>";
					 
					 }
					catch (Exception e)
					 {
						 output = "Error while reading the user details";
						 System.err.println(e.getMessage());
					 }
					return output;
				}
			 
			 public String EditUserDetails(String userId,String accountNo,String pincode,String name,String address,String NIC, String email, String phone, String username, String password)
			   {
				   String output = "";
				   try
					   {
					   Connection con = connect();
					   if (con == null)
					   {
						   return "Error while connecting to the database for updating"; 
					   }
					   // create a prepared statement
					   String query = "UPDATE user SET accountNo=?,pincode=?,address=?,name=?,NIC=?,email=?,phone=?,username=?,password=?WHERE userId=?";
					   PreparedStatement preparedStmt = con.prepareStatement(query);
					   // binding values
					   preparedStmt.setString(1, accountNo);
					   preparedStmt.setString(2, pincode);
					   preparedStmt.setString(3, name);
					   preparedStmt.setString(4, address);
					   preparedStmt.setString(5, NIC);
					   preparedStmt.setString(6, email);
					   preparedStmt.setString(7, phone);
					   preparedStmt.setString(8, username);
					   preparedStmt.setString(9, password);
					   preparedStmt.setInt(10, Integer.parseInt(userId));
					   // execute the statement
					   preparedStmt.execute();
					   con.close();
					   output = "User details Updated successfully";
					   }
				    catch (Exception e)
					{
					   output = "Error while updating the user";
					   System.err.println(e.getMessage());
					}
				    return output;
				    }
			 
			  
			   public String deleteUser(String userId)
			   {
				   String output = "";
				   try
				   {
				   Connection con = connect();
				   if (con == null)
				   {return "Error while connecting to the database for deleting."; }
				   // create a prepared statement
				   String query = "delete from user where userId=?";
				   PreparedStatement preparedStmt = con.prepareStatement(query);
				   // binding values
				   preparedStmt.setInt(1, Integer.parseInt(userId));
				   // execute the statement
				   preparedStmt.execute();
				   con.close();
				   output = "Deleted successfully";
				   }
				   catch (Exception e)
				   {
				   output = "Error while deleting the user";
				   System.err.println(e.getMessage());
				   }
				   return output;
				}
				 
			   
			   
}

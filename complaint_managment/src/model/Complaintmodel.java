package model;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Complaintmodel
{ //A common method to connect to the DB
public Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");

	System.out.print("Successfully connected to db");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertComplaint(String customername, String date, String location, String problem, String problemstatus ,String phonenumber)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into complaint values (NULL, ?, ?, ?, ?, ?, ?) ";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values

 preparedStmt.setString(1, customername);
 preparedStmt.setString(2, date);
 preparedStmt.setString(3, location);
 preparedStmt.setString(4, problem);
 preparedStmt.setString(5, problemstatus);
 preparedStmt.setString(6, phonenumber);
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the complaint";
 System.err.println(e.getMessage());
 }
 return output;
 }


public String readComplaints()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table  border:1  style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; radius: 10px\">"  + 
 		                   "<tr style=\"border: 1px solid #ddd; padding: 8px;\">"+
		 "<th style=\"padding-top: 12px; padding-bottom: 13px; text-align: left; background-color: #04AA6D; color: white;\">complaintid</th>"+
 		"<th  style=\"padding-top: 12px; padding-bottom: 13px; text-align: left; background-color: #04AA6D; color: white;\">customername</th>" +
 "<th  style=\"padding-top: 12px; padding-bottom: 13px; text-align: left; background-color: #04AA6D; color: white;\">date</th>" +
 "<th  style=\"padding-top: 12px; padding-bottom: 13px; text-align: left; background-color: #04AA6D; color: white;\">location</th>" +
 "<th  style=\"padding-top: 12px; padding-bottom: 13px; text-align: left; background-color: #04AA6D; color: white;\">problem</th>" +
 "<th   style=\"padding-top: 12px; padding-bottom: 13px; text-align: left; background-color: #04AA6D; color: white;\">problemstatus</th>" +
 "<th   style=\"padding-top: 12px; padding-bottom: 13px; text-align: left; background-color: #04AA6D; color: white;\">phonenumber</th>" ;
 //"<th>Update</th><th>Remove</th></tr>";

 String query = "select * from complaint";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 int complaintid = rs.getInt("complaintid");
 String customername = rs.getString("customername");
 String date = rs.getString("date");
 String location = rs.getString("location");
 String problem = rs.getString("problem");
 String problemstatus = rs.getString("problemstatus");
 String phonenumber = rs.getString("phonenumber");
 // Add into the html table
 output += "<tr style=\"border: 1px solid #ddd; padding: 8px;\"><td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + complaintid + "</td>";
 output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + customername + "</td>";
 output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + date + "</td>";
 output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + location + "</td>";
 output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + problem + "</td>";
 output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + problemstatus + "</td>";
 output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + phonenumber + "</td>";
 // buttons
/* output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value=' ' itemID '>" + "</form></td></tr>";*/
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the complaints.";
 System.err.println(e.getMessage());
 }
 return output;
 }



public String getComplaintById(int complaintid) {
	try(Connection con = connect()) {
		String getQuery = "select  o.customername, o.date, o.location,o.problem,o.problemstatus, o.phonenumber\n " +"from complaint o \n"
				
				+ "where o.complaintid = ?;";
		PreparedStatement pstmnt = con.prepareStatement(getQuery);
		pstmnt.setInt(1, complaintid);
		
		String output = "<table border='1' style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; radius: 10px\">" + 
				"<tr style=\"border: 1px solid #ddd; padding: 8px;\">" 
				+ "<th>customername</th>"
				+ "<th>date</th>"
				+ "<th>location</th>" 
				+ "<th>problem</th>"
				+ "<th>problemstatus</th>"
				+ "<th>phonenumber</th>";
       ResultSet rs = pstmnt.executeQuery();
       
       while (rs.next()) {
			
			String customername = rs.getString("customername");
			String date= rs.getString("date");
			String location = rs.getString("location");
			String problem= rs.getString("problem");
			String problemstatus = rs.getString("problemstatus");
			String phonenumber = rs.getString("phonenumber");
			

			output += "<tr><td>" + customername + "</td>";
			output += "<td>" + date + "</td>";
			output += "<td>" +location  + "</td>";
			output += "<td>" +  problem+ "</td>";
			output += "<td>" +  problemstatus + "</td>";
			output += "<td>" + phonenumber + "</td></tr>";
			

		}
       output += "</table>";
		return output;
		
	}
	catch(Exception e) {
		return "Error occur during retrieving \n" + e.getMessage();
	}
	
}


public String updateComplaint(int complaintid, String customername, String date, String location, String problem, String problemstatus ,String phonenumber)
{		

	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE complaint SET customername=?,date=?,location=?,problem=?,problemstatus=?,phonenumber=? WHERE complaintid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 
	 preparedStmt.setString(1, customername);
	 preparedStmt.setString(2, date);
	 preparedStmt.setString(3, location);
	 preparedStmt.setString(4, problem);
	 preparedStmt.setString(5, problemstatus);
	 preparedStmt.setString(6, phonenumber);
	 preparedStmt.setInt(7, complaintid);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 System.out.println(complaintid);
	 return "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the complaint.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String deleteComplaint(int complaintid) {
	
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from complaint where complaintid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, (complaintid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the complaint";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	}
 
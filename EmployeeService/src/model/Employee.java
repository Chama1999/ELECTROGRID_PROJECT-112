package model; 
import java.sql.*; 
public class Employee 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 }
public String insertEmployee(String employeeid, String employeename, String employeedob, String employeeaddress, String employeegender, String employeesalary) 
{ 
String output = ""; 
try
{ 
Connection con = connect(); 
if (con == null) 
{return "Error while connecting to the database for inserting."; } 
// create a prepared statement
String query = " insert into employee values (?, ?, ?, ?, ?,?)"; 

PreparedStatement preparedStmt = con.prepareStatement(query); 
// binding values
preparedStmt.setString(1, employeeid); 
preparedStmt.setString(2, employeename); 
preparedStmt.setString(3, employeedob); 
preparedStmt.setString(4, employeeaddress); 
preparedStmt.setString(5, employeegender);
preparedStmt.setDouble(6, Double.parseDouble(employeesalary));

// execute the statement
preparedStmt.execute(); 
con.close(); 
output = "Inserted successfully"; 
} 
catch (Exception e) 
{ 
output = "Error while inserting the employee."; 
System.err.println(e.getMessage()); 
} 
return output; 
}
}

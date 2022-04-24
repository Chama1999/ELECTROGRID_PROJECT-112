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
public String insertEmployee(String employeename, String employeedob, String employeeaddress, String employeegender, String employeesalary) 
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
preparedStmt.setInt(1, 0); 
preparedStmt.setString(2, employeename); 
preparedStmt.setString(3, employeedob); 
preparedStmt.setString(4, employeeaddress); 
preparedStmt.setString(5, employeegender);
preparedStmt.setString(6, employeesalary);

// execute the statement
preparedStmt.execute(); 
con.close(); 
output = "Inserted employee successfully"; 
} 
catch (Exception e) 
{ 
output = "Error while inserting the employee."; 
System.err.println(e.getMessage()); 
} 
return output; 
}
 


public String readEmployees() 
{ 
String output = ""; 
try
{ 
Connection con = connect(); 
if (con == null) 
{return "Error while connecting to the database for reading."; } 
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Employee Name</th><th>Employee DOB</th>" +
"<th>Employee Address</th>" + 
"<th>Employee Gender</th>" +
"<th>Employee Salary</th>" +
"<th>Update</th><th>Remove</th></tr>"; 

String query = "select * from employees"; 
Statement stmt = con.createStatement(); 
ResultSet rs = stmt.executeQuery(query); 
// iterate through the rows in the result set
while (rs.next()) 
{ 
int employeeid = rs.getInt("employeeid"); 
String employeename = rs.getString("employeename"); 
String employeedob = rs.getString("employeedob"); 
String employeeaddress = rs.getString("employeeaddress"); 
String employeegender = rs.getString("employeegender"); 
String employeesalary = rs.getString("employeesalary"); 
// Add into the html table
    output += "<tr style=\"border: 1px solid #ddd; padding: 8px;\"><td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: Violet;\">" + employeeid + "</td>";
	output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeename + "</td>";
	output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeedob + "</td>";
	output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeeaddress + "</td>";
	output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeegender + "</td>";
	output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeesalary + "</td>";

} 
con.close(); 
// Complete the html table
output += "</table>"; 
} 
catch (Exception e) 
{ 
output = "Error while reading the employees."; 
System.err.println(e.getMessage()); 
} 
return output; 
}



public String updateEmployee(int employeeid, String employeename, String employeedob, String employeeaddress, String employeegender, String employeesalary)
{		

	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE complaint SET employeename=?,employeedob=?,employeeaddress=?,employeegender=?,employeesalary=? WHERE complaintid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 
	 preparedStmt.setString(1, employeename);
	 preparedStmt.setString(2, employeedob);
	 preparedStmt.setString(3, employeeaddress);
	 preparedStmt.setString(4, employeegender);
	 preparedStmt.setString(5, employeesalary);
	 preparedStmt.setInt(6, employeeid);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 System.out.println(employeeid);
	 return "Updated employee successfully";
	 }
	 
	 catch (Exception e)
	 {
	 output = "Error while updating the employee.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }



public String deleteEmployee(String employeeid) {
	
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from complaint where employeeid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(employeeid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted employee successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the employee";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	}
	







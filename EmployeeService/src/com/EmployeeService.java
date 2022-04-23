package com; 
import model.Employee; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Employees") 
public class EmployeeService 
{ 
 Employee employeeObj = new Employee(); 


@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertEmployee(@FormParam("employeeid") String employeeid, 
 @FormParam("employeename") String employeename, 
 @FormParam("employeedob") String employeedob, 
 @FormParam("employeeaddress") String employeeaddress,
 @FormParam("employeegender") String employeegender, 
 @FormParam("employeesalary") String employeesalary)
{ 
 String output = employeeObj.insertEmployee(employeeid, employeename, employeedob, employeeaddress, employeegender, employeesalary); 
return output; 
}
}
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
 String output = employeeObj.insertEmployee(employeename, employeedob, employeeaddress, employeegender, employeesalary); 
return output; 
}



@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 { 
 return employeeObj.readEmployees(); 
}



@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateEmployee(String employeeData) 
{ 
//Convert the input string to a JSON object 
 JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject(); 
//Read the values from the JSON object
 int employeeid = employeeObject.get("employeeid").getAsInt(); 
 String employeename = employeeObject.get("employeename").getAsString(); 
 String employeedob = employeeObject.get("employeedob").getAsString(); 
 String employeeaddress = employeeObject.get("employeeaddress").getAsString(); 
 String employeegender = employeeObject.get("employeegender").getAsString(); 
 String employeesalary = employeeObject.get("employeesalary").getAsString();
 String output = employeeObj.updateEmployee(employeeid, employeename, employeedob, employeeaddress, employeegender, employeesalary); 
return output; 
}



@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteEmployee(String employeeData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser()); 
 
//Read the value from the element <employeeid>
 String employeeid = doc.select("employeeid").text(); 
 String output = employeeObj.deleteEmployee(employeeid); 
return output; 
}


}


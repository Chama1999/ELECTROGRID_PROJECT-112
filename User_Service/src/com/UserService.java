package com;

import Model.UserModel;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Users")

public class UserService {
	
UserModel userObj = new UserModel();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	//register user
	public String RegisterUser(@FormParam("accountNo") String accountNo,
	 @FormParam("pincode") String pincode,
	 @FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("NIC") String NIC,
	 @FormParam("email") String email,
	 @FormParam("phone") String phone,
	 @FormParam("username") String username,
	 @FormParam("password") String password
	
	 )
	{	
		if(accountNo.isEmpty()||name.isEmpty()||address.isEmpty()||NIC.isEmpty()||email.isEmpty()||phone.isEmpty()||username.isEmpty()||password.isEmpty()||pincode.isEmpty()) 
		 {
			 return "input fields cannot be empty";
		 } 
		 else if(accountNo.length()!=10) {
			 return "Account number is consist of 10 digits";
		 }
		 else if(NIC.length()!=10) {
			 return "NIC length must be 10 characters long";
		 }
		 else if(password.length()<8||password.length()>20) {
			 return "password should be more than 8 and less than 20 in length";
		 }
		 else if(!password.matches("(.*[@,#,$,%].*$)")) {
	    	 return "password must contain at least one special character";
	     }
		 else if(!email.matches ("(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|$)")) {
			 return "Invalid email address";
		 }
		



		String output = userObj.RegisterUser(accountNo,pincode, name,address, NIC, email,phone,username,password);
		return output;
	}
	
	//reading data
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUserDetails()//view all users
	{
		return userObj.readUserDetails();
	}
	
	@GET
	@Path("/getUserbyID/{userId}")//view a specific user
	@Produces(MediaType.TEXT_HTML)
	public String UserProfileDetails(@PathParam("userId") String userId) {

		return userObj.getUserDetails(userId);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)//update user
	{
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		 String userId = userObject.get("userId").getAsString();
		 String accountNo = userObject.get("accountNo").getAsString();
		 String pincode = userObject.get("pincode").getAsString();
		 String name = userObject.get("name").getAsString();
		 String address = userObject.get("address").getAsString();
		 String NIC = userObject.get("NIC").getAsString();
		 String email = userObject.get("email").getAsString();
		 String phone = userObject.get("phone").getAsString();
		 String username = userObject.get("username").getAsString();
		 String password = userObject.get("password").getAsString();
		 
		if(accountNo.isEmpty()||name.isEmpty()||address.isEmpty()||NIC.isEmpty()||email.isEmpty()||phone.isEmpty()||username.isEmpty()||password.isEmpty()) 
		 {
			 return "input fields cannot be empty";
		 } 
		 else if(accountNo.length()!=10) {
			 return "Account number is consist of 10 digits";
		 }
		 else if(NIC.length()!=10) {
			 return "NIC length must be 10 characters long";
		 }
		 else if(password.length()<8||password.length()>20) {
			 return "password should be more than 8 and less than 20 in length";
		 }
		 else if(!password.matches("(.*[@,#,$,%].*$)" )) {
	    	 return "password must contain at least one special character";
	     }
		 
		 String output = userObj.EditUserDetails(userId,accountNo,pincode, name,address, NIC, email,phone,username,password);
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)//delete users
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	
		//Read the value from the element <userId>
		 String userID = doc.select("userId").text();
		 String output = userObj.deleteUser(userID);
		return output;
	}

	
	

}

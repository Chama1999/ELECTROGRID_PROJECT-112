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
	 @FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("NIC") String NIC,
	 @FormParam("email") String email,
	 @FormParam("phone") String phone,
	 @FormParam("userType") String userType,
	 @FormParam("username") String username,
	 @FormParam("password") String password
	 )
	{	
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
		 else if(!password.matches("(.*[@,#,$,%].*$)")) {
	    	 return "password must contain at least one special character";
	     }
		



		String output = userObj.RegisterUser(accountNo, name,address, NIC, email,phone,username,password);
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
	
	
	

}

package com;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Model.Password;

@Path("/Password")

public class PasswordService {

	Password password = new Password();

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String ResetPassword(@FormParam("pincode") String pincode,@FormParam("password") String newpassword) 
	{
		String output = password.ForgotPassword(pincode,newpassword);//pass userCode sent through the email and new password
		return output;
	}
	

	@POST
	@Path("/validateUser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String validateLogin(@FormParam("username") String Username, 
							    @FormParam("password") String Password) 
	{
		String output = password.validateUserLogin(Username, Password);
		return output;
	}

	

}

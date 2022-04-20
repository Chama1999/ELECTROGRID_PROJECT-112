package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


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
	
	public String ResetPassword(@FormParam("userId") String userId,@FormParam("password") String newpassword) 
	{	
		
		
		String output = password.ForgotPassword(userId,newpassword);//use the unique user ID and set the new password
		return output;
	}
	

	@POST
	@Path("/validateUser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String validateLogin(@FormParam("userId") String UserID,
			                    @FormParam("username") String Username, 
							    @FormParam("password") String Password) 
	{
		String output = password.validateUserLogin(UserID,Username, Password);
		return output;
	}

	

}

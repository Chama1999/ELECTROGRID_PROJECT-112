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
		
	 String output = userObj.RegisterUser(accountNo, name,address, NIC, email,phone,userType,username,password);
	 return output;
    }
	

}

package com;

import java.sql.Date;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.BillingModel;



@Path("/Billing")
public class BillingService {

	
	BillingModel billing = new BillingModel();
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertbillingdata(@FormParam("Account_No") String Account_No,
			@FormParam("From_Date") Date From_Date,
			@FormParam("To_Date") Date To_Date,
			@FormParam("Current_Reading") int Current_Reading,
			@FormParam("Status") String Status)
	{
		String output = billing.insertbillingdata(Account_No, From_Date, To_Date, Current_Reading, Status);
		return output;
	}
	
	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_HTML)
	public String readBilingDetails() {//view all billing details of users
		return billing.readBilingDetails();
	}
	
	@GET
	@Path("/getDetailsbyid/{Account_No}/{To_Date}")//view a specific billing details of user
	@Produces(MediaType.TEXT_HTML)
	public String UserBillingDetails(@PathParam("Account_No") String Account_No, @PathParam("To_Date") Date To_Date) {

		return billing.getuserBilingDetails(Account_No, To_Date);
	}

	
}

package com;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
			@FormParam("Previous_Reading") int Previous_Reading,
			@FormParam("To_Date") Date To_Date,
			@FormParam("Current_Reading") int Current_Reading,
			@FormParam("Previous_Amount") double Previous_Amount,
			@FormParam("Status") String Status)
	{
		String output = billing.insertbillingdata(Account_No, From_Date, Previous_Reading, To_Date, Current_Reading, Previous_Amount, Status);
		return output;
	}
}

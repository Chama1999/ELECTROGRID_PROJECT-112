package com;
import model.PaymentModel;


import java.sql.Date;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/Payment")
public class PaymentService {
	PaymentModel payment = new PaymentModel();
	
	//add service
	

		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String addPayment(@FormParam("account_number") String account_number,
				                 @FormParam("card_type") String card_type,
				                 @FormParam("card_number") int card_number,
				                 @FormParam("name_on_card") String name_on_card,
				                 @FormParam("cvc") int cvc,
				                 @FormParam("expire_date") Date expire_date,
				                 @FormParam("status") String status,
				                 @FormParam("date") Date date,
				                 @FormParam("bill_id") int bill_id)
		{
			String output = payment.addPayment(account_number, card_type, card_number, name_on_card, cvc, expire_date, status, date, bill_id);
			return output;
		}
		
		//view service
		
		
		@GET
	    @Path("/get")
	    @Produces(MediaType.TEXT_HTML)
	    public String getAllPatmentEntry(){
	        return this.payment.getAllPayment();
	    }
		
		@GET
		@Path("/getById/{user_id}")
		@Produces(MediaType.TEXT_HTML)
		public String getPaymentById(@PathParam("user_id") int user_id) {
			return this.payment.getPaymentByUser(user_id);
		}
		

}

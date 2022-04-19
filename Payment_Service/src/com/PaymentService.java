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
		public String addPayment(
				                 @FormParam("CardType") String CardType,
				                 @FormParam("CardNumber") int CardNumber,
				                 @FormParam("CardHolderName") String CardHolderName,
				                 @FormParam("CVC") int CVC,
				                 @FormParam("CardExpireDate") Date CardExpireDate,
				                 @FormParam("Status") String Status,
				                 @FormParam("PaymentDate") Date PaymentDate,
				                 @FormParam("BillID") int BillID)
		{
			String output = payment.addPayment(CardType, CardNumber, CardHolderName, CVC, CardExpireDate, Status, PaymentDate, BillID);
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
		@Path("/getById/{UserID}")
		@Produces(MediaType.TEXT_HTML)
		public String getPaymentById(@PathParam("UserID") int UserID) {
			return this.payment.getPaymentByUser(UserID);
		}
		
		
		//update service
		@PUT
		@Path("/update/payment/{PaymentID}")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePaymentById(@PathParam("PaymentID") int PaymentID ,
				 @FormParam("CardType") String CardType,
				@FormParam("CardNumber") int CardNumber,
				 @FormParam("CardHolderName") String CardHolderName,
				 @FormParam("CVC") int CVC,
				@FormParam("CardExpireDate") Date CardExpireDate,
				@FormParam("Status") String Status,
				@FormParam("PaymentDate") Date PaymentDate,
				@FormParam("BillID") int BillID ) {
			
		return this.payment.updatePayment(PaymentID, CardType, CardNumber, CardHolderName, CVC, CardExpireDate, Status, PaymentDate, BillID);
		
		}
		
		
		//delete service
		@DELETE
		@Path("/delete/payment/{PaymentID}")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String DeleteOrder(
		@PathParam ("PaymentID") int PaymentID )
		{

		//Read the value from the element <AppID>
		String output = payment.DeletePayment(PaymentID);
		        return output;



		}
		

}

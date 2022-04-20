package com;
import model.PaymentModel;







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
				                 @FormParam("CardNumber") String CardNumber,
				                 @FormParam("CardHolderName") String CardHolderName,
				                 @FormParam("CVC") String CVC,
				                 @FormParam("CardExpireDate") String CardExpireDate,
				                 @FormParam("Status") String Status,
				                 @FormParam("PaymentDate") String PaymentDate,
				                 @FormParam("BillID") int BillID)
		{
			
			if(CardType.isEmpty()||CardNumber.isEmpty()||CardHolderName.isEmpty()||CVC.isEmpty()||CardExpireDate.isEmpty()||Status.isEmpty()||PaymentDate.isEmpty())
			{
				 return "Fields must be filled out";
			}
			
			
			
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
				@FormParam("CardNumber") String CardNumber,
				 @FormParam("CardHolderName") String CardHolderName,
				 @FormParam("CVC") String CVC,
				@FormParam("CardExpireDate") String CardExpireDate,
				@FormParam("Status") String Status,
				@FormParam("PaymentDate") String PaymentDate,
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

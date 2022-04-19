
package com;
import model.Complaintmodel;
//For REST Service
import java.sql.Date;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Complaint")
public class Complaintservice
{
 Complaintmodel complaint = new Complaintmodel();
 
 @POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertComplaint(@FormParam("customername") String customername,
			                 @FormParam("date") Date date,
			                 @FormParam("location") String location,
			                 @FormParam("problem") String problem,
			                 @FormParam(" problemstatus") String  problemstatus,
			                 @FormParam("phonenumber") String phonenumber
			                 )
	{
		String output = complaint.insertComplaint(customername,date,location,problem,problemstatus,phonenumber);
		return output;
	}
@GET
@Path("/get")
@Produces(MediaType.TEXT_HTML)
public String readComplaints()
 {
	return this.complaint.readComplaints(); 

 }


/*@PUT
@Path("/update/Complaint/{complaintid}")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String updatePaymentById(@PathParam("payment_id") int payment_id ,
		 @FormParam("account_number") String account_number,
		 @FormParam("card_type") String card_type,
		@FormParam("card_number") int card_number,
		 @FormParam("name_on_card") String name_on_card,
		 @FormParam("cvc") int cvc,
		@FormParam("expire_date") Date expire_date,
		@FormParam("status") String status,
		@FormParam("date") Date date,
		@FormParam("bill_id") int bill_id ) {
	
return this.payment.updatePayment(payment_id, account_number, card_type, card_number, name_on_card, cvc, expire_date, status, date, bill_id);

}*/

@DELETE
@Path("/delete/complaint/{complaintid}")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String DeleteComplaint(
@PathParam ("complaintid") int complaintid )
{

//Read the value from the element <AppID>
String output = complaint.deleteComplaint(complaintid);
        return output;



}

}

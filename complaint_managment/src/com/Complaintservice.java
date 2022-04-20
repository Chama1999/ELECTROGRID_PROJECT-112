
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
			                 @FormParam("date") String date,
			                 @FormParam("location") String location,
			                 @FormParam("problem") String problem,
			                 @FormParam("problemstatus") String  problemstatus,
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


@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String updateComplaintById(@PathParam("complaintid") int complaintid ,
		 @FormParam("customername") String customername,
		 @FormParam("date") String date,
		@FormParam("location") String location,
		 @FormParam("problem") String problem,
		 @FormParam("problemstatus") String problemstatus,
		@FormParam("phonenumber2") String phonenumber
		 ) {
	
return this.complaint.updateComplaint(complaintid,customername,date,location,problem,problemstatus,phonenumber);

}

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

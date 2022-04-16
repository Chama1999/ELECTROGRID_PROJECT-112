package model;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class PaymentModel {
	public Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid",
					"root", "");
			//For testing
			System.out.print("Successfully connected to db");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String addPayment(String account_number,String card_type,int card_number,String name_on_card,int cvc,Date expire_date,String status,Date date,int bill_id )
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			
			String insertQuery = "insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 999, ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertQuery);
			
			double tot_charges = this.calculateSubAmount(bill_id);
			pstmnt.setString(1, account_number);
			pstmnt.setString(2, card_type);
			pstmnt.setInt(3, card_number);
			pstmnt.setString(4, name_on_card);
			pstmnt.setInt(5, cvc);
			pstmnt.setDate(6, expire_date);
			pstmnt.setString(7, status);
			pstmnt.setDouble(8, tot_charges);
			pstmnt.setDate(9, date);
			pstmnt.setInt(10, bill_id);
			
			// execute the statement3
						pstmnt.execute();
						con.close();
						output = "Payment Added successfully";
			
			
		}
		catch (Exception e)
		{
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String getAllPayment() {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\">" +
                    "<tr>" +
					"<th>payment_id</th>" +
                    "<th>account_number</th>" +
					"<th>card_type</th>" +
					"<th>card_number</th>" +
					"<th>name_on_card</th>" +
					"<th>cvc</th>" +
					"<th>expire_date</th>" +
					"<th>status</th>" +
                    "<th>tot_charges</th>" +
					"<th>date</th>" +
					"<th>tax_id</th>" +
					"<th>bill_id</th>";
						String query = "select * from payment";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						// iterate through the rows in the result set
						while (rs.next())
						{
							int payment_id = rs.getInt("payment_id");
							String account_number = rs.getString("account_number");
							String card_type = rs.getString("card_type");
							int card_number = rs.getInt("card_number");
							String name_on_card = rs.getString("name_on_card");
							int cvc = rs.getInt("cvc");
							Date expire_date = rs.getDate("expire_date");
							String status = rs.getString("status");
							float tot_charges = rs.getFloat("tot_charges");
							Date date = rs.getDate("date");
							int tax_id = rs.getInt("tax_id");
							int bill_id = rs.getInt("bill_id");

							output += "<tr><td>" + payment_id + "</td>";
							output += "<td>" + account_number + "</td>";
							output += "<td>" + card_type + "</td>";
							output += "<td>" + card_number + "</td>";
							output += "<td>" + name_on_card + "</td>";
							output += "<td>" + cvc + "</td>";
							output += "<td>" + expire_date + "</td>";
							output += "<td>" + status + "</td>";
							output += "<td>" + tot_charges + "</td>";
							output += "<td>" + date + "</td>";
							output += "<td>" + tax_id + "</td>";
							output += "<td>" + bill_id + "</td>";
						}
						con.close();
						// Complete the html table
						output += "</table>";
	}
		catch (Exception e)
		{
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;

    }
	
	
	public String getPaymentByUser(int user_id) {
		try(Connection con = connect()) {
			String getQuery = "select py.payment_id, c.name, py.date, py.tot_charges from billing o \n"
					+ "join user c on o.user_id = c.user_id \n"
					+ "join payment py on o.bill_id = py.bill_id \n" 
					+ "where c.user_id = ?;";
			PreparedStatement pstmnt = con.prepareStatement(getQuery);
			pstmnt.setInt(1, user_id);
			
			String output = "<table>" + 
					"<tr>" 
					+ "<th>Payment ID</th>" 
					+ "<th>Full Name</th>"
					+ "<th>Payment Date</th>" 
					+ "<th>Total Amount</th>";
	       ResultSet rs = pstmnt.executeQuery();
	       
	       while (rs.next()) {
				int payment_id = rs.getInt("payment_id");
				String name = rs.getString("name");
				Date date = rs.getDate("date");
				double tot_charges = rs.getDouble("tot_charges");
				

				output += "<tr><td>" + payment_id + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + tot_charges + "</td>";
				

			}
	       output += "</table>";
			return output;
			
		}
		catch(Exception e) {
			return "Error occur during retrieving \n" + e.getMessage();
		}
		
	}
	
	
	public double calculateSubAmount(int bill_id) {
		double tot_charges = 0;
		try(Connection con = connect()) {
			String getQuery = "select o.amount\n" 
					+ "from billing o\n"
					+ "where o.bill_id = ?;";
		     
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setInt(1, bill_id);
			ResultSet rs = pstmt.executeQuery();
			
			float amount = 0;
			float tax = 200;
			
            while (rs.next()) {
				
				amount = rs.getFloat("amount");
			}
            con.close();
			tot_charges = amount + tax;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return tot_charges;
		
	}
	
	public String updatePayment(int payment_id,
			String account_number,
			String card_type,
			int card_number,
			String name_on_card,
			int cvc,
			Date expire_date,
			String status,
			Date date,
			int bill_id) {
		
		try(Connection con = connect()) {
			
			String updateQuery = "update payment set account_number=?,card_type=?,card_number=?,name_on_card=?,cvc=?,expire_date=?,status=? ,tot_charges =? ,date=?,tax_id=?,bill_id=? where payment_id =?" ;
			
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			
			double tot_charges = this.calculateSubAmount(bill_id);
			@SuppressWarnings("restriction")
			int tax_id = 999;
			pstmt.setString(1,account_number);
			pstmt.setString(2,card_type);
			pstmt.setInt(3,card_number);
			pstmt.setString(4,name_on_card);
			pstmt.setInt(5,cvc);
			pstmt.setDate(6,expire_date);
			pstmt.setString(7,status);
			pstmt.setDouble(8,tot_charges);
			pstmt.setDate(9,date);
			pstmt.setInt(10,tax_id);
			pstmt.setInt(11,bill_id);
			pstmt.setInt(12,payment_id);
			pstmt.execute();
			con.close();
			System.out.println(payment_id);
	
			return "Payment updated successfully 123";
			
			
		}
		catch(Exception e) {
			return "Error occur during updating \n" +
					e.getMessage();
		}
		
		
	}
    
}

package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			
			
			String insertQuery = "insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, NULL, ?, NULL, ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertQuery);
			
			pstmnt.setString(1, account_number);
			pstmnt.setString(2, card_type);
			pstmnt.setInt(3, card_number);
			pstmnt.setString(4, name_on_card);
			pstmnt.setInt(5, cvc);
			pstmnt.setDate(6, expire_date);
			pstmnt.setString(7, status);
			pstmnt.setDate(8, date);
			pstmnt.setInt(9, bill_id);
			
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
}

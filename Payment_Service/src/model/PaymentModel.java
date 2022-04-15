package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
			System.out.print("Successfully connected");
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
			
			
			String insertQuery = "insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, NULL, ?, NULL, NULL)";
			PreparedStatement pstmnt = con.prepareStatement(insertQuery);
			
			pstmnt.setString(1, account_number);
			pstmnt.setString(2, card_type);
			pstmnt.setInt(4, card_number);
			pstmnt.setString(3, name_on_card);
			pstmnt.setInt(4, cvc);
			pstmnt.setDate(5, expire_date);
			pstmnt.setString(6, status);
			pstmnt.setDate(8, date);
			pstmnt.setInt(10, bill_id);
			
			// execute the statement3
						pstmnt.execute();
						con.close();
						output = "Payment Added successfully";
			
			
		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

package model;

import java.sql.Connection;
import javafx.util.Pair;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

@SuppressWarnings("restriction")
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
	
	public String addPayment(String CardType,int CardNumber,String CardHolderName,int CVC,Date CardExpireDate,String Status,Date PaymentDate,int BillID )
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			
			String insertQuery = "insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertQuery);
			double TaxAmount = 200;
			double TotalAmount = this.calculateSubAmount(BillID);
			pstmnt.setString(1, CardType);
			pstmnt.setInt(2, CardNumber);
			pstmnt.setString(3, CardHolderName);
			pstmnt.setInt(4, CVC);
			pstmnt.setDate(5, CardExpireDate);
			pstmnt.setString(6, Status);
			pstmnt.setDouble(7, TaxAmount);
			pstmnt.setDouble(8, TotalAmount);
			pstmnt.setDate(9, PaymentDate);
			pstmnt.setInt(10, BillID);
			
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
					"<th>PaymentID</th>" +
                    "<th>CardType</th>" +
					"<th>CardNumber</th>" +
					"<th>CardHolderName</th>" +
					"<th>CVC</th>" +
					"<th>CardExpireDate</th>" +
					"<th>Status</th>" +
					"<th>TaxAmount</th>" +
                    "<th>TotalAmount</th>" +
					"<th>PaymentDate</th>" +
					"<th>BillID</th>";
						String query = "select * from payment";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						// iterate through the rows in the result set
						while (rs.next())
						{
							int PaymentID = rs.getInt("PaymentID");
							String CardType = rs.getString("CardType");
							int CardNumber = rs.getInt("CardNumber");
							String CardHolderName = rs.getString("CardHolderName");
							int CVC = rs.getInt("CVC");
							Date CardExpireDate = rs.getDate("CardExpireDate");
							String Status = rs.getString("Status");
							float TaxAmount = rs.getFloat("TaxAmount");
							float TotalAmount = rs.getFloat("TotalAmount");
							Date PaymentDate = rs.getDate("PaymentDate");
							int BillID = rs.getInt("BillID");

							output += "<tr><td>" + PaymentID + "</td>";
							output += "<td>" + CardType + "</td>";
							output += "<td>" + CardNumber + "</td>";							
							output += "<td>" + CardHolderName + "</td>";
							output += "<td>" + CVC + "</td>";
							output += "<td>" + CardExpireDate + "</td>";
							output += "<td>" + Status + "</td>";
							output += "<td>" + TaxAmount + "</td>";
							output += "<td>" + TotalAmount + "</td>";
							output += "<td>" + PaymentDate + "</td>";
							output += "<td>" + BillID + "</td>";
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
	
	
	public String getPaymentByUser(int UserID) {
		try(Connection con = connect()) {
			String getQuery = "select py.PaymentID, c.name, py.PaymentDate, py.TotalAmount from billing o \n"
					+ "join user c on o.UserID = c.UserID \n"
					+ "join payment py on o.BillID = py.BillID \n" 
					+ "where c.UserID = ?;";
			PreparedStatement pstmnt = con.prepareStatement(getQuery);
			pstmnt.setInt(1, UserID);
			
			String output = "<table>" + 
					"<tr>" 
					+ "<th>PaymentID</th>" 
					+ "<th>name</th>"
					+ "<th>PaymentDate</th>" 
					+ "<th>TotalAmount</th>";
	       ResultSet rs = pstmnt.executeQuery();
	       
	       while (rs.next()) {
				int PaymentID = rs.getInt("PaymentID");
				String name = rs.getString("name");
				Date PaymentDate = rs.getDate("PaymentDate");
				double TotalAmount = rs.getDouble("TotalAmount");
				

				output += "<tr><td>" + PaymentID + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + PaymentDate + "</td>";
				output += "<td>" + TotalAmount + "</td>";
				

			}
	       output += "</table>";
			return output;
			
		}
		catch(Exception e) {
			return "Error occur during retrieving \n" + e.getMessage();
		}
		
	}
	
	
	
	/*public float calculateTaxAmount(int BillID, int NoOfUnit) {
		float TaxAmount = 0;
		try(Connection con = connect()) {
			String getQuery = "select o.NoOfUnits\n" 
					+ "from billing o\n"
					+ "where o.BillID = ?;";
		     
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setInt(1, NoOfUnit);
			ResultSet rs = pstmt.executeQuery();
			
			
			if (NoOfUnit <= 100)
			{
				TaxAmount = NoOfUnit * 3;
			}	
			else if (NoOfUnit <= 200)
			{
				TaxAmount = 100 * 3 + (NoOfUnit-100)*4;
			}
			else
			{
				TaxAmount = 100 * 3 + 100 * 4 + (NoOfUnit-200) * 5;
			}
			
            while (rs.next()) {
				
            	TaxAmount = rs.getFloat("TaxAmount");
			}
            con.close();
             
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return TaxAmount;
		
	}*/
	
	
	
	
	
	
	public double calculateSubAmount(int BillID) {
		double TotalAmount = 0;
		double TaxAmount = 0;
		try(Connection con = connect()) {
			String getQuery = "select o.Amount, o.NoOfUnits\n" 
					+ "from billing o\n"
					+ "where o.BillID = ?;";
		     
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setInt(1, BillID);
			ResultSet rs = pstmt.executeQuery();
			
			int NoOfUnits = 0;
			float Amount = 0;
			
			
            while (rs.next()) {
				
            	NoOfUnits = rs.getInt("NoOfUnits");
				Amount = rs.getFloat("Amount");
				
				if(NoOfUnits < 100)
				{
					TaxAmount = NoOfUnits*3;
				}
				else if(NoOfUnits < 200)
				{
					TaxAmount = 100*3+(NoOfUnits-100)*4;
				}
				else
				{
					TaxAmount = 100*3 + 100*4 + (NoOfUnits-200)*5;
				}
			}
            con.close();
            TotalAmount = Amount + TaxAmount;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return TotalAmount;
		
	}
	
	public String updatePayment(int PaymentID,
			String CardType,
			int CardNumber,
			String CardHolderName,
			int CVC,
			Date CardExpireDate,
			String Status,
			Date PaymentDate,
			int BillID) {
		
		try(Connection con = connect()) {
			
			String updateQuery = "update payment set CardType=?,CardNumber=?,CardHolderName=?,CVC=?,CardExpireDate=?,Status=?,TaxAmount=? ,TotalAmount=? ,PaymentDate=?,BillID=? where PaymentID =?" ;
			
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			double TaxAmount = 200;
			double TotalAmount = this.calculateSubAmount(BillID);
			pstmt.setString(1,CardType);
			pstmt.setInt(2,CardNumber);
			pstmt.setString(3,CardHolderName);
			pstmt.setInt(4,CVC);
			pstmt.setDate(5,CardExpireDate);
			pstmt.setString(6,Status);
			pstmt.setDouble(7, TaxAmount);
			pstmt.setDouble(8,TotalAmount);
			pstmt.setDate(9,PaymentDate);
			pstmt.setInt(10,BillID);
			pstmt.setInt(11,PaymentID);
			pstmt.execute();
			con.close();
			System.out.println(PaymentID);
	
			return "Payment updated successfully";
			
			
		}
		catch(Exception e) {
			return "Error occur during updating \n" +
					e.getMessage();
		}
		
		
	}
	
	public String DeletePayment(int PaymentID) 
	{ 
		String output = ""; 
		try
		{  
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from payment where PaymentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1,PaymentID); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the payment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	

    
}

package model;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillingModel {
	
	//Creating the db connection
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 
	
	
	public String insertbillingdata(String account_no, Date from_d, int previous_r, Date to_d, int current_r, double p_amount, String status )
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
			
			// create a prepared statement
			String query = " insert into billing values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			String name = this.getuserdetailsname(account_no);
			String address = this.getuserdetailsaddress(account_no);
			
			
			int units = this.calculateUnits(previous_r,current_r);
			double c_amount = this.calculateCurrentAmount(units);
			double t_amount = this.calculateTotalAmount(c_amount,p_amount);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, account_no);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, address);
			preparedStmt.setDate(5, from_d);
			preparedStmt.setInt(6, previous_r);
			preparedStmt.setDate(7, to_d);
			preparedStmt.setInt(8, current_r);
			preparedStmt.setInt(9, units);
			preparedStmt.setDouble(10,  c_amount);
			preparedStmt.setDouble(11, p_amount);
			preparedStmt.setDouble(12,  t_amount);
			preparedStmt.setString(13, status);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Billing data added successfully";
		}
		catch (Exception e)
		{
			output = "Error occur during inserting\n";
			System.err.println(e.getMessage());
		}
		return output;
	}



	private String getuserdetailsaddress(String account_no) {
		 
		 String address="";
		 
		try {
			
			Connection con = connect();
			
			String getQuery = "select u.Address\n"
					+ "from user u\n"
					+ "where u.accountNo = ?; ";
			
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setString(1, account_no);

			String getaddress ="";
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				
				getaddress = rs.getString("Address");
				
			}
			con.close();

			address = getaddress;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return address;
	}


	private String getuserdetailsname(String account_no) {
		 String name="";
		  
		 
		try {
			
			Connection con = connect();
			
			String getQuery = "select u.Name , u.Address\n"
					+ "from user u\n"
					+ "where u.accountNo = ?; ";
			
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setString(1, account_no);
			
			String getname ="";
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				getname = rs.getString("Name");
	
			}
			con.close();
			
			name = getname;

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}


	private int calculateUnits(int previous_r, int current_r) {
		
		 int units = 0;
		 
		 //calculation
		 units = current_r - previous_r;
		
		 return units;
	}

	
	private double calculateCurrentAmount(int units) {
		
		double c_amount = 0;
		
		c_amount = units * 5;
		
		return c_amount;
	}


	private double calculateTotalAmount(double c_amount, double p_amount) {
		
		double t_amount = 0;
		
		t_amount = c_amount + p_amount;
		
		return t_amount;
	}



	
}

package model;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	
	public String insertbillingdata(String account_no, Date from_d, Date to_d, int current_r, String status )
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
			
			int previous_r = this.getpreviousreading(account_no, status);
			
			int units = this.calculateUnits(previous_r,current_r);
			double c_amount = this.calculateCurrentAmount(units);
			double p_amount = this.getPreviousAmount(account_no, status);
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



	public double getPreviousAmount(String account_no, String status) {
		 
		double p_amount=0;
		 
		try {
			
			Connection con = connect();
			
			String getQuery = "select Total_amount\n"
								+ "from billing\n"
								+ "where Account_No = ? and Status='Pending'; ";
			
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setString(1, account_no);

			double Total_a= 0;
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				
				Total_a = rs.getDouble("Total_amount");
				
			}
			con.close();
			
			 
			p_amount = Total_a;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p_amount;
	}


	public int getpreviousreading(String account_no, String status) {
		 
		int previous_r=0;
		 
		try {
			
			Connection con = connect();
			
			String getQuery = "select Current_Reading\n"
					+ "from billing\n"
					+ "where Account_No = ? and Status='Pending' or Status='Last Month'; ";
			
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setString(1, account_no);

			int Current_R= 0;
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				
				Current_R = rs.getInt("Current_Reading");
				
			}
			con.close();
			
			previous_r = Current_R;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return previous_r;
	}


	public String getuserdetailsaddress(String account_no) {
		 
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


	public String getuserdetailsname(String account_no) {
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


	public int calculateUnits(int previous_r, int current_r) {
		
		 int units = 0;
		 
		 //calculation
		 units = current_r - previous_r;
		
		 return units;
	}

	
	public double calculateCurrentAmount(int units) {
		
		double c_amount = 0;
		
		c_amount = units * 5;
		
		return c_amount;
	}


	public double calculateTotalAmount(double c_amount, double p_amount) {
		
		double t_amount = 0;
		
		t_amount = c_amount + p_amount;
		
		return t_amount;
	}


	public String readBilingDetails() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Account No</th>"
					+"<th>Name</th>" 
					+"<th>Address</th>"
					+"<th>From Date</th>"
					+"<th>Previous Meter Reading</th>"
					+"<th>To date</th>"
					+"<th>Current Meter Reading</th>"
					+"<th>No of Units Consumed</th>"
					+"<th>Charge for electricity consumed</th>"
					+"<th>Total amount according to the previous amount</th>"
					+"<th>Total Amount</th>"
					+"<th>Status</th></tr>";
			
			String query = "select * from billing";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 int ID= rs.getInt("ID");
				 String Account_No = rs.getString("Account_No");
				 String Name = rs.getString("Name");
				 String Address = rs.getString("Address");
				 Date From_Date = rs.getDate("From_Date");
				 int Previous_Reading = rs.getInt("Previous_Reading");
				 Date To_Date = rs.getDate("To_Date");
				 int Current_Reading = rs.getInt("Current_Reading");
				 int Units = rs.getInt("Units");
				 Double Current_amount = rs.getDouble("Current_amount");
				 Double Previous_amount = rs.getDouble("Previous_amount");
				 Double Total_amount = rs.getDouble("Total_amount");
				 String Status = rs.getString("Status");
				 // Add a row into the html table
				 output += "<tr><td>" + Account_No + "</td>";
				 output += "<td>" + Name + "</td>";
				 output += "<td>" + Address + "</td>";
				 output += "<td>" + From_Date + "</td>";
				 output += "<td>" + Previous_Reading + "</td>";
				 output += "<td>" + To_Date + "</td>"; 
				 output += "<td>" + Current_Reading + "</td>";
				 output += "<td>" + Units + "</td>";
				 output += "<td>" + Current_amount + "</td>";
				 output += "<td>" + Previous_amount + "</td>";
				 output += "<td>" + Total_amount + "</td>";
				 output += "<td>" + Status + "</td>";
				 // buttons
				 output += "<input name='ID' type='hidden' "
				 + " value='" + ID + "'>"
				 + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			
		}catch(Exception e) {
			
			output = "Error while reading the bill betails of users";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}

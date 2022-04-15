package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserModel {

	//creating connection for the database
			public Connection connect()
			{
				Connection con = null;

				try
				{
				 Class.forName("com.mysql.jdbc.Driver");
				 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid","root", "");
				 
				 //For testing whether it is connected with the database
				 
				 System.out.print("Successfully connected with the database");
			 	}
				catch(Exception e)
			 	{
			 		e.printStackTrace();
				 }

			return con;
			}
			
}

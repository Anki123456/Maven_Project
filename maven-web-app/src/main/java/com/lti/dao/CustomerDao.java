package com.lti.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.lti.model.Customer;

//Data Access Object
public class CustomerDao {

	// public void add(int id, String name, String email)
	public void add(Customer customer) {

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Properties dbProps= new Properties();
			dbProps.load(this.getClass().getClassLoader().getResourceAsStream("prod-db.properties"));  //used for reading the properties file
			
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		   //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "Newuser123");
			
			//Now the code is loosely coupled--Can't predict which db we are using (mysql/oracle)
			Class.forName(dbProps.getProperty("driver"));
			conn= DriverManager.getConnection(dbProps.getProperty("url"), dbProps.getProperty("user"),dbProps.getProperty("pwd"));

			String sql = "insert into Customer_e values(?,?, ?) ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getEmail());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}

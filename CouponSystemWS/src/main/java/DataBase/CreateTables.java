package DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Connections.ConnectionPool;
import exceptions.CouponSystemException;

public class CreateTables {
	
	static ConnectionPool connect;
	
	public CreateTables() throws CouponSystemException {
		super();
		connect = ConnectionPool.getInstance();
	}
	
	// create all tables in DB. 
	public static void CreateAllTables() throws CouponSystemException {
		
		Collection<String> creating = new ArrayList<String>();
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		creating.add("CREATE TABLE Company(ID INT PRIMARY KEY, COMP_NAME VARCHAR(100), PASSWORD VARCHAR(50), EMAIL VARCHAR(100))");
		creating.add("CREATE TABLE Customer(ID INT PRIMARY KEY, CUST_NAME VARCHAR(100), PASSWORD VARCHAR(50))");
		creating.add("CREATE TABLE Coupon(ID INT PRIMARY KEY, TITLE VARCHAR(30), START_DATE DATE, END_DATE DATE, AMOUNT INT, TYPE VARCHAR(100), MESSAGE VARCHAR(100), PRICE FLOAT, IMAGE VARCHAR(100))");
		creating.add("CREATE TABLE Customer_Coupon(CUST_ID INT, COUPON_ID INT, FOREIGN KEY (COUPON_ID) REFERENCES Coupon (ID) ON DELETE CASCADE, FOREIGN KEY (CUST_ID) REFERENCES Customer (ID) ON DELETE CASCADE, PRIMARY KEY(CUST_ID, COUPON_ID))");
		creating.add("CREATE TABLE Company_Coupon(COMP_ID INT, COUPON_ID INT, FOREIGN KEY (COUPON_ID) REFERENCES Coupon (ID) ON DELETE CASCADE, PRIMARY KEY(COMP_ID, COUPON_ID))");
		try {
			for(String c : creating) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(c);
				System.out.println(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		finally {
			cpool.returnConnection(con);
		}
	System.out.println("disconnected");	
	}
	}


// + "CREATE TABLE Customer(ID INT PRIMARY KEY, CUST_NAME VARCHAR(40), PASSWORD VARCHAR(30); "
//+ "CREATE TABLE Coupon(ID INT PRIMARY KEY, TITLE VARCHAR(30), START_DATE VARCHAR(30), END_DATE VARCHAR(30), AMOUNT VARCHAR(30), TYPE VARCHAR(30), MESSAGE VARCHAR(40), PRICE DOUBLE(20, 20), IMAGE VARCHAR(40); "
//+ "CREATE TABLE Coustomer_Coupon(CUST_ID VARCHAR(50), COUPON_ID VARCHAR(40), PRIMARY_KEY(CUST_ID, COUPON_ID)); "
//+ "CREATE TABLE Company_Coupon(COMP_ID VARCHAR(50), COUPON_ID VARCHAR(40), PRIMARY_KEY(COMP_ID, COUPON_ID));"
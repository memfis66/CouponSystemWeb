package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Connections.ConnectionPool;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import exceptions.CouponSystemException;


public class CustomerDBDAO {
	
	public void createCustomer(Customer customer) throws CouponSystemException {		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "INSERT INTO Customer(ID, CUST_NAME, PASSWORD) VALUES(?,?,?) ";
		try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, customer.getId());
		stmt.setString(2, customer.getCustName());
		stmt.setString(3, customer.getPassword());
		stmt.executeUpdate();
		
		System.out.println("operation complite");
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		finally {
			cpool.returnConnection(con);
		}
	System.out.println("disconnected");	
	}
	
	public void removeCustomer(long id) throws CouponSystemException {		
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "DELETE FROM Customer WHERE ID = ?";
		try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);
		
		stmt.executeUpdate();
		System.out.println("operation complite");
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		finally {
			cpool.returnConnection(con);
		}
	System.out.println("disconnected");	
	}
	
public void updateCustomer(Customer customer) throws CouponSystemException {		
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "UPDATE Customer SET CUST_NAME = ?, PASSWORD = ? WHERE ID = ?";
		try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, customer.getCustName());
		stmt.setString(2, customer.getPassword());
		stmt.setLong(3, customer.getId());
		stmt.executeUpdate();
		System.out.println("operation complite");
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		finally {
			cpool.returnConnection(con);
		}
	System.out.println("disconnected");	
	}

public Customer getCustomer(long id) throws CouponSystemException {
	Customer customer = new Customer();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Customer WHERE ID = ?";
	try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);
	ResultSet res = stmt.executeQuery();
	if (res.next()) {

		customer.setId(res.getLong(1));
		customer.setCustName(res.getString(2));
		customer.setPassword(res.getString(3));
	} else {
		return null;
	}

	res.close();
	stmt.close();
	
	System.out.println("operation complite");
	} catch (SQLException e) {
		e.printStackTrace();	
	}
	
	finally {
		cpool.returnConnection(con);
	}
	
System.out.println("disconnected");	
return customer;
}

public Customer getCustomer(String CustName) throws CouponSystemException {
	Customer customer = new Customer();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Customer WHERE CUST_NAME = ?";
	try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setString(1, customer.getCustName());
	ResultSet res = stmt.executeQuery();
	if (res.next()) {

		customer.setId(res.getLong(1));
		customer.setCustName(res.getString(2));
		customer.setPassword(res.getString(3));
	} else {
		return null;
	}

	res.close();
	stmt.close();
	
	System.out.println("operation complite");
	} catch (SQLException e) {
		e.printStackTrace();	
	}
	
	finally {
		cpool.returnConnection(con);
	}
	
System.out.println("disconnected");	
return customer;
}

public Collection<Customer> getAllCustomers() throws CouponSystemException {
	Collection<Customer> customers = new ArrayList<Customer>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Customer";
	try {
	Statement stmt = con.createStatement();
	ResultSet res = stmt.executeQuery(sql);
	while (res.next()) {

		customers.add(new Customer(res.getLong(1), res.getString(2), res.getString(3)));
	} 
	

	res.close();
	stmt.close();
	
	System.out.println("operation complite");
	} catch (SQLException e) {
		e.printStackTrace();	
	}
	
	finally {
		cpool.returnConnection(con);
	}
	
System.out.println("disconnected");	
return customers;
	
}

//public Collection<Coupon> getCoupons() throws CouponSystemException {
//	Collection<Coupon> coupons = new ArrayList<Coupon>();
//	ConnectionPool cpool = ConnectionPool.getInstance();
//	Connection con = cpool.getConnection();
//	System.out.println("connected");
//	String sql = "SELECT * FROM Coupon";
//	try {
//	Statement stmt = con.createStatement();
//	ResultSet res = stmt.executeQuery(sql);
//	while (res.next()) {
//
//		coupons.add(new Coupon(res.getLong(1), res.getString(2), res.getDate(3), res.getDate(4), res.getInt(5), CouponType.valueOf(res.getString(6)), res.getString(7), res.getDouble(8), res.getString(9)) );
//	} 
	

//	res.close();
//	stmt.close();
//	
//	System.out.println("operation complite");
//	} catch (SQLException e) {
//		e.printStackTrace();	
//	}
//	
//	finally {
//		cpool.returnConnection(con);
//	}
//	
//System.out.println("disconnected");	
//return coupons;
//}

public Collection<Coupon> getCouponsByCustomer(Customer customer) throws CouponSystemException {
	Collection<Coupon> coupons = new ArrayList<Coupon>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon WHERE ID IN(SELECT COUPON_ID from Customer_Coupon WHERE CUST_ID = ?)";
	//Select * from Coupon Where id IN(select COUPON_ID from CUSTOMER_COUPON where CUST_ID =?)
	try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setLong(1, customer.getId());
	ResultSet res = stmt.executeQuery();
	
	while (res.next()) {

		coupons.add(new Coupon(res.getLong(1), res.getString(2), res.getDate(3), res.getDate(4), res.getInt(5), CouponType.valueOf(res.getString(6)), res.getString(7), res.getDouble(8), res.getString(9)) );
	} 
	
	res.close();
	stmt.close();
	
	System.out.println("operation complite");
	} catch (SQLException e) {
		e.printStackTrace();	
	}
	
	finally {
		cpool.returnConnection(con);
	}
	
System.out.println("disconnected");	
return coupons;
}

// create connection between Customer and Coupon. Coupon belong to Customer after working this function 
public void updateCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemException {
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	
	String sql = "INSERT INTO Customer_Coupon VALUES (?,?)";
	try {
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setLong(1, customer.getId());
		stat.setLong(2, coupon.getId());
		stat.executeUpdate();
	}  catch (SQLException e) {
		throw new CouponSystemException("Update Customer_coupon error" , e);
	} finally {
		cpool.returnConnection(con);
	}
	
}

public boolean login(String custName, String password) {
	
	return false;
	
}

}

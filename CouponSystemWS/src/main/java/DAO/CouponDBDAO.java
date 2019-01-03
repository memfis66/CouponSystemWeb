package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Connections.ConnectionPool;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import exceptions.CouponSystemException;


public class CouponDBDAO{
	
	public void createCoupon(Coupon coupon) throws CouponSystemException {	
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "INSERT INTO Coupon(ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) VALUES(?,?,?,?,?,?,?,?,?)";		 
		try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, coupon.getId());
		stmt.setString(2, coupon.getTitle());
		stmt.setDate(3, (Date) coupon.getStartDate());
		stmt.setDate(4, (Date) coupon.getEndDate());
		stmt.setInt(5, coupon.getAmount());
		stmt.setString(6, coupon.getType().name());
		stmt.setString(7, coupon.getMessage());
		stmt.setDouble(8, coupon.getPrice());
		stmt.setString(9, coupon.getImage());
		stmt.executeUpdate();
		System.out.println("operation complite");
		} catch (SQLException e) {
			//throw new CouponSystemException("createCoupon function error", e);	
			e.printStackTrace();
		}
		
		finally {
			cpool.returnConnection(con);
		}
	System.out.println("disconnected");	
	}
	
	public void removeCoupon(long id) throws CouponSystemException {		
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "DELETE FROM Coupon WHERE ID = ? ";
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
	
public void updateCoupon(Coupon coupon) throws CouponSystemException {		
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "UPDATE Coupon SET TITLE = ?, START_DATE = ?, END_DATE = ?, AMOUNT = ?, TYPE = ?, MESSAGE = ?, PRICE = ?, IMAGE = ? WHERE ID = ?";
		try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, coupon.getTitle());
		stmt.setDate(2, coupon.getStartDate());
		stmt.setDate(3, coupon.getEndDate());
		stmt.setInt(4, coupon.getAmount());
		stmt.setString(5, coupon.getType().name());
		stmt.setString(6, coupon.getMessage());
		stmt.setDouble(7, coupon.getPrice());
		stmt.setString(8, coupon.getImage());
		stmt.setLong(9, coupon.getId());
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

public Coupon getCoupon(long id) throws CouponSystemException {
	Coupon coupon = new Coupon();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon WHERE ID = ?";
	try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);
	ResultSet res = stmt.executeQuery();
	if (res.next()) {

		coupon.setId(res.getLong(1));
		coupon.setTitle(res.getString(2));
		coupon.setStartDate(res.getDate(3));
		coupon.setEndDate(res.getDate(4));
		coupon.setAmount(res.getInt(5));
		coupon.setType(CouponType.valueOf(res.getString(6)));
		coupon.setMessage(res.getString(7));
		coupon.setPrice(res.getDouble(8));
		coupon.setImage(res.getString(9));
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
return coupon;
}

public Coupon getCoupon(String title) throws CouponSystemException {
	Coupon coupon = new Coupon();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon WHERE TITLE = ?";
	try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setString(1, coupon.getTitle());
	ResultSet res = stmt.executeQuery();
	if (res.next()) {

		coupon.setId(res.getLong(1));
		coupon.setTitle(res.getString(2));
		coupon.setStartDate(res.getDate(3));
		coupon.setEndDate(res.getDate(4));
		coupon.setAmount(res.getInt(5));
		coupon.setType(CouponType.valueOf(res.getString(6)));
		coupon.setMessage(res.getString(7));
		coupon.setPrice(res.getDouble(8));
		coupon.setImage(res.getString(9));
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
return coupon;
}

public Collection<Coupon> getAllCoupons() throws CouponSystemException {
	Collection<Coupon> coupons = new ArrayList<Coupon>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon";
	try {
	Statement stmt = con.createStatement();
	ResultSet res = stmt.executeQuery(sql);
	while (res.next()) {

		coupons.add(new Coupon(res.getLong(1), res.getString(2), res.getDate(3), res.getDate(4), res.getInt(5), CouponType.valueOf(res.getString(6)), res.getString(7), res.getDouble(8), res.getString(9) ) );
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

public Collection<Coupon> getCouponByType(CouponType type) throws CouponSystemException {
	Collection<Coupon> coupons = new ArrayList<Coupon>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon WHERE TYPE = ?";
	try {
	Statement stmt = con.createStatement();
	ResultSet res = stmt.executeQuery(sql);
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

public Collection<Coupon> getCouponByCompany(long compId) throws CouponSystemException {
	Collection<Coupon> coupons = new ArrayList<Coupon>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon JOIN Company_Coupon on Coupon.ID=Company_Coupon.COUPON_ID Where Company_Coupon.COMP_ID=?";
	try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, compId);
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




}

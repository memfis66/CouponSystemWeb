package DAO;

import java.sql.Connection;
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



public class CompanyDBDAO {
	
	/**
	 * 
	 * @param company
	 * @throws Exception
	 */
	public void createCompany(Company company) throws CouponSystemException {		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "INSERT INTO Company(ID, COMP_NAME, PASSWORD, EMAIL) VALUES(?,?,?,?)";
		try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, company.getId());
		stmt.setString(2, company.getCompName());
		stmt.setString(3, company.getPassword());
		stmt.setString(4, company.getEmail());
		stmt.executeUpdate();
		System.out.println("operation create complite");
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		finally {
			cpool.returnConnection(con);
		}
	System.out.println("disconnected");	
	}
	
	public void removeCompany(long id) throws CouponSystemException {		
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "DELETE FROM Company WHERE ID = ?";
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
	
public void updateCompany(Company company) throws CouponSystemException {		
		
		ConnectionPool cpool = ConnectionPool.getInstance();
		Connection con = cpool.getConnection();
		System.out.println("connected");
		String sql = "UPDATE Company SET COMP_NAME = ?, PASSWORD = ?, EMAIL = ? WHERE ID = ?";
		try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, company.getCompName());
		stmt.setString(2, company.getPassword());
		stmt.setString(3, company.getEmail());
		stmt.setLong(4, company.getId());
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

public Company getCompany(long id) throws CouponSystemException {
	Company company = new Company();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Company WHERE ID = ?";
	try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);
	ResultSet res = stmt.executeQuery();
	if (res.next()) {

		company.setId(res.getLong(1));
		company.setCompName(res.getString(2));
		company.setPassword(res.getString(3));
		company.setEmail(res.getString(4));
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
return company;
}

public Company getCompany(String compName) throws CouponSystemException {
	Company company = new Company();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Company WHERE COMP_NAME = ?";
	try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setString(1, compName);
	ResultSet res = stmt.executeQuery();
	if (res.next()) {

		company.setId(res.getLong(1));
		company.setCompName(res.getString(2));
		company.setPassword(res.getString(3));
		company.setEmail(res.getString(4));
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
return company;
}

public Collection<Company> getAllCompanies() throws CouponSystemException {
	Collection<Company> companies = new ArrayList<Company>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Company";
	try {
	Statement stmt = con.createStatement();
	ResultSet res = stmt.executeQuery(sql);
	while (res.next()) {

		companies.add(new Company(res.getLong(1), res.getString(2), res.getString(3), res.getString(4)) );
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
return companies;
	
}

public Collection<Coupon> getCoupons() throws CouponSystemException {
	Collection<Coupon> coupons = new ArrayList<Coupon>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon";
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

public Collection<Coupon> getCouponsByCompany(Company company) throws CouponSystemException {
	Collection<Coupon> coupons = new ArrayList<Coupon>();
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	String sql = "SELECT * FROM Coupon JOIN Company_Coupon ON Coupon.ID = Company_Coupon.COUPON_ID WHERE COMP_ID = ? ";
	try {
		
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setLong(1, company.getId());
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

public boolean login(String compName, String password) {
	
	return false;
	
}

public void updateCompanyCoupon(Company company, Coupon coupon) throws CouponSystemException {
	ConnectionPool cpool = ConnectionPool.getInstance();
	Connection con = cpool.getConnection();
	System.out.println("connected");
	
	String sql = "INSERT INTO Company_Coupon (COMP_ID, COUPON_ID) VALUES (?,?)";
	try {
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setLong(1, company.getId());
		stat.setLong(2, coupon.getId());
		stat.executeUpdate();
	}  catch (SQLException e) {
		throw new CouponSystemException("Update Company_coupon error", e);
	} finally {
		cpool.returnConnection(con);
	}
	
}

}

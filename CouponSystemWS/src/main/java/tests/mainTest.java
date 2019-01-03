package tests;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Connections.ConnectionPool;
import DataBase.CreateTables;
import beans.Company;
import exceptions.CouponSystemException;
import facades.AdminFacade;

public class mainTest {

	public static void main(String[] args) throws CouponSystemException {
		// TODO Auto-generated method stub
		
		
//		CreateTables.CreateAllTables();
		
//		Company com1 = new Company(1, "RogaiKopita", "123456", "rig@gmail.com");
//		Company com2 = new Company(2, "OOO", "1234", "ooo@gmail.com");
//		Company com3 = new Company(3, "AAA", "456", "aaa@gmail.com");
//		
//AdminFacade adside = new AdminFacade();
//		
//	adside.createCompany(com1);
		
//		ConnectionPool cpool = ConnectionPool.getInstance();
//		Connection con = cpool.getConnection();
//		String sql = "DROP TABLE Coupon";
		
//		Collection<String> creating = new ArrayList<String>();
//		ConnectionPool cpool = ConnectionPool.getInstance();
//		Connection con = cpool.getConnection();
//		System.out.println("connected");
//		creating.add("DROP TABLE Company");
//		creating.add("DROP TABLE Customer");
//		creating.add("DROP TABLE Coupon");
//		creating.add("DROP TABLE Customer_Coupon");
//		creating.add("DROP TABLE Company_Coupon");
//		try {
//			for(String c : creating) {
//				Statement stmt = con.createStatement();
//				stmt.executeUpdate(c);
//				System.out.println(c);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();	
//		}
//		finally {
//			cpool.returnConnection(con);
//		}
//	System.out.println("disconnected");	
		
	
	}
}

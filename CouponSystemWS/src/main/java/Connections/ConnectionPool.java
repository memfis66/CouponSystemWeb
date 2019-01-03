package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import exceptions.CouponSystemException;

public class ConnectionPool {
	
	// creating set of connections, url for data base and maximum of connections
	private Set<Connection> PoolOfConnections = new HashSet<Connection>();
	private String url = "jdbc:derby://localhost:1527/CouponSystemDB;create = true";
	public static final int ConnectionsNumbers = 10;
	
	private static ConnectionPool instance;
	
	public static ConnectionPool getInstance() throws CouponSystemException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
	
	// creating connections and full Set of new connections
	public ConnectionPool() throws CouponSystemException  {
	for (int i = 0; i < ConnectionsNumbers; i++) {
		try {
		Connection con = DriverManager.getConnection(url);
		PoolOfConnections.add(con);
	} catch (SQLException e) {
		throw new CouponSystemException("Creating its impossible");
	}
		
	}
	}
	
	// to get one connection
	public synchronized Connection getConnection() {
		
			while(PoolOfConnections.isEmpty()) {
				try {
				wait();
			}
		 catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
			Iterator<Connection> it = PoolOfConnections.iterator();
			Connection con = it.next();
			it.remove();
			return con;
	}
	
	public synchronized void returnConnection(Connection con) {
		PoolOfConnections.add(con);
		notifyAll();
	}
	
	public synchronized void closeAllConnections() throws CouponSystemException {
		for(Connection connect : PoolOfConnections) {
			try {
			connect.close();
			} catch (SQLException e) {
				throw new CouponSystemException("Closr commections error");
				
			}
		}
		
	}
}

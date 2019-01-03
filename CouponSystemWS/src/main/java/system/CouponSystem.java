package system;

import DAO.CompanyDBDAO;
import DAO.CustomerDBDAO;
import Thread.DailyCouponExpirationTask;
import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CouponClientFacade;
import facades.CustomerFacade;

public class CouponSystem {
	
	DailyCouponExpirationTask dailytask = new DailyCouponExpirationTask();
	Thread dailyThread = new Thread(dailytask);
	
private static CouponSystem instance;
	
	public static CouponSystem getInstance() throws CouponSystemException {
		if (instance == null) {
			instance = new CouponSystem();
		}
		return instance;
	}

	public CouponSystem() {
		
		dailyThread.start();

	}
	
	CompanyDBDAO comdao = new CompanyDBDAO();
	CustomerDBDAO cusdao = new CustomerDBDAO();
	
	
	public CouponClientFacade login(String username, String password, ClientType clientType)
			throws CouponSystemException {


		if (clientType.equals(ClientType.ADMIN)) {
			AdminFacade adside = new AdminFacade();
			if (adside.Login(username, password)) {
				return adside;
			}		
		}

		if (clientType.equals(ClientType.COMPANY)) {
			CompanyFacade comside = new CompanyFacade(new Company());
			if (comside.login(username, password)) {
				return comside;
			}
		}

		if (clientType.equals(ClientType.CUSTOMER)) {
			CustomerFacade cusside = new CustomerFacade(new Customer());
			cusside.login(username, password);
		}
		
		throw new CouponSystemException("Wrong client type");

		
	}
	

}

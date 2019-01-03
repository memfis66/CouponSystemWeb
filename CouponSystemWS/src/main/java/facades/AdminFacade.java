package facades;

import java.util.ArrayList;
import java.util.Collection;

import DAO.CompanyDBDAO;
import DAO.CouponDBDAO;
import DAO.CustomerDBDAO;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import exceptions.CouponSystemException;


public class AdminFacade implements CouponClientFacade {
	
	// create new company 
	public void createCompany(Company company) throws CouponSystemException {
		CompanyDBDAO dbdao = new CompanyDBDAO();
		
		//check exist or no company already
		if(dbdao.getCompany(company.getCompName()) == null) {
			dbdao.createCompany(company);			
		} else {
			//need create coupon system exceptions
		}
	}
	
	
	// delete company and all coupon was creating this company
	public void removeCompany(long id) throws CouponSystemException {
		
		CompanyDBDAO comdao = new CompanyDBDAO();
		CouponDBDAO cupdao = new CouponDBDAO();
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		
		// check exist or no company
		if(comdao.getCompany(id) == null) {
			//need create coupon system exceptions
		} 
		
		// get coupons belonging to our Company in collections
		coupons.addAll(cupdao.getCouponByCompany(id));
		
		// removing all coupons belonging to Company and all connections width Customers and Companies
		for(Coupon c : coupons) {
			cupdao.removeCoupon(c.getId());
		}
		
		// deleting out Company
		comdao.removeCompany(id);		
		
	}
	
	// Updating parameters of Company
	public void updateCompany(Company company) throws CouponSystemException {
		
		CompanyDBDAO comdao = new CompanyDBDAO();
		Company com = new Company();
		com = comdao.getCompany(company.getCompName());
		
		if(comdao.getCompany(company.getId())== null) {
			System.out.println("Updaiting is inpossible. Company does exist.");
		}
		
		if(!company.getCompName().equals(com.getCompName())) {
			System.out.println("Changing name is impossible");
		}
		
		if(company.getId() != com.getId()) {
			System.out.println("Changing ID is impossible");
		}
		
		comdao.updateCompany(company);
		
	}
	
	public Company getCompany(long id) throws CouponSystemException {
		CompanyDBDAO comdao = new CompanyDBDAO();
		if(comdao.getCompany(id) == null) {
			System.out.println("Company does exist");
		}
		
		return comdao.getCompany(id);
	}
	
	public Collection<Company> getAllCompanies() throws CouponSystemException {
		CompanyDBDAO comdao = new CompanyDBDAO();
		Collection<Company> allCompanies = new ArrayList<Company>();
		try {
		allCompanies.addAll(comdao.getAllCompanies());
		} catch (Exception e) {
			throw new CouponSystemException("getAllCompanies function error", e);
		}
		return allCompanies;
	}
	
	public void createCustomer(Customer customer) throws CouponSystemException {
		CustomerDBDAO cusdao = new CustomerDBDAO();
		
		if(cusdao.getCustomer(customer.getCustName()) == null) {
			cusdao.createCustomer(customer);	
				
		} else {
			throw new CouponSystemException("CreateCustomer function error");
		}
	}
	
	
public void removeCustomer(long id) throws CouponSystemException {
		
	    CustomerDBDAO cusdao = new CustomerDBDAO();
        Customer tempcust = new Customer();
		
		
		// check being Customer
		if(cusdao.getCustomer(id) == null) {
			//need create coupon system exceptions
		} 
		
		// deleting our Customer
		cusdao.removeCustomer(id);		
		
	}


public void updateCustomer(Customer customer) throws CouponSystemException {
	
	CustomerDBDAO cusdao = new CustomerDBDAO();
	Customer cus = new Customer();
	cus = cusdao.getCustomer(customer.getId());
	
	if(cusdao.getCustomer(customer.getId())== null) {
		System.out.println("Updaiting is inpossible. Company does exist.");
	}
	
	if(!customer.getCustName().equals(cus.getCustName())) {
		System.out.println("Changing name is impossible");
	}
	
	if(customer.getId() != cus.getId()) {
		System.out.println("Changing ID is impossible");
	}
	
	cusdao.updateCustomer(customer);
	
}

public Customer getCustomer(long id) throws CouponSystemException {
	CustomerDBDAO cusdao = new CustomerDBDAO();
	if(cusdao.getCustomer(id) == null) {
		System.out.println("Customer does not exist");
	}
	
	return cusdao.getCustomer(id);
}

public Collection<Customer> getAllCustomers() throws Exception {
	CustomerDBDAO cusdao = new CustomerDBDAO();
	Collection<Customer> allCustomers = new ArrayList<Customer>();
	try {
	allCustomers.addAll(cusdao.getAllCustomers());
	} catch (Exception e) {
		throw new CouponSystemException("getAllCustomer function error", e);
	}
	return allCustomers;
}

public boolean Login(String UserName,String password) {
	if ("admin".equals(UserName) && "1234".equals(password)) {
		return true;
	} else {
		System.out.println("Login or password not correct");
		return false;
	}
}


@Override
public boolean login(String name, String password) throws CouponSystemException {
	// TODO Auto-generated method stub
	return false;
}
	
	

}

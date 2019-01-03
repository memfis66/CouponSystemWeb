package web.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.CouponClientFacade;
import system.CouponSystem;


@RestController
@CrossOrigin("*")
public class AdminService {
	
	
	private AdminFacade getFacade(HttpServletRequest request) throws CouponSystemException  {


		AdminFacade adsade = (AdminFacade)request.getSession().getAttribute("facade");
		return adsade;
		
	}
	/**
	 * This method closes the session
	 * @param request
	 */
	@RequestMapping(value = "/adminlogout", method = RequestMethod.POST)
	public void logOut(HttpServletRequest req) throws CouponSystemException {
		HttpSession hsession = req.getSession(false);
		hsession.invalidate();
}
	
//	AdminFacade adside = new AdminFacade();
//	CouponSystem coupsys = new CouponSystem();
	
//	@RequestMapping(value ="/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void loginAdmin(@RequestBody String username, String password, CouponClientFacade clientType) throws CouponSystemException
//	{
//		
//	 coupsys.login(username, password, clientType);
//	 
//	}
//	
	
	@RequestMapping(value ="/newcompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postCreateCompany(HttpServletRequest request, @RequestBody Company company) throws CouponSystemException
	{
		try {
		AdminFacade adside = getFacade(request);
	    adside.createCompany(company);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(company.toString());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	 
	}
	
	//ng build --prod
	@RequestMapping(value ="/deletecompany/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCompany(HttpServletRequest request, @PathVariable("id") long id) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
	 adside.removeCompany(id);
	 
	}
	
	
	@RequestMapping(value ="/updatecompany", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCompany(HttpServletRequest request, @RequestBody Company company) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
	 adside.updateCompany(company);
	 
	}
	
	@RequestMapping(value ="/getcompany/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Company getCompany(HttpServletRequest request, @PathVariable("id") long id) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
		Company company;
		company = adside.getCompany(id);
	return company;
	 
	}
	
	
	@RequestMapping(value ="/getallcompanies", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Company> getAllCompanies(HttpServletRequest request) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
		Collection<Company> companies = new ArrayList<Company>();
		companies = adside.getAllCompanies();
	return companies;
	 
	}
	
	@RequestMapping(value ="/createcustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(HttpServletRequest request, @RequestBody Customer customer) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
	adside.createCustomer(customer);
	 
	}
	
	
	@RequestMapping(value ="/deletecustomer/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCustomer(HttpServletRequest request, @PathVariable("id") long id) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
	 adside.removeCustomer(id);
	 
	}
	
	@RequestMapping(value ="/updatecustomer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(HttpServletRequest request, @RequestBody Customer customer) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
		adside.updateCustomer(customer);
	}
	
	@RequestMapping(value ="/getcustomer/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomer(HttpServletRequest request, @PathVariable("id") long id) throws CouponSystemException
	{
		AdminFacade adside = getFacade(request);
	Customer customer;
	customer = adside.getCustomer(id);
	return customer;
	}
	
	@RequestMapping(value ="/getallcustomers", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Customer> getAllCustomers(HttpServletRequest request) throws Exception
	{
		AdminFacade adside = getFacade(request);
	return adside.getAllCustomers();
	 
	}
	

}

package web.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Company;
import beans.Coupon;
import beans.CouponType;
import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CouponClientFacade;
import system.CouponSystem;

@RestController
@CrossOrigin("*")
public class CompanyService {
	
	private CompanyFacade getFacade(HttpServletRequest request) throws CouponSystemException  {


		CompanyFacade compside = (CompanyFacade)request.getSession().getAttribute("facade");
		return compside;
		
	}
	
//	private Company company = new Company(2, "Samsung", "1234", "samsung@gmail.com");
//	CompanyFacade compside = new CompanyFacade(company);
	
//CouponSystem coupsys = new CouponSystem();
	
//	@RequestMapping(value ="/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void loginCompany(@RequestBody String username, String password, CouponClientFacade clientType) throws CouponSystemException
//	{
//		
//	 coupsys.login(username, password, clientType);
//	 
//	}
	
	@RequestMapping(value = "/companylogout", method = RequestMethod.POST)
	public void logOut(HttpServletRequest req) throws CouponSystemException {
		HttpSession session = req.getSession(false);
		session.invalidate();
}
	
	@RequestMapping(value ="/newcoupon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postCreateCoupony(HttpServletRequest request, @RequestBody Coupon coupon) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);
	 compside.createCoupon(coupon);
	 
	}
	
	@RequestMapping(value ="/deletecoupon/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCoupon(HttpServletRequest request, @PathVariable("id") long id) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);		
		compside.removeCoupon(id);
	 
	}
	
	
	@RequestMapping(value ="/updatecoupon", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCoupon(HttpServletRequest request, @RequestBody Coupon coupon) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);
	 compside.updateCoupon(coupon);
	 
	}
	
	@RequestMapping(value ="/getcoupon/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Coupon getCoupon(HttpServletRequest request, @PathVariable("id") long id) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);
	return compside.getCoupon(id);
	 
	}
	
	
	@RequestMapping(value ="/getallcoupons", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCoupons(HttpServletRequest request) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = compside.getAllCoupons();
	return coupons;
	 
	}
	
	@RequestMapping(value ="/getallcouponbytype/{type}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> gelAllCouponByType(HttpServletRequest request, @PathVariable("type") CouponType type) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = compside.gelAllCouponByType(type);
	return coupons;
	 
	}
	
	@RequestMapping(value ="/getallcouponbyprice/{topPrice}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> gelAllCouponByPrice(HttpServletRequest request, @PathVariable("topPrice") double topPrice) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = compside.gelAllCouponByPrice(topPrice);
	return coupons;
	 
	}
	
	@RequestMapping(value ="/getallcouponbyenddate/{endDate}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> gelAllCouponByEndDate(HttpServletRequest request, @PathVariable("endDate") Date endDate) throws CouponSystemException
	{
		CompanyFacade compside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = compside.gelAllCouponByEndDate(endDate);
	return coupons;
	 
	}
	

}

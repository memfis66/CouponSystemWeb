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

import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import exceptions.CouponSystemException;
import facades.CompanyFacade;
import facades.CustomerFacade;

@RestController
@CrossOrigin("*")
public class CustomerService {
	
	private CustomerFacade getFacade(HttpServletRequest request) throws CouponSystemException  {

		CustomerFacade cusside = (CustomerFacade)request.getSession().getAttribute("facade");
		return cusside;		
	}
	
//	Customer customer = new Customer(1, "Jimmy", "1234");
//	CustomerFacade cusside = new CustomerFacade(customer);
	
	@RequestMapping(value ="/purchasecoupon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postPurchaceCoupon(HttpServletRequest request, @RequestBody Coupon coupon) throws CouponSystemException
	{
		CustomerFacade cusside = getFacade(request);
		cusside.purchaseCoupon(coupon);
	 
	}
	
	@RequestMapping(value ="/getallmycoupons", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllMyCoupons(HttpServletRequest request) throws CouponSystemException
	{
		CustomerFacade cusside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = cusside.getAllMyCoupons();
	return coupons;
	 
	}
	
	@RequestMapping(value ="/getallcouponslist", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCouponsList(HttpServletRequest request) throws CouponSystemException
	{
		CustomerFacade cusside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = cusside.getAllCouponsList();
	return coupons;
	 
	}
	
	@RequestMapping(value ="/getallmycouponbytype/{type}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> gelAllMyCouponByType(HttpServletRequest request, @PathVariable("type") CouponType type) throws CouponSystemException
	{
		CustomerFacade cusside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = cusside.gelAllCouponByType(type);
	return coupons;
	 
	}
	
	@RequestMapping(value ="/getallmycouponbyprice/{topPrice}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> gelAllMyCouponByPrice(HttpServletRequest request, @PathVariable("topPrice") double topPrice) throws CouponSystemException
	{
		CustomerFacade cusside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = cusside.gelAllCouponByPrice(topPrice);
	return coupons;
	 
	}
	
	@RequestMapping(value ="/getallmycouponbyenddate/{endDate}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> gelAllMyCouponByEndDate(HttpServletRequest request, @PathVariable("endDate") Date endDate) throws CouponSystemException
	{
		CustomerFacade cusside = getFacade(request);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		coupons = cusside.gelAllCouponByEndDate(endDate);
	return coupons;
	 
	}
	
	@RequestMapping(value = "/customerlogout", method = RequestMethod.POST)
	public void logOut(HttpServletRequest req) throws CouponSystemException {
		HttpSession session = req.getSession(false);

		session.invalidate();
}

}

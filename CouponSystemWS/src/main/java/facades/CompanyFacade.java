package facades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import DAO.CompanyDBDAO;
import DAO.CouponDBDAO;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import exceptions.CouponSystemException;


public class CompanyFacade implements CouponClientFacade{
	
	private Company company;
	
	public CompanyFacade(Company company) {
		super();
		this.company = company;
	}

	public void createCoupon(Coupon coupon) throws CouponSystemException {
		CouponDBDAO coupdao = new CouponDBDAO();
		CompanyDBDAO comdao = new CompanyDBDAO();
		//Coupon tempcoup = new Coupon();
		
		//tempcoup = coupdao.getCoupon(coupon.getId());
		
		if(coupdao.getCoupon(coupon.getTitle()) == null) {
			coupdao.createCoupon(coupon);
			comdao.updateCompanyCoupon(company, coupon);
		} else {
			throw new CouponSystemException("createCoupon function error");
		}
	}
	
	public void removeCoupon(long id) throws CouponSystemException {
		
		CouponDBDAO coupdao = new CouponDBDAO();
		Coupon tempcoup = new Coupon();
		tempcoup = coupdao.getCoupon(id);
		if(tempcoup.getTitle() == null) {
			throw new CouponSystemException("Coupon does not exist");
		} 
		
		// deleting our Coupon. From Customer_Coupon coupon was deleting automatically
		coupdao.removeCoupon(id);		
		
	}
	
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		
		CouponDBDAO coupdao = new CouponDBDAO();
		Coupon tempcoup = new Coupon();
		tempcoup = coupdao.getCoupon(coupon.getId());
		
		if(tempcoup.getTitle()== null) {
			System.out.println("Updaiting is impossible. Coupon does not exist.");
		}
		
		//if(coupon.getTitle().equals(tempcoup.getTitle()) && coupon.getId() == tempcoup.getId() && coupon.getAmount() == tempcoup.getAmount() && coupon.getImage().equals(tempcoup.getImage()) && coupon.getMessage().equals(tempcoup.getMessage()) && coupon.getType().equals(tempcoup.getType())) {
		if(coupon.getId() == tempcoup.getId()) {
		coupdao.updateCoupon(coupon);
		} else {
			throw new CouponSystemException("updateCoupon function error");
		}
		
	}
	
	public Coupon getCoupon(Long id) throws CouponSystemException {
		CouponDBDAO coupdao = new CouponDBDAO();
		if(coupdao.getCoupon(id) == null) {
			System.out.println("Coupon does exist");
		}
		
		return coupdao.getCoupon(id);
	}
	
	public Collection<Coupon> getAllCoupons() throws CouponSystemException {
		CompanyDBDAO comdao = new CompanyDBDAO();
		Collection<Coupon> allCoupons = new ArrayList<Coupon>();
		try {
		allCoupons.addAll(comdao.getCouponsByCompany(this.company));
		} catch (Exception e) {
			throw new CouponSystemException("getAllCompanies function error", e);
		}
		return allCoupons;
	}
	
	public Collection<Coupon> gelAllCouponByType(CouponType type) throws CouponSystemException {
		CompanyDBDAO comdao = new CompanyDBDAO();
		Collection<Coupon> allCoupons = new ArrayList<Coupon>();
		Collection<Coupon> tempallCoupons = new ArrayList<Coupon>();
		try {
			tempallCoupons.addAll(comdao.getCouponsByCompany(this.company));
			for (Coupon coup : tempallCoupons) {
				if(coup.getType().equals(type)) {
					allCoupons.add(coup);
				}
			}
			//allCoupons.addAll(coupdao.getCouponByType(type));
		} catch (Exception e) {
			throw new CouponSystemException("getAllCouponByType function error", e);
		}
			
		return allCoupons;
		
	}
	
	public Collection<Coupon> gelAllCouponByPrice(double price) throws CouponSystemException {
		CompanyDBDAO comdao = new CompanyDBDAO();
		Collection<Coupon> allCoupons = new ArrayList<Coupon>();
		Collection<Coupon> tempallCoupons = new ArrayList<Coupon>();
		try {
			tempallCoupons.addAll(comdao.getCouponsByCompany(this.company));
			for (Coupon coup : tempallCoupons) {
				if(coup.getPrice() <= price) {
					allCoupons.add(coup);
				}
			}
			
		} catch (Exception e) {
			throw new CouponSystemException("getAllCouponByPrice function error", e);
		}
			
		return allCoupons;
		
	}
	
	public Collection<Coupon> gelAllCouponByEndDate(Date endDate) throws CouponSystemException {
		CompanyDBDAO comdao = new CompanyDBDAO();
		Collection<Coupon> allCoupons = new ArrayList<Coupon>();
		Collection<Coupon> tempallCoupons = new ArrayList<Coupon>();
		try {
			tempallCoupons.addAll(comdao.getCouponsByCompany(this.company));
			for (Coupon coup : tempallCoupons) {
				if(coup.getEndDate().compareTo(endDate) <= 0) {
					allCoupons.add(coup);
				}
			}
			
		} catch (Exception e) {
			throw new CouponSystemException("getAllCouponByEndDate function error", e);
		}
			
		return allCoupons;
		
	}
	
	public boolean login(String userName, String password) throws CouponSystemException {
		CompanyDBDAO comdao = new CompanyDBDAO();
		Collection<Company> companies = new ArrayList<Company>();
		companies = comdao.getAllCompanies();
		for(Company c : companies) {
			if(c.getCompName().equals(userName) && c.getPassword().equals(password)) {
				return true;
			}
			
				
			}
		throw new CouponSystemException("Password or login not correct");
		}
	
	
	
	}




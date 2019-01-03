package DAO;

import java.util.Collection;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;

public interface CompanyDAO {
	
	public void createCompany(Company company) throws CouponSystemException;
	public void removeCompany(Company company) throws CouponSystemException;
	public void updateCompany(Company company) throws CouponSystemException;
	public Company getCompany(long id) throws CouponSystemException;
	public Collection<Company> getAllCompanies() throws CouponSystemException;
	public Collection<Coupon> getCoupons() throws Exception;
	public boolean login(String compName, String password) throws CouponSystemException;
	
	

}

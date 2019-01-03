package DAO;

import java.util.Collection;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import exceptions.CouponSystemException;

public interface CustomerDAO {
	
	public void createCustomer(Customer customer) throws CouponSystemException;
	public void removeCustomer(Customer customer) throws CouponSystemException;
	public void updateCustomer(Customer customer) throws CouponSystemException;
	public Customer getCustomer(long id) throws CouponSystemException;
	public Collection<Company> getAllCustomer() throws CouponSystemException;
	public Collection<Coupon> getCoupons() throws CouponSystemException;
	public boolean login(String custName, String password) throws CouponSystemException;
	

}

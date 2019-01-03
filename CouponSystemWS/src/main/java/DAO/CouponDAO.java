package DAO;

import java.util.Collection;
import beans.Coupon;
import beans.CouponType;
import exceptions.CouponSystemException;

public interface CouponDAO {
	
	public void createCoupon(Coupon coupon) throws CouponSystemException;
	public void removeCoupon(Coupon coupon) throws CouponSystemException;
	public void updateCoupon(Coupon coupon) throws CouponSystemException;
	public Coupon getCoupon(long id) throws CouponSystemException;
	public Collection<Coupon> getAllCoupon() throws CouponSystemException;
	public Collection<Coupon> getCouponByType(CouponType type) throws CouponSystemException;
	

}

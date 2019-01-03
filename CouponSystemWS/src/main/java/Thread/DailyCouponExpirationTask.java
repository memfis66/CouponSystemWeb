package Thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import beans.Coupon;
import exceptions.CouponSystemException;
import DAO.CouponDBDAO;

public class DailyCouponExpirationTask implements Runnable {

	@Override
	public void run() {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		CouponDBDAO cupdao = new CouponDBDAO();
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		try {
			coupons.addAll(cupdao.getAllCoupons());
			for(Coupon c : coupons) {
				if(c.getEndDate().compareTo(date) < 0) {
					cupdao.removeCoupon(c.getId());
				}
			}
			Thread.sleep(1000*60*60*24);
		} catch (InterruptedException e) {
			System.out.println("was interupted");
			
		}
		
		catch (CouponSystemException e) {
			System.out.println("Thread error");
					
	}

	
	}
}

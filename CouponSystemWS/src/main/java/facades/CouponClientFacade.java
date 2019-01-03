package facades;

import exceptions.CouponSystemException;

//public enum CouponClientFacade {
//	ADMIN, COMPANY, CUSTOMER;
//
//}

public interface CouponClientFacade {

  boolean login (String name, String password)throws CouponSystemException;
}

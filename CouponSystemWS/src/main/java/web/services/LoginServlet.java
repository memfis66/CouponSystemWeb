package web.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import exceptions.CouponSystemException;
import facades.CouponClientFacade;
import system.ClientType;
import system.CouponSystem;

@Controller
public class LoginServlet {
	CouponClientFacade coupside = null;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doPost(@RequestParam String username, @RequestParam String password, @RequestParam ClientType usertype,
			HttpServletRequest request, HttpServletResponse response) throws CouponSystemException {

		CouponSystem coupsys = CouponSystem.getInstance();

		try {
			coupside = coupsys.login(username, password, usertype);

			if (coupside == null) {
				return "redirect:http://localhost:8080";
			}

			

			request.getSession().setAttribute("facade", coupside);

			
			switch (usertype) {
			case ADMIN:
				return "redirect:http://localhost:8080/admin/index.html";
			case COMPANY:
				return "redirect:http://localhost:8080/company/index.html";
			case CUSTOMER:
				return "redirect:http://localhost:8080/customer/index.html";
			}

			return "redirect:http://localhost:8080";
		} catch (CouponSystemException e) {	
			e.printStackTrace();
		}
		return "redirect:http://localhost:8080";
	}
}
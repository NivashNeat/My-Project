package com.neat.hibernate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neat.hibernate.dao.DbUtil;
import com.neat.hibernate.model.Login;

/**
 * Handles requests for the application home page.
 */
@Controller
@SuppressWarnings("unchecked")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	private DbUtil dbUtil;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		System.out.println("Count of Login :"+dbUtil.queryForInt(Login.class));
		
		List<Login> loginResult = dbUtil.queryForList(Login.class);
		for(Login login:loginResult) {
			System.out.println(login.getUserName() + "  "+login.getPassWord());
		}
		
		
		System.out.println("Delete Query :"+dbUtil.deleteRecord(Login.class,"userName","nivash"));
		return "home";
	}
	
}

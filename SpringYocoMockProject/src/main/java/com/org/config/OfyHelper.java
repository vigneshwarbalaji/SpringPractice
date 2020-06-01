package com.org.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.org.model.UserAccountDetail;
import com.org.model.UserAccounts;

//..Omit..

public class OfyHelper implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.init();
		ObjectifyService.register(UserAccounts.class);
		ObjectifyService.register(UserAccountDetail.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
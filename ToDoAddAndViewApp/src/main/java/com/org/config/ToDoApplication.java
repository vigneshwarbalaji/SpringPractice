package com.org.config;

//package com.journaldev.jaxrs.resteasy.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.org.action.ControllerServlet;
//import com.org.dao.UserToDoServiceImpl;

//import com.journaldev.jaxrs.service.EmployeeServiceImpl;

@ApplicationPath("/")
public class ToDoApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();

	public ToDoApplication() {
		singletons.add(new ControllerServlet());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
package com.org.config;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;

//import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.org.model.AddTaskModel;
import com.org.model.ToDoListApp;
//..Omit..

public class OfyHelper implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.init();
		ObjectifyService.register(ToDoListApp.class);
		ObjectifyService.register(AddTaskModel.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
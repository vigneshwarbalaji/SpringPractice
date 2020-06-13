package com.org;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.org.action.ControllerServlet;

@ApplicationPath("/")
public class ApplicationConfig extends Application 

{

	
//	Set<Class<?>> singleton=new HashSet<Class<?>>(Arrays.asList(ControllerServlet.class));
//    public Set<Class<?>> getClasses() {
//      //  System.out.println(singleton);
//        return singleton;
	
	private Set<Object> singletons = new HashSet<Object>();

	public ApplicationConfig() {
		singletons.add(new ControllerServlet());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	
}


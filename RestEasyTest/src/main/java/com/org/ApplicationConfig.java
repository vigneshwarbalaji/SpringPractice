package com.org;

import java.util.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class ApplicationConfig extends Application {
	
	Set<Class<?>> singleton=new HashSet<Class<?>>(Arrays.asList(UserService.class));
    public Set<Class<?>> getClasses() {
      //  System.out.println(singleton);
        return singleton;
    }
//    public Set<Class<?>> getSingletons()
//    {
//       return singleton;
//    }
}
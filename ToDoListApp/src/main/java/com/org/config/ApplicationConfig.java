package com.org.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.org.model.ToDoConverter;
import com.org.model.ToDoListEntity;



@ApplicationPath("/")
public class ApplicationConfig extends Application {
	
	Set<Class<?>> singleton=new HashSet<Class<?>>(Arrays.asList(ToDoListEntity.class));
    public Set<Class<?>> getClasses() {

        return singleton;
    }

}

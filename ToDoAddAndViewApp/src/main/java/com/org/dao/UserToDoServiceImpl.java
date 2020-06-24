package com.org.dao;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.googlecode.objectify.ObjectifyService;
import com.org.model.ToDoListApp;


//@Path("/")
public class UserToDoServiceImpl implements UserToDoService{

//	@POST
//	@
	public boolean createUserAcc(ToDoListApp users)
	{
		ToDoListApp existingUser = getUserByMail(users.getEmail());
	
		if(existingUser == null)
		{
			   ObjectifyService.ofy().save().entity(users);
		        return true;
		}
		return false;
	     
	}
	
	public ToDoListApp getUserByMail(String email)
	{
		ToDoListApp existingUser=ObjectifyService.ofy().load().type(ToDoListApp.class).filter("email",email).first().now();
        
        return existingUser;
	}
	
}

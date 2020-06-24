package com.org.dao;

import com.googlecode.objectify.ObjectifyService;
import com.org.model.ToDoListApp;
import com.org.model.UserAccounts;

public class UserToDoServiceImpl {

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

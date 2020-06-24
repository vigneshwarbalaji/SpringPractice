package com.org.dao;

import com.org.model.ToDoListApp;

public interface UserToDoService {
	
	public boolean createUserAcc(ToDoListApp users);
	public ToDoListApp getUserByMail(String email);

}

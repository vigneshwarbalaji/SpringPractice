package com.org.dao;

import java.util.List;

import com.org.model.AddTaskModel;
import com.org.model.ToDoListApp;

public interface UserToDoService {
	
	public boolean createUserAcc(ToDoListApp users);
	public ToDoListApp getUserByMail(String email);
	public boolean createTask(AddTaskModel toDoUser);
	public List<AddTaskModel> viewTask(String email);
	
}

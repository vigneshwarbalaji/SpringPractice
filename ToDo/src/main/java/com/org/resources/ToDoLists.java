package com.org.resources;

import java.net.http.HttpHeaders;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.org.model.ToDoList;

import ccom.org.service.ToDoListService;

@Path("/todolists")
public class ToDoLists {

ToDoListService toDoListService = new ToDoListService();	

@GET
@Produces({MediaType.APPLICATION_JSON})
 public List<ToDoList> getAllStudents(@Context HttpHeaders headers)
 {
	List<ToDoList>todo = toDoListService.getAllLists();
	return todo;
 }
}

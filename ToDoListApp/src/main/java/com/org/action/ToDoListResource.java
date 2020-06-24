package com.org.action;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.org.model.ToDoConverter;
import com.org.model.ToDoListEntity;

@Path("/")
public class ToDoListResource {
 
  
 @GET
 @Produces({"application/json"})
 @Path("todo/{Email}/") 
 public ToDoConverter getToDo( @PathParam ("Email") String email) {
 //dummy code
 ToDoListEntity tlist = new ToDoListEntity();
 tlist.setEmail(email);
 tlist.setProject("ecommerce App");
 tlist.setTaskDescription("new feature");
 ToDoConverter converter = new ToDoConverter(tlist);
 return converter;
 } 
}
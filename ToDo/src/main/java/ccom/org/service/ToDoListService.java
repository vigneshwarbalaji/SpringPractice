package ccom.org.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.org.model.ToDoList;


@Path("/")
public class ToDoListService {
		
	
		@GET
    	@Path("/todo/{id}")
	    public List<ToDoList> getAllLists()
	    {
	        List<ToDoList>toDo = new ArrayList<ToDoList>();	
	        
	        ToDoList l1 = new ToDoList();
	        
	        l1.setId(1);
	        l1.setProject("ecommerceApp");
	        l1.setTaskDescription("new feature");
	        l1.setTime("1:12 PM");
	        
	        ToDoList l2 = new ToDoList();
	        
	        l2.setId(2);
	        l2.setProject("financeApp");
	        l2.setTaskDescription("new feature");
	        l2.setTime("11:12 AM");
	        
	        toDo.add(l1);
	        toDo.add(l2);
	        return toDo;
	    }	
	
	
}

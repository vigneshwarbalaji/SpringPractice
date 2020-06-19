package com.org.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;


@Controller
public class TaskQueueScheduler 
{
	@RequestMapping(value = "/task",method = RequestMethod.GET)
	public void simpleTasks(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		
		Queue queue = QueueFactory.getQueue("test-queue");
	    queue.add(TaskOptions.Builder.withUrl("/taskProcess").method(Method.GET));
	    
	    queue.add(TaskOptions.Builder.withUrl("/taskProcess").method(Method.GET));
	    
	    queue.add(TaskOptions.Builder.withUrl("/taskProcess").method(Method.GET));
	    
	    queue.add(TaskOptions.Builder.withUrl("/taskProcess").method(Method.GET));
	    
	    queue.add(TaskOptions.Builder.withUrl("/taskProcess").method(Method.GET));
				
	}

}

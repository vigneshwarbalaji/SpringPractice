package com.org.action;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.org.dao.UserToDoService;
import com.org.dao.UserToDoServiceImpl;
import com.org.model.ToDoListApp;





/**
 * Servlet implementation class ControllerServlet
 */
//@WebServlet("/ControllerServlet")

//@Controller
@Path("/")
public class ControllerServlet  {
	//private static final long serialVersionUID = 1L;
	UserToDoService dao = (UserToDoService) new UserToDoServiceImpl();
	
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	
	@Context
	ServletContext servletContext;
	
//	@GET
//	public String TestMethodOne()
//	{
//		return "Hello";
//	}
	
	@GET
	public String testMethod() throws IOException
	{
		
		InputStream st=servletContext.getResourceAsStream("/index.jsp");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(st));

		String string = new String();
		String modifiedHtml = "";
		while( (string = buffReader.readLine() ) != null){
		modifiedHtml=modifiedHtml+string;
		}

		buffReader.close();
		st.close();
		
		
		
		return modifiedHtml;
		
	}

	
	
	
	@GET
	@Path("/ControllerServlet")
	@Produces("application/json")
	public String signIn() throws IOException
	{
				
		String email = request.getParameter("email").trim();
//		System.out.println(email);
		String pass = request.getParameter("pass").trim();
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(false);
		
		if(email.isEmpty()||pass.isEmpty())
		{
			map.put("value","please fill all the details");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else
		{
//			UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			
			ToDoListApp userToDo = dao.getUserByMail(email);
			
			
			if(userToDo == null)
			{
				map.put("value","User doesnot exist.Please register.");
				//response.getWriter().print("<h6>User doesnot exist.Please register.</h6>");
			}
			else if(!(userToDo.getPassword().equals(pass)))
			{
				map.put("value","Password incorrect");
				//response.getWriter().print("<h6>Password incorrect</h6>");
			}
			else
			{	
				session = request.getSession(true);
				session.setAttribute("email", userToDo.getEmail());
				session.setAttribute("name", userToDo.getName());
				//System.out.println(userAcc.getEmail());
				
				//response.getWriter().print("true");		
				map.put("value","true");
				
				//return "/Dashboard";
				
//				UserAccountDetail userdetail = dao.getAccountDetailByMail(email);
				
//				ObjectMapper objectMapper = new ObjectMapper();

				
//				objectMapper.writeValueAsString(userdetail);
//				
//				return objectMapper; 
				
//				map.put("userdetail", userdetail);
				
//				map.put("userdetail",userdetail);
			}
		}
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	@POST
	@Path("/ControllerServlet")
	@Produces("application/json")
	public String signUp() throws IOException
	{
		String name = request.getParameter("name").trim();
		String email = request.getParameter("email").trim();
		String pass = request.getParameter("pass").trim();
		String rePass = request.getParameter("rePass").trim();
		
		HashMap<String,Object>map = new HashMap<String, Object>();
        HttpSession session = request.getSession(false);

		//PrintWriter out = response.getWriter();
		
		int passLength = pass.length();
		int rePassLength = rePass.length();
		
		
		if((name.isEmpty()||email.isEmpty()||
				pass.isEmpty() ||rePass.isEmpty()))
		{
			map.put("value","please fill all the details");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else if(!(pass.equals(rePass)) || passLength != rePassLength)
		{
			map.put("value","Password doesn't match");
			//response.getWriter().print("<h6>Password doesn't match</h6>");
		}
		else if(pass.length() < 6 || rePass.length() < 6)
		{
			map.put("value","Password must atleast be of length 6");
			//response.getWriter().print("<h6>Password must atleast be of length 6</h6>");
		}
		else
		{
			ToDoListApp users = new ToDoListApp();
			boolean result = false;
			
			users.setId(null);
			users.setName(name);
			users.setEmail(email);
			users.setPassword(pass);
			//user.setId(user.getId());
			
			//UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			
			result = dao.createUserAcc(users);
			
			if(result == true)
			{
				session = request.getSession(true);
				session.setAttribute("email", users.getEmail());
				session.setAttribute("name", users.getName());
				map.put("value","true");
				
			}
			else
			{
				map.put("value","Account Already exists"+"<br>");
				//response.getWriter().print("<h6>Account Already exists</h6>"+"<br>");
			}
			
		}
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
}
	


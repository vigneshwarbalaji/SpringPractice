package com.org.action;

import java.io.IOException;

//import java.io.PrintWriter;
import java.util.HashMap;

//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.objectify.ObjectifyService;
import com.org.dao.UserService;
import com.org.dao.UserServiceImpl;
import com.org.model.UserAccountDetail;
import com.org.model.UserAccounts;




/**
 * Servlet implementation class ControllerServlet
 */
//@WebServlet("/ControllerServlet")

@Controller
public class ControllerServlet  {
	//private static final long serialVersionUID = 1L;
	UserService dao = new UserServiceImpl();
	
//	@RequestMapping(value = "/SessionController",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody String Session(HttpServletRequest request,HttpServletResponse response) throws IOException
//	{
//		HttpSession session = request.getSession(false);
//		
//		if(session != null)
//		{
//			return "/Dashboard";
//		}
//		
//		return "/ControllerServlet";
//	}
	
	@RequestMapping(value = "/ControllerServlet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String signIn(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
				
		String email = request.getParameter("email").trim();
//		System.out.println(email);
		String pass = request.getParameter("pass").trim();
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(false);
		
		if(email.isEmpty()||pass.isEmpty())
		{
			map.put("value","<h6>please fill all the details</h6>");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else
		{
//			UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			
			UserAccounts userAcc = dao.getUserByMail(email);
			
			
			if(userAcc == null)
			{
				map.put("value","<h6>User doesnot exist.Please register.</h6>");
				//response.getWriter().print("<h6>User doesnot exist.Please register.</h6>");
			}
			else if(!(userAcc.getPassword().equals(pass)))
			{
				map.put("value","<h6>Password incorrect</h6>");
				//response.getWriter().print("<h6>Password incorrect</h6>");
			}
			else
			{	
				session = request.getSession(true);
				session.setAttribute("email", userAcc.getEmail());
				session.setAttribute("name", userAcc.getName());
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
	

	@RequestMapping(value = "/ControllerServlet",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String signUp(HttpServletRequest request,HttpServletResponse response) throws IOException
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
			map.put("value","<h6>please fill all the details</h6>");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else if(!(pass.equals(rePass)) || passLength != rePassLength)
		{
			map.put("value","<h6>Password doesn't match</h6>");
			//response.getWriter().print("<h6>Password doesn't match</h6>");
		}
		else if(pass.length() < 6 || rePass.length() < 6)
		{
			map.put("value","<h6>Password must atleast be of length 6</h6>");
			//response.getWriter().print("<h6>Password must atleast be of length 6</h6>");
		}
		else
		{
			UserAccounts users = new UserAccounts();
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
				map.put("value","<h6>Account Already exists</h6>"+"<br>");
				//response.getWriter().print("<h6>Account Already exists</h6>"+"<br>");
			}
			
		}
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
}
	

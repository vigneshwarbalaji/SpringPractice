package com.org.sampletest;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.dao.UserService;
import com.org.dao.UserServiceImpl;
import com.org.model.UserAccounts;

public class SampleTest {

	UserService dao = new UserServiceImpl();

	public int getLucky() {
        return 7;
    }
	
	
//	throws IOException
//   @ResponseBody	
	public String signIn(String email,String pass) 
	{
//		ObjectifyService.init();
//		ObjectifyService.register(UserAccounts.class);
		
		
		String res = "";
				
//		String email = request.getParameter("email").trim();
////		System.out.println(email);
//		String pass = request.getParameter("pass").trim();
		
//		HashMap<String,Object>map = new HashMap<String, Object>();
//		
//		HttpSession session = request.getSession(false);
		
		if(email.isEmpty()||pass.isEmpty())
		{
			res = "please fill all the details";
//			map.put("value","please fill all the details");
		}
		else
		{
//			dao.getUserByMail(email)
//			userAcc.setId(122345);
//			userAcc.setEmail("zap@gmail.com");
//			userAcc.setPassword("password");
			UserAccounts userAcc = dao.getUserByMail(email);
			
			
//			String userAccName = "vicky";
//			String userAccPass = "password";
			
			if(userAcc == null)
			{
				res = "User doesnot exist.Please register.";
//				map.put("value","User doesnot exist.Please register.");
			}
			else if(!(userAcc.getPassword().equals(pass)))
			{
				res = "Password incorrect";
//				map.put("value","Password incorrect");
			}
			else
			{	
//				session = request.getSession(true);
//				session.setAttribute("email", userAcc.getEmail());
//				session.setAttribute("name", userAcc.getName());
//						
				res = "success";
//				map.put("value","true");
			}
		}
		
		return res;
		
//		String obj = new ObjectMapper().writeValueAsString(map);
//		
//		return obj;
	}
	
	
	public @ResponseBody String signUp(String name,String email,String pass,String rePass)
	{
//		String name = request.getParameter("name").trim();
//		String email = request.getParameter("email").trim();
//		String pass = request.getParameter("pass").trim();
//		String rePass = request.getParameter("rePass").trim();
		
//		HashMap<String,Object>map = new HashMap<String, Object>();
//        HttpSession session = request.getSession(false);

		//PrintWriter out = response.getWriter();
		
		String res = "";
		
		int passLength = pass.length();
		int rePassLength = rePass.length();
		
		
		if((name.isEmpty()||email.isEmpty()||
				pass.isEmpty() ||rePass.isEmpty()))
		{
			res = "please fill all the details";
//			map.put("value","please fill all the details");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else if(!(pass.equals(rePass)) || passLength != rePassLength)
		{
//			map.put("value","Password doesn't match");
			res = "Password doesn't match";
			//response.getWriter().print("<h6>Password doesn't match</h6>");
		}
		else if(pass.length() < 6 || rePass.length() < 6)
		{
//			map.put("value","Password must atleast be of length 6");
			res = "Password must atleast be of length 6";
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
//				session = request.getSession(true);
//				session.setAttribute("email", users.getEmail());
//				session.setAttribute("name", users.getName());
//				map.put("value","true");
			
				res = "success";
			}
			else
			{
				res = "Account Already exists";
//				map.put("value","Account Already exists"+"<br>");
				//response.getWriter().print("<h6>Account Already exists</h6>"+"<br>");
			}
			
		}
		
//		String obj = new ObjectMapper().writeValueAsString(map);
//		
//		return obj;
		
		return res;
	}


	
	
	
}

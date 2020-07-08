package com.org.action;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
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
import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.Session;
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
	private static final String CLIENT_ID = "1057523589135-9gb46eembt589ce228tfbetn8nhvmqkn.apps.googleusercontent.com";
	
	  private static final HttpTransport TRANSPORT = new NetHttpTransport();
	  private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

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
			map.put("value","please fill all the details");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else
		{
//			UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			
			UserAccounts userAcc = dao.getUserByMail(email);
			
			
			if(userAcc == null)
			{
				map.put("value","User doesnot exist.Please register.");
				//response.getWriter().print("<h6>User doesnot exist.Please register.</h6>");
			}
			else if(!(userAcc.getPassword().equals(pass)))
			{
				map.put("value","Password incorrect");
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
				map.put("value","Account Already exists"+"<br>");
				//response.getWriter().print("<h6>Account Already exists</h6>"+"<br>");
			}
			
		}
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	

	@RequestMapping(value = "/googleSignIn",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String googleSignIn(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
//		String id_token = googleUser.getAuthResponse().id_token;
		
		HttpSession session = request.getSession(false);
		
		HashMap<String,Object>map = new HashMap<String, Object>();
//		HttpTransport transport = null;
//		JsonFactory jsonFactory = null;

		
//		if(session == null)
//		{
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT,JSON_FACTORY)
				    // Specify the CLIENT_ID of the app that accesses the backend:
				    .setAudience(Collections.singletonList(CLIENT_ID))
				    // Or, if multiple clients access the backend:
				    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				    .build();

				// (Receive idTokenString by HTTPS POST)
			System.out.println("test1");	
			
			
				String idTokenString = request.getParameter("googleToken");

//				String idTokenString = googleUser.getAuthResponse().id_token;
			
				GoogleIdToken idToken = null;
				try {
					idToken = verifier.verify(idTokenString);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idToken != null) {
				  Payload payload = idToken.getPayload();

				  // Print user identifier
				  String userId = payload.getSubject();
				  System.out.println("User ID: " + userId);

				  // Get profile information from payload
				  String email = (String) payload.get("email");
				  boolean emailVerified = Boolean.valueOf(((com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload) payload).getEmailVerified());
				  String name = (String) payload.get("name");
//				  String pictureUrl = (String) payload.get("picture");
//				  String locale = (String) payload.get("locale");
//				  String familyName = (String) payload.get("family_name");
//				  String givenName = (String) payload.get("given_name");

				  // Use or store profile information
				  // ...
//				  UserAccounts users = new UserAccounts();
//				  boolean result = false;
//				  
//				  	users.setId(null);
//					users.setName(name);
//					users.setEmail(email);
//					
//					result = dao.createUserAcc(users);
					
//					if(result == true)
//					{
//						System.out.println(users.getEmail());
//						session.setAttribute("email",users.getEmail());
//						session.setAttribute("name",users.getName());
//						map.put("value","true");
//					}
//					else
//					{
				  
				  		UserAccounts userAcc = dao.getUserByMail(email);
				  		
				  		if(userAcc == null) 
				  		{
				  			map.put("value","false");
				  		}
				  		else
				  		{
				  			session.setAttribute("email",userAcc.getEmail());
							session.setAttribute("name",userAcc.getName());
							map.put("value","true");
				  		}
						
//					}

				} else {
					
					map.put("value","false");
				  System.out.println("Invalid ID token.");
				}
//		}
//		else
//		{
////			System.out.println(session.getAttribute("email"));
////			session.invalidate();
////			System.out.println(session.getAttribute("name"));
//			
//			System.out.println("Trap for jasper exception");
////			map.put("value","true");
//		}

				String obj = new ObjectMapper().writeValueAsString(map);

				return obj;

		
		
	}
	
	
	@RequestMapping(value = "/googleSignUp",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String googleSignUp(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
//		String id_token = googleUser.getAuthResponse().id_token;
		
		HttpSession session = request.getSession(false);
		
		HashMap<String,Object>map = new HashMap<String, Object>();
//		HttpTransport transport = null;
//		JsonFactory jsonFactory = null;

		
//		if(session == null)
//		{
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT,JSON_FACTORY)
				    // Specify the CLIENT_ID of the app that accesses the backend:
				    .setAudience(Collections.singletonList(CLIENT_ID))
				    // Or, if multiple clients access the backend:
				    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				    .build();

				// (Receive idTokenString by HTTPS POST)
			System.out.println("test1");	
			
			
				String idTokenString = request.getParameter("googleToken");

//				String idTokenString = googleUser.getAuthResponse().id_token;
			
				GoogleIdToken idToken = null;
				try {
					idToken = verifier.verify(idTokenString);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idToken != null) {
				  Payload payload = idToken.getPayload();

				  // Print user identifier
				  String userId = payload.getSubject();
				  System.out.println("User ID: " + userId);

				  // Get profile information from payload
				  String email = (String) payload.get("email");
				  boolean emailVerified = Boolean.valueOf(((com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload) payload).getEmailVerified());
				  String name = (String) payload.get("name");
//				  String pictureUrl = (String) payload.get("picture");
//				  String locale = (String) payload.get("locale");
//				  String familyName = (String) payload.get("family_name");
//				  String givenName = (String) payload.get("given_name");

				  // Use or store profile information
				  // ...
				  UserAccounts users = new UserAccounts();
				  boolean result = false;
				  
				  	users.setId(null);
					users.setName(name);
					users.setEmail(email);
					
					result = dao.createUserAcc(users);
					
					if(result == true)
					{
						System.out.println(users.getEmail());
						session.setAttribute("email",users.getEmail());
						session.setAttribute("name",users.getName());
						map.put("value","true");
					}
					else
					{
						map.put("value","false");
					}
//					else
//					{
//				  
//				  		UserAccounts userAcc = dao.getUserByMail(email);
//				  		
//						session.setAttribute("email",userAcc.getEmail());
//						session.setAttribute("name",userAcc.getName());
//						map.put("value","true");
////					}

				} else {
					
					map.put("value","false");
				  System.out.println("Invalid ID token.");
				}
//		}
//		else
//		{
////			System.out.println(session.getAttribute("email"));
////			session.invalidate();
////			System.out.println(session.getAttribute("name"));
//			
//			System.out.println("Trap for jasper exception");
////			map.put("value","true");
//		}

				String obj = new ObjectMapper().writeValueAsString(map);

				return obj;

		
		
	}


	
}
	

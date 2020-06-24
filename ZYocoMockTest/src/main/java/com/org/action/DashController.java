//package com.org.action;
//
//import java.io.IOException;
//
//import java.io.PrintWriter;
//import java.lang.reflect.Type;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.TimeZone;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.googlecode.objectify.ObjectifyService;
//import com.googlecode.objectify.impl.Session;
//import com.org.dao.UserService;
//import com.org.dao.UserServiceImpl;
//import com.org.model.UserAccountDetail;
//import com.org.model.UserAccounts;
//
//
///**
// * Servlet implementation class DashController
// */
///*
// * @WebServlet("/DashController")
// */
//@Controller
//public class DashController{
//	//private static final long serialVersionUID = 1L;
//    
//	/*
//	static{
//		ObjectifyService.init();
//	    ObjectifyService.register(UserAccountDetail.class );
//	}
//*/
//	
//	UserService dao = new UserServiceImpl();
//	
//	@RequestMapping(value = "/DashController",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody String clockIn(HttpServletRequest request,HttpServletResponse response) throws IOException
//	{
//		
//		HashMap<String,Object>map = new HashMap<String, Object>();
//		
//		HttpSession session = request.getSession(true);
//		String name = session.getAttribute("name").toString();
//		String email = session.getAttribute("email").toString();
//		
//		String project = request.getParameter("pro");
//		String description = request.getParameter("descr");
//		
//		UserAccountDetail accDet = new UserAccountDetail();
//		boolean result = false;
//		
//		UserAccountDetail existingUser = dao.getAccountDetailByMail(email);
//		
//			accDet.setId(null);
//			accDet.setEmail(email);
//			accDet.setProject(project);
//			accDet.setTaskDescription(description);
//			String milliSeconds = String.valueOf(System.currentTimeMillis());
//			
//			accDet.setClockIn(milliSeconds);
//			
//			//UserService dao = (UserService) this.getServletContext().getAttribute("dao");
//			
//
//			
//			result = dao.createUserAccDetails(accDet);
//			
//			//System.out.println("result"+result);
//			
//			if(result == true)
//			{
//				UserAccountDetail userAcc = dao.getAccountDetailByMail(email);
//				
//				long millisec = Long.parseLong(userAcc.getClockIn());
//
//				
//				String formattedTime = dao.milliSecToTimeConversion(millisec);
//
//				userAcc.setClockIn(formattedTime);
//				
//				String json = new Gson().toJson(userAcc);
//				
//				return json;
//			}
//			
//			map.put("value","false");
//				
//			String obj = new ObjectMapper().writeValueAsString(map);
//			
//			return obj;
//	
//	}
//}
//
//	/*
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		HttpSession session = request.getSession(true);
//		String email = session.getAttribute("email").toString();
//		boolean result = false;
//		
//		UserService dao = (UserService) this.getServletContext().getAttribute("dao");
//		
//		//UserAccountDetail updated = new UserAccountDetail();
//		
//		UserAccountDetail updated = dao.getAccountDetailByMail(email);
//		
//		if(updated.getClockIn() != null && updated.getClockOut() == null)
//		{
//			//String milliSecondsClockIn = updated.getClockIn();
//			String milliSecondsClockOut = String.valueOf(System.currentTimeMillis());
//			
//			updated.setClockOut(milliSecondsClockOut);
//			
//			result = dao.createUserAccDetails(updated);
//			
//			if(result == true)
//			{
//				long milliClockIn = Long.parseLong(updated.getClockIn());
//				long milliClockOut = Long.parseLong(updated.getClockOut());
//			    String formattedTimeIn = dao.milliSecToTimeConversion(milliClockIn);
//			    String formattedTimeOut = dao.milliSecToTimeConversion(milliClockOut);
//				updated.setClockIn(formattedTimeIn);
//				updated.setClockOut(formattedTimeOut);
//				
//			    String json = new Gson().toJson(updated);
//			    		    
//			    System.out.println(json);
//			    response.setContentType("application/json");
//				response.getWriter().print(json);
//			}
//		}
//		else
//		{
//			response.getWriter().print("false");
//		}
//		
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		HttpSession session = request.getSession(true);
//		String name = session.getAttribute("name").toString();
//		String email = session.getAttribute("email").toString();
//		
////		System.out.println(name);
////		System.out.println(email);
////		
////		System.out.println(request.getParameter("action"));
////		System.out.println(request.getParameter("pro"));
////		System.out.println(request.getParameter("descr"));
//		
//		String project = request.getParameter("pro");
//		String description = request.getParameter("descr");
//		
//		UserService dao = (UserService) this.getServletContext().getAttribute("dao");
//
//		
//		
//		UserAccountDetail accDet = new UserAccountDetail();
//		boolean result = false;
//		
//		UserAccountDetail existingUser = dao.getAccountDetailByMail(email);
////		if(project != null|| description != null)
////		{
////		if(existingUser.getClockIn().isEmpty() ||existingUser == null)
////		{
//			
//		
//			accDet.setId(null);
//			accDet.setEmail(email);
//			accDet.setProject(project);
//			accDet.setTaskDescription(description);
//			//Instant now = Instant.now();
//			//System.out.println(now.getEpochSecond());
//			//long seconds = now.getEpochSecond();
//			String milliSeconds = String.valueOf(System.currentTimeMillis());
//			
//			accDet.setClockIn(milliSeconds);
//			
//			//UserService dao = (UserService) this.getServletContext().getAttribute("dao");
//			
//
//			
//			result = dao.createUserAccDetails(accDet);
//
//			
//			
////			List<UserAccountDetail> userAcc = dao.getAccountDetailList(email);
//			
////			Type listType = new TypeToken<List<UserAccountDetail>>() {}.getType();
//
//			
////			for (UserAccountDetail list : userAcc) {
//				
////				long millisec = Long.parseLong(list.getClockIn());
////				long millisecOut = Long.parseLong(list.getClockOut());
////			    //String formattedTime = date.format(formatter);
////				
////				String formattedTime = dao.milliSecToTimeConversion(millisec);
////				String formattedTimeOut = dao.milliSecToTimeConversion(millisecOut);
////				list.setClockIn(formattedTime);
////				list.setClockIn(formattedTimeOut);
//				
//
//
////			}
//			
//			UserAccountDetail userAcc = dao.getAccountDetailByMail(email);
//			
//			long millisec = Long.parseLong(userAcc.getClockIn());
////			long millisecOut = Long.parseLong(list.getClockOut());
////		    //String formattedTime = date.format(formatter);
////			
//			String formattedTime = dao.milliSecToTimeConversion(millisec);
////			String formattedTimeOut = dao.milliSecToTimeConversion(millisecOut);
//			userAcc.setClockIn(formattedTime);
//			
////			String json = new Gson().toJson(userAcc,listType);
//			String json = new Gson().toJson(userAcc);
//		    System.out.println(json);
//		    response.setContentType("application/json");
//			response.getWriter().print(json);
//			
//			
//			//Type listType = new TypeToken<List<String>>() {}.getType();
//
//
//		
//		
//		
//		
//	}
//
//}
//*/
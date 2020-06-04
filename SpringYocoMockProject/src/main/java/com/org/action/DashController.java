package com.org.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.Session;
import com.org.dao.UserService;
import com.org.dao.UserServiceImpl;
import com.org.model.UserAccountDetail;
import com.org.model.UserAccounts;


/**
 * Servlet implementation class DashController
 */
/*
 * @WebServlet("/DashController")
 */
@Controller
public class DashController{
	
	
	UserService dao = new UserServiceImpl();
	
	@RequestMapping(value = "/GetEntry",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String GetEntry(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession(true);
		String email = session.getAttribute("email").toString();
//		UserAccountDetail existingUser = dao.getAccountDetailByMail(email);
		
		List<UserAccountDetail>existingUser = dao.getAccountListByMail(email);
		
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		
		for(UserAccountDetail userList: existingUser)
		{
			if(userList != null)
			{
				long inMillisec = Long.parseLong(userList.getClockIn());

				String inFormattedTime = dao.milliSecToTimeConversion(inMillisec);

				userList.setClockIn(inFormattedTime);
				
				if(userList.getClockOut() != null)
				{
					long outMillisec = Long.parseLong(userList.getClockOut());

					String outFormattedTime = dao.milliSecToTimeConversion(outMillisec);

					userList.setClockOut(outFormattedTime);
				}
				else
				{
					userList.setClockOut("ongoing");
					
					System.out.println(userList.getClockOut());
				}

			}

		}
				
		map.put("user", existingUser);
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	@RequestMapping(value = "/clockIn",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String clockIn(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(true);
		String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		
		String project = request.getParameter("project");
		String description = request.getParameter("descript");
		
		UserAccountDetail accDet = new UserAccountDetail();
		boolean result = false;
		
		UserAccountDetail existingUser = dao.getAccountDetailByMail(email);
		
			accDet.setId(null);
			accDet.setEmail(email);
			accDet.setProject(project);
			accDet.setTaskDescription(description);
			String milliSeconds = String.valueOf(System.currentTimeMillis());
			
			accDet.setClockIn(milliSeconds);
			//accDet.setClockOut("on going");
			//UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			

			
			result = dao.createUserAccDetails(accDet);
			
			//System.out.println("result"+result);
//			List<UserAccountDetail>userList = dao.getAccountListByMail(email);
//			
//			for(UserAccountDetail x : userList)
//			{
//			
//				long inGrpMillisec = Long.parseLong(x.getClockIn());
//
//				
//				String inGrpFormattedTime = dao.milliSecToTimeConversion(inGrpMillisec);
//
//				x.setClockIn(inGrpFormattedTime);				
//				
//				if(x.getClockOut() != null)
//				{
//					long outGrpMillisec = Long.parseLong(x.getClockOut());
//					String outGrpFormattedTime = dao.milliSecToTimeConversion(outGrpMillisec);
//					x.setClockOut(outGrpFormattedTime);
//				}
//				else
//				{
//					x.setClockOut("ongoing");
//				}
//				
//			}
//			map.put("userList", userList);
//			
//			String object = new ObjectMapper().writeValueAsString(map);

			if(result == true)
			{
				UserAccountDetail userList = dao.getAccountDetailByMail(email);
				
				long millisec = Long.parseLong(userList.getClockIn());

				
				String formattedTime = dao.milliSecToTimeConversion(millisec);

				userList.setClockIn(formattedTime);
				userList.setClockOut("ongoing");
				
				map.put("userList",userList);
//				String json = new Gson().toJson(userAcc);
				String json = new ObjectMapper().writeValueAsString(map);
				
				return json;
			}
			
			//map.put("value","false");
				
			String obj = new ObjectMapper().writeValueAsString(map);
			
			return obj;
	
}



@RequestMapping(value = "/clockOut",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody String clockOut(HttpServletRequest request,HttpServletResponse response) throws IOException
{
	
	HashMap<String,Object>map = new HashMap<String, Object>();
	
	boolean result = false;
	
	HttpSession session = request.getSession(true);
	String name = session.getAttribute("name").toString();
	String email = session.getAttribute("email").toString();
	
	//boolean checkIfUserClockedIn = true;
	//UserAccountDetail accDet = new UserAccountDetail();
	
	try {
		UserAccountDetail existingUser = dao.getAccountDetailByMail(email);
		
		existingUser.setEmail(existingUser.getEmail());
		existingUser.setClockIn(existingUser.getClockIn());
		existingUser.setProject(existingUser.getProject());
		existingUser.setTaskDescription(existingUser.getTaskDescription());
		
		String milliSeconds = String.valueOf(System.currentTimeMillis());
		existingUser.setClockOut(milliSeconds);
		
		result = dao.createUserAccDetails(existingUser);
		
		
//		List<UserAccountDetail>userList = dao.getAccountListByMail(email);
//		
//		for(UserAccountDetail x : userList)
//		{
////			System.out.println(x.getEmail());
////			System.out.println(x.getProject());
////			System.out.println(x.getTaskDescription());
////			
//			long inGrpMillisec = Long.parseLong(x.getClockIn());
//
//			
//			String inGrpFormattedTime = dao.milliSecToTimeConversion(inGrpMillisec);
//
//			x.setClockIn(inGrpFormattedTime);
//
//			
////			System.out.println(x.getClockIn());
//			
//			long outGrpMillisec = Long.parseLong(x.getClockOut());
//
//			
//			String outGrpFormattedTime = dao.milliSecToTimeConversion(outGrpMillisec);
//
//			x.setClockOut(outGrpFormattedTime);
//			
////			System.out.println(x.getClockOut());
////			System.out.println();
//			
//			
//		}
//		map.put("userList", userList);
//		
//		String object = new ObjectMapper().writeValueAsString(map);
		
		if(result == true)
		{
//			UserAccountDetail userList = dao.getAccountDetailByMail(email);
			
			long inMillisec = Long.parseLong(existingUser.getClockIn());

			
			String inFormattedTime = dao.milliSecToTimeConversion(inMillisec);

			existingUser.setClockIn(inFormattedTime);
			
			long outMillisec = Long.parseLong(existingUser.getClockOut());

			
			String outFormattedTime = dao.milliSecToTimeConversion(outMillisec);

			existingUser.setClockOut(outFormattedTime);
			
			map.put("userList",existingUser);
		
			String json = new ObjectMapper().writeValueAsString(map);
			
//			String json = new Gson().toJson(userList);
			
			return json;
		}
		
		map.put("value","false");
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		//System.out.println(object);
		
		return obj;
	}
	catch (Exception e) {
		// TODO: handle exception
		
		map.put("checkIfUserClockedIn","false");
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	

}
}

	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String email = session.getAttribute("email").toString();
		boolean result = false;
		
		UserService dao = (UserService) this.getServletContext().getAttribute("dao");
		
		//UserAccountDetail updated = new UserAccountDetail();
		
		UserAccountDetail updated = dao.getAccountDetailByMail(email);
		
		if(updated.getClockIn() != null && updated.getClockOut() == null)
		{
			//String milliSecondsClockIn = updated.getClockIn();
			String milliSecondsClockOut = String.valueOf(System.currentTimeMillis());
			
			updated.setClockOut(milliSecondsClockOut);
			
			result = dao.createUserAccDetails(updated);
			
			if(result == true)
			{
				long milliClockIn = Long.parseLong(updated.getClockIn());
				long milliClockOut = Long.parseLong(updated.getClockOut());
			    String formattedTimeIn = dao.milliSecToTimeConversion(milliClockIn);
			    String formattedTimeOut = dao.milliSecToTimeConversion(milliClockOut);
				updated.setClockIn(formattedTimeIn);
				updated.setClockOut(formattedTimeOut);
				
			    String json = new Gson().toJson(updated);
			    		    
			    System.out.println(json);
			    response.setContentType("application/json");
				response.getWriter().print(json);
			}
		}
		else
		{
			response.getWriter().print("false");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		
//		System.out.println(name);
//		System.out.println(email);
//		
//		System.out.println(request.getParameter("action"));
//		System.out.println(request.getParameter("pro"));
//		System.out.println(request.getParameter("descr"));
		
		String project = request.getParameter("pro");
		String description = request.getParameter("descr");
		
		UserService dao = (UserService) this.getServletContext().getAttribute("dao");

		
		
		UserAccountDetail accDet = new UserAccountDetail();
		boolean result = false;
		
		UserAccountDetail existingUser = dao.getAccountDetailByMail(email);
//		if(project != null|| description != null)
//		{
//		if(existingUser.getClockIn().isEmpty() ||existingUser == null)
//		{
			
		
			accDet.setId(null);
			accDet.setEmail(email);
			accDet.setProject(project);
			accDet.setTaskDescription(description);
			//Instant now = Instant.now();
			//System.out.println(now.getEpochSecond());
			//long seconds = now.getEpochSecond();
			String milliSeconds = String.valueOf(System.currentTimeMillis());
			
			accDet.setClockIn(milliSeconds);
			
			//UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			

			
			result = dao.createUserAccDetails(accDet);

			
			
//			List<UserAccountDetail> userAcc = dao.getAccountDetailList(email);
			
//			Type listType = new TypeToken<List<UserAccountDetail>>() {}.getType();

			
//			for (UserAccountDetail list : userAcc) {
				
//				long millisec = Long.parseLong(list.getClockIn());
//				long millisecOut = Long.parseLong(list.getClockOut());
//			    //String formattedTime = date.format(formatter);
//				
//				String formattedTime = dao.milliSecToTimeConversion(millisec);
//				String formattedTimeOut = dao.milliSecToTimeConversion(millisecOut);
//				list.setClockIn(formattedTime);
//				list.setClockIn(formattedTimeOut);
				


//			}
			
			UserAccountDetail userAcc = dao.getAccountDetailByMail(email);
			
			long millisec = Long.parseLong(userAcc.getClockIn());
//			long millisecOut = Long.parseLong(list.getClockOut());
//		    //String formattedTime = date.format(formatter);
//			
			String formattedTime = dao.milliSecToTimeConversion(millisec);
//			String formattedTimeOut = dao.milliSecToTimeConversion(millisecOut);
			userAcc.setClockIn(formattedTime);
			
//			String json = new Gson().toJson(userAcc,listType);
			String json = new Gson().toJson(userAcc);
		    System.out.println(json);
		    response.setContentType("application/json");
			response.getWriter().print(json);
			
			
			//Type listType = new TypeToken<List<String>>() {}.getType();


		
		
		
		
	}

}
*/
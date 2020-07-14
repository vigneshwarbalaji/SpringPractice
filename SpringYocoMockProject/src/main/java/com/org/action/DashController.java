package com.org.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.io.IOException;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	
	long lastInTime = 0;
	
	String zones = "";
	
	@RequestMapping(value = "/GetEntry",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String GetEntry(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession(true);
		String email = session.getAttribute("email").toString();
//		UserAccountDetail existingUser = dao.getAccountDetailByMail(email);
		
		zones = request.getParameter("zone");
		
		
		List<UserAccountDetail>existingUser = dao.getAccountListByMail(email);
		
		List<String>hours = new ArrayList<String>();
		List<String>mins = new ArrayList<String>();
		List<String>secs = new ArrayList<String>();
		List<String>date = new ArrayList<String>();
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		int index = 0;
		Collections.sort(existingUser);
		
		for(UserAccountDetail userList: existingUser)
		{
			if(userList != null)
			{
				long inMillisec = Long.parseLong(userList.getClockIn());

				String inFormattedTime = dao.milliSecToTimeConversion(inMillisec,zones);

				userList.setClockIn(inFormattedTime);
				
				lastInTime = inMillisec;
				
				if(userList.getClockOut() != null)
				{
					System.out.println(index+" ");
					
					long outMillisec = Long.parseLong(userList.getClockOut());
					
					String diffInHours = String.valueOf((outMillisec - inMillisec)/(60 * 60 * 1000));
					//System.out.println(outMillisec);
					hours.add(index, diffInHours);
					
					String diffInMins = String.valueOf((outMillisec - inMillisec)/(60 * 1000) % 60);
					mins.add(index, diffInMins);
					
					String diffInSecs = String.valueOf((outMillisec - inMillisec)/1000 % 60);
					secs.add(index, diffInSecs);
					
					String outFormattedTime = dao.milliSecToTimeConversion(outMillisec,zones);
					
					date.add(index, dao.milliSecToDateConversion(inMillisec,zones));

					userList.setClockOut(outFormattedTime);
					
					
				}
				else
				{
					userList.setClockOut("ongoing");
//					System.out.println(userList.getClockOut());
				}
				
//				++index;
			}
			
			index++;
			
//			if(userList.getClockOut() != null)
//			{
//				++index;
//			}
			
		}
		
		map.put("diffInHours", hours);
		map.put("diffInMins", mins);
		map.put("diffInSecs", secs);
		map.put("date", date);
				
		map.put("user", existingUser);
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	@RequestMapping(value = "/clockIn",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String clockIn(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
//		zones = request.getParameter("zone");
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

			if(result == true)
			{
				UserAccountDetail userList = dao.getAccountDetailByMail(email);
				
				long millisec = Long.parseLong(userList.getClockIn());
				
//				long dateDifference = ((millisec - lastInTime) / (24 * 60 * 60 * 1000));
//				
//				if(dateDifference >= 1)
//				{
//					String date = dao.milliSecToDateConversion(millisec);
//					
//					map.put("date", date);
//				}
				
				String formattedTime = dao.milliSecToTimeConversion(millisec,zones);
				String formattedDate = dao.milliSecToDateConversion(millisec,zones);

				userList.setClockIn(formattedTime);
				userList.setClockOut("ongoing");
				
				map.put("date",formattedDate);
				
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
//	zones = request.getParameter("zone");
	
	HashMap<String,Object>map = new HashMap<String, Object>();
	
	boolean result = false;
	
	HttpSession session = request.getSession(true);
	String name = session.getAttribute("name").toString();
	String email = session.getAttribute("email").toString();
	
	long hours = 0;
	long mins = 0;
	long secs = 0;
	
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
		
		if(result == true)
		{
//			UserAccountDetail userList = dao.getAccountDetailByMail(email);
			
			long inMillisec = Long.parseLong(existingUser.getClockIn());

			
			String inFormattedTime = dao.milliSecToTimeConversion(inMillisec,zones);

			existingUser.setClockIn(inFormattedTime);
			
			long outMillisec = Long.parseLong(existingUser.getClockOut());

			
			String outFormattedTime = dao.milliSecToTimeConversion(outMillisec,zones);
//			String outFormattedDate = dao.milliSecToDateConversion(outMillisec,zones);

			existingUser.setClockOut(outFormattedTime);
			
			hours = (outMillisec - inMillisec)/(60 * 60 * 1000);
			mins  = (outMillisec - inMillisec)/(60 * 1000) % 60;
			secs = (outMillisec - inMillisec)/1000 % 60;
			
			map.put("hours", hours);
			map.put("mins", mins);
			map.put("secs", secs);
			
			map.put("date","Current Entries");
			
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




//LogOut logic
//@ResponseBody

@RequestMapping(value = "/logOut",method = RequestMethod.GET)
public String logOut(HttpServletRequest request,HttpServletResponse response) throws IOException
{
	HttpSession session = request.getSession();
	
	session.invalidate();
	
	return "redirect:/";
}

@RequestMapping(value = "/addAdjust",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody String addAdjust(HttpServletRequest request,HttpServletResponse response) throws IOException
{
	String startDate = request.getParameter("start");
	String stopDate = request.getParameter("stop");
	String inTime = request.getParameter("inClk");
	String outTime = request.getParameter("outClk");
	String project = request.getParameter("pro");
	String description = request.getParameter("des");
	
	HttpSession session = request.getSession(false);
	
	String name = session.getAttribute("name").toString();
	String email = session.getAttribute("email").toString();
	
	HashMap<String,Object>map = new HashMap<String, Object>();
	
//	System.out.println(startDate+" "+stopDate+" "+inTime+" "+outTime+" "+project+" "+" "+description);
	
	String myStartDate = startDate+" "+inTime;
	String myStopDate  = stopDate+" "+outTime;
//	String myDate = "2014/10/29 18:10:45";
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//	Date date = sdf.parse(myDate);
//	long millis = date.getTime();
	long startmilli = dao.timeAndDateToMillis(myStartDate);
	long stopmilli = dao.timeAndDateToMillis(myStopDate);
	
	long res = stopmilli - startmilli;
//	Out Specification Cannot be lesser than the start specification
	
	boolean isOverLappingExist = dao.getOverlappingTimings(email, startmilli, stopmilli);
	
	if(isOverLappingExist == true)
	{
		map.put("isOverLappingExist","true");
	}
	else
	{
		if(res < 0)
		{
			map.put("isThisNegativeValue","true");
		}
		else
		{
			UserAccountDetail accDetails = new UserAccountDetail();
			
			accDetails.setId(null);
			accDetails.setEmail(email);
			accDetails.setClockIn(String.valueOf(startmilli));
			accDetails.setClockOut(String.valueOf(stopmilli));
			accDetails.setProject(project);
			accDetails.setTaskDescription(description);
			
			dao.createUserAccDetails(accDetails);
			
			map.put("adjustmentCreated", "true");
		}
	}
	
	String obj = new ObjectMapper().writeValueAsString(map);
	
	return obj;

}



//public long timeAndDateToMillis(String myDate)
//{
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//	Date date = null;
//	try {
//		date = sdf.parse(myDate);
//	} catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	long millis = date.getTime();
//	
//	return millis;
//}


}

package com.org.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.googlecode.objectify.ObjectifyService;
//import com.org.action.Date;
import com.org.model.UserAccountDetail;
import com.org.model.UserAccounts;



@Service ("UserService")
public class UserServiceImpl implements UserService
{
	
	public boolean createUserAcc(UserAccounts users)
	{
		UserAccounts existingUser = getUserByMail(users.getEmail());
	
		if(existingUser == null)
		{
			   ObjectifyService.ofy().save().entity(users);
		        return true;
		}
		return false;
	     
	}
	
	public UserAccounts getUserByMail(String email)
	{
        UserAccounts existingUser=ObjectifyService.ofy().load().type(UserAccounts.class).filter("email",email).first().now();
        
        return existingUser;
	}
	
	public UserAccountDetail getAccountDetailByMail(String email)
	{
		UserAccountDetail existingUser=ObjectifyService.ofy().load().type(UserAccountDetail.class).filter("email",email).filter("clockOut",null).first().now();
        
        return existingUser;
	}
	
	public boolean createUserAccDetails(UserAccountDetail accDetails)
	{
//		System.out.println(accDetails.getClockOut());
//		UserAccountDetail existingUser = new UserAccountDetail();
		UserAccountDetail existingUser = getAccountDetailByMail(accDetails.getEmail());
//		System.out.println("existing user"+existingUser.getClockOut());
				
//		if(existingUser == null)
//		{
		//try {
//			if(existingUser.getClockIn() == null)
//			{	
				ObjectifyService.ofy().save().entity(accDetails);
				return true;
//			}
//		return false;
//		}
//		else if(existingUser.getClockOut() == null)
//		{
//			System.out.println("Inside loop:"+accDetails.getClockOut());
//			existingUser.setClockOut(accDetails.getClockOut());
//			ObjectifyService.ofy().save().entity(existingUser);
//			return true;
//		}
		
//		else if(existingUser.getProject().isEmpty()|| existingUser.getTaskDescription().isEmpty())
//		{
//			System.out.println(accDetails.getProject()+" "+accDetails.getTaskDescription());
//			ObjectifyService.ofy().save().entity(accDetails);
//		}
//		return false;
	     
	}
	
	public String milliSecToTimeConversion(long millisec,String zones)
	{
		LocalDateTime date;
		if(zones == null)
		{
			date =
		    	    LocalDateTime.ofInstant(Instant.ofEpochMilli(millisec), ZoneId.systemDefault());
		}
		else
		{
			date =
		    	    LocalDateTime.ofInstant(Instant.ofEpochMilli(millisec), ZoneId.of(zones));
		}
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
	    String formattedTime = date.format(formatter);
	    
	    return formattedTime;
	}
	
	public String milliSecToDateConversion(long millisec,String zones)
	{
		LocalDateTime date;
		
		if(zones == null)
		{
			date =
		    	    LocalDateTime.ofInstant(Instant.ofEpochMilli(millisec), ZoneId.systemDefault());
		}
		else
		{
			date =
		    	    LocalDateTime.ofInstant(Instant.ofEpochMilli(millisec), ZoneId.of(zones));
		}
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
	    String formattedDate = date.format(formatter);
	    
	    return formattedDate;
	}
	
	/*public List<UserAccountDetail> getAccountDetailList(String email)
	{
		List<UserAccountDetail> existingUser=ObjectifyService.ofy().load().type(UserAccountDetail.class).filter("email",email).list();
        
        return existingUser;
	}*/
	
	public List<UserAccountDetail> getAccountListByMail(String email)
	{
		List<UserAccountDetail>UserDetailList = ObjectifyService.ofy().load().type(UserAccountDetail.class).filter("email ==",email).list();
		return UserDetailList;
	}
	
//	public long timeAndDateToMillis(String myDate)
//	{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		Date date = sdf.parse(myDate);
//		long millis = date.getTime();
//	}
	
	
	public long timeAndDateToMillis(String myDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(myDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long millis = date.getTime();
		
		return millis;
	}

	public boolean getOverlappingTimings(String email,long startMilli,long stopMilli)
	{
		boolean checkExistTimings = false;
		UserAccountDetail existingTimings = ObjectifyService.ofy().load().type(UserAccountDetail.class).filter("email",email).filter("clockIn >= ",startMilli).filter("clockOut <= ",stopMilli).first().now();
        
		if(existingTimings != null)
		{
			checkExistTimings = true;
		}
		
        return checkExistTimings;
	}
	
}

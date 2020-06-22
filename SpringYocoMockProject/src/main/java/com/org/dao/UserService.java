package com.org.dao;

import java.util.List;

import com.org.model.UserAccountDetail;
import com.org.model.UserAccounts;

public interface UserService {
	
	boolean createUserAcc(UserAccounts users);
	UserAccounts getUserByMail(String email);
	UserAccountDetail getAccountDetailByMail(String email);
	boolean createUserAccDetails(UserAccountDetail accDetails);
	String milliSecToTimeConversion(long millisec,String zones);
	//List<UserAccountDetail> getAccountDetailList(String email);
	String milliSecToDateConversion(long millisec,String zones);
	public List<UserAccountDetail> getAccountListByMail(String email);
}

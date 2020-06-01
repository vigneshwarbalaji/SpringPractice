package com.org.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserAccountDetail {
	
	@Id
	Long id;
	@Index
	String email;
	
	String project;
	String taskDescription;
	String clockIn;
	@Index
	String clockOut;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getClockIn() {
		return clockIn;
	}
	public void setClockIn(String clockIn) {
		this.clockIn = clockIn;
	}
	public String getClockOut() {
		return clockOut;
	}
	public void setClockOut(String clockOut) {
		this.clockOut = clockOut;
	}
	
	

}

package com.org.model;

//import javax.jdo.annotations.Index;

//import com.googlecode.objectify.annotation.Entity;


public class ToDoListEntity 
{
	String email;
	String project;
	String taskDescription;
	
		
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
	
//	public String toString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("email: ").append(getEmail());
//        sb.append(" - project: ").append(getProject());
//        sb.append(" - TaskDescription: ").append(getTaskDescription());
//        return sb.toString();
//    }
}

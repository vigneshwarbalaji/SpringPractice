package com.org.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ToDoListEntity")
public class ToDoConverter {
 private ToDoListEntity entity = null;
 public ToDoConverter() {
 entity = new ToDoListEntity();
 }
 
 public ToDoConverter(ToDoListEntity entity) {
 this.entity = entity;
 }
 
 @XmlElement
 public String getEmail() {
 return entity.getEmail();
 }
 
 @XmlElement
 public String getProject() {
 return entity.getProject();
 }
 
 @XmlElement
 public String getTaskDescription() {
 return entity.getTaskDescription();
 }
 
 /*
 public ToDoListEntity getDoListEntity() {
 return entity;
 }
 */
 public void setEmail(String email) {
 entity.setEmail(email);
 }
 
 public void setProject(String project) {
 entity.setProject(project);
 }
 
 public void setTaskDescription(String TaskDescription) {
 entity.setTaskDescription(TaskDescription);
 }
}
package com.org;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;



//@RequestMapping(value = "/OperationsController")
@Controller
public class OperationsController 
{	
	@RequestMapping(value = "/add",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String add(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int a = Integer.parseInt(req.getParameter("numOne"));
		int b = Integer.parseInt(req.getParameter("numTwo"));
		
		System.out.println("test1"+a+" "+b);
		
		int c = a + b;
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		String value = Integer.toString(c);
		
		map.put("value",value);
		
		//System.out.println("test2"+map.size());
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	
	
	@RequestMapping(value = "/sub",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String sub(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int a = Integer.parseInt(req.getParameter("numOne"));
		int b = Integer.parseInt(req.getParameter("numTwo"));
		
		System.out.println("test1"+a+" "+b);
		
		int c = a - b;
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		String value = Integer.toString(c);
		
		map.put("value",value);
		
		//System.out.println("test2"+map.size());
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	
	@RequestMapping(value = "/mul",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String mul(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int a = Integer.parseInt(req.getParameter("numOne"));
		int b = Integer.parseInt(req.getParameter("numTwo"));
		
		//System.out.println("test1"+a+" "+b);
		
		int c = a * b;
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		String value = Integer.toString(c);
		
		map.put("value",value);
		
		//System.out.println("test2"+map.size());
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	@RequestMapping(value = "/div",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String div(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int a = Integer.parseInt(req.getParameter("numOne"));
		int b = Integer.parseInt(req.getParameter("numTwo"));
		
		//System.out.println("test1"+a+" "+b);
		
		int c = a / b;
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		String value = Integer.toString(c);
		
		map.put("value",value);
		
		//System.out.println("test2"+map.size());
		
		String obj = new ObjectMapper().writeValueAsString(map);
		
		return obj;
	}
	
	
}


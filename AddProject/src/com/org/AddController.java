package com.org;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.model.SimpleInteger;

@Controller
//@RequestMapping(value = "/AddController" )
public class AddController 
{
   //private int response;

	
	  @RequestMapping(value = "/AddController",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HashMap<String, Object> add(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		//int a = Integer.parseInt(req.getParameter("numOne"));
		//int b = Integer.parseInt(req.getParameter("numTwo"));
		
		//System.out.println(a+" "+b);
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		//int c = a + b;
		
		
	
		
		map.put("name","Vignesh");
		
		return map;
		
		//return new SimpleInteger(Integer.toString(c));
		//return c;
		
		//this.response = c;
		
		//return "this.response";
//		
//		System.out.println(this.response);
		//res.getWriter().print(c);
		
		//return "index";
		//return JSONObject.quote("Hello World");

	}
}

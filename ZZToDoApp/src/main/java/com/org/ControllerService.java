package com.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.sun.mail.iap.Response;

@Path("/")
public class ControllerService {

	
	@Context
	HttpServletResponse response;
	
	@Context
	ServletContext servletContext;
	
	@GET
	public String testMethod() throws IOException
	{
		
		InputStream st=servletContext.getResourceAsStream("/index.jsp");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(st));

		String string = new String();
		String modifiedHtml = "";
		while( (string = buffReader.readLine() ) != null){
		modifiedHtml=modifiedHtml+string;
		}

		buffReader.close();
		st.close();
		
		
		
		return modifiedHtml;
		
	}
	
	@Path("/api")
	@GET
	public String testNew() throws IOException
	{
		
//		response.sendRedirect("index.jsp");
		
		System.out.println("api test method............................................................");
		response.setContentType("text/html");
//		response.getWriter().write("test String");
		
		return "new String";
	}
	
	
	
}

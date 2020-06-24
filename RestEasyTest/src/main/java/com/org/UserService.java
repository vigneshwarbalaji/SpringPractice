package com.org;

import java.io.IOException;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//import org.codehaus.jackson.map.ObjectMapper;

//import com.org.dao.UserToDoService;
//import com.org.dao.UserToDoServiceImpl;
//import com.org.model.ToDoListApp;
 


//@Path("/")
//public class UserService {
//   
// 
//    @GET
//    @Path("/")
//    public Response getServiceInfo() {
//    	 String result = "<h1>RESTEasy Demo Application</h1>Hello there , welcome to our website";
//         return Response.status(200).entity(result).build();
//    }
// 
//  
// 
//    @GET
//    @Path("/user/{id}")
//    public Response getUserById(@PathParam("id") int id) {
//        User user = new User();
//        user.setId(id);
//        user.setFirstName("demo");
//        user.setLastName("user");
//        
//        return Response.status(200).entity(user).build();
//    }
//    
//    @GET
//    @Path("/book/{id}")
//    public Response getBookId(@PathParam("id") PathSegment id) {
//    	
//		return Response.status(200).entity(id).build();
//    	
//    }
//    
//    
//    
// 
//   
//}




@Path("/")
public class UserService  {
	//private static final long serialVersionUID = 1L;
//	UserToDoService dao = (UserToDoService) new UserToDoServiceImpl();
	
	@GET
	@Path("/ControllerServlet")
//	@Produces("application/json")
	public String signIn(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
				
		String email = request.getParameter("email").trim();
//		System.out.println(email);
		String pass = request.getParameter("pass").trim();
		
		HashMap<String,Object>map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(false);
		
		if(email.isEmpty()||pass.isEmpty())
		{
			map.put("value","please fill all the details");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else
		{
//			UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			
//			ToDoListApp userToDo = dao.getUserByMail(email);
			
			
//			if(userToDo == null)
//			{
//				map.put("value","User doesnot exist.Please register.");
//				//response.getWriter().print("<h6>User doesnot exist.Please register.</h6>");
//			}
//			else if(!(userToDo.getPassword().equals(pass)))
//			{
//				map.put("value","Password incorrect");
//				//response.getWriter().print("<h6>Password incorrect</h6>");
//			}
//			else
//			{	
//				session = request.getSession(true);
//				session.setAttribute("email", userToDo.getEmail());
//				session.setAttribute("name", userToDo.getName());
//				//System.out.println(userAcc.getEmail());
//				
//				//response.getWriter().print("true");		
//				map.put("value","true");
//				
//				//return "/Dashboard";
//				
////				UserAccountDetail userdetail = dao.getAccountDetailByMail(email);
//				
////				ObjectMapper objectMapper = new ObjectMapper();
//
//				
////				objectMapper.writeValueAsString(userdetail);
////				
////				return objectMapper; 
//				
////				map.put("userdetail", userdetail);
//				
////				map.put("userdetail",userdetail);
//			}
//		}
//		
////		String obj = new ObjectMapper().writeValueAsString(map);
//		
//		
//		return obj;
		
		}
		return "sampleobject ";
	}
	
	@POST
	@Path("ControllerServlet")
//	@Produces("application/json")
	public String signUp(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String name = request.getParameter("name").trim();
		String email = request.getParameter("email").trim();
		String pass = request.getParameter("pass").trim();
		String rePass = request.getParameter("rePass").trim();
		
		HashMap<String,Object>map = new HashMap<String, Object>();
        HttpSession session = request.getSession(false);

		//PrintWriter out = response.getWriter();
		
		int passLength = pass.length();
		int rePassLength = rePass.length();
		
		
		if((name.isEmpty()||email.isEmpty()||
				pass.isEmpty() ||rePass.isEmpty()))
		{
			map.put("value","please fill all the details");
			//response.getWriter().print("<h6>please fill all the details</h6>");
		}
		else if(!(pass.equals(rePass)) || passLength != rePassLength)
		{
			map.put("value","Password doesn't match");
			//response.getWriter().print("<h6>Password doesn't match</h6>");
		}
		else if(pass.length() < 6 || rePass.length() < 6)
		{
			map.put("value","Password must atleast be of length 6");
			//response.getWriter().print("<h6>Password must atleast be of length 6</h6>");
		}
		else
		{
//			ToDoListApp users = new ToDoListApp();
//			boolean result = false;
//			
//			users.setId(null);
//			users.setName(name);
//			users.setEmail(email);
//			users.setPassword(pass);
//			//user.setId(user.getId());
			
			//UserService dao = (UserService) this.getServletContext().getAttribute("dao");
			
//			result = dao.createUserAcc(users);
//			
//			if(result == true)
//			{
//				session = request.getSession(true);
//				session.setAttribute("email", users.getEmail());
//				session.setAttribute("name", users.getName());
//				map.put("value","true");
//				
//			}
//			else
//			{
//				map.put("value","Account Already exists"+"<br>");
//				//response.getWriter().print("<h6>Account Already exists</h6>"+"<br>");
//			}
//			
		}
		
//		String obj = new ObjectMapper().writeValueAsString(map);
		
//		return obj;
		
		return "SignUpTestObject";
	}
}
	



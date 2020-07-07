package com.org.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Redirect 
{
	@RequestMapping("/SignUp")  
    public String signupRedirect()  
    {  
        return "Signup";  
    }
	
	@RequestMapping("/Dashboard")  
    public String dashRedirect()  
    {  
        return "Dashboard";  
    }
	
	/*@RequestMapping("/Login")  
    public String loginRedirect()  
    {  
        return "/";  
    }*/
}

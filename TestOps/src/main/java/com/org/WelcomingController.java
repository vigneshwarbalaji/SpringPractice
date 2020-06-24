package com.org;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomingController 
{
	@RequestMapping("/hello")  
    public String redirect()  
    {  
        return "Hello";  
    }
	
	@RequestMapping("/hi")  
    public String redirection()  
    {  
        return "Hi";  
    }
}

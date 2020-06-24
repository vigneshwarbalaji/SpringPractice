package com.org.model;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SimpleInteger 
{
	String add;
	
	


    public SimpleInteger(String add) {
      this.add = add;
    }

}

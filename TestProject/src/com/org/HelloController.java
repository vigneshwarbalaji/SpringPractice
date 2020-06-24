package com.org;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/")
public class HelloController {
   @RequestMapping(value = "/hello", method = RequestMethod.GET)public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
   
   
   
   
//   @RequestMapping(method = RequestMethod.GET)public String printHello2(ModelMap model) {
//	      model.addAttribute("message", "Hello Spring MVC Framework!");
//	      return "hello";
//	   }
}

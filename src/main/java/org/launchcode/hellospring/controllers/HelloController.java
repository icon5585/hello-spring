package org.launchcode.hellospring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class to represent a simple "hello" controller
 * 
 * @author Hank DeDona
 */
// 10.1.2.1 - Controller annotation - This designates this class as a controller to spring
@Controller
//@RequestMapping(value="hello")	// Note: This is allowed at the class level, would make ALL methods below after /hello/... 
//@ResponseBody		// Note: Allowed if all methods have @ResponseBody
public class HelloController {

	// 10.1.2.2 - Controllers Map to requests
	// There are multiple ways to map requests, this is 3 of them below...
	// In order for the controller to "serve" requests to users, it needs a mapping.

	// 10.1.2.3 - @ResponseBody
	// This annotation tells spring that the return value is the body, instead of a
	// template or model.

	// We can do this through the @GetMapping annotation for GET requests.
	// This would map all GET requests to /hello to here
	@RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
	public String hello(@RequestParam String coder, Model model) {
		// 10.2.3 - @RequestParam - allows for query params to be passed into your controller, in 
		// this case it looks for a query param called "coder". e.g. http://localhost:8080/hello?coder=billy
		
		// 11.3.2 - Pass variable into view
		// In order to do this, we're going to use the Spring provided class Model
		String helloMessage = "Hello, " + coder + "!";
		
		// Add it to the model, this will be provided to the view
		model.addAttribute("greeting", helloMessage);
		
		return "hello";
	}

	
	// 10.2.5 - @PathVariable - This allows us to pass in parameters through the path (instead of query parms above).
	// Responds to get requests at e.g. http://localhost:8080/hello/LaunchCode
	@GetMapping("hello/{name}")
	public String helloAgain(@PathVariable String name, Model model) {
		String helloMessage = "Hello, " + name + "!";
		
		model.addAttribute("greeting", helloMessage);
		
	   return "hello";
	}
	
	
	// This would map all POST requests to /goodbye to here
	@PostMapping("goodbye")
	@ResponseBody
	public String goodbye() {
		return "Goodbye, world";
	}

	// This would respond to GET and POST requests at /hellogoodbye
	@RequestMapping(value = "hellogoodbye", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String hellogoodbye() {
		return "Hello and goodbye, world";
	}
	
	
	// 10.3.2 - Sending form data via text 
	// Note: Nobody does this, please don't do this, it's only for demonstration purposes!
	@GetMapping("form")
	public String helloForm() {
		// 11.2.3 - Tell controller to return form.html template
		// This refers to the form.html file!
		// Note: No longer need @ResponseBody 
	   return "form";
	}
	
	// 11.4 - Controller for list to use for/each loop
	@GetMapping("hello-names")
	public String helloNames(Model model) {
		List<String> names = new ArrayList<>();
		
		names.add("LaunchCode");
		names.add("Java");
		names.add("JavaScript");
		
		// Pass names into the model
		model.addAttribute("names", names);
		
		return "hello-list";
	}
	
	
	

}

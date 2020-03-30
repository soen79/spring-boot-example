package springbootapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController             // Marks this class as being able to handle Web Requests
public class HelloController {
	
	@RequestMapping("/")     // Marks this method  to the '/' in the web request
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
}

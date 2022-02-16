package app.core.part4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.part3.util.JwtUtil;
import app.core.part3.util.JwtUtil.ClientDetails;

@RestController
public class MyController {

	@Autowired
	private JwtUtil jwtUtil;

	@PutMapping("/register")
	public String registerAndGetToken(@RequestBody ClientDetails clientDetails) {
		return jwtUtil.generateToken(clientDetails);
	}

	@GetMapping("/greet")
	public String greet(@RequestHeader String token) {
		try {
			ClientDetails clientDetails = jwtUtil.extractClient(token);
			return "Hello " + clientDetails;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "token invalid: " + e.getMessage());
		}
	}

}

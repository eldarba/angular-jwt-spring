package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.part3.util.JwtUtil;
import app.core.part3.util.JwtUtil.ClientDetails;
import app.core.part3.util.JwtUtil.ClientDetails.ClientType;
import app.core.services.SchoolService;

@RestController
public class LoginController {

	@Autowired
	private SchoolService schoolService;
	@Autowired
	private JwtUtil jwtUtil;

	@PutMapping("/login")
	public String login(String email, String password) {
		if (this.schoolService.login(email, password)) {
			return this.jwtUtil.generateToken(new ClientDetails(email, ClientType.ADMIN, 0));
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "bad login credentials");
		}
	}
}

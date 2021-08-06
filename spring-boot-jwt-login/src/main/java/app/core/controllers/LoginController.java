package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.services.JwtUtil;
import app.core.services.JwtUtil.UserDetails;
import app.core.services.JwtUtil.UserDetails.UserType;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private JwtUtil jwtUtil;

	@PutMapping
	public String login(@RequestParam String userId, @RequestParam String userEmail, @RequestParam UserType userType) {
		UserDetails userDetails = new UserDetails(userId, userEmail, userType);
		switch (userType) {
		case ADMIN:
			if (!(userId.equals("admin") && userEmail.equals("admin@mail"))) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "admin login failed - bad credentials");
			}

			break;
		case COMPANY:
		case CUSTOMER:
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "company and customer login not yet supported");

		default:
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "bad user type");
		}
		String token = jwtUtil.generateToken(userDetails);
		return token;
	}

}

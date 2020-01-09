package com.eldar.products2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eldar.products2.jwt.models.AuthenticationRequest;
import com.eldar.products2.jwt.models.AuthenticationResponse;
import com.eldar.products2.jwt.services.MyUserDetailsService;
import com.eldar.products2.jwt.util.JwtUtil;

/*This class is an API we want to put behind an authentication wall. 
 * 
 * Since Spring Security is added to the classpath this is kind of the default behavior.
 * 
 *  We are going to override the default out-of-the-box user behavior this API is being put behind.
 *  
 *  We are going to seed a user
 *  */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class HelloResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> creatAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "{\"msg\": \"Hello World!\"}";
	}

}

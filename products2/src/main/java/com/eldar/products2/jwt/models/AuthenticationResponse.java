package com.eldar.products2.jwt.models;

public class AuthenticationResponse {

	private String jwt;

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}

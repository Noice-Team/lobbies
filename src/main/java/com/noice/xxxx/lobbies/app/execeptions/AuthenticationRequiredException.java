package com.noice.xxxx.lobbies.app.execeptions;

public class AuthenticationRequiredException extends LobbyException {

	public AuthenticationRequiredException() {
		super("Authentication required");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4755484602396509428L;

	@Override
	public String getCode() {
		return "00004";
	}
}

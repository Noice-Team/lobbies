package com.noice.xxxx.lobbies.app.execeptions.join;

import com.noice.xxxx.lobbies.app.execeptions.LobbyException;

public class LobbyFullException extends LobbyException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -683471731574029328L;

	@Override
	public String getCode() {
		return "00006";
	}

}

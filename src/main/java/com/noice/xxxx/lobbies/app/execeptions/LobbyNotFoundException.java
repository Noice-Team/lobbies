package com.noice.xxxx.lobbies.app.execeptions;

public class LobbyNotFoundException extends LobbyException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 651691318L;

	@Override
	public String getCode() {
		return "00001";
	}

}

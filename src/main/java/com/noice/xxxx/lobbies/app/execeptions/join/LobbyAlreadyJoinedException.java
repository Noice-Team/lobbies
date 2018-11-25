package com.noice.xxxx.lobbies.app.execeptions.join;

import com.noice.xxxx.lobbies.app.execeptions.LobbyException;

public class LobbyAlreadyJoinedException extends LobbyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9045560429618390314L;


	@Override
	public String getCode() {
		return "00005";
	}
	
}

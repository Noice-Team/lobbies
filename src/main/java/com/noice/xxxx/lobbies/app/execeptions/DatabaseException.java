package com.noice.xxxx.lobbies.app.execeptions;

public class DatabaseException extends LobbyException {

	public DatabaseException() {
		super();
	}
	
	public DatabaseException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8791386L;

	@Override
	public String getCode() {
		return "00003";
	}
}

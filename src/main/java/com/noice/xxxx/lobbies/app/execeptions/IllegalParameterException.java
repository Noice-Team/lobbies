package com.noice.xxxx.lobbies.app.execeptions;

public class IllegalParameterException extends LobbyException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -900433299840415545L;

	public IllegalParameterException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IllegalParameterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IllegalParameterException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalParameterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalParameterException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCode() {
		return "00002";
	}
}

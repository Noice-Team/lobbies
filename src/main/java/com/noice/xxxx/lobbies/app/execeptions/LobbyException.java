/**
 * 
 */
package com.noice.xxxx.lobbies.app.execeptions;

/**
 * @author kamule
 *
 */
public abstract class LobbyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9032731375238229704L;

	/**
	 * 
	 */
	public LobbyException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public LobbyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public LobbyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LobbyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public LobbyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public abstract String getCode();
	
}

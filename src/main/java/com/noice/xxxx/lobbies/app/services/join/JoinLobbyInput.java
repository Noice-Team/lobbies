package com.noice.xxxx.lobbies.app.services.join;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class JoinLobbyInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5385789102619033561L;
	
	
	private String id;

}

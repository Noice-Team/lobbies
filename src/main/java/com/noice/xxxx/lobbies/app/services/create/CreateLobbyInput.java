package com.noice.xxxx.lobbies.app.services.create;

import java.io.Serializable;

import com.noice.xxxx.lobbies.app.enums.GameType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CreateLobbyInput implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5084863284870557052L;

	private String _name;
	private GameType _gameType;
	private int _size;
}

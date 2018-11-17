package com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.noice.xxxx.lobbies.app.enums.GameType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LobbyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2881352238361651113L;
	private String _id;
	private String _name;
	private String _owner;
	private GameType _gameType;
	private int _size;
	private Date _creationDate;
	private List<String> _members;
}

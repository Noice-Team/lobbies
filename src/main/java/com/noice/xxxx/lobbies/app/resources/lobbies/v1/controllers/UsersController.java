package com.noice.xxxx.lobbies.app.resources.lobbies.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noice.xxxx.lobbies.app.db.lobbies.LobbiesDao;
import com.noice.xxxx.lobbies.app.execeptions.DatabaseException;
import com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto.LobbyDto;

@RestController
public class UsersController {
	public static final String NAME = "/lobbies";

	@Autowired
	private LobbiesDao dao;

	
	@RequestMapping(value = NAME, method = RequestMethod.GET)
	public List<LobbyDto> listUsers() throws DatabaseException {
		return this.dao.getLobbies();
	}
}

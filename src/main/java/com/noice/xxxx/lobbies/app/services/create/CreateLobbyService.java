package com.noice.xxxx.lobbies.app.services.create;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noice.xxxx.lobbies.app.db.lobbies.LobbiesDao;
import com.noice.xxxx.lobbies.app.execeptions.AuthenticationRequiredException;
import com.noice.xxxx.lobbies.app.execeptions.DatabaseException;
import com.noice.xxxx.lobbies.app.execeptions.IllegalParameterException;
import com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto.LobbyDto;
import com.noice.xxxx.lobbies.app.services.FireAuthControllerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateLobbyService {

	@Autowired
	private FireAuthControllerService auth;
	
	@Autowired
	private LobbiesDao dao;
	
	public String create(String authToken, CreateLobbyInput data) throws AuthenticationRequiredException, DatabaseException, IllegalParameterException {
		String uid = auth.controlToken(authToken);
		this.controlInput(authToken, data);
		return createLobby(uid, data);
	}
	
	public void controlInput(String authToken, CreateLobbyInput data) throws IllegalParameterException {
		if(data.get_size() < 1 ) {
			throw new IllegalParameterException("size");
		}
	}

	private String createLobby(String uid, CreateLobbyInput data) throws DatabaseException {
		List<String> list = new ArrayList<>();
		list.add(uid);
		
		return dao.create(LobbyDto.builder()
			.owner(uid)
			.creationDate(new Date())
			.gameType(data.get_gameType())
			.name(data.get_name())
			.size(data.get_size())
			.members(list )
		.build());
	}
}

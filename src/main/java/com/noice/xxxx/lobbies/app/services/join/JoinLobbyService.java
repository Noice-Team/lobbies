package com.noice.xxxx.lobbies.app.services.join;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noice.xxxx.lobbies.app.db.lobbies.LobbiesDao;
import com.noice.xxxx.lobbies.app.execeptions.AuthenticationRequiredException;
import com.noice.xxxx.lobbies.app.execeptions.DatabaseException;
import com.noice.xxxx.lobbies.app.execeptions.LobbyNotFoundException;
import com.noice.xxxx.lobbies.app.execeptions.join.LobbyAlreadyJoinedException;
import com.noice.xxxx.lobbies.app.execeptions.join.LobbyFullException;
import com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto.LobbyDto;
import com.noice.xxxx.lobbies.app.services.FireAuthControllerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JoinLobbyService {

	@Autowired
	private FireAuthControllerService auth;

	@Autowired
	LobbiesDao dao;
	
	public void join(String authToken, String lobbyId) throws LobbyNotFoundException, DatabaseException, AuthenticationRequiredException, LobbyAlreadyJoinedException, LobbyFullException {
		LobbyDto lobby = this.getLobby(lobbyId);
		String uid = auth.controlToken(authToken);
		this.controlInput(authToken, lobbyId, lobby, uid);
		this.add(lobby, uid);
	}

	public void controlInput(String authToken, String id, LobbyDto lobby, String userId) throws LobbyAlreadyJoinedException, LobbyFullException {
		if(lobby.getMembers() == null) {
			lobby.setMembers(new ArrayList<>());
			lobby.getMembers().add(lobby.getOwner());
		}
		if(lobby.getMembers().contains(userId)) {
			throw new LobbyAlreadyJoinedException(); 
		}
		if(lobby.getSize() <= lobby.getMembers().size()) {
			throw new LobbyFullException();
		}
	}
	
	private LobbyDto getLobby(String id) throws LobbyNotFoundException, DatabaseException {
		return dao.getLobby(id);
	}
	
	private void add(LobbyDto lobby, String uid) {
		lobby.getMembers().add(uid);
		this.dao.save(lobby);
	}
}

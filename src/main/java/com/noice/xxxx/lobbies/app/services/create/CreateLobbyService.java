package com.noice.xxxx.lobbies.app.services.create;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.noice.xxxx.lobbies.app.db.lobbies.LobbiesDao;
import com.noice.xxxx.lobbies.app.execeptions.AuthenticationRequiredException;
import com.noice.xxxx.lobbies.app.execeptions.DatabaseException;
import com.noice.xxxx.lobbies.app.execeptions.IllegalParameterException;
import com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto.LobbyDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateLobbyService {
	
	@Autowired
	FirebaseAuth auth;

	@Autowired
	LobbiesDao dao;
	
	public String create(String authToken, CreateLobbyInput data) throws AuthenticationRequiredException, DatabaseException, IllegalParameterException {
		String uid = this.controlInput(authToken, data);
		return createLobby(uid, data);
	}
	
	public String controlInput(String authToken, CreateLobbyInput data) throws AuthenticationRequiredException, IllegalParameterException {
		String uid = controlFireAuth(authToken);
		if(data.get_size() < 1 ) {
			throw new IllegalParameterException("size");
		}
		return uid;
	}
	
	private String controlFireAuth(String authToken) throws AuthenticationRequiredException {
		if(authToken == null) {
			throw new AuthenticationRequiredException();
		}
		
		FirebaseToken decodedToken;
		try {
			decodedToken = auth.verifyIdToken(authToken);
		} catch (FirebaseAuthException e) {
			log.error(new StringBuilder().append("Firebase auth failure with id ").append(authToken).toString(), e);
			throw new AuthenticationRequiredException();
		}
		String uid = decodedToken.getUid();
		if(uid == null) {
			log.error(new StringBuilder().append("Firebase auth failure with id ").append(authToken).toString());
			throw new AuthenticationRequiredException();
		}
		return uid;
	}

	private String createLobby(String uid, CreateLobbyInput data) throws DatabaseException {
		List<String> list = new ArrayList<>();
		list.add(uid);
		
		return dao.create(LobbyDto.builder()
			._owner(uid)
			._creationDate(new Date())
			._gameType(data.get_gameType())
			._name(data.get_name())
			._size(data.get_size())
			._members(list )
		.build());
	}
}

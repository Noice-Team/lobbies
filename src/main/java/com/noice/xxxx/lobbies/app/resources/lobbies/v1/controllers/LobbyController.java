package com.noice.xxxx.lobbies.app.resources.lobbies.v1.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.noice.xxxx.lobbies.app.db.lobbies.LobbiesDao;
import com.noice.xxxx.lobbies.app.execeptions.AuthenticationRequiredException;
import com.noice.xxxx.lobbies.app.execeptions.DatabaseException;
import com.noice.xxxx.lobbies.app.execeptions.IllegalParameterException;
import com.noice.xxxx.lobbies.app.execeptions.LobbyNotFoundException;
import com.noice.xxxx.lobbies.app.execeptions.join.LobbyAlreadyJoinedException;
import com.noice.xxxx.lobbies.app.execeptions.join.LobbyFullException;
import com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto.LobbyDto;
import com.noice.xxxx.lobbies.app.services.create.CreateLobbyInput;
import com.noice.xxxx.lobbies.app.services.create.CreateLobbyService;
import com.noice.xxxx.lobbies.app.services.join.JoinLobbyInput;
import com.noice.xxxx.lobbies.app.services.join.JoinLobbyService;

@RestController
public class LobbyController {
	public static final String NAME = "/lobbies";

	public static final String TOKEN = "FIREBASE_ID_TOKEN";
	
	@Autowired
	private CreateLobbyService createService;
	
	@Autowired
	private JoinLobbyService joinService;
	
	@PostMapping(value = NAME)
	@ResponseBody
	public ResponseEntity<LobbyDto> createLobby(
		@RequestHeader(value=TOKEN) String authToken,
		@RequestBody CreateLobbyInput data) throws AuthenticationRequiredException, DatabaseException, IllegalParameterException {

		String created = createService.create(authToken, data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(created).toUri();		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping(value = NAME)
	@ResponseBody
	public ResponseEntity<LobbyDto> joinLobby(
		@RequestHeader(value=TOKEN) String authToken,
		@RequestBody JoinLobbyInput data) throws LobbyNotFoundException, DatabaseException, AuthenticationRequiredException, LobbyAlreadyJoinedException, LobbyFullException  {

		joinService.join(authToken, data.getId());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(data.getId()).toUri();		
		return ResponseEntity.created(location).build();
	}
}

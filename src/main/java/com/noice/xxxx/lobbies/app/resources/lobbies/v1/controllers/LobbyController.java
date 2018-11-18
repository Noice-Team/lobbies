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
import com.noice.xxxx.lobbies.app.execeptions.LobbyNotFoundException;
import com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto.LobbyDto;
import com.noice.xxxx.lobbies.app.services.create.CreateLobbyInput;
import com.noice.xxxx.lobbies.app.services.create.CreateLobbyService;

@RestController
public class LobbyController {
	public static final String NAME = "/lobbies";

	public static final String TOKEN = "FIREBASE_ID_TOKEN";
	
	@Autowired
	private LobbiesDao dao;
	
	@Autowired
	private CreateLobbyService createService;

	
	@GetMapping(value = NAME)
	public List<LobbyDto> getAllLobbies() throws DatabaseException {
		return this.dao.getLobbies();
	}
	
	@GetMapping(value = NAME+"/{id}")
	public LobbyDto getLobby(
		@PathVariable("id") String id) throws DatabaseException, LobbyNotFoundException {
		return this.dao.getLobby(id);
	}
	
	@PostMapping(value = NAME)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"})
	public ResponseEntity<LobbyDto> createLobby(
		@RequestHeader(value=TOKEN) String authToken,
		@RequestBody CreateLobbyInput data) throws AuthenticationRequiredException, DatabaseException {

		String created = createService.create(authToken, data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(created).toUri();		
		return ResponseEntity.created(location).build();
	}
}

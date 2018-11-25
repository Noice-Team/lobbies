package com.noice.xxxx.lobbies.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.noice.xxxx.lobbies.app.execeptions.AuthenticationRequiredException;
import com.noice.xxxx.lobbies.app.services.create.CreateLobbyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FireAuthControllerService {
	
	@Autowired
	FirebaseAuth auth;
	
	public String controlToken(String authToken) throws AuthenticationRequiredException {
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
}

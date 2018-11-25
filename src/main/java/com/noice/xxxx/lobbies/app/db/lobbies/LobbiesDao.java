package com.noice.xxxx.lobbies.app.db.lobbies;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.noice.xxxx.lobbies.app.execeptions.DatabaseException;
import com.noice.xxxx.lobbies.app.execeptions.LobbyNotFoundException;
import com.noice.xxxx.lobbies.app.resources.lobbies.v1.dto.LobbyDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LobbiesDao {
	private static final String LOBBIES_COLLECTION = "Lobbies";

	@Autowired
	Firestore db;

	public List<LobbyDto> getLobbies() throws DatabaseException {
		ApiFuture<QuerySnapshot> future = db.collection(LOBBIES_COLLECTION).get();
		List<QueryDocumentSnapshot> documents;
		try {
			documents = future.get().getDocuments();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error load", e);
			throw new DatabaseException(e);
		}

		return documents.stream().map(doc -> doc.toObject(LobbyDto.class)).collect(Collectors.toList());
	}

	public String create(LobbyDto data) throws DatabaseException {
		ApiFuture<DocumentReference> future = db.collection(LOBBIES_COLLECTION).add(data);
		DocumentReference document;
		try {
			document = future.get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error create", e);
			throw new DatabaseException(e);
		}
		return document.getId();
	}

	public LobbyDto getLobby(String id) throws LobbyNotFoundException, DatabaseException {
		DocumentReference docRef = db.collection(LOBBIES_COLLECTION).document(id);

		DocumentSnapshot snapshot;
		try {
			snapshot = docRef.get().get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error load", e);
			throw new DatabaseException(e);
		}
		if (!snapshot.exists()) {
			throw new LobbyNotFoundException();
		}
		LobbyDto model = snapshot.toObject(LobbyDto.class);
		return model;
	}

	public void save(LobbyDto lobby) {
		db.collection(LOBBIES_COLLECTION)
				.document(lobby.get_id())
				.set(lobby);
	}
}

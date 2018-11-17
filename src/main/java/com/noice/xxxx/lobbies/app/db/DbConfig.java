package com.noice.xxxx.lobbies.app.db;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class DbConfig {
	
	@Value("${firebase.app}")
	private String appId;
	
	@Bean
	public Firestore getAccountService() throws IOException {
		GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .setProjectId(appId)
		    .build();
		FirebaseApp.initializeApp(options);

		return FirestoreClient.getFirestore();
   }
}

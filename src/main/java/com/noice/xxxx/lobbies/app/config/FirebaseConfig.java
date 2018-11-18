package com.noice.xxxx.lobbies.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseConfig {

	@Value("${firebase.app}")
	private static String appId;

	private static boolean isInitialized = false;

	@Bean
	public Firestore getFirestore() throws IOException {
		initialize();
		return FirestoreClient.getFirestore();
	}
	
	
	@Bean
	public FirebaseAuth getFireAuth() throws IOException {
		initialize();
		return FirebaseAuth.getInstance();
	}
	
	
	private static void initialize() throws IOException {
		if (isInitialized) {
			return;
		}
		GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
		FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).setProjectId(appId).build();
		FirebaseApp.initializeApp(options);

		isInitialized = true;
	}
}

package com.example.lab11_ea

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthService(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    suspend fun login(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getUserRole(userId: String): String? {
        val snapshot = firestore.collection("users").document(userId).get().await()
        return snapshot.getString("role")
    }
}
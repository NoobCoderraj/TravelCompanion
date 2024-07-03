package com.example.travelcompanionapp.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class AuthViewModel:ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val fireStore:FirebaseFirestore = FirebaseFirestore.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun signIn(email:String, password:String , onComplete: (Boolean) ->Unit){

        auth.signInWithEmailAndPassword(email ,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    onComplete(true )
                }else {
                    onComplete(false)
                }
            }
    }


    fun signUp(email:String,password: String,firstName:String, lastName:String, onComplete:(Boolean)->Unit){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userProfile = hashMapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "email" to email
                    )
                    user?.let {
                        fireStore.collection("users").document(it.uid)
                            .set(userProfile)
                            .addOnSuccessListener {
                                onComplete(true)
                            }
                            .addOnFailureListener { e ->
                                onComplete(false)
                            }
                    }
                } else {
                    onComplete(false)
                }
            }
    }


    fun signOut(){
        auth.signOut()
    }
}

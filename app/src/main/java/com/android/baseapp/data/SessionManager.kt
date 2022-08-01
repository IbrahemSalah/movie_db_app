package com.android.baseapp.data

import com.android.baseapp.data.repositories.Repository
import com.android.baseapp.util.FirebaseUtils


class SessionManager(val repository: Repository) {

    companion object {
        private var instance: SessionManager? = null
        fun getInstance(repository: Repository) = synchronized(this) {
            if (instance == null)
                instance = SessionManager(repository)
            instance
        }
    }

    //dummy return
    fun current(): String? {
        return null
    }

    fun hasCachedUser(): Boolean {
        return (repository.getToken().isNotEmpty())
    }

    fun setActiveSession(loginResponse: String) {
    }

    fun logout() {
        repository.logOut()
        FirebaseUtils.firebaseAuth.signOut()
    }

}
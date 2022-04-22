package com.example.facebookclone.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.facebookclone.db.FacebookCloneDB
import com.example.facebookclone.entities.User
import kotlinx.coroutines.launch

class UserModel(application: Application) : AndroidViewModel(application) {
    private val userDao  = FacebookCloneDB.getDB(application).getUserDao()
    val errorMessage : MutableLiveData<String> = MutableLiveData()
    var userExist : User? = null

    fun login(email : String, password : String, callback : (Long) -> Unit){
        viewModelScope.launch {
            userExist = userDao.getUserByEmail(email)
            if(userExist != null){
                if(userExist!!.password == password){
                    callback(userExist!!.id)
                }else{
                    errorMessage.value = "Incorrect Password"
                }
            }else{
                errorMessage.value = "Email does not exist!"
            }
        }
    }

    fun register(user: User, callback : (Long) -> Unit){
        viewModelScope.launch {
            userExist = userDao.getUserByEmail(user.email)
            if(userExist != null){
                errorMessage.value = "Email Already Exist!"
            }else{
                val rowId = userDao.insertUser(user)
                userExist = user
                callback(rowId)
            }
        }
    }
}
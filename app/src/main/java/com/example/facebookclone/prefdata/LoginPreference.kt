package com.example.facebookclone.prefdata

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val prefName = "login_preference"
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = prefName
)

class LoginPreference(context : Context) {
    private val isLoggedIn = booleanPreferencesKey("isLoggedIn")
    private val isAnyUserId = longPreferencesKey("isAnyUserId")
    val isLoggedInFlow : Flow<Boolean> = context.dataStore.data.catch {
        if(it is IOException){
            emit(emptyPreferences())
        }else {throw it}
    }.map{
        it[isLoggedIn] ?: false
    }
    val isAnyUserIdFlow : Flow<Long> = context.dataStore.data.catch {
        if(it is IOException){
            emit(emptyPreferences())
        }else {throw it}
    }.map{
        it[isAnyUserId] ?: 0L
    }
    suspend fun setLoginStatus(status: Boolean, id: Long, context: Context){
        context.dataStore.edit {
            it[isLoggedIn] = status
            it[isAnyUserId] = id
        }
    }
}
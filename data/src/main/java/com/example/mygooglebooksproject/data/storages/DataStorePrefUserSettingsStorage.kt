package com.example.mygooglebooksproject.data.storages

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val USER_PREFS = "user_preferences"

private val Context.dataStore by preferencesDataStore(USER_PREFS)

class DataStorePrefUserSettingsStorage(context: Context): IUserSettingsStorage {

    private var preferences = context.dataStore

    override suspend fun saveCount(count: Int) {
        preferences.edit {
            it[KEY_ITEMS_COUNT] = count
        }
    }

    override val countFlow: Flow<Int>
        get() = preferences.data.map {
            val count = it[KEY_ITEMS_COUNT] ?: 4
            count
        }

    companion object {
        val KEY_ITEMS_COUNT = intPreferencesKey("itemsCount")
    }

}
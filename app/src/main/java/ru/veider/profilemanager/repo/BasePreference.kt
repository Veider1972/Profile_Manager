package ru.veider.profilemanager.repo

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BasePreference(
    val context: Context,
    val gson: Gson,
) {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getString(key: String): String? = prefs.getString(key, null)
    fun putString(key: String, value: String?) =
        if (value != null)
            prefs.edit { putString(key, value) }
        else
            prefs.edit {
                remove(key)
                commit()
            }

    fun getBoolean(key: String): Boolean? =
        if (prefs.contains(key))
            try {
                prefs.getBoolean(key, false)
            } catch (_: Throwable) {
                null
            }
        else
            null

    fun putBoolean(key: String, value: Boolean?) =
        if (value != null)
            prefs.edit { putBoolean(key, value) }
        else
            prefs.edit {
                remove(key)
                commit()
            }

    fun getInt(key: String): Int? =
        if (prefs.contains(key))
            try {
                prefs.getInt(key, 0)
            } catch (_: Throwable) {
                null
            }
        else
            null

    fun putInt(key: String, value: Int?) =
        if (value != null)
            prefs.edit { putInt(key, value) }
        else
            prefs.edit {
                remove(key)
                commit()
            }

    fun getLong(key: String): Long? =
        if (prefs.contains(key))
            try {
                prefs.getLong(key, 0)
            } catch (_: Throwable) {
                null
            }
        else
            null

    fun putLong(key: String, value: Long?) =
        if (value != null)
            prefs.edit { putLong(key, value) }
        else
            prefs.edit {
                remove(key)
                commit()
            }

    fun getFloat(key: String): Float? =
        if (prefs.contains(key))
            try {
                prefs.getFloat(key, 0f)
            } catch (_: Throwable) {
                null
            }
        else
            null

    fun putFloat(key: String, value: Float?) =
        if (value != null)
            prefs.edit { putFloat(key, value) }
        else
            prefs.edit {
                remove(key)
                commit()
            }

    fun <T> putObject(key: String, value: T?) = putString(key, value?.let { gson.toJson(it) })
    inline fun <reified T> getObject(key: String): T? =
        getString(key)?.let {
            try {
                gson.fromJson(it, object : TypeToken<T>() {})
            } catch (_: Throwable) {
                null
            }
        }

    fun <T> putList(key: String, list: List<T>) = putString(key, gson.toJson(list))

    inline fun <reified T> getList(key: String): List<T>? =
        try {
            getString(key)?.let { gson.fromJson(it, object : TypeToken<List<T>>() {}.type) }
        } catch (t: Throwable) {
            emptyList<T>()
        }

    fun <K, V> putMap(key: String, map: Map<K, V>) = putString(key, gson.toJson(map))

    inline fun <reified K, reified V> getMap(key: String): Map<K, V>? =
        try {
            getString(key)?.let { gson.fromJson(it, object : TypeToken<Map<K, V>>() {}.type) }
        } catch (t: Throwable) {
            emptyMap<K, V>()
        }
}
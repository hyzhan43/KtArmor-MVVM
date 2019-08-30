package com.zhan.mvvm.utils

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
import android.content.Context
import android.content.SharedPreferences
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *  @author: hyzhan
 *  @date:   2019/5/17
 *  @desc:   TODO
 */
class Preference<T>(private val name: String, private val default: T) : ReadWriteProperty<Any?, T> {

    companion object {
        lateinit var preference: SharedPreferences

        fun init(context: Context) {
            preference = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        }

        // 清除数据
        fun clear() = preference.edit().clear().apply()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, default)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putPreference(name, value)

    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T): T = with(preference) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be get from Preferences")
        }
        res as T
    }

    private fun <T> putPreference(name: String, value: T) = with(preference.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
}
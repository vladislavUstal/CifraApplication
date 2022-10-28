package ru.vladislav.cifraapplication.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.IOException


object ErrorUtils {
    fun parseError(response: Response<*>): APIError? {
        val gson = Gson()
        val type = object : TypeToken<APIError>() {}.type
        val error: APIError? = try {
            response.errorBody()?.let {
                gson.fromJson(it.charStream(), type)
            }
        } catch (e: IOException) {
            return APIError()
        }
        return error
    }
}

class APIError {

    private val code: String = ""
    private val error: String = ""
    private val errorId: String = ""
    private val message: String = ""
    private val result: String = ""
    fun status(): String {
        return code
    }

    fun message(): String {
        return message
    }
}
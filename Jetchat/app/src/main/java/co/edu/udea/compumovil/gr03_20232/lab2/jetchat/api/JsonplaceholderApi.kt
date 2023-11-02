package co.edu.udea.compumovil.gr03_20232.lab2.jetchat.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val bs: String,
    val email: String
)

interface UserService {
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Call<User>
}

suspend fun getUser(id: Int): User? {
    return withContext(Dispatchers.IO) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(UserService::class.java)
            val response = api.getUser(id)

            if (response != null) {
                return@withContext response.await()
            } else {
                Log.e("getUserFromApi", "Error en la solicitud}")
                return@withContext null
            }
        } catch (e: Exception) {
            Log.e("Main", "Failed: ${e.message}")
            return@withContext null
        }
    }
}
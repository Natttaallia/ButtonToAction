package com.android.study.buttontoaction.data.api

import com.android.study.buttontoaction.data.entities.Animation
import com.android.study.buttontoaction.data.entities.Call
import com.android.study.buttontoaction.data.entities.Notification
import com.android.study.buttontoaction.data.entities.Toast
import com.android.study.buttontoaction.data.mappers.RuntimeTypeAdapterFactory
import com.android.study.buttontoaction.domain.entities.Action
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
interface ActionsApi {
    @GET("androidexam/butto_to_action_config.json")
    suspend fun getActions(): ArrayList<Action>

    companion object {

        fun provideRetrofit(): Retrofit {
            val runtimeTypeAdapterFactory: RuntimeTypeAdapterFactory<Action> =
                RuntimeTypeAdapterFactory
                    .of(Action::class.java, "type")
                    .registerSubtype(Animation::class.java, "animation")
                    .registerSubtype(Call::class.java, "call")
                    .registerSubtype(Toast::class.java, "toast")
                    .registerSubtype(Notification::class.java, "notification")

            val gson = GsonBuilder()
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                .create()

            return Retrofit.Builder()
                .baseUrl("https://s3-us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(OkHttpClient.Builder().build())
                .build()
        }

        inline fun <reified T> provideService(retrofit: Retrofit): T {
            return retrofit.create(T::class.java)
        }
    }
}
package poc.naveen.com.search.data.network

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Naveen on 21-07-2020.
 *
 * Class used to initialise retrofit
 */

object ApiProvider {
    private const val BASE_URL: String = "https://ws.audioscrobbler.com/2.0/"
    const val API_KEY: String = "10ae04a67ea56b9921ff02f90a0d7382"
    const val FORMAT_JSON: String = "&format=json"

    fun create(): ApiService {
        val retrofit = buildRetrofit()
        return retrofit.create(ApiService::class.java)
    }

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}
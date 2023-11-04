package dk.deepak.news

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

   @GET("top-headlines")
    fun getProductData(
    @Query("country")  countryy :String,
    @Query("apiKey")  apikey :String,
    @Query("pagesize") page :String,
    @Query("category") category: String
    ):Call<MyData>

}
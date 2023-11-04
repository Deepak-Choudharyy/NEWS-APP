package dk.deepak.news
import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var myAdapter: MyAdapter
    lateinit var pgsBar : ProgressBar
    var  countryy = "in"
    val apikey = "23d930c098c5487687dd1f5e689517e5"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val general = findViewById<ImageView>(R.id.general)
        val entertainment = findViewById<ImageView>(R.id.entertainment)
        val business = findViewById<ImageView>(R.id.business)
        val health = findViewById<ImageView>(R.id.health)
        val science = findViewById<ImageView>(R.id.science)
        val sports = findViewById<ImageView>(R.id.sports)
        val technology = findViewById<ImageView>(R.id.technology)
         pgsBar = findViewById<ProgressBar>(R.id.pgs)

        recyclerView = findViewById(R.id.rcView)

        supportActionBar?.hide()
        pgsBar.visibility = View.VISIBLE
        fetchNewsByCategory("general")
        general.setOnClickListener {
            pgsBar.visibility = View.VISIBLE
            fetchNewsByCategory("general")
        }
        entertainment.setOnClickListener {
            pgsBar.visibility = View.VISIBLE
            fetchNewsByCategory("entertainment")
        }
        business.setOnClickListener {
            pgsBar.visibility = View.VISIBLE
            fetchNewsByCategory("business")
        }
        health.setOnClickListener {
            pgsBar.visibility = View.VISIBLE
            fetchNewsByCategory("health")
        }
        science.setOnClickListener {
            pgsBar.visibility = View.VISIBLE
            fetchNewsByCategory("science")
        }
        sports.setOnClickListener {
            pgsBar.visibility = View.VISIBLE
            fetchNewsByCategory("sports")
        }
        technology.setOnClickListener {
            pgsBar.visibility = View.VISIBLE
            fetchNewsByCategory("technology")
        }
        }

      @SuppressLint("SuspiciousIndentation")
      fun fetchNewsByCategory(category: String) {

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
//        .client(OkHttpClient())
                .build()
//        .create(ApiInterface::class.java)


            val apiInterface = retrofitBuilder.create(ApiInterface::class.java)
//    val Call = retrofitBuilder.getProductData(apiKey,country)
//            val Call = retrofitBuilder.getProductData(apiKey,country)
            val Call = apiInterface.getProductData(countryy, apikey, "100",category)
            pgsBar.visibility = View.GONE
            Call.enqueue(object : Callback<MyData?> {
                override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        var productList = responseBody?.articles!!
                        myAdapter = MyAdapter(productList, this@MainActivity)
                        recyclerView.adapter = myAdapter
                        recyclerView.layoutManager =
                            LinearLayoutManager(
                                this@MainActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    } else {
//              Toast.makeText(this,"this",Toast.LENGTH_SHORT).Show()

                    }
                }


                override fun onFailure(call: Call<MyData?>, t: Throwable) {

                    Log.d("Main Activity", "onFailure:" + t.message)
                }
            })
        }



    fun onItemClicked(item: Article) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}
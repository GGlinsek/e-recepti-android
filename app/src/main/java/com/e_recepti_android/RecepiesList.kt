package com.e_recepti_android

import android.app.DownloadManager.Request
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import java.nio.charset.Charset

class RecepiesList : AppCompatActivity() {

    val url = "https://e-recepti.fly.dev/api/v1/recipes"
    lateinit var button4: Button
    lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recepies)

        button4 = findViewById(R.id.button2)



        val requestBody = ""
        val textView = findViewById<TextView>(R.id.textView2)
        val RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest =object: StringRequest(com.android.volley.Request.Method.GET, url,
            { response ->
                val jsonArray = JSONArray(response)
                val recipes = mutableListOf<MutableMap<String, Any>>()


                for (i in 0 until jsonArray.length()){
                    val recipe = jsonArray.getJSONObject(i)

                    val id = recipe.getInt("id")
                    val userId = recipe.getInt("user_id")
                    val title = recipe.getString("title")
                    val description = recipe.getString("description")
                    val cookTime = recipe.getInt("cook_time")
                    val feeds = recipe.getInt("feeds")
                    val difficulty = recipe.getInt("difficulty")
                    val imageUrl = recipe.getString("image_url")


                }

                ///val string = title + "\n" + description
                ///textView.setText( )
            },
            { error ->  textView.setText("ne dela")}) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Api-Key"] = "f08568219563577d0a12336dad42b6ec"
                return headers
            }
        }
        RequestQueue.add(jsonObjectRequest)






    }








    }

package com.e_recepti_android
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

class RecipePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipepage)
        /*
        val homeRecepti = findViewById<TextView>(R.id.homepagerecepti)
        homeRecepti.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

         */

        val imageView = findViewById<ImageView>(R.id.ivPicture)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val value = intent.getIntExtra("recipe_id", 0)
        val url = "https://e-recepti.fly.dev/api/v1/recipes/" + value
        val RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest =object: StringRequest(
            Method.GET, url,
            { response ->


                val recipe = JSONObject(response)

                    val id = recipe.getInt("id")
                    val userId = recipe.getInt("user_id")
                    val title = recipe.getString("title")
                    val description = recipe.getString("description")
                    tvDescription.text = "Description: " + description
                    val difficulty = recipe.getInt("difficulty")
                    val imageUrl = recipe.getString("image_url")
                    Glide.with(this).load(imageUrl).into(imageView)





            },
            { error ->  }) {
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
package com.e_recepti_android
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

class RecipePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipepage)

        val homeRecepti = findViewById<TextView>(R.id.homepagerecepti)
        homeRecepti.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val imageView = findViewById<ImageView>(R.id.ivPicture)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val tvCookingTime = findViewById<TextView>(R.id.tvCookingTime)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDifficulty = findViewById<TextView>(R.id.tvDifficulty)
        val tvFeeds = findViewById<TextView>(R.id.tvFeeds)
        val value = intent.getIntExtra("recipe_id", 0)
        val url = "https://e-recepti.fly.dev/api/v1/recipes/" + value
        val RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest =object: StringRequest(
            Method.GET, url,
            { response ->


                val recipe = JSONObject(response)

                    val userId = recipe.getInt("user_id")
                    val title = recipe.getString("title")
                    tvTitle.text = title
                    val description = recipe.getString("description")
                    tvDescription.text = "Description: " + description
                    val cook_time = recipe.getString("cook_time")
                    tvCookingTime.text = "Time: " + cook_time + " min"
                    val difficulty = recipe.getString("difficulty")
                    tvDifficulty.text = "Difficulty: " + difficulty + " stars"
                    val feeds = recipe.getString("feeds")
                    tvFeeds.text = "Feeds: " + feeds + " people"
                    val imageUrl = recipe.getString("image_url")
                    Glide.with(this).load(imageUrl).into(imageView)
                    val recipeIngredients = recipe.getJSONArray("recipe_ingredients")
                    Ingredient(recipeIngredients)




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

    fun Ingredient(list: JSONArray) {
        for (i in 0 until list.length()) {
            val ingredient = list.getJSONObject(i)
            var name = " "
            val id = ingredient.getString("ingredient_id")
            val quantity = ingredient.getString("quantity")
            val unit = ingredient.getString("unit")

            val url = "https://e-recepti.fly.dev/api/v1/ingredients/" + id
            val RequestQueue = Volley.newRequestQueue(this)
            val jsonObjectRequest = object : StringRequest(
                Method.GET, url,
                { response ->

                    val ingredientjson = JSONObject(response)

                    name = ingredientjson.getString("name")

                    val layout = findViewById<LinearLayout>(R.id.llinformation)
                    val ingredientInfo = TextView(this)
                    ingredientInfo.text = "  -" + name + " " + quantity + " " + unit
                    layout.addView(ingredientInfo)



                },
                { error -> }) {
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
}
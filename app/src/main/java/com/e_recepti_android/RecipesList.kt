package com.e_recepti_android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray

class RecipesList : AppCompatActivity() {

    val url = "https://e-recepti.fly.dev/api/v1/recipes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        val homeRecepti = findViewById<TextView>(R.id.homepagerecepti)
        homeRecepti.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        val RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest =object: StringRequest(Method.GET, url,
            { response ->


                val jsonArray = JSONArray(response)
                val recipes = mutableListOf<MutableMap<String, Any>>()


                for (i in 0 until jsonArray.length()){
                    val recipe = jsonArray.getJSONObject(i)

                    val id = recipe.getInt("id")
                    val userId = recipe.getInt("user_id")
                    val title = recipe.getString("title")
                    val description = recipe.getString("description")
                    val difficulty = recipe.getInt("difficulty")
                    val imageUrl = recipe.getString("image_url")
                    addRecipe(title, description, imageUrl, id)
                }


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


    fun addRecipe(title: String, description: String, imageUrl: String, recipe_id: Int){
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        val linearLayout2 = LinearLayout(this)
        linearLayout2.orientation = LinearLayout.VERTICAL
        val imageView = ImageView(this)
        Glide.with(this).load(imageUrl).into(imageView)
        val layoutParams =LinearLayout.LayoutParams(400, 400)
        imageView.layoutParams = layoutParams
        val Title = TextView(this)
        Title.text = title
        Title.textSize = 16f
        Title.setPadding(16, 16, 16, 16)
        val Description = TextView(this)
        Description.text = description
        Description.textSize = 16f
        Description.setPadding(16, 16, 16, 16)
        val separator = View(this)
        separator.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2)
        separator.setBackgroundColor(Color.BLACK)





        val layout = findViewById<LinearLayout>(R.id.layout1)
        layout.addView(linearLayout)
        linearLayout.addView(imageView)
        linearLayout.addView(linearLayout2)
        linearLayout2.addView(Title)
        linearLayout2.addView(Description)
        layout.addView(separator)

        linearLayout.setOnClickListener {
            val intent = Intent(this, RecipePage::class.java)
            intent.putExtra("recipe_id", recipe_id)
            startActivity(intent)
        }


    }

}
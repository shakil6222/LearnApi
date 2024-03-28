package com.example.learnapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val userList = ArrayList<TodoDetailsModel>() // Assuming you have TodoDetailsModel defined
    private lateinit var listItem: ListView

    @SuppressLint("MissingInflatedId", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listItem = findViewById(R.id.listItem_listView)
        val text = findViewById<TextView>(R.id.textName)

        // Assuming PlaceHolderApi is your Retrofit interface
        PlaceHolderApi.create().getToList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { data ->
                userList.addAll(data)

                val newText = StringBuilder()
                for (user in userList) {
                    newText.append("userId: ").append(user.userId).append("\n")
                    newText.append("title: ").append(user.title).append("\n")
                    newText.append("body: ").append(user.body).append("\n")
                }
                text.text = newText.toString()
            }

        val postModel = TodoDetailsModel(2, 25, "Shakil") // Assuming TodoPostModel is defined correctly

        PlaceHolderApi.create().addPost(postModel)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { response ->
                Toast.makeText(this, "${response.id} Id success", Toast.LENGTH_SHORT).show()
            }
    }
}
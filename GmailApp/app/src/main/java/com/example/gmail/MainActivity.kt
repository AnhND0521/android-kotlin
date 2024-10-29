package com.example.gmail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailList = listOf(
            Email("LinkedIn", "New job recommendations for you", "3:00 PM", "L"),
            Email("Spotify", "Your weekly music mix is here", "2:30 PM", "S"),
            Email("Udemy", "Courses you might like", "1:10 PM", "U"),
            Email("Medium", "Top articles this week", "12:45 PM", "M"),
            Email("Facebook", "You have new friend requests", "11:15 AM", "F"),
            Email("Zoom", "Meeting reminder: Project discussion", "10:50 AM", "Z"),
            Email("Google Photos", "Memories from last year", "9:30 AM", "G"),
            Email("Trello", "Board updates for today", "8:10 AM", "T"),
            Email("PayPal", "Your recent transaction is complete", "7:45 AM", "P"),
            Email("Asana", "Tasks assigned to you", "6:00 AM", "A")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EmailAdapter(emailList)
    }
}
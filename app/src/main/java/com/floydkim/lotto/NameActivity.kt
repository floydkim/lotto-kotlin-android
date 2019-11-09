package com.floydkim.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        goButton.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }

        backButton.setOnClickListener {
            finish()
        }

        Toast.makeText(applicationContext, "NameActivity 입니다.", Toast.LENGTH_SHORT).show()
    }
}

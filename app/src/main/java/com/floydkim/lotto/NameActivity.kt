package com.floydkim.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*
import kotlin.random.Random

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        goButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            val inputText = editText.text.toString()
            val lottoNumbersList = getLottoNumbersFromHash(inputText)

            intent.putIntegerArrayListExtra("result", ArrayList(lottoNumbersList))

            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish()
        }

        Toast.makeText(applicationContext, "NameActivity 입니다.", Toast.LENGTH_SHORT).show()
    }
}

fun getLottoNumbersFromHash(name: String): MutableList<Int> {
    val list = mutableListOf<Int>()

    for (number in 1..45) {
        list.add(number)
    }

    // 입력받은 이름을 hashing해 seed로 삼아 shuffle을 수행
    list.shuffle(Random(name.hashCode().toLong()))

    return list.subList(0, 6)
}
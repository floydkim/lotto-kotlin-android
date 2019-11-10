package com.floydkim.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        goButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            val inputText = editText.text.toString().trim()

            if (TextUtils.isEmpty(inputText)) {
                Toast.makeText(applicationContext, "이름을 입력해주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val lottoNumbersList = getLottoNumbersFromHash(inputText)

            intent.putIntegerArrayListExtra("result", ArrayList(lottoNumbersList))
            intent.putExtra("name", inputText)

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

    // 같은 이름이라도 오늘이 지나면 다른 번호를 만들기 위함
    val targetString = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date()) + name

    // 입력받은 이름을 hashing해 seed로 삼아 shuffle을 수행
    list.shuffle(Random(targetString.hashCode().toLong()))

    return list.subList(0, 6)
}
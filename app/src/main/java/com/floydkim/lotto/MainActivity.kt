package com.floydkim.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randomCard.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            // 대상 액티비티에 Int Array를 전달
            intent.putIntegerArrayListExtra("result", ArrayList(getRandomLottoNumbers()))

            startActivity(intent)
        }

        constellationCard.setOnClickListener {
            startActivity(Intent(this, ConstellationActivity::class.java))
        }

        nameCard.setOnClickListener {
            startActivity(Intent(this, NameActivity::class.java))
        }

        Toast.makeText(applicationContext, "MainActivity 입니다.", Toast.LENGTH_SHORT).show()
    }
}

/**
 * 1 ~ 45 정수 반환
 */
fun getRandomLottoNumber(): Int {
    return Random.nextInt(45) + 1
}

/**
 * 6개 번호 리턴
 * */
fun getRandomLottoNumbers(): MutableList<Int> {
    val lottoNumbers = mutableListOf<Int>()

    for (i in 1..6) {
        lottoNumbers.add(getRandomLottoNumber())
    }

    return lottoNumbers
}

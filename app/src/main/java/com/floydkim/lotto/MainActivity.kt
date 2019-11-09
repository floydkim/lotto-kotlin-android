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
            intent.putIntegerArrayListExtra("result", ArrayList(getShuffleLottoNumbers()))

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
        var number = 0

        do {
            number = getRandomLottoNumber()
        } while (lottoNumbers.contains(number))

        lottoNumbers.add(number)
    }

    return lottoNumbers
}

fun getShuffleLottoNumbers(): MutableList<Int> {
    val list = mutableListOf<Int>()

    for (number in 1..45) {
        list.add(number) // 1 ~ 45가 모두 들어간 어레이 만들고
    }

    list.shuffle() // 섞어서 랜덤하게 만듦

    return list.subList(0, 6) // 앞 6개만 잘라서 리턴

    // 45개 중 6개 숫자를 뽑는데 항상 45회 반복을 한다.
    // 중복검사 방식은 높은 확률로 10회 미만 반복으로 종료될 것.
    // 45개 리스트 만드는 부분도 이 함수에 들어있는 것은 큰 비효율.
}
package com.floydkim.lotto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        randomCard.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            // 대상 액티비티에 Int Array를 전달
//            intent.putIntegerArrayListExtra("result", ArrayList(getShuffleLottoNumbers()))

            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getShuffleLottoNumbers()))

            startActivity(intent)
        }

        constellationCard.setOnClickListener {
            startActivity(Intent(this, ConstellationActivity::class.java))
        }

        nameCard.setOnClickListener {
            startActivity(Intent(this, NameActivity::class.java))
        }
    }
}


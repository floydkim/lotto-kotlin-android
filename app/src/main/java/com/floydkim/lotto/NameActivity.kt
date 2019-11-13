package com.floydkim.lotto

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        goButton.setOnClickListener {

            val inputText = editText.text.toString().trim()

            if (TextUtils.isEmpty(inputText)) {
                Toast.makeText(applicationContext, "이름을 입력해주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ResultActivity::class.java)

            val lottoNumbersList = LottoNumberMaker.getLottoNumbersFromHash(inputText)

            intent.putIntegerArrayListExtra("result", ArrayList(lottoNumbersList))
            intent.putExtra("name", inputText)

            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}

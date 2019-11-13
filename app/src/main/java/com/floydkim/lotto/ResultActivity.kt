package com.floydkim.lotto

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {

    // 책에선 이미지라 이게 필요
    // val lottoImageStartId = R.drawable.ball_01

    // 난 텍스트만 갈아주면 되니까..

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getIntegerArrayListExtra("result") // putIntegerArrayListExtra 호출시 정한 name을 이용해 값을 가져온다
        val name = intent.getStringExtra("name")
        val constellation = intent.getStringExtra("constellation")

        resultLabel.text = "랜덤으로 생성된\n번호입니다"

        if (!TextUtils.isEmpty(name)) {
            resultLabel.text = "${name} 님의\n${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())}\n번호입니다"
        }

        if (!TextUtils.isEmpty(constellation)) {
            resultLabel.text = "${constellation}의\n${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())}\n번호입니다"
        }

        // 전달받은 결과가 있을때만 실행
        result?.let {
            updateLottoBallText(result.sortedBy { it })
            // sortBy 메서드는 in-place sort이며 대상 어레이를 mutate 한다. (mutableList에 사용가능)
            // 반면 sortedBy 메서드는 새로운 어레이를 반환한다.
        }
    }

    fun updateLottoBallText(result: List<Int>) {

        if (result.size < 6) return

        // 책에서는 이미지라 이미지 리소스 교체해주는 식으로 구현됨.
        // 랜덤 정수 범위가 1 ~ 45인데 이미지 리소스 이름과 1 차이나게 되어있는 모양임.
        // imageView01.setImageResource(lottoImageStartId + (result[0] - 1))

        // 난 텍스트를 갈아줘야 한다.

        // textViewBall1.setText("${result[0]}") // 이걸 쓰면 getter/setter로 인식하고 코틀린에선 property access syntax를 쓰라는 warning이 뜬다.
        // 코틀린의 안드로이드 확장 덕분에 View binding 뿐만 아니라 java의 get/set 패턴 대신 kotlin의 property access syntax 사용을 권장한다.
        textViewBall1.text = "${result[0]}"
        textViewBall2.text = "${result[1]}"
        textViewBall3.text = "${result[2]}"
        textViewBall4.text = "${result[3]}"
        textViewBall5.text = "${result[4]}"
        textViewBall6.text = "${result[5]}"

        // TODO: background 색도 바꿔보자.
    }
}

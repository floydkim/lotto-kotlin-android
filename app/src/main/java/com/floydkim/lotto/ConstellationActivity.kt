package com.floydkim.lotto

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_constellation.*
import java.util.*

//import android.icu.util.Calendar // 이건 API level 하위호환 안됨

class ConstellationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)

        goResultButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            intent.putIntegerArrayListExtra(
                "result", ArrayList(
                    LottoNumberMaker.getLottoNumbersFromHash(
                        makeConstellationString(datePicker.month, datePicker.dayOfMonth)
                    )
                )
            )

            intent.putExtra("constellation", makeConstellationString(datePicker.month, datePicker.dayOfMonth))

            startActivity(intent)
        }

        textView.text = makeConstellationString(datePicker.month, datePicker.dayOfMonth)

        val calendar = Calendar.getInstance()
        datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            object : CalendarView.OnDateChangeListener, DatePicker.OnDateChangedListener {
                override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                    textView.text = makeConstellationString(datePicker.month, datePicker.dayOfMonth)
                }

                override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
                }
            })
    }
}

fun makeConstellationString(month: Int, day: Int): String {
    val target = "${month + 1}${String.format("%02d", day)}".toInt()
    var constellation = ""

    when (target) {
        in 101..119 -> constellation = "염소"
        in 120..218 -> constellation = "물병"
        in 219..320 -> constellation = "물고기"
        in 321..419 -> constellation = "양"
        in 420..520 -> constellation = "황소"
        in 521..621 -> constellation = "쌍둥이"
        in 622..722 -> constellation = "게"
        in 723..822 -> constellation = "사자"
        in 823..923 -> constellation = "처녀"
        in 924..1022 -> constellation = "천칭"
        in 1023..1122 -> constellation = "전갈"
        in 1123..1224 -> constellation = "사수"
        in 1225..1231 -> constellation = "염소"
        else -> constellation = "잘못된 별"
    }

    return "${constellation}자리"
}
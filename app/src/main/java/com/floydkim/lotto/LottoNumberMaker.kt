package com.floydkim.lotto

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

object LottoNumberMaker {

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
            list.add(number)
        }

        list.shuffle() // 섞어서 랜덤하게 만듦

        return list.subList(0, 6) // 앞 6개만 잘라서 리턴

        // 45개 중 6개 숫자를 뽑는데 항상 45회 반복을 한다.
        // 중복검사 방식은 높은 확률로 10회 미만 반복으로 종료될 것.
        // 45개 리스트 만드는 부분도 이 함수에 들어있는 것은 큰 비효율.
    }

    fun shuffleLottoNumbers(numbers: MutableList<Int>): MutableList<Int> {
        numbers.shuffle()
        return numbers.subList(0, 6)
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
}
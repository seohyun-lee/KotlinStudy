package org.example

import kotlinx.coroutines.runBlocking

class MyActivity {
    // 콜백 함수
    /*
    myFunc({
        println("함수 호출")
    })
    */
    // 람다식이 이런 식으로 표현됨
    init {
        myFunc {
            println("함수 호출")
        }
        runBlocking {
            myFunc2(10)
            {
                println("함수 호출")
            }
        }
    }
    // 이런 식으로 함수를 매개변수로 전달하는 함수를 고차 함수라고 함

    // suspend 키워드: 정지 함수
    // 이 함수가 실행 되고 끝날 때까지 기다려 줘야 함. main은 suspend로 만들면 안 됨
    // suspend는 suspend 안에서 실행 가능
    // suspend 실행하려면 코루틴 스코프를 만들어서 실행 가능
}

// void가 없음. void가 아나고 Unit이에요
// callBack 이름 정의
// () : input이 없는 함수 의미
// Unit : 리턴 (없음)
fun myFunc(callBack : () -> Unit) {
    println("함수 시작")
    callBack()
    println("함수 끝")
}
suspend fun myFunc2(a: Int, callBack : () -> Unit) {
    println("함수 시작: $a")
    callBack()
    println("함수 끝")
}
// fun myFunc(callBack : () -> Unit={}){} 이러면 써도 되고 안 써도 됨 (빈 값)
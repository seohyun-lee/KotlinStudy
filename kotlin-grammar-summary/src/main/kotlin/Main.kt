package org.example

import java.lang.Exception
import kotlin.random.Random
import java.util.*
import kotlin.math.max

// 코틀린은 자바와 달리 모든 파일에 클래스 있지 않아도 메소드 가능
val num = 20 // 탑 레벨 상수. (final)
const val num2 = 20 // 컴파일 타임 상수 (main보다 우선해서 컴파일 됨)

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
fun main() {
    var i = 10
    var j : Int = 10 // 모든 타입들이 레퍼런스 타입을 가지고 있음
    var str : String = "Hello"
    var point : Double = 3.3
    val integer = 20 // 상수는 재대입 불가. final과 비슷

    var l = 20L
    l = i.toLong() // 자바처럼 자동 캐스팅이나 (long) i 없음
    i = l.toInt()
    // string <-> int 도 toInt(), toString()하면 됨

    val name = "Kotlin"
    name.uppercase()
    name.lowercase()
    print(name[0]) // 인덱스 접근 됨
    println("Hello, " + name + "!")
    println("이 언어는 $name 입니다")
    println("이 언어는 ${name}입니다") // 띄어쓰기 없이 붙여야 할 때. +로 concat 가능

    println(max(i, j)) // kotlin에 있는 것을 임포트

    val randNum = Random.nextInt(0,100); // from은 inclusive, until은 exclusive
    println(randNum)

    val reader = Scanner(System.`in`)
    print("아무거나 입력하세요: ")
    var input = reader.next()
    // 정수: reader.nextInt()
    println(input)

    i = 5
    /*
    if (i>10) {
        print("10보다 크다")
    } else if (i>5) {
        print ("5보다 크다")
    } else {
        print ("5이하이다")
    }

    when {
        i > 10 -> print("10보다 크다")
        i > 5 -> print("5보다 크다")
        else -> print("5이하이다")
    }

    var res = if (i>10) { // if문이 아니라 식. when으로도 가능함.
        "10보다 크다"
    } else if (i>5) {
        "5보다 크다"
    } else {
        "5이하이다"
    }
    print(res)
    */
    // when (switch문 같은)
    var res = when {
        i > 10 -> "10보다 크다"
        i > 5 -> "5보다 크다"
        else -> "5이하이다"
    }

    // 코틀린에서는 삼항연산 x, 대신 if-else 문
    var res2 = if (i>10) true else false

    // 리스트
    val items = listOf(1,2,3,4,5)

    // 반복문
    for (item in items) {
        print(item)
    }
    items.forEach{
        item -> print(item)
    }
    // for(int i=0; i<items.length; i++)
    for (i in 0..items.size-1) {
        print(items[i])
        break
        continue
    }
    // for(i in 0..3)
    for (i in 0..3){}

    // 변경이 가능한 리스트
    val newItems = mutableListOf(1, 2, 3, 4, 5)
    // mutableListOf<Int>(1, 2, 3, 4, 5)
    // Generic. 근데 타입 추론 가능해서 생략 가능
    // val newItems : MutableListOf<Int> 도 마찬가지
    newItems.add(6)
    newItems.remove(3)

    // 배열
    val array = arrayOf(1, 2, 3)
    array.size
    array[0]=10
    // 배열은 잘 쓰지 않기 때문에 리스트를 사용
    try{
        val item = items[4]
    } catch (e:Exception) {
        print(e.message) // array bound 초과 exception
    }

    // Null Safety
    var a: String? = null
    // ?가 없으면 null을 넣을 수 없음. ?가 있고 없냐는 별개의 타입으로 취급됨.
    a = "준석"
    var b: String = ""
    // b = a는 불가
    if (a != null) // null 체크
        b = a // b가 String? 타입으로 변환
    var c: String = ""
    c = a!! // 이러면 ?를 뗄 수 있지만 개발자가 임의로 null이 아님을 보증 -> 에러 나면 개발자 책임 -> 하지 마세요

    // a가 null이 아니라면~
    a?.let { b = a }

    // Person 클래스 실습
    val john = Person("John", 20)
    val john2 = Person("John", 20)

    println(john)
    println(john2) // hashcode로 둘이 다른 객체인지를 볼 수 있음
    println(john == john2) // false

    val johnData = PersonData("John", 20)
    val johnData2 = PersonData("John", 20)
    println(johnData)
    println(johnData2) // hashcode로 둘이 다른 객체인지를 볼 수 있음
    println(johnData == johnData2) // false
    johnData.some()

    val dog: Animal = Dog() // 선언이랑 실제 인스턴스 타입이 다른 경우
    val cat = Cat()
    // 타입체크 is
    if (dog is Dog) {
        dog.draw() // Dog 타입체크를 통과했기 때문에 draw도 사용 가능
        dog.move()
        println("멍멍이")
    }
    // 강제 타입 캐스팅 as
    // cat as Animal


    val box = Box(10)
    val box2 = Box("dfdfd")
    print(box.value)
}

// 파일의 맨 바깥에 작성한 이런 함수들은 톱 레벨 함수
// fun sum(a: Int, b: Int) : Int = a + b // 한 줄 밖에 없는 경우
fun sum(a: Int, b: Int, c: Int=0) = a + b + c // 한 줄 밖에 없는 경우
// 메소드 오버로드. 메소드를 여러개 만드는 게 아니라 파라미터를 조절해서 (Default 값 조정)
fun result() {
    // 입력 파라미터 명시적으로 작성 -> 가독성 좋. 순서 바뀌어도 됨
    print(sum(a=10, b=20))
    print(sum(b=20, a=10))
}

// 객체 비교를 수행함
// 객체 비교 시 클래스와 해시코드가 출력됨
class Person (
    val name: String,
    var age: Int,
    // 기본적으로 public. private val name: String 하면 getter도 제공 x
)

// 데이터 클래스: 값 비교를 수행함
// 객체 출력 시 Person(name=John, age=20)
// 객체 비교 시 true
// * 한 줄에 표시: data class PersonData (val name: String, var age: Int)
data class PersonData (
    val name: String,
    var age: Int,
) {
    var hobby = "축구"
        // getter, setter 제어 및 재정의
        private set
        get() = "취미: $field" // field로 hobby에 접근

    fun some() {
        hobby = "축구"
    }

    init { // 생성할 때 마다 이 안의 코드가 실행
        print("init")
    }
}

// 상속

abstract class Animal{
    // 기본적으로 오버라이딩 불가. open으로 열어주어야 오버라이딩이 가능하다.
    open fun move(){
        print("이동")
    }
}

class Dog : Animal(), Drawable {
    override fun move() {
        println("껑충")
    }
    // 미구현된 메소드 구현하기
    override fun draw() {
        TODO("Not yet implemented")
    }
}
class Cat : Animal(), Drawable {
    override fun move() {
        println("살금")
    }
    // 미구현된 메소드 구현하기
    override fun draw() {
        TODO("Not yet implemented")
    }
}

// 일반 클래스는 상속 불가능. 마찬가지로 Open 해주어야 상속 가능.
// class SuperMan : Person() // 클래스 적을 때는 괄호 빼먹으면 안 됨 (기본 생성자)

// 인터페이스
interface Drawable{
    fun draw()
}

// class Box<T> (value: T) 하면 안 보이게 됨. 사용도 수정도 x.
// value를 사용/수정 가능하게 열어두려면 val이나 var 붙이면 됨
class Box<T>(var value: T)
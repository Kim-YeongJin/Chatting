import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class temp {
}
fun main() {
    //1번 - LocalDateTime을 Long값으로 변환
    val currentTime = LocalDateTime.now()
    val currentTimeMillis = currentTime.toInstant(ZoneOffset.of("+9")).epochSecond
    println("유닉스 시간: $currentTimeMillis")
    val localDateTime = LocalDateTime.ofEpochSecond(currentTimeMillis,0, ZoneOffset.of("+09"))
    println("유닉스 시간에서 현재 시간으로 변경: $localDateTime")

    //2 - Local Date를 Int 값으로 변경. 1970년 1월1일 부터 현재까지의 일을 스칼라 실수로 나타내었습니다. (365 * 50) + 윤일 + 이번년 일수 22일 더하면 18283이 나옵니다.
    //int형 최대 값은 2147483647 이므로 5881580년-07-11 까지 사용가능합니다.
    val currentDateInt = LocalDate.now()
    val currentDateMillsInt = currentDateInt.toEpochDay().toInt()
    println("Int 형 유닉스 날짜: $currentDateMillsInt")
    val localDateInt = LocalDate.ofEpochDay(currentDateMillsInt.toLong())
    println("Int 형 유닉스 날짜에서 현재 날짜로 변경: $localDateInt")
    val maxlocalDateInt = LocalDate.ofEpochDay(2147483647)
    println("Int 형 유닉스 날짜의 최대 일 수: $maxlocalDateInt")

    //3 - LocalDateTime을 Int값으로 변환
    //int형 최대 값은 2147483647 이므로 2038-01-19 T 12:14:00 까지 사용가능합니다.
    val currentTimeInt = LocalDateTime.now()
    val currentTimeMillisInt = currentTimeInt.toInstant(ZoneOffset.of("+9")).epochSecond.toInt()
    println("Int 형 유닉스 시간 $currentTimeMillisInt")
    val localDateTimeInt = LocalDateTime.ofEpochSecond(currentTimeMillisInt.toLong(),0, ZoneOffset.of("+09"))
    println("Int 형 유닉스 시간에서 현재 시간으로 변경: $localDateTimeInt")
    val maxLocalDateTimeInt = LocalDateTime.ofEpochSecond(2147483647,0, ZoneOffset.of("+09"))
    println("Int 형 유닉스 시간의 최대 날짜: $maxLocalDateTimeInt")

    //4 - Local Date를 EpochDay로 기본형인 Long으로 변환
    val currentDate = LocalDate.now()
    val currentDateMills = currentDate.toEpochDay()
    println("유닉스 날짜: $currentDateMills")
    val localDate = LocalDate.ofEpochDay(currentDateMills)
    println("유닉스 날짜에서 현재 날짜로 변경: $localDate")
}

fun ControlTime(i : Any) : Any{
    when(i) {
        is LocalDateTime ->{
            //인수로 LocalDateTime.now() 입력시
            return i.toInstant(ZoneOffset.of("+9")).epochSecond
        }
        is Long -> {
            //인수로 LocalDateTime.now()가 변환된 Long을 입력시
            return LocalDateTime.ofEpochSecond(i,0, ZoneOffset.of("+09"))
        }
        is LocalDate -> {
            //인수로 LocalDate.now() 입력시
            return i.toEpochDay().toInt()
        }
        is Int -> {
            //인수로 LocalDate.now()가 변환된 Int를 입력시
            return LocalDate.ofEpochDay(i.toLong())
        }
        else -> {
            return "시간 타입을 정확히 입력해 주세요"
        }
    }
}
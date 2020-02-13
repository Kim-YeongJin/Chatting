import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.music961.mct_char_prac.bean.PeopleBean
import java.io.File
import java.io.FileOutputStream
import java.net.SocketAddress
import java.time.LocalDateTime
import java.time.ZoneOffset

fun main() {
    ChatServer()
}

class ChatServer : MacheteServer("192.168.1.101:3306/mchat", "yj", "0719","192.168.1.105",5000) {

    init {
    }

    override fun qaddRoom() {
        qadd("signup", "INSERT mchat_usr (usr_mail, usr_nick, usr_gender, usr_age, usr_latitude, usr_longitude, usr_dist, usr_gem, usr_talk, usr_face, usr_last) VALUES (?,?,?,?,?,?,?,?,?,?,?)")
        qadd("search id", "select usr_mail from mchat_usr where usr_mail = (?)")
        qadd("last login time", "Update mchat_usr set usr_last = (?) where usr_mail = (?)")
    }

    override fun sopoOpen(ara: SocketAddress, protocol: Int, sopo: Array<Any>) {
        when(protocol) {
            // 회원 가입 정보 디비 등록
            600 -> {
                val temp = sopo[0] as String
                val nick = sopo[1] as String
                val gender = sopo[2] as Boolean
                val age = sopo[3] as Int
                val latitude = sopo[4] as Double
                val longitude = sopo[5] as Double
                val currentTimeInt = LocalDateTime.now()
                val currentTimeMillisInt = currentTimeInt.toInstant(ZoneOffset.of("+9")).epochSecond.toInt()

                try {
                    val idToken: GoogleIdToken = verifier.verify(temp)
                    val payload: GoogleIdToken.Payload = idToken.payload
                    val email: String = payload.email


                    cast.getValue("signup").apply {
                        setString(1, email)
                        setString(2, nick)
                        setBoolean(3, gender)
                        setInt(4, age)
                        setDouble(5, latitude)
                        setDouble(6, longitude)
                        setInt(7, 0)
                        setInt(8, 100)
                        setString(9, "k")
                        setString(10, "k")
                        setInt(11, currentTimeMillisInt)
                    }.executeUpdate()
                } catch (e:Exception) {
                    send(ara, 610)
                }

            }

            //아이디 토큰 -> 이메일 파싱 & 중복 검사 후 프로토콜 전송
            200 -> {
                val temp = sopo[0] as String
                var reg = ""

                val idToken: GoogleIdToken = verifier.verify(temp)
                val payload: GoogleIdToken.Payload = idToken.payload
                val email: String = payload.email

                val rs = cast.getValue("search id").apply {
                    setString(1, email)
                }.executeQuery()

                while(rs.next()){
                    reg = rs.getString("usr_mail")
                }

                if(reg == ""){
                    send(ara, 600)
                }
                else {
                    val currentTimeInt = LocalDateTime.now()
                    val currentTimeMillisInt = currentTimeInt.toInstant(ZoneOffset.of("+9")).epochSecond.toInt()
                    cast.getValue("last login time").apply {
                        setInt(1, currentTimeMillisInt)
                        setString(2, email)
                    }.executeUpdate()
                    send(ara, 200)
                }

            }

            1000 -> {
                val temp = sopo[0] as Int
                //if(temp == -1 )
            }
        }
    }

    override fun sopoProtocol(ara: SocketAddress, protocol: Int) {
        when (protocol) {
            100 -> {
                var i = 0
                while(i<100) {
                    bean.add(PeopleBean("{$i}", 37.0, 126.0, 50.toByte(), true, 50, "ㅎㅇ", "ddd"))
                    i++
                }
                send(ara, 300, bean)
            }
        }
    }

    override fun usrKick(ara: SocketAddress) {
    }

    override fun wrErrLog(ara: SocketAddress, key: Int, code: Int) {
    }

    override fun wrLog(ara: SocketAddress, key: Int, protocol: Int) {
    }
}
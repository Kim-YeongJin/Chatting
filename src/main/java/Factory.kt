import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.music961.mct_char_prac.bean.PeopleBean
import java.io.File
import java.io.FileOutputStream
import java.net.SocketAddress
import java.util.concurrent.ConcurrentHashMap

var transport = NetHttpTransport()
val currentuser: ConcurrentHashMap<SocketAddress, String> = ConcurrentHashMap()
var verifier = GoogleIdTokenVerifier(transport, JacksonFactory.getDefaultInstance())
val bean : ArrayList<PeopleBean> = ArrayList()
val f = File("/home/shpark/바탕화면")
val out = FileOutputStream(f)
val buf = ByteArray(1024)

fun distance(lat1 : Double, lon1 : Double, lat2 : Double, lon2 : Double) : Double {
    val theta = lon1 - lon2
    var dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta))

    dist = Math.acos(dist)
    dist = rad2deg(dist)
    dist = dist * 60 * 1.1515 * 1.609344

    return dist
}

private fun deg2rad(deg : Double): Double {
    return (deg * Math.PI / 180.0)
}

private fun rad2deg(rad : Double): Double {
    return (rad * 180.0 / Math.PI )
}
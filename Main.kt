package converter
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.math.pow

fun main() {
    println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
    var str = readln()
    while ("/exit" !in str) {
        val (sbase, tbase) = str.split(" ")
        println("Enter number in base $sbase to convert to base $tbase (To go back type /back)")
        var num = readln()
        while (num != "/back") {
            if ("." in num) {
                val (number, fract) = num.split(".")
                num = fromN(toN(number, sbase.toInt()), tbase.toInt())
                num += "." + fromF(toF(fract, sbase.toInt()),tbase.toInt())
                print("Conversion result: " + num.toString() + "\n")
                println("Enter number in base $sbase to convert to base $tbase (To go back type /back)")
                num = readln()
            } else {
                println("Conversion result: " + fromN(toN(num, sbase.toInt()), tbase.toInt()) + "\n")
                println("Enter number in base $sbase to convert to base $tbase (To go back type /back)")
                num = readln()
            }
        }
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        str = readln()
    }
}
fun toN(num: String, base: Int): BigInteger {
    var rezult = BigInteger.ZERO
    for (i in 0..num.length - 1) {
        rezult += num[i].toString().toBigInteger(36) * base.toBigInteger().pow(num.length - 1 - i)
    }
    return rezult
} 
fun fromN(num: BigInteger, base: Int): String {
    var num = num
    var str = ""
    if (num == BigInteger.ZERO) {
        val otv = "0"
        return otv
    } else {
        while (num > BigInteger.ZERO) {
            str += (num % base.toBigInteger()).toString(36)
            num /= base.toBigInteger()
        }
        val otv = str.reversed()
        return otv
    }
} 
fun toF(fract: String, base: Int): Double {
    var rezult = 0.0
    for (i in 0..fract.length - 1) {
        rezult += fract[i].toString().toInt(36) * base.toDouble().pow(- 1 - i)
    }
    return rezult
} 
fun fromF(fract: Double , base: Int): String {
    var i = 0
    var fract = fract
    var rez = ""
    while (fract != 0.0 && i <= 4) {
        rez += ((fract * base).toInt() % 36).toString(36)
        fract = fract * base - (fract * base).toInt() % 36
        i++
    }
    while (i < 5) {
        rez += "0"
        i++
    }
    return rez
} 

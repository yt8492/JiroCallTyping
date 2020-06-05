import logic.constructTypeSentence
import logic.parseSentence

fun main() {
    val str = "ニンニクマシマシヤサイマシマシアブラカラメ"
    println(parseSentence(str))
    console.log(
        constructTypeSentence(parseSentence(str)).joinToString("\n") { s ->
            s.joinToString { it }
        }
    )
}
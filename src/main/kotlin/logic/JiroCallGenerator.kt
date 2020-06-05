package logic

object JiroCallGenerator {
    private val ninnniku = listOf(
        "ニンニク",
        "ニンニクマシ",
        "ニンニクマシマシ",
        "ニンニクナシ",
        "ニンニクスクナメ"
    )
    private val yasai = listOf(
        "ヤサイ",
        "ヤサイマシ",
        "ヤサイマシマシ",
        "ヤサイスクナメ"
    )
    private val abura = listOf(
        "",
        "アブラ",
        "アブラマシ",
        "アブラマシマシ",
        "アブラスクナメ"
    )
    private val karame = listOf(
        "",
        "カラメ"
    )

    fun generateSentence(): String {
        return ninnniku.random() + yasai.random() + abura.random() + karame.random()
    }
}

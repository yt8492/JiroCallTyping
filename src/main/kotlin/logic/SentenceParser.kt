package logic

fun parseSentence(japanese: String): List<String> {
    val result = mutableListOf<String>()
    var i = 0
    while (i < japanese.length) {
        val (uni, bi, tri) = when {
            i + 2 < japanese.length -> {
                listOf(japanese.substring(i, i + 1), japanese.substring(i, i + 2), japanese.substring(i, i + 3))
            }
            i + 1 < japanese.length -> {
                listOf(japanese.substring(i, i + 1), japanese.substring(i, i + 2), "")
            }
            else -> {
                listOf(japanese.substring(i, i + 1), "", "")
            }
        }
        when {
            JapaneseToRomeMap.containsKey(tri) -> {
                i += 3
                result.add(tri)
            }
            JapaneseToRomeMap.containsKey(bi) -> {
                i += 2
                result.add(bi)
            }
            else -> {
                i++
                result.add(uni)
            }
        }
    }
    return result
}

fun constructTypeSentence(strList: List<String>): List<List<String>> {
    return strList.flatMap { str ->
        (0..str.length).mapNotNull {
            JapaneseToRomeMap[str.substring(0, it)]
        }
    }
}

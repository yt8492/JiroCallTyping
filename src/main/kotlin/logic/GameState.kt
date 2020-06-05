package logic

sealed class GameState {
    abstract val score: Int

    object Start : GameState() {
        override val score: Int = 0
        fun newGame(): Playing {
            return nextSentenceState(0)
        }
    }

    data class Playing(
        override val score: Int,
        val sentence: String,
        val typingCandidateList: List<List<String>>,
        val currentCharIndexList: List<Int>,
        val currentRomeIndexList: List<Int>
    ) : GameState() {
        fun input(input: Char): GameState {
            val nextTypingCandidateList = mutableListOf<List<String>>()
            val nextCharIndexList = mutableListOf<Int>()
            val nextRomeIndexList = mutableListOf<Int>()
            var valid = false
            for (i in typingCandidateList.indices) {
                val candidate = typingCandidateList[i]
                val charIndex = currentCharIndexList[i]
                val romeIndex = currentRomeIndexList[i]
                if (candidate[charIndex][romeIndex] == input) {
                    valid = true
                    when {
                        romeIndex + 1 < candidate[charIndex].length -> {
                            nextTypingCandidateList.add(candidate)
                            nextCharIndexList.add(charIndex)
                            nextRomeIndexList.add(romeIndex + 1)
                        }
                        charIndex + 1 < candidate[i].length -> {
                            nextTypingCandidateList.add(candidate)
                            nextCharIndexList.add(charIndex + 1)
                            nextRomeIndexList.add(0)
                        }
                        else -> {
                            // 入力された文字が文字列の最後の文字に一致
                            return nextSentenceState(1)
                        }
                    }
                }
            }
            return if (valid) {
                Playing(score, sentence, nextTypingCandidateList, nextCharIndexList, nextRomeIndexList)
            } else {
                this
            }
        }

    }

    protected fun nextSentenceState(increment: Int): Playing {
        val sentence = JiroCallGenerator.generateSentence()
        val parsedSentence = parseSentence(sentence)
        val initialCandidateList = constructTypeSentence(parsedSentence)
        val initialCharIndexList = List(initialCandidateList.size) {0}
        val initialRomeIndexList = List(initialCandidateList.size) {0}
        return Playing(
            score + increment,
            sentence,
            initialCandidateList,
            initialCharIndexList,
            initialRomeIndexList
        )
    }
}

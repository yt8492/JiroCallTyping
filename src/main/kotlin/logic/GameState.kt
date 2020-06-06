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
        val currentCharIndex: Int,
        val currentRomeIndexList: List<Int>
    ) : GameState() {
        fun input(input: Char): GameState {
            val nextTypingCandidateAndRomeIndex = typingCandidateList[currentCharIndex].zip(currentRomeIndexList).takeWhile { (candidate, i) ->
                candidate[i] == input
            }
            println(nextTypingCandidateAndRomeIndex)
            if (nextTypingCandidateAndRomeIndex.isNotEmpty()) {
                val isLastIndex = currentCharIndex == typingCandidateList.lastIndex && nextTypingCandidateAndRomeIndex.any { (candidate, i) ->
                    i + 1 == candidate.length
                }
                if (isLastIndex) {
                    return nextSentenceState(1)
                }
                val canMoveToNext = nextTypingCandidateAndRomeIndex.any { (candidate, i) ->
                    i + 1 < candidate.length
                }
                println(canMoveToNext)
                if (canMoveToNext) {
                    return Playing(
                        score,
                        sentence,
                        typingCandidateList,
                        currentCharIndex,
                        currentRomeIndexList.map { it + 1 }
                    )
                } else {
                    return Playing(
                        score,
                        sentence,
                        typingCandidateList,
                        currentCharIndex + 1,
                        List(typingCandidateList.size) {0}
                    )
                }
            } else {
                return this
            }
        }

    }

    protected fun nextSentenceState(increment: Int): Playing {
        val sentence = JiroCallGenerator.generateSentence()
        val parsedSentence = parseSentence(sentence)
        val initialCandidateList = constructTypeSentence(parsedSentence)
        val initialRomeIndexList = List(initialCandidateList.size) {0}
        return Playing(
            score + increment,
            sentence,
            initialCandidateList,
            0,
            initialRomeIndexList
        )
    }
}

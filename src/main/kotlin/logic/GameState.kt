package logic

sealed class GameState {
    abstract val score: Int
    abstract val miss: Int

    object Start : GameState() {
        override val score: Int = 0
        override val miss: Int = 0
        fun startGame(): Playing {
            return nextSentenceState(0)
        }
    }

    data class Playing(
        override val score: Int,
        override val miss: Int,
        val sentence: String,
        val typingCandidateList: List<List<String>>,
        val currentCharIndex: Int,
        val currentRomeIndex: Int
    ) : GameState() {
        fun input(input: Char): GameState {
            val nextTypingCandidateAndRomeIndex = typingCandidateList[currentCharIndex].filter { candidate ->
                candidate[currentRomeIndex] == input
            }
            if (nextTypingCandidateAndRomeIndex.isNotEmpty()) {
                val isLastIndex = currentCharIndex == typingCandidateList.lastIndex && nextTypingCandidateAndRomeIndex.any { candidate ->
                    currentRomeIndex + 1 == candidate.length
                }
                if (isLastIndex) {
                    return nextSentenceState(1)
                }
                val canMoveToNext = nextTypingCandidateAndRomeIndex.any { candidate ->
                    currentRomeIndex + 1 < candidate.length
                }
                val nextTypingCandidateList = typingCandidateList.mapIndexed { index, list ->
                    if (index == currentCharIndex) {
                        nextTypingCandidateAndRomeIndex
                    } else {
                        list
                    }
                }
                if (canMoveToNext) {
                    return Playing(
                        score,
                        miss,
                        sentence,
                        nextTypingCandidateList,
                        currentCharIndex,
                        currentRomeIndex + 1
                    )
                } else {
                    return Playing(
                        score,
                        miss,
                        sentence,
                        nextTypingCandidateList,
                        currentCharIndex + 1,
                        0
                    )
                }
            } else {
                val miss = miss + 1
                return if (miss < 10) {
                    this.copy(miss = miss)
                } else {
                    End(score, miss)
                }
            }
        }
    }

    data class End(
        override val score: Int,
        override val miss: Int
    ) : GameState() {
        fun newGame(): Start {
            return Start
        }
    }

    protected fun nextSentenceState(increment: Int): Playing {
        val sentence = JiroCallGenerator.generateSentence()
        val parsedSentence = parseSentence(sentence)
        val initialCandidateList = constructTypeSentence(parsedSentence)
        return Playing(
            score + increment,
            miss,
            sentence,
            initialCandidateList,
            0,
            0
        )
    }
}

package component

import logic.GameState
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.events.KeyboardEvent
import react.*
import kotlin.browser.window

class Game : RComponent<RProps, Game.State>() {

    override fun componentDidMount() {
        window.addEventListener("keydown", {
            it as KeyboardEvent
            onInput(it.key)
        })
        setState {
            gameState = GameState.Start
        }
    }

    private fun onInput(key: String) {
        when (val currentState = state.gameState) {
            is GameState.Start -> {
                if (key == " ") {
                    setState {
                        gameState = currentState.newGame()
                    }
                }
            }
            is GameState.Playing -> {
                setState {
                    gameState = currentState.input(key[0])
                }
            }
        }
    }

    private fun drawGameState(context: CanvasRenderingContext2D, gameState: GameState) {
        val canvasWidth = context.canvas.width.toDouble()
        val canvasHeight = context.canvas.height.toDouble()
        context.fillStyle = "white"
        context.fillRect(
            0.0,
            0.0,
            canvasWidth,
            canvasHeight
        )
        when (gameState) {
            is GameState.Start -> {
                val message = "Please press space key to start game!"
                val width = context.measureText(message).width
                context.fillText(message, canvasWidth / 2 - width / 2, 100.0)
            }
            is GameState.Playing -> {
                val sentence = gameState.sentence
                context.fillStyle = "black"
                context.font = "20px \"Hiragino Kaku Gothic ProN\""
                context.fillText(sentence, canvasWidth / 2 - context.measureText(sentence).width / 2, 100.0)
                val candidateSentence = gameState.typingCandidateList.joinToString("") {
                    it.first()
                }
                val currentPosition = gameState.typingCandidateList
                    .take(gameState.currentCharIndex)
                    .sumBy { it.first().length } + gameState.currentRomeIndex
                val width = context.measureText(candidateSentence).width
                var x = canvasWidth / 2 - width / 2
                candidateSentence.forEachIndexed { index, c ->
                    if (index == currentPosition) {
                        context.fillStyle = "red"
                    } else {
                        context.fillStyle = "black"
                    }
                    context.fillText(c.toString(), x, 200.0)
                    x += context.measureText(c.toString()).width
                }
                context.restore()
            }
        }
    }

    override fun RBuilder.render() {
        canvasComponent("800", "600") { context ->
            drawGameState(context, state.gameState)
        }
    }

    interface State : RState {
        var gameState: GameState
    }
}

package component

import logic.GameState
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Image
import org.w3c.dom.events.KeyboardEvent
import react.*
import kotlin.browser.window

class Game : RComponent<RProps, Game.State>() {

    init {
        state.apply {
            gameState = GameState.Start
        }
    }

    override fun componentDidMount() {
        window.addEventListener("keydown", {
            it as KeyboardEvent
            onInput(it.key)
        })
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

    private fun drawImage(context: CanvasRenderingContext2D, image: Image, x: Double, y: Double, w: Double, h: Double) {
        image.onload = {
            context.drawImage(image, x, y, w, h)
        }
        context.drawImage(image, x, y, w, h)
    }

    private fun drawStart(context: CanvasRenderingContext2D, gameState: GameState.Start) {
        val canvasWidth = context.canvas.width.toDouble()
        val title = "ジロリアンタイピング"
        val titleWidth = context.measureText(title).width
        context.fillText(title, canvasWidth / 2 - titleWidth / 2, 100.0)
        val jiroHeight = 300.0
        val jiroWidth = jiro.width * jiroHeight / jiro.height
        drawImage(context, jiro, canvasWidth / 2 - jiroWidth / 2, 150.0, jiroWidth, jiroHeight)
        val message = "スペースキーでスタート"
        val messageWidth = context.measureText(message).width
        context.fillText(message, canvasWidth / 2 - messageWidth / 2, 500.0)
    }

    private fun drawPlaying(context: CanvasRenderingContext2D, gameState: GameState.Playing) {
        val canvasWidth = context.canvas.width.toDouble()
        context.fillText("スコア: ${gameState.score}杯", 50.0, 50.0)
        val sentence = gameState.sentence
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
            context.fillText(c.toString(), x, 150.0)
            x += context.measureText(c.toString()).width
        }
        val teninHeight = 300.0
        val teninWidth = tenin.width * teninHeight / tenin.height
        drawImage(context, tenin, canvasWidth / 2 - teninWidth / 2, 250.0, teninWidth, teninHeight)
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
        context.fillStyle = "black"
        context.font = "20px \"Hiragino Kaku Gothic ProN\""
        when (gameState) {
            is GameState.Start -> {
                drawStart(context, gameState)
            }
            is GameState.Playing -> {
                drawPlaying(context, gameState)
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

    companion object {
        private val jiro = Image().apply {
            src = "./images/ramen_moyashi.png"
        }
        private val tenin = Image().apply {
            src = "./images/ramen_tenin.png"
        }
    }
}

package component

import logic.GameState
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Image
import org.w3c.dom.events.KeyboardEvent
import react.*
import kotlin.browser.window

class Game : RComponent<RProps, Game.State>() {

    override fun componentDidMount() {
        window.addEventListener("keydown", {
            it as KeyboardEvent
            onInput(it.key)
        })
        jiro.onload = {
            setState {
                gameState = GameState.Start
            }
        }
    }

    private fun onInput(key: String) {
        when (val currentState = state.gameState) {
            is GameState.Start -> {
                if (key == " ") {
                    setState {
                        gameState = currentState.startGame()
                    }
                }
            }
            is GameState.Playing -> {
                setState {
                    gameState = currentState.input(key[0])
                }
            }
            is GameState.End -> {
                if (key == " ") {
                    setState {
                        gameState = currentState.newGame()
                    }
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
        val teninHeight = 300.0
        val teninWidth = tenin.width * teninHeight / tenin.height
        drawImage(context, tenin, canvasWidth / 2 - teninWidth / 2, 150.0, teninWidth, teninHeight)
        val message = "スペースキーでスタート"
        val messageWidth = context.measureText(message).width
        context.fillText(message, canvasWidth / 2 - messageWidth / 2, 500.0)
    }

    private fun drawPlaying(context: CanvasRenderingContext2D, gameState: GameState.Playing) {
        val canvasWidth = context.canvas.width.toDouble()
        context.fillText("スコア: ${gameState.score}杯", 50.0, 50.0)
        context.fillText("ミス: ${gameState.miss}", 50.0, 80.0)
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
        val jiroHeight = 300.0
        val jiroWidth = jiro.width * jiroHeight / jiro.height
        drawImage(context, jiro, canvasWidth / 2 - jiroWidth / 2, 250.0, jiroWidth, jiroHeight)
    }

    private fun drawEnd(context: CanvasRenderingContext2D, gameState: GameState.End) {
        val canvasWidth = context.canvas.width.toDouble()
        val title = "リザルト"
        val titleWidth = context.measureText(title).width
        context.fillText(title, canvasWidth / 2 - titleWidth / 2, 50.0)
        val scoreText = "スコア: ${gameState.score}杯"
        val scoreWidth = context.measureText(scoreText).width
        context.fillText(scoreText, canvasWidth / 2 - scoreWidth / 2, 100.0)
        val missText = "ミス: ${gameState.miss}"
        val missWidth = context.measureText(missText).width
        context.fillText(missText, canvasWidth / 2 - missWidth / 2, 130.0)
        val meganeHeight = 300.0
        val meganeWidth = megane.width * meganeHeight / megane.height
        drawImage(context, megane, canvasWidth / 2 - meganeWidth / 2, 200.0, meganeWidth, meganeHeight)
        val restartText = "スペースキーでタイトルに戻る"
        val restartWidth = context.measureText(restartText).width
        context.fillText(restartText, canvasWidth / 2 - restartWidth / 2, 550.0)
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
            is GameState.End -> {
                drawEnd(context, gameState)
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
        private val megane = Image().apply {
            src = "./images/ramen_megane_kumoru.png"
        }
    }
}

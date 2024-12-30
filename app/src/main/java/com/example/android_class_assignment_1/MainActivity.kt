package com.example.android_class_assignment_1

import Board
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var board: Board
    private lateinit var imageViews: Array<ImageView>
    private lateinit var feedback: TextView
    private lateinit var play: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupUi()
        setupGameListeners()
    }
    private fun setupUi() {
         imageViews = arrayOf(
            findViewById(R.id.imageView1),
            findViewById(R.id.imageView2),
            findViewById(R.id.imageView3),
            findViewById(R.id.imageView4),
            findViewById(R.id.imageView5),
            findViewById(R.id.imageView6),
            findViewById(R.id.imageView7),
            findViewById(R.id.imageView8),
            findViewById(R.id.imageView9)
        )
        // disable all cells at the beginning
        imageViews.forEach {
            it.isEnabled = false
        }
        feedback = findViewById(R.id.feedback)
        play = findViewById(R.id.play_button)
        board = Board() // create a new instance of the Board class
    }
    private fun setupGameListeners() {
        play.setOnClickListener {
            startGame()
        }
        // set click listeners for each cell
        imageViews.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                handleMove(index)
            }
        }

    }
    private fun startGame() {
        board.reset()
        imageViews.forEach {
            it.isEnabled = true
            it.setImageResource(R.drawable.blank)
        }
        feedback.text = "${board.getCurrentPlayer()}'s turn"

        play.visibility = View.GONE // hide the button
    }
    private fun handleMove(index: Int) {
        if (board.makeMove(index / 3, index % 3)) {
            imageViews[index].isEnabled = false // disable the cell
            val player = board.getCurrentPlayer()
            imageViews[index].setImageResource(if (player == "X") R.drawable.x else R.drawable.o)
            if (board.checkWin()) { // check if the current player wins
                feedback.text = "$player wins!"
                endGame()
            } else if (board.isFullBoard()) { // check if the board is full
                feedback.text = "It's a draw!"
                endGame()
            } else { // switch to the next player
                board.switchPlayer()
                feedback.text = "${board.getCurrentPlayer()}'s turn"
            }
        }
    }
    private fun endGame() {
        // disable all cells
        imageViews.forEach {
            it.isEnabled = false
        }
        // show the play button
        play.text = "Play Again" // prepare for next game
        play.visibility = View.VISIBLE
    }
}
 class Board{
     private lateinit var board: Array<Array<String>>
     private lateinit var currentPlayer: String

     init {
            reset()
     }
     fun reset() {
         board = Array(3) { Array(3) { "" } }
         currentPlayer = "X"
     }

     fun getCurrentPlayer(): String {
            return currentPlayer
     }

     fun switchPlayer() {
         if (currentPlayer == "X") {
             currentPlayer = "O"
         } else {
             currentPlayer = "X"
         }
     }
    fun makeMove(row: Int, col: Int): Boolean {
        if (isEmptyCell(row, col)) {
            board[row][col] = currentPlayer
            return true
        }
        return false
    }
    fun checkWin(): Boolean {
        for (i in 0..2) {
            // check rows
            if (!isEmptyCell(i,0) && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true
            }
            // check columns
            if (!isEmptyCell(0,i) && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true
            }

        }
        // check diagonals
        if (!isEmptyCell(0,0) && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true
        }
        if (!isEmptyCell(0,2) && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true
        }
        return false
    }

    fun isFullBoard(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (isEmptyCell(i, j)) {
                    return false
                }
            }
        }
        return true
    }

     private fun isEmptyCell(row: Int, col: Int): Boolean {
         return board[row][col] == ""
     }

}
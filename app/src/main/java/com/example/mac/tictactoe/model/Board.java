package com.example.mac.tictactoe.model;

public class Board {
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private Cell board[][] = new Cell[3][3];

    private String winner;
    private boolean isGameOver;

    public Board(String player1, String player2) {
        this.playerX = new Player("X", player1);
        this.playerO = new Player("O", player2);
        this.currentPlayer = this.playerX;

        this.initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < 3 ; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column] = new Cell();
            }
        }
    }

    public void fillCell(int row, int column) {
        Cell selectedCell = board[row][column];

        if (selectedCell.getValue().equals("")) {
            board[row][column].setValue(currentPlayer.name);

            if (isPlayerWin(currentPlayer, row, column)) {
                this.winner = currentPlayer.username + " WIN!!!";
                this.isGameOver = true;
            } else if(isDraw()) {
                this.winner = "DRAW !!!";
                this.isGameOver = true;
            } else {
                this.changePlayer();
            }
        }
    }

    public Cell getCell(int row, int column) {
        return board[row][column];
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getWinner() {
        return winner;
    }

    private void changePlayer() {
        if (currentPlayer.name.equals(playerX.name)) {
            this.currentPlayer = playerO;
        } else {
            this.currentPlayer = playerX;
        }
    }

    private boolean isPlayerWin(Player player, int row, int col) {
        String playerName = player.name;
        if (board[row][0].getValue().equals(playerName)
                && board[row][1].getValue().equals(playerName)
                && board[row][2].getValue().equals(playerName)) return true;

        if(board[0][col].getValue().equals(playerName)
                && board[1][col].getValue().equals(playerName)
                && board[2][col].getValue().equals(playerName)) return true;

        if (row == col
                && board[0][0].getValue().equals(playerName)
                && board[1][1].getValue().equals(playerName)
                && board[2][2].getValue().equals(playerName)) return true;

        if (row + col == 2
                && board[0][2].getValue().equals(playerName)
                && board[1][1].getValue().equals(playerName)
                && board[2][0].getValue().equals(playerName)) return true;

        return false;
    }

    private boolean isDraw() {
        int filledCell = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                String cellValue = board[row][column].getValue();
                if (cellValue.equals("") == false) {
                    filledCell++;
                }
            }
        }

        return filledCell == 9 ? true : false;
    }

    public void resetBoard() {
        initBoard();
        this.isGameOver = false;
        this.currentPlayer = playerX;
    }
}

package main;

public class ChessBoard {

    // создаем поле для игры 8 на 8
    public ChessPiece[][] board = new ChessPiece[8][8];

    // чей сейчас ход - "White" или "Black"
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    // обычный гетер который указывает [ход какого игрока сейчас идет]
    public String nowPlayerColor() {
        return this.nowPlayer;
    }


    // метод в который передается с какой линии и колонки на какую линию и колонку нужно перевести фигуру
    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {

        // проверяем что такая клетка существует на доске
        if (checkPos(startLine) && checkPos(startColumn)) {

            // если цвет текущего игрока не совпадает с цветом фигуры на данной клетки, то ход невозможен
            // [нельзя двигать чужие фигуры]
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            // если данная фигура может быть сдвинута на эту позицию
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                // проверяем позицию для рокировки
                // если фигура это король (King) или ладья (Rook)
                if (board[startLine][startColumn].getSymbol().equals("K") ||
                        board[startLine][startColumn].getSymbol().equals("R")) {
                    // устанавливаем что данная фигура еще не двигралась
                    board[startLine][startColumn].check = false;
                }
                // если была возможность сдвинуть фигру, то переместили на конечную позицию (клетку)
                board[endLine][endColumn] = board[startLine][startColumn];
                // удалили фигуру со стартовой позиции (клетки)
                board[startLine][startColumn] = null; // set null to previous cell

                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    // этот метод тупо выводи доску (board) в консоль (либо две точки [..], либо фигуру и цвет [Hb]
    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}

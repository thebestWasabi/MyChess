package main;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // все координаты существуют
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            // стартовая координата не равна конечной
            if (line != toLine && column != toColumn &&
                    // конечная клетка пустая или цвет фигуры в конечной клетке не равен текущему
                    (chessBoard.board[toLine][toColumn] == null || chessBoard.board[toLine][toColumn].color.equals(this.color))
                    // и стартовая клетка не пустая
                    && chessBoard.board[line][column] != null) {

                // если стартовая клетка не равна коню, то не ходим
                if (!chessBoard.board[line][column].equals(this)) {
                    return false;
                }

            }

        } else return false;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}

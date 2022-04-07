package component.game;

import java.awt.Point;
import blocks.*;

/**
 * 20 [ ][ ][ ][X][X][X][X][ ][ ][ ]
 * 19 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 18 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 17 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 16 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 15 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 14 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 13 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 12 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 11 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 10 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 9  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 8  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 7  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 6  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 5  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 4  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 3  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 2  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 1  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 *     1  2  3  4  5  6  7  8  9 10
 */
public class Board {

    private static final int DROP_X = 5;
    private static final int DROP_Y = 19;

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    private Point blockCenter = new Point(DROP_X, DROP_Y);

    private Block currentBlock;

    private BoardCell[][] board = new BoardCell[WIDTH][HEIGHT];

    private int fullLines = 0;

    public Board() {
        board = createEmptyBoard();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getFullLines() {
        return fullLines;
    }

    public void setFullLines(int fullLines) {
        this.fullLines = fullLines;
    }

    public BoardCell getBoardAt(int x, int y) {
        return board[x][y];
    }

    private boolean isRowFull(int y) {
        for (int x = 0; x < WIDTH; x++) {
            if (getBoardAt(x, y).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void removeFullRows() {
        BoardCell[][] boardX = createEmptyBoard();

        int full = 0;
        for (int y = 0; y < HEIGHT; y++) {
            if (isRowFull(y)) {
                full++;
            } else {
                copyRow(boardX, y, y-full);
            }
        }

        fullLines += full;
        board = boardX;
    }

    private void copyRow(BoardCell[][] to, int y, int toy) {
        for (int x = 0; x < WIDTH; x++) {
            to[x][toy] = board[x][y];
        }
    }

    private BoardCell[][] createEmptyBoard() {
        BoardCell[][] boardX = new BoardCell[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x++) {
            boardX[x] = BoardCell.getEmptyArray(HEIGHT);
        }
        return boardX;
    }

    public void rotate() {
        Block rot = currentBlock.rotate();
        if (fit(rot.getPoints(), 0, 0)) {

            currentBlock = rot;
        }
    }

    public void moveLeft() {
        if (fit(currentBlock.getPoints(), -1, 0)) {
            mv( -1, 0);
        }
    }

    public void moveRight() {
        if (fit(currentBlock.getPoints(), 1, 0)) {
            mv(1, 0);
        }
    }

    public boolean canCurrentPieceMoveDown() {
        return fit(currentBlock.getPoints(), 0, -1);
    }

    public void moveDown() {
        if (canCurrentPieceMoveDown()) {
            mv(0, -1);
            removeFullRows();
        }
    }

    public boolean fit(Point[] points, int moveX, int moveY) {
        for (Point point : points) {
            int x = blockCenter.x + point.x + moveX;
            int y = blockCenter.y + point.y + moveY;

            if (x < 0 || x >= getWidth() || y >= getHeight() || y < 0) {
                return false;
            }

            if (!board[x][y].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public BoardCell[][] getBoardWithBlock() {
        BoardCell[][] dest = new BoardCell[WIDTH][HEIGHT];

        for (int y = 0; y < WIDTH; y++) {
            System.arraycopy(board[y], 0, dest[y], 0, board[0].length);
        }

        // add block
        for (Point point : currentBlock.getPoints()) {
            int x = point.x + blockCenter.x;
            int y = point.y + blockCenter.y;
            dest[x][y] = BoardCell.getCell(currentBlock.getType());
        }

        return dest;
    }

    private void addBlockToBoard() {
        for (Point point : currentBlock.getPoints()) {
            int x = blockCenter.x + point.x;
            int y = blockCenter.y + point.y;
            board[x][y] = BoardCell.getCell(currentBlock.getType());
        }
    }

    private void mv(int moveX, int moveY) {
        blockCenter = new Point(blockCenter.x + moveX, blockCenter.y + moveY);
    }

    public void setCurrentBlock(Block block) {
        if (currentBlock != null) {
            addBlockToBoard();
        }
        currentBlock = block;
        resetBlockCenter();
    }

    private void resetBlockCenter() {
        blockCenter.x = DROP_X;
        blockCenter.y = DROP_Y;
    }

}

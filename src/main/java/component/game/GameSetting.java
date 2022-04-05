package component.game;

import blocks.*;

public class GameSetting {


    private final Board board;
    private Block nextBlock;

    private boolean playing = false;
    private boolean paused = false;
    private boolean dropping = false;
    private boolean gameOver = false;

    private int freeFallIterations;
    private int totalScore;

    public GameSetting() {
        board = new Board();
    }

    public BoardCell[][] getBoardCells() {
        return board.getBoardWithBlock();
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public long getIterationDelay() {
        return (long) (((11 - getLevel()) * 0.05) * 1000);
    }

    public int getScore() {
        return ((21 + (3 * getLevel())) - freeFallIterations);
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getLines() {
        return board.getFullLines();
    }

    public int getLevel() {
        if ((board.getFullLines() >= 1) && (board.getFullLines() <= 90)) {
            return 1 + ((board.getFullLines() - 1) / 10);
        } else if (board.getFullLines() >= 91) {
            return 10;
        } else {
            return 1;
        }
    }

    public void startGame() {
        paused = false;
        dropping = false;
        nextBlock = Block.getRandomBlock();
        board.setCurrentBlock(Block.getRandomBlock());
        playing = true;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void pauseGame() {
        paused = !paused;
    }

    public void moveLeft() {
        board.moveLeft();
    }

    public void moveRight() {
        board.moveRight();
    }

    public void moveDown() {
        if (!board.canCurrentPieceMoveDown()) {

            if (freeFallIterations == 0) {
                playing = false;
                gameOver = true;
            } else {
                dropping = false;
                board.setCurrentBlock(nextBlock);
                nextBlock = Block.getRandomBlock();
                totalScore += getScore();
                freeFallIterations = 0;
            }
        } else {
            board.moveDown();
            freeFallIterations++;
        }
    }

    public void rotate() {
        board.rotate();
    }

    public void drop() {
        dropping = true;
    }

    public boolean isDropping() {
        return dropping;
    }

}

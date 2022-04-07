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
    private int level = 1;
    private int lineCount = 0;

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
        return (long)(((11 - getLevel()) * 0.05) * 1000);
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public int getScore() {
        if (board.getFullLines() > getLineCount()) {
            if (board.getFullLines() == getLineCount() + 1) {
                setLineCount(board.getFullLines());
                return 100 * getLevel();
            } else if(board.getFullLines() == getLineCount() + 2) {
                setLineCount(board.getFullLines());
                return 100 * getLevel() * 2;
            } else if (board.getFullLines() == getLineCount() + 3) {
                setLineCount(board.getFullLines());
                return 100 * getLevel() * 3;
            } else {
                setLineCount(board.getFullLines());
                return 100 * getLevel() * 4;
            }
        } else {
            return getLevel();
        }
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getLines() {
        return board.getFullLines();
    }

    public int getLevel() {
        if (board.getFullLines() >= 10 * level) {
            level = level + 1;
            return level;
        } else {
            return level;
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
                board.setFullLines(board.getFullLines());
                totalScore += getScore();
                freeFallIterations = 0;
            }
        } else {
            board.moveDown();
            totalScore += getScore();
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

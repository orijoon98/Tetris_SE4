package component.game;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import blocks.Block;
import blocks.BlockType;
import org.junit.Test;

public class BoardTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    @Test
    public void newBoardIsFullOfEmptyCells() {
        Board board = new Board();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                assertTrue(board.getBoardAt(x, y).isEmpty());
            }
        }
    }

    @Test
    public void moveDownO() {

        Board board = new Board();
        board.setCurrentBlock(Block.getBlock(BlockType.O));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 18);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    @Test
    public void moveDownI() {

        Board board = new Board();
        board.setCurrentBlock(Block.getBlock(BlockType.I));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 19);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    @Test
    public void moveDownJ() {

        Board board = new Board();
        board.setCurrentBlock(Block.getBlock(BlockType.J));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 18);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    @Test
    public void moveDownL() {

        Board board = new Board();
        board.setCurrentBlock(Block.getBlock(BlockType.L));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 18);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    @Test
    public void moveDownS() {

        Board board = new Board();
        board.setCurrentBlock(Block.getBlock(BlockType.S));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 18);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    @Test
    public void moveDownT() {

        Board board = new Board();
        board.setCurrentBlock(Block.getBlock(BlockType.T));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 18);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    @Test
    public void moveDownZ() {

        Board board = new Board();
        board.setCurrentBlock(Block.getBlock(BlockType.Z));
        assertTrue(board.canCurrentPieceMoveDown());

        // move current piece at bottom of the board
        repeat(new Repeater() {
            @Override
            public void doAction() {
                board.moveDown();
            }
        }, 18);

        assertFalse(board.canCurrentPieceMoveDown());
    }

    private interface Repeater {
        void doAction();
    }

    private void repeat(Repeater repeat, int count) {
        for (int i = 0; i < count; i++) {
            repeat.doAction();
        }
    }

}

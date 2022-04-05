package component.game;

import java.util.Arrays;
import blocks.*;

public class BoardCell {

    private final BlockType blockType;

    private BoardCell() {
        blockType = null;
    }

    private BoardCell(BlockType type) {
        blockType = type;
    }

    public boolean isEmpty() {
        return blockType == null;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public static BoardCell getCell(BlockType blockType) {
        return new BoardCell(blockType);
    }

    public static BoardCell[] getEmptyArray(int size) {
        BoardCell[] cells = new BoardCell[size];
        Arrays.fill(cells, new BoardCell());
        return cells;
    }

}

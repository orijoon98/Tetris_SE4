package component.game;

import java.util.Arrays;
import blocks.*;

public class BoardCell {

    private final BlockType pieceType;

    private BoardCell() {
        pieceType = null;
    }

    private BoardCell(BlockType type) {
        pieceType = type;
    }

    public boolean isEmpty() {
        return pieceType == null;
    }

    public BlockType getPieceType() {
        return pieceType;
    }

    public static BoardCell getCell(BlockType pieceType) {
        return new BoardCell(pieceType);
    }

    public static BoardCell[] getEmptyArray(int size) {
        BoardCell[] cells = new BoardCell[size];
        Arrays.fill(cells, new BoardCell());
        return cells;
    }

}

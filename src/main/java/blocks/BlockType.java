package blocks;

import java.awt.*;
import java.util.Random;

public enum BlockType {

    O(0, p(-1, 0), p(0, 0),  p(-1, -1), p(0, -1)),
    I(2, p(-2, 0), p(-1, 0), p(0, 0),   p(1, 0)),
    S(2, p(0, 0),  p(1, 0),  p(-1, -1), p(0, -1)),
    Z(2, p(-1, 0), p(0, 0),  p(0, -1),  p(1, -1)),
    L(4, p(-1, 0), p(0, 0),  p(1, 0),   p(-1, -1)),
    J(4, p(-1, 0), p(0, 0),  p(1, 0),   p(1, -1)),
    T(4, p(-1, 0), p(0, 0),  p(1, 0),   p(0, -1));


    private static final Random random = new Random();
    private final int maxOrientations;
    private final Point points[];

    BlockType(int maxOrientations, Point... points) {
        this.maxOrientations = maxOrientations;
        this.points = points;
    }

    public static BlockType getRandomBlock() {
        return BlockType.values()[random.nextInt(BlockType.values().length)];
    }

    public Point[] getPoints() {
        return points;
    }

    public int getMaxOrientations() {
        return maxOrientations;
    }

    private static Point p(int x, int y) {
        return new Point(x, y);
    }
}

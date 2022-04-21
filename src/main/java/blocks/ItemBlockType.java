package blocks;

import java.awt.*;
import java.util.Random;

public enum ItemBlockType {

    Y(0, p(-1, 0), p(0, 0), p(-1, -1), p(0, -1), p(-2, -1), p(1, -1)),
    IL(2, p(-2, 0), p(-1, 0), p(0, 0), p(1, 0)),
    SL(2, p(0, 0), p(1, 0), p(-1, -1), p(0, -1)),
    ZL(2, p(-1, 0), p(0, 0), p(0, -1), p(1, -1)),
    LL(4, p(-1, 0), p(0, 0), p(1, 0), p(-1, -1)),
    JL(4, p(-1, 0), p(0, 0), p(1, 0), p(1, -1)),
    TL(4, p(-1, 0), p(0, 0), p(1, 0), p(0, -1));


    private static final Random random = new Random();
    private final int maxOrientations;
    private final Point points[];

    ItemBlockType(int maxOrientations, Point... points) {
        this.maxOrientations = maxOrientations;
        this.points = points;
    }

    public static ItemBlockType getRandomBlock() {
        return ItemBlockType.values()[random.nextInt(ItemBlockType.values().length)];
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
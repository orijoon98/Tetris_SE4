package blocks;

import java.awt.*;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import blocks.BlockProbabilty;

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
    private final String type="normal";

    BlockType(int maxOrientations, Point... points) {
        this.maxOrientations = maxOrientations;
        this.points = points;
    }

    public static BlockType getRandomBlock() {
        BlockProbabilty prob = new BlockProbabilty();

        return BlockType.values()[prob.randomProb()];
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

    public BlockType getBlockType(return type;)

}
package blocks;

import java.awt.*;

public class Block {

    private final Point points[];
    private final BlockType type;
    private final boolean initialOrientation;

    private Block(BlockType blockType, Point[] points, boolean initial) {
        initialOrientation = initial;
        this.points = points;
        this.type = blockType;
    }

    public static Block getRandomPiece() {
        BlockType blockType = BlockType.getRandomPiece();
        return new Block(blockType, blockType.getPoints(), true);
    }

    public static Block getBlock(BlockType blockType) {
        return new Block(blockType, blockType.getPoints(), true);
    }

    public BlockType getType() {
        return type;
    }

    public Point[] getPoints() {
        return points;
    }

    public Block rotate() {
        if (type.getMaxOrientations() == 0) {
            return this;
        } else if (type.getMaxOrientations() == 2) {
            if (initialOrientation) {
                return new Block(type, rotateRight(points), false);
            } else {
                return new Block(type, rotateLeft(points), true);
            }
        }
        return new Block(type, rotateRight(points), true);
    }

    private Point[] rotateLeft(Point toRotate[]) {
        return rotate(toRotate, 1, -1);
    }

    private Point[] rotateRight(Point toRotate[]) {
        return rotate(toRotate, -1, 1);
    }

    private Point[] rotate(Point toRotate[], int x, int y) {
        Point rotated[] = new Point[4];

        for (int i = 0; i < 4; i++) {
            int temp = toRotate[i].x;
            rotated[i] = new Point(x * toRotate[i].y, y * temp);
        }

        return rotated;
    }
}

package blocks;

import java.awt.*;

public class ItemBlock {

    private final Point points[];
    private final ItemBlockType type;
    private final boolean initialOrientation;

    private ItemBlock(ItemBlockType itemblockType, Point[] points, boolean initial) {
        initialOrientation = initial;
        this.points = points;
        this.type = itemblockType;
    }

    public static ItemBlock getRandomBlock() {
        ItemBlockType itemblockType = ItemBlockType.getRandomBlock();
        return new ItemBlock(itemblockType, itemblockType.getPoints(), true);
    }

    public static ItemBlock getBlock(ItemBlockType itemblockType) {
        return new ItemBlock(itemblockType, itemblockType.getPoints(), true);
    }

    public ItemBlockType getType() {
        return type;
    }

    public Point[] getPoints() {
        return points;
    }

    public ItemBlock rotate() {
        if (type.getMaxOrientations() == 0) {
            return this;
        } else if (type.getMaxOrientations() == 2) {
            if (initialOrientation) {
                return new ItemBlock(type, rotateRight(points), false);
            } else {
                return new ItemBlock(type, rotateLeft(points), true);
            }
        }
        return new ItemBlock(type, rotateRight(points), true);
    }

    private Point[] rotateRight(Point toRotate[]) {
        return rotate(toRotate, 1, -1);
    }

    private Point[] rotateLeft(Point toRotate[]) {
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
package blocks;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    private void assertPoints(Point[] expected, Block actual) {
        assertEquals(expected.length, actual.getPoints().length);
        for (Point e : actual.getPoints()) {
            assertTrue(containsPointExactlyOnce(e, expected));
        }
    }

    private boolean containsPointExactlyOnce(Point point, Point[] points) {
        int count = 0;
        for (Point p : points) {
            if (p.x == point.x && p.y == point.y) {
                count++;
            }
        }
        return count == 1;
    }

    @Test
    public void getRandomBlockReturnsValue() {
        assertNotNull(Block.getRandomBlock());
    }

    @Test
    public void rotateBlockO() {
        Block block = Block.getBlock(BlockType.O);

        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, -1);
        points[3] = new Point(0, -1);
        assertPoints(points, block);

        assertPoints(points, block.rotate());
    }

    @Test
    public void rotateBlockI() {
        Block block = Block.getBlock(BlockType.I);

        Point[] points = new Point[4];
        points[0] = new Point(-2, 0);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(1, 0);
        assertPoints(points, block);

        points[0] = new Point(0, 2);
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, -1);
        block = block.rotate();
        assertPoints(points, block);

        points[0] = new Point(-2, 0);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(1, 0);
        block = block.rotate();
        assertPoints(points, block);
    }

    @Test
    public void rotatePieceS() {
        Block block = Block.getBlock(BlockType.S);

        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, -1);
        points[3] = new Point(0, -1);
        assertPoints(points, block);

        points[0] = new Point(0, 0);
        points[1] = new Point(0, -1);
        points[2] = new Point(-1, 1);
        points[3] = new Point(-1, 0);
        block = block.rotate();
        assertPoints(points, block);

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, -1);
        points[3] = new Point(0, -1);
        block = block.rotate();
        assertPoints(points, block);
    }

    @Test
    public void rotatePieceZ() {
        Block block = Block.getBlock(BlockType.Z);

        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(1, -1);
        assertPoints(points, block);

        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, 0);
        points[3] = new Point(-1, -1);
        block = block.rotate();
        assertPoints(points, block);

        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(1, -1);
        block = block.rotate();
        assertPoints(points, block);
    }

    @Test
    public void rotatePieceL() {
        Block block = Block.getBlock(BlockType.L);

        // Start
        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(-1, -1);
        assertPoints(points, block);

        // 90
        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(-1, 1);
        block = block.rotate();
        assertPoints(points, block);

        // 180
        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, 0);
        points[3] = new Point(1, 1);
        block = block.rotate();
        assertPoints(points, block);

        // 270
        points[0] = new Point(0, -1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(1, -1);
        block = block.rotate();
        assertPoints(points, block);

        // 360 - Start
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(-1, -1);
        block = block.rotate();
        assertPoints(points, block);
    }

    @Test
    public void rotatePieceJ() {
        Block block = Block.getBlock(BlockType.J);

        // start
        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(1, -1);
        assertPoints(points, block);

        // 90
        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(-1, -1);
        block = block.rotate();
        assertPoints(points, block);

        // 180
        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, 0);
        points[3] = new Point(-1, 1);
        block = block.rotate();
        assertPoints(points, block);

        // 270
        points[0] = new Point(0, -1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(1, 1);
        block = block.rotate();
        assertPoints(points, block);

        // 360
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(1, -1);
        block = block.rotate();
        assertPoints(points, block);
    }

    @Test
    public void rotatePieceT() {
        Block block = Block.getBlock(BlockType.T);

        // Start
        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(0, -1);
        assertPoints(points, block);

        // 90
        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(-1, 0);
        block = block.rotate();
        assertPoints(points, block);

        // 180
        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, 0);
        points[3] = new Point(0, 1);
        block = block.rotate();
        assertPoints(points, block);

        // 270
        points[0] = new Point(0, -1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(1, 0);
        block = block.rotate();
        assertPoints(points, block);

        // 360 - Start
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(0, -1);
        block = block.rotate();
        assertPoints(points, block);
    }
}



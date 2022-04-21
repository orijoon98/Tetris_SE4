package component;

import blocks.BlockType;
import component.game.BoardCell;
import component.game.GameSetting;
import input.ItemInput;
import setting.UserSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class Item extends Canvas {
	static UserSetting UserSetting = new UserSetting();	
	private static int sizeInt = UserSetting.getSizeInt();

    public JFrame itemFrame;
    private GameSetting game = new GameSetting();
    private final BufferStrategy strategy;

    private final int BOARD_CORNER_X = sizeInt * 150;
    private final int BOARD_CORNER_Y = sizeInt * 25;

    private final ItemInput keyboard = new ItemInput();
    private long lastIteration = System.currentTimeMillis();

    private static final int BLOCK_WIDTH = sizeInt * 10;
    private static final String mode="item";

    public String getMode(){return mode;}

    public Item() {
        itemFrame = new JFrame("SeoulTech SE4 Tetris");
        
        JPanel panel = (JPanel) itemFrame.getContentPane();
        panel.setPreferredSize(new Dimension(sizeInt * 400, sizeInt * 300));
        panel.setLayout(null);

        setBounds(sizeInt * 0, sizeInt * 0, sizeInt * 400, sizeInt * 300);
        
        panel.add(this);
        
        setIgnoreRepaint(true);

        itemFrame.pack();
        itemFrame.setResizable(false);
        itemFrame.setVisible(false);

        itemFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(keyboard);
        requestFocus();

        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    public void itemLoop(Frame homeFrame, Home home) {
        while (true) {
            if (keyboard.newGame()) {
                game = new GameSetting();
                game.startGame();
            }
            if (game.isPlaying()) {
                if (!game.isPaused()) {
                    tetrisLoop();
                }
                if (keyboard.pauseGame()) {
                    game.pauseGame();
                }
            }
            if (keyboard.home()) {
                game = new GameSetting();
                homeFrame.setVisible(true);
                itemFrame.setVisible(false);
                homeFrame.requestFocus();
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) { }
            draw(homeFrame, home);
        }
    }

    void tetrisLoop() {
        if (game.isDropping()) {
            game.moveDown();
        } else if (System.currentTimeMillis() - lastIteration >= game.getIterationDelay()) {
            game.moveDown();
            lastIteration = System.currentTimeMillis();
        }
        if (keyboard.rotate()) {
            game.rotate();
        } else if (keyboard.left()) {
            game.moveLeft();
        } else if (keyboard.right()) {
            game.moveRight();
        } else if (keyboard.down()) {
            game.moveDown();
        }
        else if (keyboard.drop()) {
            game.drop();
        }
    }

    public void draw(Frame homeFrame, Home home) {
        Graphics2D g = getGameGraphics();
        drawEmptyBoard(g);
        drawHelpBox(g);
        drawPiecePreviewBox(g);

        if (game.isPlaying()) {
            drawCells(g);
            drawBlockPreview(g, game.getNextBlock().getType());

            if (game.isPaused()) {
                drawGamePaused(g);
            }
        }

        if (game.isGameOver()) {
            drawCells(g);
            drawGameOver(g);

            ScoreBoard scoreBoard = home.scoreBoardGUI;

            int min = scoreBoard.min("item");
            if (min < game.getTotalScore()) {
                addNewRecordPanel(home, game.getTotalScore());
            } else {
                addGameOverPanel(home, game.getTotalScore());
            }

            home.itemGameOverGUI.rank = 0;
            home.itemGameOverGUI.score = game.getTotalScore();
            home.itemGameOverGUI.itemGameOverFrame.setVisible(true);
            itemFrame.setVisible(false);
            game = new GameSetting();
        }

        drawStatus(g);
        drawPlayTetris(g);

        g.dispose();
        strategy.show();
    }

    private void addNewRecordPanel(Home home, int score) {
        home.itemGameOverGUI.contentPanel = new Panel();
        home.itemGameOverGUI.contentPanel.setBounds(sizeInt * 75, sizeInt * 110, sizeInt * 250, sizeInt * 105);
        home.itemGameOverGUI.contentPanel.setLayout(new GridLayout(2, 1));
        home.itemGameOverGUI.contentPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));
        home.itemGameOverGUI.contentPanel.setForeground(Color.white);

        Label textLabel = new Label("New Record " + Integer.toString(score));
        textLabel.setAlignment(Label.CENTER);

        home.itemGameOverGUI.textField = new TextField("Your Name");
        home.itemGameOverGUI.textField.setForeground(Color.black);

        home.itemGameOverGUI.contentPanel.add(textLabel);
        home.itemGameOverGUI.contentPanel.add(home.itemGameOverGUI.textField);

        home.itemGameOverGUI.itemGameOverPanel.add(home.itemGameOverGUI.contentPanel);
    }

    private void addGameOverPanel(Home home, int score) {
        home.itemGameOverGUI.contentPanel = new Panel();
        home.itemGameOverGUI.contentPanel.setBounds(sizeInt * 75, sizeInt * 110, sizeInt * 250, sizeInt * 105);
        home.itemGameOverGUI.contentPanel.setLayout(new GridLayout(2, 1));
        home.itemGameOverGUI.contentPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));
        home.itemGameOverGUI.contentPanel.setForeground(Color.white);

        Label textLabel = new Label("Your Score");
        Label scoreLabel = new Label(Integer.toString(score));
        textLabel.setAlignment(Label.CENTER);
        scoreLabel.setAlignment(Label.CENTER);

        home.itemGameOverGUI.contentPanel.add(textLabel);
        home.itemGameOverGUI.contentPanel.add(scoreLabel);

        home.itemGameOverGUI.itemGameOverPanel.add(home.itemGameOverGUI.contentPanel);
    }

    private Graphics2D getGameGraphics() {
        return (Graphics2D) strategy.getDrawGraphics();
    }

    private void drawCells(Graphics2D g) {
        BoardCell[][] cells = game.getBoardCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                drawBlock(g, BOARD_CORNER_X + i * sizeInt * 10, BOARD_CORNER_Y + (19 - j) * sizeInt * 10, getBoardCellColor(cells[i][j]));
            }
        }
    }

    private void drawEmptyBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(sizeInt * 0, sizeInt * 0, sizeInt * 400, sizeInt * 300);
        g.setColor(Color.GRAY);
        g.drawRect(BOARD_CORNER_X - 1, BOARD_CORNER_Y - 1, 10 * BLOCK_WIDTH + sizeInt * 1, 20 * BLOCK_WIDTH + sizeInt * 1);
    }

    private void drawStatus(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
        g.setColor(Color.RED);
        g.drawString("Mode: Item", sizeInt * 5, sizeInt * 10);
        g.drawString(getLevel(), sizeInt * 5, sizeInt * 20);
        g.drawString(getLines(), sizeInt * 5, sizeInt * 30);
        g.drawString(getScore(), sizeInt * 10, sizeInt * 50);
    }

    private void drawGameOver(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, sizeInt * 8);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("GAME OVER", sizeInt * 175, sizeInt * 275);
    }

    private void drawGamePaused(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, sizeInt * 8);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("GAME PAUSED", sizeInt * 175, sizeInt * 275);
    }


    private void drawPlayTetris(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, sizeInt * 8);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Play TETRIS !", sizeInt * 175, sizeInt * 250);
    }

    private String getLevel() {
        return String.format("Your level: %1s", game.getLevel());
    }

    private String getLines() {
        return String.format("Full lines: %1s", game.getLines());
    }

    private String getScore() {
        return String.format("Score     %1s", game.getTotalScore());
    }

    private void drawPiecePreviewBox(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
        g.setColor(Color.RED);
        g.drawString("Next:", sizeInt * 25, sizeInt * 210);
    }

    private void drawHelpBox(Graphics2D g) {
    	UserSetting UserSetting = new UserSetting();
    	g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
        g.setColor(Color.RED);
        g.drawString("H E L P", sizeInt * 25, sizeInt * 70);
        g.drawString("F1: New Game", sizeInt * 5, sizeInt * 80);
        g.drawString("ESC: Pause Game/Continue", sizeInt * 5, sizeInt * 90);
        g.drawString(UserSetting.getStringKey("ROTATE") +" : Rotate", sizeInt * 5, sizeInt * 100);
        g.drawString(UserSetting.getStringKey("LEFT") + " : Move Left", sizeInt * 5, sizeInt * 110);
        g.drawString(UserSetting.getStringKey("RIGHT") + " : Move Right", sizeInt * 5, sizeInt * 120);
        g.drawString(UserSetting.getStringKey("DOWN")+ " : Move Down", sizeInt * 5, sizeInt * 130);
        g.drawString(UserSetting.getStringKey("DROP") + " : Drop", sizeInt * 5, sizeInt * 140);
        g.drawString("F2: Home", sizeInt * 5, sizeInt * 150);
    }

    private void drawBlockPreview(Graphics2D g, ItemBlockType type) {
        for (Point p : type.getPoints()) {
            drawBlock(g, 60 + p.x * BLOCK_WIDTH, 380 + (3 - p.y) * 20, getBlockColor(type));
        }
    }

    private Color getBoardCellColor(BoardCell boardCell) {
        if (boardCell.isEmpty()) {
            return Color.BLACK;
        }
        return getBlockColor(boardCell.getBlockType());
    }

    private Color getBlockColor(BlockType blockType) {
        switch (blockType) {
            case I:
                return Color.RED;
            case J:
                return Color.GRAY;
            case L:
                return Color.CYAN;
            case O:
                return Color.BLUE;
            case S:
                return Color.GREEN;
            default:
                return Color.MAGENTA;
        }
    }

    private Color getBlockColor(ItemBlockType blockType) {
        switch (blockType) {
            case IL:
                return Color.RED;
            case JL:
                return Color.GRAY;
            case LL:
                return Color.CYAN;
            case Y:
                return Color.WHITE;
            case SL:
                return Color.GREEN;
            default:
                return Color.MAGENTA;
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);

        UserSetting UserSetting = new UserSetting();

        if(UserSetting.getColorBlindMode()) {        	
            g.fill3DRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH, true);
            if(color == getBlockColor(BlockType.J) || color == getBlockColor(BlockType.S)) {
            	g.fill3DRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH, false);                	
            }
        }
        else {
            g.fillRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH);        	
        }
        
        g.drawRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH);
    }
}

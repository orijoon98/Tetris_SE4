package component;

import org.json.simple.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ItemGameOver extends Canvas {

    public Frame itemGameOverFrame;
    public Panel itemGameOverPanel, titlePanel, buttonPanel, contentPanel;
    public TextField textField;
    public int score, rank = 0;

    public ItemGameOver(Home home) {
        itemGameOverFrame = new Frame("Seoultech SE4 Tetris");
        itemGameOverFrame.setSize(800, 600);
        itemGameOverFrame.setResizable(false);
        itemGameOverFrame.setLayout(null);
        itemGameOverFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        itemGameOverPanel = new Panel();
        itemGameOverPanel.setSize(800, 600);
        itemGameOverPanel.setBackground(Color.black);
        itemGameOverPanel.setLayout(null);
        itemGameOverPanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));

        Label title = new Label("Game Over");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(150, 470, 500, 50);
        buttonPanel.setLayout(new GridLayout(1, 1));

        Button next = new Button("Continue");

        buttonPanel.add(next);

        itemGameOverPanel.add(titlePanel);
        itemGameOverPanel.add(buttonPanel);

        itemGameOverFrame.add(itemGameOverPanel);

        itemGameOverFrame.setVisible(false);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreBoard scoreBoard = home.scoreBoardGUI;
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", textField.getText());
                    jsonObject.put("score", score);
                    scoreBoard.postTopTen("item", jsonObject);
                    List<ScoreBoard.Scores> scoresList = scoreBoard.getTopTen("item");
                    for (int i = 0; i < scoresList.size(); i++) {
                        if (scoresList.get(i).getName().equals(textField.getText()) && scoresList.get(i).getScore() == score) {
                            rank = i + 1;
                            break;
                        }
                    }
                } catch (Exception ne) {}
                ScoreBoardItem scoreBoarditem = home.scoreBoardGUI.scoreBoardItemGUI;
                scoreBoard.prepareScoreBoardItemTable(rank);
                scoreBoarditem.scoreBoardItemFrame.setVisible(true);
                itemGameOverFrame.setVisible(false);
                scoreBoarditem.scoreBoardItemFrame.requestFocus();
                itemGameOverPanel.remove(contentPanel);
            }
        });
    }
}

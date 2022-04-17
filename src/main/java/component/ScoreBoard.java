package component;

import input.ScoreBoardInput;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends Canvas {

    public Frame scoreBoardFrame;
    private Panel scoreBoardPanel, titlePanel, buttonPanel;

    public ScoreBoardNormal scoreBoardNormalGUI;
    public ScoreBoardItem scoreBoardItemGUI;

    private final ScoreBoardInput keyboard = new ScoreBoardInput();

    private Button normal, item, home;
    private Button selected;
    private List<Button> buttonList = new ArrayList<>();

    public ScoreBoard(Frame homeFrame) {
        prepareScoreBoardGUI(homeFrame);

        scoreBoardNormalGUI = new ScoreBoardNormal(homeFrame, scoreBoardFrame);
        scoreBoardItemGUI = new ScoreBoardItem(homeFrame, scoreBoardFrame);
    }

    private void prepareScoreBoardGUI(Frame homeFrame) {
        scoreBoardFrame = new Frame("Seoultech SE4 Tetris");
        scoreBoardFrame.setSize(800, 600);
        scoreBoardFrame.setResizable(false);
        scoreBoardFrame.setLayout(null);
        scoreBoardFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        scoreBoardPanel = new Panel();
        scoreBoardPanel.setSize(800, 600);
        scoreBoardPanel.setBackground(Color.black);
        scoreBoardPanel.setLayout(null);
        scoreBoardPanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));

        Label title = new Label("Scoreboard");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(150, 220, 500, 300);
        buttonPanel.setLayout(new GridLayout(3, 1));

        normal = new Button("Normal Mode");
        item = new Button("Item Mode");
        home = new Button("Home");

        selected = normal;
        selected.setForeground(Color.gray);
        buttonList.add(normal);
        buttonList.add(item);
        buttonList.add(home);

        buttonPanel.add(normal);
        buttonPanel.add(item);
        buttonPanel.add(home);

        scoreBoardPanel.add(titlePanel);
        scoreBoardPanel.add(buttonPanel);

        scoreBoardFrame.add(scoreBoardPanel);

        scoreBoardFrame.setVisible(false);

        scoreBoardFrame.addKeyListener(keyboard);
        scoreBoardFrame.requestFocus();

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareScoreBoardNormalTable();

                selected.setForeground(Color.black);
                selected = normal;
                selected.setForeground(Color.gray);
                scoreBoardNormalGUI.scoreBoardNormalFrame.setVisible(true);
                scoreBoardFrame.setVisible(false);
                scoreBoardNormalGUI.scoreBoardNormalFrame.requestFocus();
            }
        });

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareScoreBoardItemTable();

                selected.setForeground(Color.black);
                selected = item;
                selected.setForeground(Color.gray);
                scoreBoardItemGUI.scoreBoardItemFrame.setVisible(true);
                scoreBoardFrame.setVisible(false);
                scoreBoardItemGUI.scoreBoardItemFrame.requestFocus();
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = home;
                selected.setForeground(Color.gray);
                homeFrame.setVisible(true);
                scoreBoardFrame.setVisible(false);
                homeFrame.requestFocus();
            }
        });
    }

    public void scoreBoardLoop(Frame homeFrame) {
        while (true) {
            if (keyboard.up()) {
                int cur = buttonList.indexOf(selected);
                int next = (cur - 1 == -1 ) ? 2 : cur - 1;
                selected.setForeground(Color.black);
                selected = buttonList.get(next);
                selected.setForeground(Color.gray);
            }
            if (keyboard.down()) {
                int cur = buttonList.indexOf(selected);
                int next = (cur + 1 == 3 ) ? 0 : cur + 1;
                selected.setForeground(Color.black);
                selected = buttonList.get(next);
                selected.setForeground(Color.gray);
            }
            if (keyboard.enter()) {
                int cur = buttonList.indexOf(selected);
                switch (cur) {
                    case 0:
                        prepareScoreBoardNormalTable();

                        selected = normal;
                        scoreBoardNormalGUI.scoreBoardNormalFrame.setVisible(true);
                        scoreBoardFrame.setVisible(false);
                        scoreBoardNormalGUI.scoreBoardNormalFrame.requestFocus();
                        break;
                    case 1:
                        prepareScoreBoardItemTable();

                        selected = item;
                        scoreBoardItemGUI.scoreBoardItemFrame.setVisible(true);
                        scoreBoardFrame.setVisible(false);
                        scoreBoardItemGUI.scoreBoardItemFrame.requestFocus();
                        break;
                    case 2:
                        selected = home;
                        homeFrame.setVisible(true);
                        scoreBoardFrame.setVisible(false);
                        homeFrame.requestFocus();
                        break;
                    default:
                        break;
                }
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) { }
        }
    }

    private void prepareScoreBoardNormalTable() {
        prepareNormalTable();
        try {
            List<Scores> scoresList = getTopTen("normal");
            if (scoresList.size() < 10) {
                while (scoresList.size() != 10) {
                    Scores score = new Scores();
                    score.setName("");
                    score.setScore(0);
                    scoresList.add(score);
                }
            }
            for (int i = 0; i < 30; i++) {
                switch (i % 3) {
                    case 0:
                        Button rank = new Button(Integer.toString(i/3 + 1));
                        setInactiveButton(rank);
                        scoreBoardNormalGUI.tablePanel.add(rank);
                        break;
                    case 1:
                        int index = i/3;
                        Scores scores = scoresList.get(index);
                        Button name = new Button(scores.getName());
                        setInactiveButton(name);
                        scoreBoardNormalGUI.tablePanel.add(name);
                        break;
                    case 2:
                        index = i/3;
                        scores = scoresList.get(index);
                        Button score = new Button(Integer.toString(scores.getScore()));
                        setInactiveButton(score);
                        scoreBoardNormalGUI.tablePanel.add(score);
                        break;
                    default:
                        break;
                }
            }
        }
        catch (IOException e) {}
        catch (ParseException e) {}
    }

    private void prepareScoreBoardItemTable() {
        prepareItemTable();
        try {
            List<Scores> scoresList = getTopTen("item");
            if (scoresList.size() < 10) {
                while (scoresList.size() != 10) {
                    Scores score = new Scores();
                    score.setName("");
                    score.setScore(0);
                    scoresList.add(score);
                }
            }
            for (int i = 0; i < 30; i++) {
                switch (i % 3) {
                    case 0:
                        Button rank = new Button(Integer.toString(i/3 + 1));
                        setInactiveButton(rank);
                        scoreBoardItemGUI.tablePanel.add(rank);
                        break;
                    case 1:
                        int index = i/3;
                        Scores scores = scoresList.get(index);
                        Button name = new Button(scores.getName());
                        setInactiveButton(name);
                        scoreBoardItemGUI.tablePanel.add(name);
                        break;
                    case 2:
                        index = i/3;
                        scores = scoresList.get(index);
                        Button score = new Button(Integer.toString(scores.getScore()));
                        setInactiveButton(score);
                        scoreBoardItemGUI.tablePanel.add(score);
                        break;
                    default:
                        break;
                }
            }
        }
        catch (IOException e) {}
        catch (ParseException e) {}
    }

    private void prepareNormalTable() {
        Button rank = new Button("Rank");
        Button name = new Button("Name");
        Button score = new Button("Score");
        setInactiveButton(rank);
        setInactiveButton(name);
        setInactiveButton(score);
        scoreBoardNormalGUI.tablePanel.add(rank);
        scoreBoardNormalGUI.tablePanel.add(name);
        scoreBoardNormalGUI.tablePanel.add(score);
    }

    private void prepareItemTable() {
        Button rank = new Button("Rank");
        Button name = new Button("Name");
        Button score = new Button("Score");
        setInactiveButton(rank);
        setInactiveButton(name);
        setInactiveButton(score);
        scoreBoardItemGUI.tablePanel.add(rank);
        scoreBoardItemGUI.tablePanel.add(name);
        scoreBoardItemGUI.tablePanel.add(score);
    }

    private void setInactiveButton(Button button) {
        button.setBackground(Color.white);
        button.setEnabled(false);
    }

    private List<Scores> getTopTen(String mode) throws IOException, ParseException {
        URL url = (mode == "normal") ?
                new URL("http://ec2-3-38-185-14.ap-northeast-2.compute.amazonaws.com:8080/api/normal/score")
                : new URL("http://ec2-3-38-185-14.ap-northeast-2.compute.amazonaws.com:8080/api/item/score");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null)  {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        String response = stringBuffer.toString();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
        JSONArray arr = (JSONArray) jsonObject.get("data");

        List<Scores> scores = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            JSONObject tmp = (JSONObject) arr.get(i);
            Scores score = new Scores();
            score.setName((String) tmp.get("name"));
            score.setScore(Integer.parseInt(String.valueOf(tmp.get("score"))));
            scores.add(score);
        }

        return scores;
    }

    private class Scores {
        private String name;
        private int score;

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}

import animation.*;
import game.GameFlow;
import levels.*;
import biuoop.GUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adi Yadlin
 * This class represents the game.
 */
public class Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * this main runs the game.
     * @param args program arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        AnimationRunner ar = new AnimationRunner(60, gui);
        GameFlow flow = new GameFlow(ar, gui.getKeyboardSensor());
        List<LevelInformation> levelInformationList = new ArrayList<>();
        // run starting screen
        ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),  "enter",
                new StartingScreen(gui.getKeyboardSensor())));
        // add levels to the game
        add_all_levels(levelInformationList);
        // run the wanted levels from the game flow
        flow.runLevels(levelInformationList);
        //End Screen
        int score_to_win = 0;
        for (LevelInformation levelInfo : levelInformationList){
            score_to_win += levelInfo.scoreToWinLevel();
        }
            if(flow.getScore() == score_to_win) {
            ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),  "space",
                    new WinningScreen(gui.getKeyboardSensor())));
        } else {
            ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),  "space",
                    new LosingScreen(gui.getKeyboardSensor(), flow.getScore())));
        }
        gui.close();
    }

    /**
     * this method adds all the levels to the game
     * @param levelInformationList the list of levels
     */
    private static void add_all_levels(List<LevelInformation> levelInformationList) {
        levelInformationList.add(new DirectHit());
        levelInformationList.add(new WideEasy());
        levelInformationList.add(new Green3());
        levelInformationList.add(new FinalFour());
    }
}

import animation.*;
import game.GameFlow;
import levels.*;
import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * This class represents the game.
 */
public class Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    public static final int WINNING_SCORE = 1205;

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
        if(flow.getScore() == WINNING_SCORE) {
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
     * @param levelInformationList
     */
    private static void add_all_levels(List<LevelInformation> levelInformationList) {
        levelInformationList.add(new DirectHit());
        levelInformationList.add(new WideEasy());
        levelInformationList.add(new Green3());
        levelInformationList.add(new FinalFour());
    }
}

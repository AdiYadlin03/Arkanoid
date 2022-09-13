// ID: 315126433

import animation.AnimationRunner;
import animation.GameOver;
import animation.KeyPressStoppableAnimation;
import animation.WinningScreen;
import game.GameFlow;
import levels.*;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * This class represents the game.
 */
public class Ass6Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    /**
     * this main runs the game.
     * @param args program arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        AnimationRunner ar = new AnimationRunner(60, gui);
        //GameLevel gameLevel = new GameLevel(new FinalFour());
        GameFlow flow = new GameFlow(ar, gui.getKeyboardSensor());
        List<LevelInformation> levelInformationList = new ArrayList<>();
        levelInformationList.add(new DirectHit());
        levelInformationList.add(new WideEasy());
        levelInformationList.add(new Green3());
        levelInformationList.add(new FinalFour());
        // run the wanted levels from the game flow
        flow.runLevels(levelInformationList);
        //End Screen
        if(flow.getScore() == 1205) {
            ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),  "space",
                    new WinningScreen(gui.getKeyboardSensor())));
        } else {
            ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),  "space",
                    new GameOver(gui.getKeyboardSensor(), flow.getScore())));
        }
        gui.close();
    }
}

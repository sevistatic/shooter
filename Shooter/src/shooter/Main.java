package shooter;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
 * a 2-d side-scrolling shooter game
 * The player uses the arrow keys to move up and down and the spacebar or mouse
 * buttons to fire.  The game is won when the score reaches 100.  The game is
 * lost when the player misses 10 shots.
 * @author Spencer Bryant
 */
public class Main {

    /**
     * Sets up the game frame and panel.  Executes the game looping skeleton
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create a JFrame
        JFrame gameFrame = new JFrame();
        //JButton musicButton = new JButton("Musix");
//****************************************************
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create a Jpanel
        //TO-DO: convert to a standard size that can be fullscreened and have
        //everything resize with proper ratios
        gameFrame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 + 100);
        gameFrame.setLocation(0,
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
        gameFrame.setResizable(false);
        gameFrame.setAlwaysOnTop(true);
        gameFrame.setTitle("Spencer's 2-D Shooter");
        Background background = new Background();
        gameFrame.add(background);
        gameFrame.addKeyListener(background.player);

        //*************************************************
        

        JOptionPane.showMessageDialog (null, "Use the arrow keys to move, " +
                "press the spacebar to fire");
        gameFrame.setVisible(true);
        background.initializeBackground();
        int count = 0;
        while (true){
            pause(40);
            count += 40;
            if (count % 2600 == 0){
                background.addEnemy();
            }
            background.repaint();
            background.move();
            background.scoreLabel.setText("Score: " + background.getScore());
            if (background.getScore() >= 100){
              background.resetScore();
                gameFrame.setAlwaysOnTop(false);
                background.clearEnemies();
                JOptionPane.showMessageDialog(null, "YOU WIN!!! YAY!!  RE-STARTING PLAY!");
            }
        }
    }//end method main

    /**
     * Pauses the game for a specific amount of time (in ms)
     * @param ms the number of milliseconds to pause the game
     */
    public static void pause (int ms) {
        try {
           Thread.sleep (ms);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    } // end method pause


    
}//end class Main

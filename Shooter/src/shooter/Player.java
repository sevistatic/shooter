package shooter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
//import java.io.File;
import javax.imageio.ImageIO;
import sun.audio.*;
import java.io.*;

/**
 *
 * @author Spencer Bryant
 */
public class Player extends JComponent implements KeyListener {

    private Image stillShip = new ImageIcon("ship3.png").getImage();
    private Image movingShip = new ImageIcon("ship4split.png").getImage();
    private Image shipdown1 = new ImageIcon("shiptop3quarter.png").getImage();
    private Image shipdown2 = new ImageIcon("shiptop.png").getImage();
    private int xPos = 100;
    private int yPos = 50;
    private Missile missile;
    private boolean missileExists = false;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean spacebarTyped = false;
    private int speed = 10;
    private int panelHeight;
    private int panelWidth;
    private int shipHeight = stillShip.getHeight(this);
    private int shipWidth = stillShip.getWidth(this);
    public int lives = 3;

    Player() {
        super();
        this.setSize(getShipWidth(), getShipHeight());
       this.setLocation(xPos, yPos);
    }

    public void setPanelHeight(int height){
        panelHeight = height;
    }

     public void setPanelWidth(int width){
        panelWidth = width;
    }


    public int getNumOfLives(){
      return lives;
    }
    public int getxPos() {
        return xPos;
    }

    public int getShipHeight() {
        return shipHeight;
    }

    public int getShipWidth() {
        return shipWidth;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getGunXPos(){
        int x = getxPos() + getWidth() - 20;
        return x;
    }
    
    public int getGunYPos(){
        int y = getyPos() + getHeight() / 2 - 3;
        return y;
    }
        
    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean missileExists(){
        return missileExists;
    }
    
    public void fireMissile() {
        if (!missileExists()) {
            missile = new Missile(getGunXPos(), getGunYPos());
            missileExists = true;
        }
        return;
    }

    public void destroyMissile() {
        missile = null;
        missileExists = false;
        spacebarTyped = false;
    }

    public Missile getMissile() {
        return missile;
    }

    public Image getShip(boolean isMoving) {
        if (!isMoving) {
          if (isMovingDown()){
            return shipdown1;
          }
            return stillShip;
        } else {
            return movingShip;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (e.getKeyCode() == e.VK_RIGHT) {
            right = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }

        if (e.getKeyCode() == e.VK_DOWN) {
            down = true;
        }

        if (e.getKeyCode() == e.VK_SPACE) {
            spacebarTyped = true;
        }
        if (e.getKeyCode() == e.VK_BACK_SPACE) {
            music();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_LEFT) {
            left = false;
        }

        if (e.getKeyCode() == e.VK_RIGHT) {
            right = false;
        }

        if (e.getKeyCode() == e.VK_UP) {
            up = false;
        }

        if (e.getKeyCode() == e.VK_DOWN) {
            down = false;
        }

    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            spacebarTyped = true;
        }
    }

    public boolean isMovingRight() {
        if (right){
            return true;
        } else return false;
    }

    public boolean isMovingDown(){
      if (down){
        return true;
      } else
      {
        return false;
      }
    }

    public static void music(){
      AudioPlayer AP = AudioPlayer.player;
      AudioStream BGM;
      AudioData AD;
      ContinuousAudioDataStream loop = null;

      try{
        
      BGM = new AudioStream(new FileInputStream("snare.wav"));
      AD = BGM.getData();
      loop = new ContinuousAudioDataStream(AD);
      
      }catch(IOException error){}

      AP.start(loop);
      //System.out.println("moop");
    }

    public void move() {
        if (up && getyPos() > 0) {
            setyPos(getyPos() - speed);
        }
        if (down){
            if (getyPos() + shipHeight < panelHeight)
        {
            setyPos(getyPos() + speed);
        }
        }
        if (left && getxPos() > 0) {
            setxPos(getxPos() - speed);
        }
        if (right) {
            if (getGunXPos() <= (panelWidth * 3 / 5) ){
                setxPos(getxPos() + speed);
            }
        }
        setLocation(getxPos(), getyPos());
        if (spacebarTyped) {
            if (!missileExists()) {
                fireMissile();
             } else {
                missile.move();                
            }
        }
    }
}

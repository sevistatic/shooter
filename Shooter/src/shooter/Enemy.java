package shooter;

import java.awt.*;
import javax.swing.*;

/**
 * An Enemy object is an object that can be attacked.  It comes in 2 sizes and
 * travels up and down repeatedly across the height of the screen.  Its starting
 * point is a random number from the last 1/3 of the game panel to 50 pixels
 * from the end of the panel
 * @author Spencer Bryant
 */
public class Enemy extends JComponent {

  private int y;
  private int x;
  private double rate = 10;
  private Image avatar = new ImageIcon("pod.png").getImage();
  public boolean isDead = false;
  public int severity = 0;

  /**
   * The Enemy Constructor.  Takes an X-value to start from and a size multiplier
   * @param xLoc the starting x-value of the enemy.  Does not change.
   * @param size a number to multiply the dimensions of the enemy by
   */
  Enemy(int xLoc, int size) {
    y = 20;
    x = xLoc;
    this.setSize(getImage().getWidth(this) * size, getImage().getHeight(this) * size);
    this.setLocation(x, y);
  }//end constructor Enemy

  /**
   * Gets the Image used to display the Enemy
   * @return a visual depiction of the Enemy as an Image
   */
  public Image getImage() {
      if (severity == 1){
        severity++;
        return new ImageIcon("poddissolve1.png").getImage();
      } else if(severity == 2){
        severity++;
        isDead = true;
        return new ImageIcon("poddissolve2.png").getImage();
    }
    return avatar;
  }//end method getImage

  /**
   * Changes the direction the Enemy is headed
   */
  public void changeDirection() {
    rate = -rate;
    y += rate * 2;
  }//end method changeDirection

  /**
   * Gets the center Y-value of the Enemy at a given time
   * @return the Y-value of the Enemy's center point
   */
  public int getCenterY() {
    int center = this.y + (getHeight() / 2);
    return center;
  }//end method getCenterY

  /**
   * Gets the center X-value of the Enemy at a given time
   * @return the X-value of the Enemy's center point
   */
  public int getCenterX() {
    int center = this.x + (getWidth() / 2);
    return center;
  }//end method getCenterX

  /**
   * moves the Enemy along a vertical path
   */
  public void move() {
    this.setLocation(x, y);
    y += rate;
  }//end method move

  public void die() {
    severity++;
  }

}//end class Enemy


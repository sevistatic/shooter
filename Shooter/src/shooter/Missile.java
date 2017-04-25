package shooter;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Spencer Bryant
 */
public class Missile extends JComponent{
private Image rocket;
private int xCoord;
private int yCoord;

 Missile(int xValue, int yValue){
     super();
     rocket = new ImageIcon("missile.png").getImage();
     setSize(rocket.getWidth(this), rocket.getHeight(this));
     setyCoord(yValue);
     setxCoord(xValue);
 }

 public boolean hasCollided(){
     return false;
 }

 public Image getImage(){

      return rocket;
 }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void move(){
        setxCoord(xCoord + 20);
        setLocation(xCoord, yCoord);
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
public int getTipX(){
    int tipX = getXCoord() + this.getWidth();
    return tipX;
}

public int getTipY(){
    int tipY = getYCoord() + (this.getHeight() / 2);
    return tipY;
}

 public int getXCoord(){
     return xCoord;
 }

 public int getYCoord(){
     return yCoord;
 }
}

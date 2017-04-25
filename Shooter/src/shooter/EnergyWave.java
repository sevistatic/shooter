package shooter;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Spencer Bryant
 */
public class EnergyWave extends JComponent{
Image wave;
Image waveStart;
 EnergyWave(){
     super();
waveStart = new ImageIcon("waveStart.png").getImage();
wave = new ImageIcon("wave.png").getImage();
     setSize(waveStart.getWidth(this) + wave.getWidth(this), wave.getHeight(this));

 }
 public Image startWave(){
      
      return this.waveStart;
 }

 public Image getImage(){

      return this.wave;
 }
}

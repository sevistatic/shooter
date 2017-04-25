package shooter;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * The background class is a JPanel with a scrolling background for use in a
 * 2-d side-scrolling shooter game.  The background speeds up as the player
 * moves right and has rules for the player, projectiles, and enemies to stay
 * on the screen.  There is a JLAbel that keeps track of the score.  The score
 * increments by 10 whenever an enemy is killed.  The game ends when a score of
 * 100 is reached
 * @author Spencer Bryant
 */
public class Background extends JPanel {
    int score;
JLabel scoreLabel;
JLabel livesLabel;
JLabel lifeIconLabel;
    Image image;
    ImageIcon lifeIcon;
    private int beginningPixel1 = 0;
    private int beginningPixel2 = 0;
    public Player player;
    private ArrayList<Enemy> eList = new ArrayList();
    int randomX;
    int randomSize;
    public Enemy deadEnemy;

    /**
     * The constructor for the Background object.  Contains a JLabel an image
     * to be scrolled along the background creates a player JComponent
     */
    public Background() {
        scoreLabel = new JLabel("Score: " + getScore());
        image = new ImageIcon("Supernova.jpg").getImage();
        lifeIcon = new ImageIcon("ship3.png");
        player = new Player();
        setLayout(null);
        addKeyListener(player);
        scoreLabel.setBounds(30, 10,65, 15);
        scoreLabel.setForeground(Color.red);

       // scoreLabel.setLocation(100, 200);
        add(scoreLabel);
        lifeIconLabel = new JLabel(lifeIcon);
        lifeIconLabel.setBounds(0, 400, lifeIcon.getIconWidth(), lifeIcon.getIconHeight());
        add(lifeIconLabel);
        livesLabel = new JLabel(" x " + player.getNumOfLives());        
        livesLabel.setBounds(player.getWidth() + 10, 420,25, 15);
        livesLabel.setForeground(Color.red);
        //livesLabel.setLocation(100, 200);
        add(livesLabel);
    } //end constructor

    /**
     * Makes a random X-value for any enemies created to begin on.
     * Uses the last 1/2 of the panel width.
     * @return a random X value between the last 1/3 of the screen and 50 pixels
     * from the end
     */
    public int getRandomX() {
        randomX = (int) (Math.random() * (getWidth() * 1 / 3) - 50);
        return randomX;
    }//end method getRandomX

    /**
     * Creates 2 numbers to be used for enemy size to be multiplied by.
     * @return a 1 or 2
     */
    public int getRandomSize() {
        randomSize = (int) (1 + Math.random() * 2);
        return randomSize;
    }//end method getRandomSize

    /**
     * Adds an Enemy with a random x-value and size to the ArrayList
     */
    public void addEnemy(){
        eList.add(new Enemy(900 + getRandomX(), getRandomSize()));
    }//end method addEnemy

    /**
     * returns the background to its initial state
     */
    public void initializeBackground() {
        beginningPixel1 = 0;
        beginningPixel2 = beginningPixel1 + image.getWidth(null);
    }//end method initializeBackground

    /**
     * Scrolls the background image, moves player, enemies, and projectiles.
     * Also contains enemy movement constraints and checks for collision between
     * Enemy and Missile Objects
     */
    public void move() {
//------------------------------------------------scrolls the background images
//------------------------------------------------changePixel(2);
        //if enemys hit walls, they change direction
        if (!eList.isEmpty()) {
                for (Enemy enemy : eList) {
                    if (enemy.getY() + enemy.getHeight() > this.getHeight() ||
                            enemy.getY() < 0)
                        enemy.changeDirection();
//------------------------------screen scrolls faster if player moves right
//-------------------------------------------if (player.isMovingRight())
//-------------------------------------------changePixel(4);
                    enemy.move();
                }
        }
        player.move();
        //checks for collision and processes all collisions
        boolean hasCollided = false;
        //triggers when a missile is fired and an enemy is on-screen
        if (player.missileExists() && !eList.isEmpty()) {
            for (int i = 0; i < eList.size(); i++) {
                hasCollided = detectCollision(player.getMissile(), eList.get(i));
                if (hasCollided) 
                    processCollision(player.getMissile(), eList.get(i));
            }
            hasCollided = false;
            //if the missile reaches the edge of the screen it is removed
            if (player.getMissile() != null) {
                if (player.getMissile().getXCoord() > this.getWidth())
                    player.destroyMissile();
            }//end missile null check
        }//end collision detection check
    }//end method move

    /**
     * Gets the score that appears on the JLable.  Score increments by 10 when
     * an enemy is killed.
     * @return
     */
    public int getScore(){
        return score;
    }//end method getScore

    /**
     * resets score to zero in event of a game win or reset
     */
    public void resetScore(){
      score = 0;
    }//end method resetScore

    /**
     * returns true if the bounded rectangle enclosing the missile
     * image intersects any point on the bounded rectangle enclosing an
     * enemy image
     * @param missile the fired Missile Object
     * @param enemy the enemy to be checked for collision
     * @return true if the enemy and missile share an X-value and y-proximity,
     * false  if the missile and enemy do not collide
     */
    public boolean detectCollision(Missile missile, Enemy enemy) {
            if (enemy != null && missile != null) {
                if (
                     missile.getBounds().intersects(enemy.getBounds())
                     )
                    return true;
            } return false;
    }//end method detectCollision

    /**
     * when the Missile collides with an Enemy, they are both nullified
     * @param missile the launched Missile
     * @param enemy the collided Enemy
     */
    public void processCollision(Missile missile, Enemy enemy) {
//--------------------------------------------------System.out.println("Pow!");
            player.destroyMissile();
            enemy.die();
    }//end method processCollision

/**
 * Increments score for each enemy killed
 * @param value
 */
    public void changeScore(int value){
      score += value;
    }//end method changeScore
/**
 * Controls how each object is painted on the background panel
 * @param g
 */
    @Override
    public void paintComponent(Graphics g) {
        //adds JLabel to top of JPanel
//-------------------------------------------scoreLabel.setOpaque(true);
//-------------------------------------------livesLabel.setOpaque(true);
        //gives the player the panel height and width so movement can be
        //restricted within the panel
        player.setPanelHeight(getHeight());
        player.setPanelWidth(getWidth());
        //draw background
        g.drawImage(image, beginningPixel1, 0, getWidth(), getHeight(), null);
 //-------g.drawImage(image, beginningPixel2, 0, getWidth(), getHeight(), null);
        //draw ship

       //--------------------------------------------------------------------
            g.drawImage(player.getShip(false), player.getX(), player.getY(), player.getWidth(),
                    player.getHeight(), null);
       //----------------------------------------------------------------
        //draw missile
        
        if (player.missileExists())
            g.drawImage(player.getMissile().getImage(), player.getMissile().getXCoord(),
                    player.getMissile().getYCoord(), null);
        //draw enemies
            for (Enemy e : eList) {
                if (eList != null)
                g.drawImage(e.getImage(), e.getX(), e.getY(), e.getWidth(), e.getHeight(), null);
                if (e.isDead){
                  deadEnemy = e;
                  changeScore(10);
                }
        }//end draw enemies loop
            eList.remove(deadEnemy);
    } // end method paintComponent

    /**
     * deletes all enemies from the list so that the screen is cleared between game runs
     */
    public void clearEnemies(){

      eList.clear();
    }
}//end class Background

    


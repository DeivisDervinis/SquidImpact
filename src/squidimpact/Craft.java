/* Deivis Dervinis
 * Craft.java
 * DATE
 */

package squidimpact;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {
  
  private int dx;
  private int dy;
  private final int pwidth = 400;
  private final int pheight = 300;
  
  private ArrayList<Missile> missiles;
  
  public Craft(int x, int y) {
    super(x, y);
    
    initCraft();
  }
  
  public int getX(){
   return x; 
  }
  
  public int getY(){
   return y; 
  }
  
  private void initCraft() {
    
    missiles = new ArrayList();
    loadImage("images/ship.png");
    getImageDimensions();
  }
  
  public void move() {
    
    x += dx;
    y += dy;
    
    if (x < 1) {
      x = 1;
    }
    
    if (y < 1) {
      y = 1;
    }
  }
  
  // A method that checks if the ship hits the wall
  public void checkWall()
  {
    if(y >= 270)
    {
      y = 270;
    }
    
    if(x >= 360)
    {
      x = 360;
    }
  }
  
  public ArrayList getMissiles() {
    return missiles;
  }
  
  // Keypressed event
  public void keyPressed(KeyEvent e) {
    
    
    
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      fire();
    }
    
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      dx = -10;
    }
    
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      dx = 10;
    }
    
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      dy = -10;
    }
    
    if (e.getKeyCode()== KeyEvent.VK_DOWN) {
      dy = 10;
    }
  }
  
  public void fire() {
    missiles.add(new Missile(x + width, y + height / 2));
  }
  
  public void keyReleased(KeyEvent e) {
    
    int key = e.getKeyCode();
    
    if (key == KeyEvent.VK_LEFT) {
      dx = 0;
    }
    
    if (key == KeyEvent.VK_RIGHT) {
      dx = 0;
    }
    
    if (key == KeyEvent.VK_UP) {
      dy = 0;
    }
    
    if (key == KeyEvent.VK_DOWN) {
      dy = 0;
    }
  }
}
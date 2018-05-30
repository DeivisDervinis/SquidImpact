/* Deivis Dervinis
 * Boss.java
 * DATE
 */

package squidimpact;

import java.awt.Rectangle;

public class Boss extends Sprite
{
  
  private int x = 2000;
  private int y = 100;
  private int health = 10;
  
  private int yvel =  2;
  private int xvel = 2;
  
  public Boss(int x, int y,int health) { // Constructor
   super(x, y);
   this.health = health;
    initBoss();
  }
  
  ////// Accessors ////////////////////////////////////
  public int getX(){
   return x; 
  }
  
  public int getY(){
   return y; 
  }
  
  public int getHealth(){
   return health; 
  }
  
  //////// Mutator///////////////////////////
  public void setX(int x){
   this.x = x;
  }
  
  
  private void initBoss() { // Loads in the image gets dimension
    
    loadImage("images/boss.png");
    getImageDimensions();
  }
  
  public void move() { // A method that allows boss to move
    y += yvel;
    x += xvel;
    
    
    if (y <= 0) {
      yvel = 2;
    }
      if(y >= 520){
        yvel = -2;
        
    }
      if(x < 0){
       xvel = 2 ;
      }
      
      if(x > 710 ){
       xvel = -2; 
      }
    
  }
  
      public Rectangle getBounds() { // Creates a rectangle around the boss
        return new Rectangle(x, y, 125, 85);
    }
      

  
}

/* Deivis Dervinis
 * Alien.java
 * DATE
 */

package squidimpact;

public class Alien extends Sprite {

    private final int INITIAL_X = 800; // Initial X that allows aliens to be returned when they leave the screen
    private int alienSpeed = 2; // Speed at which aliens are travelling

    public Alien(int x, int y, int as) { // Constructor
        super(x, y);
        alienSpeed = as;

        initAlien();
    }

    private void initAlien() { // A method that loads in the image and getsDimensions of the image

        loadImage("images/mon.png"); // Loads in the image
        getImageDimensions();
    }
    
    ////// Accessor ////////////////////////////////////
    public int getAlienSpeed(){
     return alienSpeed; 
    }
    
    ////// Mutator/////////////////////////////
    public void setAlienSpeed(int as){
     this.alienSpeed = as; 
    }
    
    
    
    public void move() { // A method that makes the alien move

        if (x < 0) { // If it passes the panel bounds it gets returned back to starting position
            x = INITIAL_X;
            alienSpeed += 1; // increases alien's speed when it passes the panel up to 3 times
        }
        if(alienSpeed >= 10){ // If the speed is faster than 3
         alienSpeed -= 1; // Reduces it by 1;
        }

        x -= alienSpeed;
    }
}
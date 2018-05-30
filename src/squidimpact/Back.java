/* Deivis Dervinis
 * Back.java
 * DATE
 */

package squidimpact;

public class Back extends Sprite {
  
  private int x = 0; // Initial X
  private int y = 0; // Initial Y
  
    public Back(int x, int y) { // Constructor
        super(x, y);

        initBack();
    }

    private void initBack() {

        loadImage("images/space.png"); // Loads the image
        getImageDimensions();
    }
    
    ////// Accessor ////////////////////////////////////
    public int getX(){
     return x; 
    }
    
    public int getY(){
     return y; 
    }

}
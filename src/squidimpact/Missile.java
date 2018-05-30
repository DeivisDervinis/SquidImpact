/* Deivis Dervinis, Lee Benzacar
 * Missile.java
 * DATE
 */

package squidimpact;

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 10;

    public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("images/pro.png");
        getImageDimensions();        
    }
    
    public int getX(){
     return x; 
    }
    
    public int getY(){
     return y; 
    }

    public void move() {
        
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH)
            vis = false;
    }
}
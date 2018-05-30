/*
-------------------------------------------
Student: Deivis Dervinis dervinis@sheridan.desire2learn.com
StudentID: 991436536
-------------------------------------------
 */
package squidimpact;

/**
 *
 * @author Deivis Dervinis dervinis@sheridan.desire2learn.com
 */
import java.awt.EventQueue;
import java.net.URL;
import javax.swing.JFrame;

public class SquidImpact extends JFrame {

public SquidImpact() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Squid Impact");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
          
            public void run() {
                SquidImpact sq = new SquidImpact();
                sq.setVisible(true);
            }
        });
    }
}

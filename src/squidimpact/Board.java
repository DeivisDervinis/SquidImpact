/* Deivis Dervinis
 * Board.java
 * DATE
 */

package squidimpact;

import java.awt.Color; // Imports
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;


public class Board extends JPanel implements ActionListener {
  
  /*Notes
   Check Mult
   Adjust speed of monsters every level
   */
  
  // Declaring variables
  private Timer timer;
  private Craft craft;
  private Boss boss;
  private Back back;
  
  private ArrayList<Alien> aliens;
  private boolean ingame;
  private boolean bossLet = false;
  private boolean lvl = true;
  private boolean lvl2 = false;
  private boolean lvl3 = false;
  private boolean lvl4 = false;
  private boolean lvl5 = false;
  
  private final int ICRAFT_X = 40;
  private final int ICRAFT_Y = 60;
  
  private final int BOSSX = 300;
  private final int BOSSY = 50;
  
  private final int PanelWidth = 800;
  private final int PanelHeight = 600;
  private final int DELAY = 15;
  
  private final int canx = 0;
  private final int cany = 0;
  
  
  private int health = 25;
  private int shipHealth = 10;
  private int score = 0;
  private int mult = 1;
  private int level = 1;
  private int alSpeed = 1;
  
  
  //Position for the aliens
  private final int[][] pos = {
    {1200, 120}, {1350, 310}, {1000, 90},
    {1400, 460}, {1100, 245}, {740, 280},
    {1050, 340}, {900, 170}, {700, 530}
  };
  
  public Board() {
    
    initBoard();
  }
  
  private void initBoard() { // A method that starts the game
    ImageIcon icon = new ImageIcon(this.getClass().getResource("images/icon.png"));
    int s = JOptionPane.showConfirmDialog(null, "Welcome to Squid Impact! Click YES to play, NO to close the program\nArrows keys to move, SPACE to shoot","Squid Impact",JOptionPane.YES_OPTION,JOptionPane.PLAIN_MESSAGE,icon);
    if(s == 1){ // If user choses NO then program exits
      System.exit(0); 
    }
    
    String[] buttons = { "Easy", "Medium", "Hard"};
    int ch = JOptionPane.showOptionDialog(null, "Please choose your difficulty: ", "Squid Impact",
        JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[2]);
    if(ch == 0){
      shipHealth = 20;
    }
    else
      if(ch == 1){
     shipHealth = 15;
     alSpeed = 10;
     health = 50;
    }
    else
      if(ch == 2){
     shipHealth = 10;
     alSpeed = 20;
     health = 100;
    }
    
    addKeyListener(new TAdapter());
    setFocusable(true);
    ingame = true;
    
    setPreferredSize(new Dimension(PanelWidth, PanelHeight)); // Sets the size of the panel
    
    craft = new Craft(ICRAFT_X, ICRAFT_Y); // Creates objects
    boss = new Boss(BOSSX,BOSSY, health);
    back = new Back(canx,cany);
    
    initAliens();
    
    timer = new Timer(DELAY, this); // Starts the timer
    timer.start();
  }
  
  public void initAliens() { // A method to initialize the aliens
    aliens = new ArrayList();
    
    if(lvl == true){
      for (int[] p : pos) { // For each position creates an alien
        aliens.add(new Alien(p[0], p[1],alSpeed));
      }
    }
    
  }
  
  public void checkLevel(){ // Checks the current level
    if(lvl == true){
      if(aliens.size() <= 1){
        lvl2 = true;
        lvl = false;
        level = 2;
        
        if(lvl2 == true){ // Level 2
          for (int[] p : pos) { // For each position creates an alien
            aliens.add(new Alien(p[0], p[1],alSpeed));
          }
          for (int i = 0; i < aliens.size(); i++) {
            
            Alien a = aliens.get(i);
            if (a.isVisible()) {
              a.move();
            }
          }
        }
      }
    }
    
    if(lvl2 == true){
      if(aliens.size() <= 1){
        lvl3 = true;
        lvl2 = false;
        level = 3;
        
        if(lvl3 == true){ // Level 3
          for (int[] p : pos) { // For each position creates an alien
            aliens.add(new Alien(p[0], p[1],alSpeed));
          }
          for (int i = 0; i < aliens.size(); i++) {
            
            Alien a = aliens.get(i);
            if (a.isVisible()) {
              a.move();
            }
          }
        }
      }
    }
    
    if(lvl3 == true){
      if(aliens.size() <= 1){
        lvl4 = true;
        lvl3 = false;
        level = 4;
        
        if(lvl4 == true){ // Level 3
          for (int[] p : pos) { // For each position creates an alien
            aliens.add(new Alien(p[0], p[1],alSpeed));
          }
          for (int i = 0; i < aliens.size(); i++) {
            
            Alien a = aliens.get(i);
            if (a.isVisible()) {
              a.move();
            }
          }
        }
      }
    }
    
    if(lvl4 == true){ // Level 5
      if(aliens.size() <= 1){
        lvl5 = true;
        lvl4 = false;
        level = 5;
        
        if(lvl5 == true){ // Level 3
          for (int[] p : pos) { // For each position creates an alien
            aliens.add(new Alien(p[0], p[1],alSpeed));
          }
          for (int i = 0; i < aliens.size(); i++) {
            
            Alien a = aliens.get(i);
            if (a.isVisible()) {
              a.move();
            }
            lvl5 = false;
          }
        }
      }
    }
  }

  
  
  public void paintComponent(Graphics g) { // A method that draws either gameObjects or gameOverScreen
    super.paintComponent(g);
    
    if (ingame) {
      
      drawObjects(g);
      
    } else {
      
      drawGameOver(g);
    }
    
    Toolkit.getDefaultToolkit().sync();
  }
  
  private void drawObjects(Graphics g) { // A method that paints all the game entities
    
    if(back.isVisible()){ // Checks if background is visible, paints the background
      g.drawImage(back.getImage(),back.getX(),back.getY(),this);  
    }
    
    if (craft.isVisible()) { // Checks if craft is visible, draws the craft
      g.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                  this);
    }
    
    if(bossLet == true) // Makes sure bossLet is true
    {
      if(boss.isVisible()){ // Draws the boss
        g.drawImage(boss.getImage(),boss.getX(),boss.getY(),this); 
      }
    }
    
    
    ArrayList<Missile> ms = craft.getMissiles();
    
    for (Missile m : ms) { // For each missle draws a missle
      if (m.isVisible()) {
        g.drawImage(m.getImage(), m.getX(), m.getY(), this);
      }
    }
    
    for (Alien a : aliens) {
      if (a.isVisible()) {
        g.drawImage(a.getImage(), a.getX(), a.getY(), this);
      }
    }
    
    // Text for the game
    g.setColor(Color.YELLOW); // Aliens Text
    g.drawString("Aliens left: " + aliens.size(), 5, 15);
    
    g.setColor(Color.YELLOW); // Score Text
    g.drawString("Score: " + score, 300, 15);
    
    g.setColor(Color.YELLOW);
    g.drawString("Level: " +level, 5, 45);
    
    g.setColor(Color.YELLOW);
    g.drawString("Mult: x" +mult,300,30);
    
    g.setColor(Color.GREEN); // Shio health Text
    g.drawString("Ship health: " + shipHealth, 5, 30);
    
    if(bossLet == true){
      g.setColor(Color.RED); // Boss text
      g.drawString("Boss health: " +health, 170,15);
    }
    
    
  }
  
  private void drawGameOver(Graphics g) { // Method that draws game over
    
    if(aliens.size() <= 0 && bossLet == true) // Checks if there are no aliens, or boss left
    {
      String ms = "You won!";
      Font small = new Font("Helvetica", Font.BOLD, 14); // Sets the font
      FontMetrics fm = getFontMetrics(small);
      
      g.setColor(Color.white);
      g.setFont(small);
      g.drawString(ms, (PanelWidth - fm.stringWidth(ms)) / 2, // Draws it in the middle of the screen
                   PanelHeight / 2);
      
    }
    else
      if(shipHealth <= 1){
      
      String msg = "Game Over";
      Font small = new Font("Helvetica", Font.BOLD, 14); // Sets the font
      FontMetrics fm = getFontMetrics(small);
      
      g.setColor(Color.white);
      g.setFont(small);
      g.drawString(msg, (PanelWidth - fm.stringWidth(msg)) / 2, // Draws it in the middle of the screen
                   PanelHeight / 2);
    }
    
    String scr = "Score: ";
    Font small = new Font("Helvetica", Font.BOLD, 14); // Sets the font
    FontMetrics fm = getFontMetrics(small);
    
    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(scr + score, (PanelWidth - fm.stringWidth(scr)) / 2, // Draws it in the middle of the screen
                 PanelHeight / 2 +30);
    
  }
  
  
  
  
  public void actionPerformed(ActionEvent e) { // Method that checks if action has been performed, and updates all the updatable methods
    
    inGame();
    
    updateCraft();
    updateMissiles();
    updateAliens();
    updateBoss();
    checkLevel();
    checkCollisions();
    
    repaint();
  }
  
  private void inGame() { // Checks if the game is still running
    
    if (!ingame) {
      JOptionPane.showMessageDialog(null, "Thanks for playing!","Space Impact",JOptionPane.PLAIN_MESSAGE);
      timer.stop();
    }
  }
  
  private void updateCraft() { // Method that updates the craft
    
    if (craft.isVisible()) {
      craft.move();
      craft.checkWall();
    }
  }
  
  private void updateBoss(){ // Method that updates the boss
    if(boss.isVisible()){
      boss.move(); 
    }
  }
  
  
  private void updateMissiles() { // Method that updates the missles
    
    ArrayList<Missile> ms = craft.getMissiles();
    
    for (int i = 0; i < ms.size(); i++) {
      
      Missile m = ms.get(i);
      
      if (m.isVisible()) {
        m.move();
      } else {
        ms.remove(i);
      }
    }
  }
  
  private void updateAliens() { // Method that updates the aliens
    
    if (aliens.isEmpty()) { // If array of aliens is empty, draws the boss
      
      bossLet = true;
      return;
    }
    
    for (int i = 0; i < aliens.size(); i++) {
      
      Alien a = aliens.get(i);
      if (a.isVisible()) {
        a.move();
      } else {
        aliens.remove(i);
      }
    }
  }
  
  public void checkAliens(){ // Checks if aliens are destroyed if true then creates a boss
    if(aliens.isEmpty()){
      bossLet = true; 
    }
    if(bossLet == true){
      
    }
  }
  
  public void checkCollisions() { // Checks colission between objects
    
    Rectangle r4 = boss.getBounds(); // Creates a rectangle around the boss image
    Rectangle r3 = craft.getBounds(); // Creates a rectangle around the craft image
    
    if(bossLet == true){
      if(r3.intersects(r4)){ // Colission between craft and boss
        boss.setX(400);
        if(shipHealth <= 1){
          craft.setVisible(false);
          boss.setVisible(false);
          ingame = false;
        }
        else
        {
          shipHealth -= 5; 
        }
      }
    }
    
    
    
    for (Alien alien : aliens) {
      Rectangle r2 = alien.getBounds();
      
      if (r3.intersects(r2)) { // Collision between craft and aliens
        if(shipHealth <= 1){
          craft.setVisible(false);
          ingame = false;
        }
        else{
          alien.setVisible(false);
          shipHealth -= 1;
        }
      }
    }
    
    ArrayList<Missile> ms = craft.getMissiles();
    
    for (Missile m : ms) {
      
      Rectangle r1 = m.getBounds();
      
      if(bossLet == true){
        if(r1.intersects(r4)){ // Colission between boss and missle
          m.setVisible(false);
          if(health <= 0){
            boss.setVisible(false);
            score += 100;
            ingame = false;
            return;
          }
          else
          {
            score += 10;
            health--; 
          }
        }
      }
      
      for (Alien alien : aliens) { // Colission between  alien and missle
        
        Rectangle r2 = alien.getBounds();
        if (r1.intersects(r2)) {
          m.setVisible(false);
          alien.setVisible(false);
          score += 10 * mult;
          mult++;
        }
      }
    }
  }
  
  private class TAdapter extends KeyAdapter { // Allows keyEvent to be working
    
    
    public void keyReleased(KeyEvent e) {
      craft.keyReleased(e);
    }
    
    public void keyPressed(KeyEvent e) {
      craft.keyPressed(e);
    }
  }
}
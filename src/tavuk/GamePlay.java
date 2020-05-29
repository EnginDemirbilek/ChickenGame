package tavuk;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class GamePlay
  extends JPanel
  implements KeyListener, ActionListener
{
  private static final Graphics Graphics = null;
  private boolean play = false;
  private int score = 0;
  private String score_str = String.valueOf(score);
  private int totalBricks = 21;
  private Timer time;
  private int delay = 4;
  private int playerX = 10;
  private int playerY = 50;
  private int backgroundY = 0;
  private int wheatposX = (int)(Math.random() * 181.0D) + 10;
  private int wheatposY = 600;
  private int wheat_score = 10;
  private int eggX = 600;
  private int eggY = playerY;
  private int basketX = 155;
  private int basketY = -60;
  private int catX = 155;
  private int catY = -60;
  
  private int egg_c = 0;
  private String egg_count = String.valueOf(egg_c);
  private int ballYdir = -1;
  private boolean is_egg_in_bottom = false;
  private boolean is_in_top = false;
  private boolean is_cat_in_top = false;
  private boolean is_basket_in_top = false;
  private boolean is_intersect = false;
  private boolean is_dead = false;
  private int level_num = 0;
  private String level = String.valueOf(level_num);
  private boolean is_egg_fired = false;
  private boolean is_fire_button_pushed = false;
  private boolean is_catched = false;
  private boolean is_jump_pressed = false;
  private boolean is_started = false;
  
  private BufferedImage wheat;
  private BufferedImage chicken;
  private BufferedImage cat;
  private BufferedImage background;
  private BufferedImage egg;
  private BufferedImage basket_icon;
  private BufferedImage basket;
  JLabel label = new JLabel("My label");
  Timer timer;
  
  public GamePlay()
  {
    try {
      background = ImageIO.read(new File("pics/90378.jpg"));
      chicken = ImageIO.read(new File("pics/chicken-icon.png"));
      wheat = ImageIO.read(new File("pics/bugday.png"));
      egg = ImageIO.read(new File("pics/egg.jpg"));
      cat = ImageIO.read(new File("pics/caticon.png"));
      basket_icon = ImageIO.read(new File("pics/basket.png"));
      basket = ImageIO.read(new File("pics/basket_icon.png"));
    }
    catch (IOException localIOException) {}
    
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay, this);
    timer.start();
  }
  

  public void paint(Graphics g)
  {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, 1024, 1024);
    g.drawImage(background, 0, backgroundY, 1600, 1280, null);
    if(is_started == false) {
        g.setColor(Color.WHITE);
    	   g.setFont(new Font("default", 1, 25));
    	      g.drawString("Press Enter To Start", 25, 275);
    }
    
    else if(is_started == true) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("default", 1, 14));
    g.drawString("Level: ", 10, 30);
    g.drawString(level, 60, 30);
    
    g.drawImage(chicken, playerX, playerY, 40, 49, null);
    
    g.drawImage(egg, eggX, eggY, 25, 34, null);
    

    g.drawImage(wheat, wheatposX, wheatposY, 45, 45, null);
    

    g.drawImage(cat, catX, catY, 60, 60, null);
    

    g.drawImage(basket_icon, 190, 10, 25, 25, null);
    g.drawString("Eggs: ", 220, 30);
    g.drawString(egg_count, 267, 30);
    

    g.drawString("Score: ", 100, 30);
    g.drawString(score_str, 155, 30);
    

    g.drawImage(basket_icon, basketX, basketY, 35, 35, null);
    
    if (is_dead)
    {
      g.setFont(new Font("default", 1, 25));
      g.drawString("YOU ARE DEAD", 50, 275);
      g.setFont(new Font("default", 1, 15));
      g.drawString("Score:", 110, 300);
      g.drawString(score_str, 170, 300);
      g.drawString("Press Enter to Play Again.", 57, 325);
    }
    }



    g.dispose();
  }
  


  public void actionPerformed(ActionEvent arg0)
  {
    timer.start();
    if (play)
    {


      if ((new Rectangle(wheatposX, wheatposY, 45, 45).intersects(new Rectangle(playerX, playerY, 40, 49))) && (!is_intersect) && (!is_dead))
      {
        wheatposX = 600;
        is_intersect = true;
        score += wheat_score;
        score_str = String.valueOf(score);
        if ((score >= 50) && (score < 100))
        {
          if (score == 30)
          {
            level_num += 1;
            level = String.valueOf(level_num);
          }
          if ((score >= 30) && (score <= 50)) {
            ballYdir += -1;
          }
        } else if ((score > 100) && (score < 200)) {
          if ((score >= 100) && (score < 110))
            ballYdir += -1;
          wheat_score += 4;
          if (score > 180)
          {
            level_num = 2;
            level = String.valueOf(level_num);
          }
          

        }
        else if (score > 200)
        {
          if (score > 300)
            level_num = 3;
          wheat_score += 5;
          if ((score >= 200) && (score < 230)) {
            ballYdir += -1;
          }
        }
      }
      if (((new Rectangle(catX, catY, 60, 60).intersects(new Rectangle(playerX, playerY, 40, 49))) && (!is_catched)) || (is_dead))
      {
        is_dead = true;
        is_catched = true;
        eggX = 1000;
        playerY += 5;
      }
      



      if (!is_dead) {
        if (new Rectangle(catX, catY, 60, 60).intersects(new Rectangle(eggX, eggY, 25, 34)))
        {
          catX = 1000;
          eggX = 1000;
          is_egg_fired = false;
          is_fire_button_pushed = false;
          is_egg_in_bottom = false;
        }
        

        if (new Rectangle(basketX, basketY, 35, 35).intersects(new Rectangle(playerX, playerY, 25, 34)))
        {
          basketX = 1000;
          if (egg_c < 10) {
            egg_c += 3;
            basketX = 1000;
            if (egg_c > 10)
              egg_c = 10;
            egg_count = String.valueOf(egg_c);
          }
        }
        if (backgroundY > -600) {
          backgroundY += -1;
        } else {
          System.out.println(backgroundY);
          backgroundY = 0;
        }
        if (is_egg_fired)
        {
          eggY += 4;
          if (eggY > 600) {
            is_egg_in_bottom = true;
          }
          if (is_egg_in_bottom)
          {
            eggY = 999;
            is_egg_fired = false;
            is_fire_button_pushed = false;
            is_egg_in_bottom = false;
          }
        }
        
        if (!is_basket_in_top)
        {
          basketY += ballYdir;
          if (basketY < 3)
            is_basket_in_top = true;
          if (is_basket_in_top)
          {
            is_basket_in_top = false;
            basketX = ((int)(Math.random() * 181.0D) + 10);
            basketY = 600;
          }
        }
        


        if (!is_cat_in_top)
        {
          catY += ballYdir;
          if (catY < -30)
            is_cat_in_top = true;
          if (is_cat_in_top)
          {
            is_catched = false;
            is_cat_in_top = false;
            catX = ((int)(Math.random() * 186.0D) + 5);
            catY = 600;
          }
        }
        


        if (!is_in_top) {
          wheatposY += ballYdir;
          if (wheatposY < 0) {
            is_in_top = true;
          }
          

          if (is_in_top)
          {
            is_intersect = false;
            is_in_top = false;
            wheatposX = ((int)(Math.random() * 181.0D) + 10);
            wheatposY = 600;
          }
        }
      }
    }
    
    repaint();
  }
  


  public void keyPressed(KeyEvent arg0)
  {
    if ((arg0.getKeyCode() == 39) && (!is_dead) && is_started)
    {
      if (playerX >= 240) {
        playerX = 240;
      } else {
        moveRight();
      }
    }
    if ((arg0.getKeyCode() == 37) && (!is_dead) && is_started)
    {
      if (playerX <= 10) {
        playerX = 10;
      } else {
        moveLeft();
      }
    }
    

    if ((arg0.getKeyCode() == 32) && (!is_dead) && is_started)
    {

      if ((!is_fire_button_pushed) && (egg_c > 0)) {
        eggX = playerX;
        eggY = playerY;
        egg_c -= 1;
        egg_count = String.valueOf(egg_c);
        is_egg_fired = true;
        is_fire_button_pushed = true;
      }
      
    }
    else if ((arg0.getKeyCode() == 10) && (is_dead))
    {

      is_dead = false;
      score = 0;
      egg_c = 0;
      level_num = 0;
      playerX = 10;
      playerY = 50;
      backgroundY = 0;
      wheatposX = ((int)(Math.random() * 181.0D) + 10);
      wheatposY = 600;
      wheat_score = 10;
      eggX = 600;
      eggY = playerY;
      basketX = 155;
      basketY = -60;
      catX = 155;
      catY = -60;
      ballYdir = -2;
      score_str = String.valueOf(score);
      level = String.valueOf(level_num);
      egg_count = String.valueOf(egg_c);
    }
    else if ((arg0.getKeyCode() == 10) && (!is_dead) && !is_started)
    {

      is_started = true;    }
    
  }
  
  






  public void moveRight()
  {
    play = true;
    playerX += 20;
  }
  
  public void moveLeft()
  {
    play = true;
    playerX -= 20;
  }
  
  public void keyReleased(KeyEvent arg0) {}
  
  public void keyTyped(KeyEvent arg0) {}
}

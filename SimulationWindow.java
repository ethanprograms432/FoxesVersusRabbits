import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.io.File;

public class SimulationWindow extends JPanel implements Runnable {

   private int numFoxes;
   private int numRabbits;
   Thread gameThread;
   EntityManager eM;
   BufferedImage bckgd;
   Clip clip;
   
   public SimulationWindow(int numRabbits,int numFoxes) {
   
      this.numFoxes = numFoxes;
      this.numRabbits = numRabbits;
      this.setPreferredSize(new Dimension(1500,1000));
      eM = new EntityManager(this,this.numRabbits,this.numFoxes);
      
      try {
      
         bckgd = ImageIO.read(getClass().getResourceAsStream("background.jpg"));
         
      } catch(IOException e) {}
      
      try {
      
         File file = new File("Alpha Mission - Jimena Contreras.wav");
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
         clip = AudioSystem.getClip();
         clip.open(audioStream);
         clip.setMicrosecondPosition(60000000);
         clip.start();
         
      } catch(IOException e) {
      
      } catch(UnsupportedAudioFileException u) {
      
      } catch(LineUnavailableException l) {}
   }
   
   public void startGameThread() {
   
      gameThread = new Thread(this);
      gameThread.start();
   
   }
   
   @Override
   public void run() {
   
      while(gameThread != null) {
      
         try {
         
            TimeUnit.MILLISECONDS.sleep(16);
            repaint();
         
         } catch(InterruptedException e) {}
      
      }
   
   }
   
   public void paintComponent(Graphics g) {
      
      Graphics2D g2 = (Graphics2D)g;
      drawBackground(g2);
      eM.drawEntities(g2);
      drawCounter(g);
   
   }
   
   public void drawCounter(Graphics g) {
   
      g.setColor(Color.yellow);
      g.fillRect(1300,0,200,200);
      
      g.setFont(new Font("TimesRoman",Font.BOLD,20));
      g.setColor(Color.black);
      g.drawString("Rabbits: " + EntityManager.numRabbits,1320,20);
      g.drawString("Foxes: " + EntityManager.numFoxes,1320,75);
      g.drawString("Grass: " + EntityManager.numGrass,1320,130);
      g.drawString("Map Size: 1 H",1320,185);
      
      if(EntityManager.numRabbits == 0 || EntityManager.numFoxes == 0) {
      
         clip.stop();
      
      }
      
   
   }
   
   public void drawBackground(Graphics g2) {
   
      g2.drawImage(bckgd,0,0,1500,1000,null);
      
   }
   
   

}
import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Rabbit {

   private int x;
   private int y;
   private int borderX;
   private int borderY;
   public int age;
   public int health;
   public int gender;
   private BufferedImage image;
   private Grass target;
   private Fox threat;
   private Rabbit mate;
   public int children = 0;
   
   public Rabbit(int x,int y,int age,int gender,int health) {
   
      this.x = x;
      this.y = y;
      this.borderX = x + 40;
      this.borderY = y + 40;
      this.age = age;
      this.health = health;
      this.gender = gender;
      this.getTargetAndThreat();
      
      try {
      
         if(this.gender == 0) {
         
            this.image = ImageIO.read(getClass().getResourceAsStream("rabbit.png"));
       
         } else {
         
            this.image = ImageIO.read(getClass().getResourceAsStream("rabbit copy.png"));
         }
      } catch(IOException e) {}
   
   }
   
   public BufferedImage getImage() {
   
      return this.image;
   
   }
   
   public int getX() {
   
      return this.x;
   }  
   
   public int getY() {
   
      return this.y;
   }
   
   public void getTargetAndThreat() {
   
      int currLargestDistance = 100000;
      
      for (int i = 0; i < EntityManager.grass.size(); i++) {
      
         int currX = EntityManager.grass.get(i).getX();
         int currY = EntityManager.grass.get(i).getY();
         
         int distance = (int)(Math.sqrt(Math.pow((currX - this.x),2) + Math.pow((currY - this.y),2)));
         
         if(distance < currLargestDistance) {
         
            this.target = EntityManager.grass.get(i);
         
         }
         
      
      }
      
      currLargestDistance = 100000;
      
      for (int j = 0; j < EntityManager.foxes.size(); j++) {
      
         int currX = EntityManager.foxes.get(j).getX();
         int currY = EntityManager.foxes.get(j).getY();
         
         int distance = (int)(Math.sqrt(Math.pow((currX - this.x),2) + Math.pow((currY - this.y),2)));
         
         if(distance < currLargestDistance) {
         
            this.threat = EntityManager.foxes.get(j);
         
         }
         
      
      }
      
      currLargestDistance = 10000;
      
      for (int k = 0; k < EntityManager.rabbits.size(); k++) {
      
         if(!EntityManager.rabbits.get(k).equals(this)) {
         
            int currX = EntityManager.rabbits.get(k).getX();
            int currY = EntityManager.rabbits.get(k).getY();
            int currGender = EntityManager.rabbits.get(k).gender;
            
            int distance = (int)(Math.sqrt(Math.pow((currX - this.x),2) + Math.pow((currY - this.y),2)));
            
            if(distance < currLargestDistance && (this.gender != currGender)) {
            
               this.mate = EntityManager.rabbits.get(k);
            
            }
            
         }
         
      
      }
   
   }
   
   public void move() {
   
      int xMovement = 0;
      int yMovement = 0;
      
      int distanceFromThreat = 10000;
      
      if(this.threat != null) {
      
         distanceFromThreat = (int)(Math.sqrt(Math.pow((this.threat.getX() - this.x),2) + Math.pow((this.threat.getY() - this.y),2)));
         
      }
      
      int distanceFromTarget = (int)(Math.sqrt(Math.pow((this.target.getX() - this.x),2) + Math.pow((this.target.getY() - this.y),2)));
      
      if(distanceFromThreat < distanceFromTarget) {
      
         if(this.x < this.threat.getX()) {
      
            xMovement = EntityManager.rand.nextInt(-2,0);
      
         } else {
         
            xMovement = EntityManager.rand.nextInt(1,3);
         
         }
         
         if(this.y < this.threat.getY()) {
         
            yMovement = EntityManager.rand.nextInt(-2,0);
         
         } else {
         
            yMovement = EntityManager.rand.nextInt(1,3);
         
         }
      
      } else if(this.health < 100 || EntityManager.numRabbits > 1000) {
      
         if(this.x < this.target.getX() + 20) {
      
            xMovement = EntityManager.rand.nextInt(1,3);
      
         } else {
         
            xMovement = EntityManager.rand.nextInt(-2,0);
         
         }
         
         if(this.y < this.target.getY() + 20) {
         
            yMovement = EntityManager.rand.nextInt(1,3);
         
         } else {
         
            yMovement = EntityManager.rand.nextInt(-2,0);
         
         }
      
      } else if(this.threat != null) {
      
         if(this.x < this.threat.getX()) {
      
            xMovement = EntityManager.rand.nextInt(-2,0);
      
         } else {
         
            xMovement = EntityManager.rand.nextInt(1,3);
         
         }
         
         if(this.y < this.threat.getY()) {
         
            yMovement = EntityManager.rand.nextInt(-2,0);
         
         } else {
         
            yMovement = EntityManager.rand.nextInt(1,3);
         
         }
      
      } else if(this.mate != null) {
      
         if(this.x < this.mate.getX()) {
      
            xMovement = EntityManager.rand.nextInt(1,3);
      
         } else {
         
            xMovement = EntityManager.rand.nextInt(-2,0);
         
         }
         
         if(this.y < this.mate.getY()) {
         
            yMovement = EntityManager.rand.nextInt(1,3);
         
         } else {
         
            yMovement = EntityManager.rand.nextInt(-2,0);
         
         }
         
      
      
      } else {
      
         xMovement = EntityManager.rand.nextInt(-2,3);
         yMovement = EntityManager.rand.nextInt(-2,3);
      
      }
      
      this.x += xMovement;
      this.y += yMovement;
      this.borderX += xMovement;
      this.borderY += yMovement;
      
      if(this.borderX > 1500) {
      
         this.borderX -= 8;
         this.x -= 8;
      
      } else if(this.borderX < 0) {
      
         this.borderX += 8;
         this.x += 8;
      
      } else if(this.borderY < 0) {
      
         this.borderY += 8;
         this.y += 8;
      
      } else if(this.borderY > 1000) {
      
         this.borderY -= 8;
         this.y -= 8;
      
      }
   
   }
   
   public void age() {
   
      this.age++;
      this.health--;
   
   }

}
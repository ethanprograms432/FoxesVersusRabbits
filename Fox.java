import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Fox {

   private int x;
   private int y;
   private int borderX;
   private int borderY;
   public int age;
   public int health;
   public int gender;
   private BufferedImage image;
   private Rabbit target;
   private Fox mate;
   public int children;
   
   public Fox(int x,int y,int age,int gender,int health) {
   
      this.x = x;
      this.y = y;
      this.borderX = x + 40;
      this.borderY = y + 40;
      this.health = health;
      this.age = age;
      this.gender = gender;
      this.getTarget();
      this.children = 0;
      
      try {
      
         if(this.gender == 0) {
         
            this.image = ImageIO.read(getClass().getResourceAsStream("fox.png"));
            
         } else {
         
            this.image = ImageIO.read(getClass().getResourceAsStream("fox copy.png"));
         
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
   
   public void getTarget() {
   
      int currLargestDistance = 100000;
      
      for (int i = 0; i < EntityManager.rabbits.size(); i++) {
      
         int currX = EntityManager.rabbits.get(i).getX();
         int currY = EntityManager.rabbits.get(i).getY();
         
         int distance = (int)(Math.sqrt(Math.pow((currX - this.x),2) + Math.pow((currY - this.y),2)));
         
         if(distance < currLargestDistance) {
         
            this.target = EntityManager.rabbits.get(i);
         
         }
         
      
      }
      
      currLargestDistance = 100000;
      
      if(this.children < 10) {
      
         for (int j = 0; j < EntityManager.foxes.size(); j++) {
         
            if(!EntityManager.foxes.get(j).equals(this)) {
            
               int currX = EntityManager.foxes.get(j).getX();
               int currY = EntityManager.foxes.get(j).getY();
               int currGender = EntityManager.foxes.get(j).gender;
               
               int distance = (int)(Math.sqrt(Math.pow((currX - this.x),2) + Math.pow((currY - this.y),2)));
               
               if(distance < currLargestDistance && (this.gender != currGender)) {
               
                  this.mate = EntityManager.foxes.get(j);
               
               }
               
            }
            
         
         }
         
      } else {
      
         this.mate = null;
      
      }
   
   }
   
   public void move() {
   
      this.getTarget();
      
      int xMovement = 0;
      int yMovement = 0;
      
         if(this.health < 200 && this.target != null) {
         
            if(this.x < this.target.getX() + 20) {
            
               xMovement = EntityManager.rand.nextInt(0,5);
            
            } else {
            
               xMovement = EntityManager.rand.nextInt(-4,0);
            
            }
            
            if(this.y < this.target.getY() + 20) {
            
               yMovement = EntityManager.rand.nextInt(0,5);
            
            } else {
            
               yMovement = EntityManager.rand.nextInt(-4,0);
            
            }
            
         } else if(this.mate != null && this.children < 10) {
         
            if(this.x < this.mate.getX() + 20) {
            
               xMovement = EntityManager.rand.nextInt(0,5);
            
            } else {
            
               xMovement = EntityManager.rand.nextInt(-4,0);
            
            }
            
            if(this.y < this.mate.getY() + 20) {
            
               yMovement = EntityManager.rand.nextInt(0,5);
            
            } else {
            
               yMovement = EntityManager.rand.nextInt(-4,0);
            
            }
         
         } else if(this.target != null) {
         
            if(this.x < this.target.getX() + 20) {
            
               xMovement = EntityManager.rand.nextInt(0,5);
            
            } else {
            
               xMovement = EntityManager.rand.nextInt(-4,0);
            
            }
            
            if(this.y < this.target.getY() + 20) {
            
               yMovement = EntityManager.rand.nextInt(0,5);
            
            } else {
            
               yMovement = EntityManager.rand.nextInt(-5,0);
            
            }
            
         
         } else {
      
            xMovement = EntityManager.rand.nextInt(-4,5);
            yMovement = EntityManager.rand.nextInt(-4,5);
      
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
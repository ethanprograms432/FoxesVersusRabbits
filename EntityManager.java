import java.awt.*;
import java.util.Random;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.util.ArrayList;

public class EntityManager {

   SimulationWindow s;
   public static ArrayList<Grass> grass = new ArrayList<Grass>();
   public static ArrayList<Rabbit> rabbits = new ArrayList<Rabbit>();
   public static ArrayList<Fox> foxes = new ArrayList<Fox>();
   public static Random rand = new Random();
   
   public static int numGrass = 50;
   public static int numFoxes;
   public static int numRabbits;
   public static int newGrass = 0;
   
   public EntityManager(SimulationWindow s,int numR,int numF) {
   
      this.s = s;
      numFoxes = numF;
      numRabbits = numR;
      generateEntities();
   }
   
   public void generateEntities() {
   
      for (int i = 0; i < numGrass; i++) {
      
         int x = rand.nextInt(1,1500);
         int y = rand.nextInt(1,750);
         
         grass.add(new Grass(x,y));
         
      }
      
      for (int j = 0; j < numFoxes; j++) {
      
         int x = rand.nextInt(1,1500);
         int y = rand.nextInt(1,750);
         int age = rand.nextInt(1,1500);
         int gender = rand.nextInt(0,2);
         int health = rand.nextInt(1,300);
         
         foxes.add(new Fox(x,y,age,gender,health));
         
      }
      
      for (int k = 0; k < numRabbits; k++) {
      
         int x = rand.nextInt(1,1500);
         int y = rand.nextInt(1,750);
         int age = rand.nextInt(1,4000);
         int gender = rand.nextInt(0,2);
         int health = rand.nextInt(1,200);
         
         rabbits.add(new Rabbit(x,y,age,gender,health));
         
      }
   
   }
   
   public void checkIfRabbitEatingGrass() {
   
      for (int i = 0; i < numRabbits; i++) {
      
         int rabbitX = rabbits.get(i).getX();
         int rabbitXBorder = rabbitX + 40;
         
         int rabbitY = rabbits.get(i).getY();
         int rabbitYBorder = rabbitY + 40;
         
         for (int j = 0; j < numGrass; j++) {
         
               int grassX = grass.get(j).getX();
               int grassXBorder = grassX + 40;
               
               int grassY = grass.get(j).getY();
               int grassYBorder = grassY + 40;
               
               if(rabbitX > grassX && rabbitX < grassXBorder && rabbitY > grassY && rabbitY < grassYBorder) {

                     grass.remove(grass.get(j));
                     numGrass--;
                     rabbits.get(i).health = 200;
                     resetPriorities();
               
               }
            
            }
         }
   
   }
   
   public void resetPriorities() {
   
      for (int i = 0; i < rabbits.size(); i++) {
      
         rabbits.get(i).getTargetAndThreat();
      
      }
   
   }
   
   public void resetFoxPriorities() {
   
      for (int i = 0; i < foxes.size(); i++) {
      
         foxes.get(i).getTarget();
      
      }
   
   }
   
   public void checkIfFoxEatingRabbit() {
   
      for (int i = 0; i < numFoxes; i++) {
      
         int foxX = foxes.get(i).getX();
         int foxXBorder = foxX + 40;
         
         int foxY = foxes.get(i).getY();
         int foxYBorder = foxY + 40;
         
         for (int j = 0; j < numRabbits; j++) {
         
               int rabbitX = rabbits.get(j).getX();
               int rabbitXBorder = rabbitX + 40;
               
               int rabbitY = rabbits.get(j).getY();
               int rabbitYBorder = rabbitY + 40;
               
               if(foxX > rabbitX && foxX < rabbitXBorder && foxY > rabbitY && foxY < rabbitYBorder) {

                     rabbits.remove(rabbits.get(j));
                     numRabbits--;
                     foxes.get(i).health = 300;
                     foxes.get(i).getTarget();
                     resetFoxPriorities();
               
               }
            
            }
         }
      
      }
   
   public void checkIfAnimalsFking() {
   
      for (int i = 0; i < numFoxes; i++) {
      
         int foxX = foxes.get(i).getX();
         int foxXBorder = foxX + 40;
         
         int foxY = foxes.get(i).getY();
         int foxYBorder = foxY + 40;
         
         int foxAgeOne = foxes.get(i).age;
         
         for (int j = 0; j < i; j++) {
         
            
            int foxAgeTwo = foxes.get(j).age;
            
            int foxX2 = foxes.get(j).getX();
            int foxXBorder2 = foxX2 + 40;
            
            int foxY2 = foxes.get(j).getY();
            int foxYBorder2 = foxY2 + 40;
            
            if(foxX > foxX2 && foxX < foxXBorder2 && foxY > foxY2 && foxY < foxYBorder2 && foxAgeOne > 310 && foxAgeTwo > 310) {
            
               if((foxes.get(i).gender != foxes.get(j).gender) && foxes.get(i).children < 10 && foxes.get(j).children < 10) {
               
                  int gender = rand.nextInt(0,2);
                  foxes.add(new Fox((foxXBorder + 10),(foxYBorder + 10),0,gender,300));
                  foxes.get(i).children++;
                  foxes.get(j).children++;
                  foxes.get(i).getTarget();
                  foxes.get(j).getTarget();
                  numFoxes++;
                  
               }
            
            }
            
         
         }
         
         for (int k = i + 1; k < numFoxes; k++) {
         
            
            int foxAgeTwo = foxes.get(k).age;
            
            int foxX2 = foxes.get(k).getX();
            int foxXBorder2 = foxX2 + 40;
            
            int foxY2 = foxes.get(k).getY();
            int foxYBorder2 = foxY2 + 40;
            
            if(foxX > foxX2 && foxX < foxXBorder2 && foxY > foxY2 && foxY < foxYBorder2 && foxAgeOne > 310 && foxAgeTwo > 310) {
            
               if(foxes.get(i).gender != foxes.get(k).gender && foxes.get(i).children < 10 && foxes.get(k).children < 10) {
               
                  int gender = rand.nextInt(0,2);
                  foxes.add(new Fox((foxXBorder + 10),(foxYBorder + 10),0,gender,300));
                  foxes.get(i).children++;
                  foxes.get(k).children++;
                  foxes.get(i).getTarget();
                  foxes.get(k).getTarget();
                  numFoxes++;
            
               }
            }
            
         
         }
         foxes.get(i).age();
         
         if(foxes.get(i).age > 1500 || foxes.get(i).health < 1) {
         
            foxes.remove(foxes.get(i));
            numFoxes--;
            resetFoxPriorities();
         }
      
      }
      
      for (int j = 0; j < numRabbits; j++) {
      
            
         int rabbitX = rabbits.get(j).getX();
         int rabbitXBorder = rabbitX + 40;
            
         int rabbitY = rabbits.get(j).getY();
         int rabbitYBorder = rabbitY + 40;
         
         int rabbitOneAge = rabbits.get(j).age;
         
         for (int k = 0; k < j; k++) {
         
            int rabbitTwoAge = rabbits.get(k).age;
            
            int rabbitX2 = rabbits.get(k).getX();
            int rabbitXBorder2 = rabbitX2 + 40;
            
            int rabbitY2 = rabbits.get(k).getY();
            int rabbitYBorder2 = rabbitY2 + 40;
            
            if(rabbitX > rabbitX2 && rabbitX < rabbitXBorder2 && rabbitY > rabbitY2 && rabbitY < rabbitYBorder2 && rabbitOneAge > 100 && rabbitTwoAge > 100) {
            
               if(rabbits.get(j).gender != rabbits.get(k).gender && rabbits.get(j).children < 3 && rabbits.get(k).children < 3) {
                  
                  int gender = rand.nextInt(0,2);
                  
                  rabbits.add(new Rabbit(rabbitXBorder2 + 10,rabbitYBorder2 + 10,0,gender,200));
                  rabbits.get(j).children++;
                  rabbits.get(k).children++;
                  numRabbits++;
                  
               }
            
            }
            
         
         }
         
         for (int l = j; l < numRabbits; l++) {
         
            int rabbitTwoAge = rabbits.get(l).age;
            
            int rabbitX2 = rabbits.get(l).getX();
            int rabbitXBorder2 = rabbitX2 + 40;
            
            int rabbitY2 = rabbits.get(l).getY();
            int rabbitYBorder2 = rabbitY2 + 40;
            
            if(rabbitX > rabbitX2 && rabbitX < rabbitXBorder2 && rabbitY > rabbitY2 && rabbitY < rabbitYBorder2 && rabbitTwoAge > 100 && rabbitOneAge > 100) {
            
               if(rabbits.get(j).gender != rabbits.get(l).gender && rabbits.get(j).children < 3 && rabbits.get(l).children < 3) {
               
                  int gender = rand.nextInt(0,2);
                  rabbits.get(j).children++;
                  rabbits.get(l).children++;
                  
                  rabbits.add(new Rabbit(rabbitXBorder2 + 10,rabbitYBorder2 + 10,0,gender,200));
                  numRabbits++;
                  
               }
            
            }
            
         
         }
         
         rabbits.get(j).age();
         
         if(rabbits.get(j).age > 4000 || rabbits.get(j).health < 1) {
         
            rabbits.remove(rabbits.get(j));
            numRabbits--;
            resetFoxPriorities();
            resetPriorities();
         }
      
      }
   
   }
   
   public void drawEntities(Graphics2D g2) {
   
      for (Grass g: grass) {
         
         BufferedImage img = g.getImage();
         
         g2.drawImage(img,g.getX(),g.getY(),40,40,null);
      
      }
      
      for (Rabbit r: rabbits) {
         
         BufferedImage img = r.getImage();
         
         g2.drawImage(img,r.getX(),r.getY(),40,40,null);
         
         r.move();
      
      }
      
      for (Fox f: foxes) {
         
         BufferedImage img = f.getImage();
         
         g2.drawImage(img,f.getX(),f.getY(),40,40,null);
         
         f.move();
      
      }
      newGrass++;
      
      checkIfRabbitEatingGrass();
      checkIfFoxEatingRabbit();
      checkIfAnimalsFking();
      
      if(newGrass == 40) {
      
         int xPos = rand.nextInt(1,1500);
         int yPos = rand.nextInt(1,1000);
         grass.add(new Grass(xPos,yPos));
         numGrass++;
         newGrass = 0;
      
      }
   
   }
   
   

}
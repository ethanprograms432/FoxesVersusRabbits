import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

public class Grass {

   private int xPos;
   private int yPos;
   private int xBorder;
   private int yBorder;
   private BufferedImage image;

   public Grass(int x,int y) {
   
      this.xPos = x;
      this.yPos = y;
      this.xBorder = x + 40;
      this.yBorder = y + 40;
      
      try {
      
         this.image = ImageIO.read(getClass().getResourceAsStream("grass.png"));
         
      } catch(IOException e) {
      
         System.out.println("Image not found");
      }
   
   }
   
   public BufferedImage getImage() {
   
      return this.image;
   
   }
   
   public int getX() {
   
      return this.xPos;
   }  
   
   public int getY() {
   
      return this.yPos;
   }

}
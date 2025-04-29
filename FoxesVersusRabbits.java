import javax.swing.*;
import java.awt.*;


public class FoxesVersusRabbits {

   static JFrame frame;
   
   public static void main(String[] args) {
   
      frame = new JFrame("Foxes V Rabbit Simulator");
      
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      MainGUI mainGUI = new MainGUI();
      frame.getContentPane().add(mainGUI);
      
      frame.pack();
      frame.setVisible(true);
   
   }
   
   
   public static void startModel(int numFoxes,int numRabbits) {
   
      JFrame model = new JFrame("FOXES VERSUS RABBITS");
      model.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      model.setSize(1500,1000);
      model.setExtendedState(JFrame.MAXIMIZED_BOTH);

      SimulationWindow s = new SimulationWindow(numRabbits,numFoxes);
      
      model.getContentPane().add(s);
      
      model.pack();
      model.setVisible(true);
      
      s.startGameThread();
      
   }
   
}
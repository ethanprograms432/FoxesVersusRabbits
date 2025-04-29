import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainGUI extends JPanel {

   JLabel title,questionOne,questionTwo,questionThree,errorMessage;
   JTextField rabbits,foxes;
   JButton submit;
   
   
   public MainGUI() {
   
      this.setPreferredSize(new Dimension(350,500));
      
      ButtonListener bL = new ButtonListener();
      
      this.setLayout(new GridLayout(7,1));
      
      title = new JLabel("Welcome To Foxes Versus Rabbits");
      add(title);
      title.setFont(new Font("TimesRoman",Font.BOLD,20));
      title.setHorizontalAlignment(SwingConstants.CENTER);
      
      questionOne = new JLabel("Num Rabbits");
      questionOne.setHorizontalAlignment(SwingConstants.CENTER);
      add(questionOne);
      
      foxes = new JTextField(10);
      foxes.setHorizontalAlignment(SwingConstants.CENTER);
      add(foxes);
      
      questionTwo = new JLabel("Num Foxes");
      questionTwo.setHorizontalAlignment(SwingConstants.CENTER);
      add(questionTwo);
      
      rabbits = new JTextField(10);
      rabbits.setHorizontalAlignment(SwingConstants.CENTER);
      add(rabbits);
      
      errorMessage = new JLabel("");
      errorMessage.setForeground(Color.red);
      errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
      add(errorMessage);
      
      submit = new JButton("Submit");
      submit.setBackground(Color.green);
      submit.setOpaque(true);
      submit.setHorizontalAlignment(SwingConstants.CENTER);
      
      submit.addActionListener(bL);
      add(submit);
   
   }
   
   private class ButtonListener implements ActionListener {
   
   
      public void actionPerformed(ActionEvent ae) {
      
         JButton button = (JButton)ae.getSource();
         
         if(button == submit) {
         
           try {
            
               errorMessage.setText("");
               
               int numRabbits = Integer.valueOf(rabbits.getText());
               
               int numFoxes = Integer.valueOf(foxes.getText());
               
               FoxesVersusRabbits.startModel(numRabbits,numFoxes);
               
           } catch(InputMismatchException e) {
           
              
              errorMessage.setText("Enter an integer!");
           
           } catch(NoSuchElementException e) {
           
              errorMessage.setText("A field is blank!");
              
           } catch(NumberFormatException e) {
           
              errorMessage.setText("Invalid entry!");
           
           }
         
         }
      
      }
      
   }

}
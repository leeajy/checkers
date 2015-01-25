/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
/**
 *
 * @author Antonio Lee - Final Project for Comp Sci 
 */
public class Checkers extends JPanel implements ActionListener
{
 // Constructor
    private JButton newGameButton;  
   private JButton resignButton; 
   private JFrame fr; 
   private boolean gameOver;
   private CheckersBoard a; 
   
   
  
  public Checkers(JFrame f)
  {
    super.setOpaque(true);
    super.setPreferredSize(new Dimension(850, 850));
    super.setBackground(new Color(225, 225, 225));
    super.setLayout(null);
    fr = f;
    a = new CheckersBoard();
    a.setBounds(20,20,800,800);
    super.add(a);
  }
  
  // Perform any custom painting (if necessary) in this method
  @Override  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    
  }
  
  // Process GUI input in this method
  @Override  
  public void actionPerformed(ActionEvent e)
  {
    
    
    super.repaint();
  }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame.setDefaultLookAndFeelDecorated(false);
    JFrame fr = new JFrame("Application: Checkers");
    
    fr.setContentPane(new Checkers(fr));
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.setLocation(10, 10);
    fr.setResizable(false);
    fr.pack();
    fr.setVisible(true);  
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    fr.setLocation( (screensize.width - fr.getWidth())/2,(screensize.height - fr.getHeight())/2 );
    }
    
}

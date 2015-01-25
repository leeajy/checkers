/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//classes: voidgetPieces(), voidNewGame(), @Override publicvoidpaintcomponent(Graphics g) private boolean isGameOver() publicvoid mousePressed(MouseEvent e)
/**
 *
 * @author Antonio
 */
public class CheckersBoard extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
   private static final int BUTTON_SIZE = 32; 
  
  private JFrame fr; 
  private int nrow, ncolumn, clicks;
  //private CLabel[][] label;
  private JButton x;
  private JTextArea a;
  private JTextField t;
  private JButton newGameButton;  
  private JButton resignButton; 
  private JLabel message;
  private Image white, red;
  private ArrayList<Piece> reds;
  private ArrayList<Piece> whites;
  private ArrayList<String> tempArray;
  private String whoWon;
  private int mover, counter;
  private Piece temp;
  private boolean gameOver, jumpOver;
  private Piece[][] grid;
  public boolean redJ, whiteJ;
  private boolean possAfterJ;
  
  
  public CheckersBoard(/*int w, int h, JFrame f*/)
  {
    tempArray = new ArrayList<String>();
    resignButton = new JButton("Resign");
    resignButton.addActionListener(this);
    newGameButton = new JButton("New Game");
    newGameButton.addActionListener(this);
    message = new JLabel("",JLabel.CENTER);
    message.setFont(new  Font(Font.MONOSPACED, Font.BOLD, 14));
    message.setForeground(Color.CYAN);
    message.setText("ANTONIO");
    newGameButton.setBounds(110,10,80,25);
    resignButton.setBounds(310,10,80,25);
    message.setBounds(570,10,350,30);
    gameOver = false; whiteJ = false; redJ = false;
    super.add(newGameButton);
    super.add(resignButton);
    super.add(message);
    setBackground(Color.BLACK);
    super.addMouseListener(this);
    super.setLayout(null);
    super.setOpaque(true);
    super.setPreferredSize(new Dimension(32, 32));
    reds = new ArrayList<Piece>();
    whites = new ArrayList<Piece>();
    
    mover = 0;
    counter = 0;
    grid = new Piece[8][8];
    this.newGame();  
 
  }
  
public void newGame()
  {
      gameOver = false;
      message.setText("NG, Red Starts");
      mover = 0;
      counter = 0;
      tempArray.clear();
      reds.clear();
      whites.clear();
      
      jumpOver = false;
      possAfterJ = false;
      for(int x = 0; x < 8;x++)
          for(int y = 0; y< 8; y++)
              grid[x][y]= null;
      getPieces();
      repaint();
  }
  
  public void getPieces()
  {
        for (int row = 0; row < 3; row++) 
      {
         for (int col = 0; col < 8; col++) 
         {
             if ( row % 2 == col % 2 )
             {
                 String s = "images/red.jpg";
                 Piece c = new Piece(s,0,col,row);
               //Piece c = new Piece(s,1,4 + col*100, 4 + row*100 );
                 reds.add(c); 
                
                 grid[col][row] =c;
             }
         }     
    }
         //System.out.println(reds);
          for (int row = 5; row < 8; row++) 
      {
         for (int col = 0; col < 8; col++) 
         {
             if ( row % 2 == col % 2 )
             {
                 String s = "images/white.jpg";
                 Piece c = new Piece(s,1,col,row);
                 //Piece c = new Piece(s,1,4 + col*100, 4 + row*100 );
                 whites.add(c); 
                 grid[col][row] =c;
             }
         }  
      }  
      
  }
  
  public void resign()
  {

   gameOver = true;
    if(mover%2 == 0)
        message.setText("WHITE WINS");
    else
        message.setText("RED WINS");
   
  }
  
    @Override
  public void paintComponent(Graphics g)
  {
     g.setColor(Color.BLACK);
     g.drawRect(0,0,getSize().width-1,getSize().height-1);
     g.drawRect(1,1,getSize().width-3,getSize().height-3);
      for (int row = 0; row < 8; row++) 
      {
         for (int col = 0; col < 8; col++) 
         {
               if ( row % 2 == col % 2 )
                  g.setColor(Color.WHITE);
               else
                  g.setColor(Color.BLACK);
               g.fillRect(col*100,row*100, 100, 100);
               
               if(grid[col][row] != null)
               {
                   //System.out.println(grid[col][row]);
                  g.drawImage(grid[col][row].getImage(), grid[col][row].getX(), grid[col][row].getY(),100,100,this);
               }
               
         }}

      
  
  }
  
  
   @Override
   public void actionPerformed(ActionEvent e)
  {
    Object src = e.getSource();
    if( src == newGameButton)
    {
      newGame();
    }
    else if (src == resignButton)
    {
        resign();
    }
  }
  
   @Override
    public void mouseClicked(MouseEvent me) 
    {
        //System.out.println("counter:" + counter);
        //System.out.println("testt");
        if(gameOver == true)
        {
           message.setText("Make new game");
        }
        else
        {
            int cx = me.getX()/100;
            int ry = me.getY()/100;
            
            if(counter == 0 && temp == null)
            {
            
            //left 
            
           // System.out.println("test");
            if(me.getButton() == MouseEvent.BUTTON1 && mover%2==1) //whiteturn
            {
                //ystem.out.println("a");
                for(int x = 0; x <whites.size(); x++)
                {
                    if( whites.get(x).inside(cx, ry))
                    {
                        //System.out.println("true");
                        
                         tempArray = Click(whites.get(x));
                         return;
                    }
                }
            }
            if(me.getButton() == MouseEvent.BUTTON1 && mover%2==0) //redturn
            {
               // System.out.println("b");
                for(int x = 0; x <reds.size(); x++)
                {
                    //System.out.println(reds.get(x));
                    //System.out.println(cx);
                    //System.out.println(ry);
                    if( reds.get(x).inside(cx, ry))
                    {
                       // System.out.println("siz");
                        tempArray = Click(reds.get(x));
                        //System.out.println(tempArray);
                        return;
                     
                    }
                }
            }}
        
        //System.out.println(tempArray.size());
        if(counter == 1 && temp != null && !(tempArray.isEmpty()))
        {
//            System.out.println(temp);
           // System.out.println("awefaw");
            System.out.println(tempArray.size());
            for(int x = 0; x < tempArray.size();x++)
            {
                int tempC = Integer.parseInt(tempArray.get(x).substring(0,1));
                int tempR = Integer.parseInt(tempArray.get(x).substring(2,3));
                //System.out.println(tempC+","+tempR);
 
                if (grid[tempC][tempR] == null && tempC == cx && tempR == ry)
                {
                    counter++;
                    if( counter ==2 )
                    {
                    counter = 0;
                    //System.out.println("works");
                    //System.out.println(tempR);
                    int nr = (int)(Math.abs(tempR - temp.getRow()));
                    int nc = (int)(Math.abs(tempC - temp.getCol()));
                        
                    if(nr == 2 && nc == 2)
                    {
                      
                      jumpOver = true;
                    }
                    System.out.println("true");
                    doMoves(temp,tempC,tempR);
                    possAfterJ = false;
                    }
                }
              
            }
        }
        }
        
    }

     public boolean isOccupied(int x,int y)
  {
      for(int p = 0; p <whites.size(); p++)
                {
                    if( whites.get(p).inside(x, y))
                    {
                        whiteJ = true;
                        return true;
                    }
                }
      for(int p = 0; p <reds.size(); p++)
                {
                    if(reds.get(p).inside(x, y))
                    {
                        redJ = true;
                        return true;
                    }
                }
      return false;
      
  }
  public ArrayList<String> Click(Piece a) {
      
      //System.out.println("pass1");
      ArrayList<String> possMoves = new ArrayList<String>();
      int r = a.getRow();
      int c = a.getCol();
      if(a.getValue() == 0)//red
      {
          if( c-1>-1 && r+1<8)
          {
              
              if(isOccupied(c-1,r+1) == false  && possAfterJ == false)
              {
                  int c1 = c-1;
                  int r1 = r+1;
                  possMoves.add(c1+","+r1);
              }
          }
          if(c+1 < 8 && r+1<8)
          {
              if(isOccupied(c+1,r+1) == false  && possAfterJ == false) 
              {
                  int c1 = c+1;
                  int r1 = r+1;
                  possMoves.add(c1+","+r1);
                 
              }
          }
          if (c- 2 > -1 && r+2 <8)
          {
              if(isOccupied(c-1,r+1) == true && isOccupied(c-2,r+2) == false && whiteJ == true)
              {
                  whiteJ = false;
                  int c1 = c-2;
                  int r1 = r+2;
                  possMoves.add(c1+","+r1);
              }
          }
          if (c+2 <8 && r+2<8)
          {
              if(isOccupied(c+1,r+1) == true && isOccupied(c+2,r+2) == false && whiteJ== true)
              {
                  whiteJ = false;
                  int c1 = c+2;
                  int r1 = r+2;
                  possMoves.add(c1+","+r1);
              }
          }
      }
      if(a.getValue() == 1 || a.getValue() == 2 )//white
      {
          if(c-1 >-1 &&  r-1>-1)
          {
              
              if(isOccupied(c-1,r-1) == false && possAfterJ == false)
              {
                  int c1 = c-1;
                  int r1 = r-1;
                  possMoves.add(c1+","+ r1);
                 
              }
          }
          if(c+1 < 8 && r-1>-1)
          {
              if(isOccupied(c+1,r-1) == false && possAfterJ == false) 
              {
                  int c1 = c+1;
                  int r1 = r-1;
                  possMoves.add(c1 +","+ r1);
                 
              }
          }
          if (c- 2 > -1 && r-2>-1)
          {
              if(isOccupied(c-1,r-1) == true && isOccupied(c-2,r-2) == false && redJ == true)
              {
                  redJ = false;
                  int c1 = c-2;
                  int r1 = r-2;
                  possMoves.add(c1+","+r1);
              }
          }
          if (c+2 <8 && r-2>-1)
          {
              if(isOccupied(c+1,r-1) == true && isOccupied(c+2,r-2) == false && redJ == true)
              {
                  redJ =false;
                  int c1 = c+2;
                  int r1 = r-2;
                  possMoves.add(c1+","+r1);
              }
          }
      }
      if(a.getValue() == 2)// redking
      {
          if( c-1>-1 && r+1<8)
          {
              
              if(isOccupied(c-1,r+1) == false  && possAfterJ == false)
              {
                  int c1 = c-1;
                  int r1 = r+1;
                  possMoves.add(c1+","+r1);
              }
          }
          if(c+1 < 8 && r+1<8)
          {
              if(isOccupied(c+1,r+1) == false && possAfterJ == false) 
              {
                  int c1 = c+1;
                  int r1 = r+1;
                  possMoves.add(c1+","+r1);
                 
              }
          }
          if (c- 2 > -1 && r+2 <8)
          {
              if(isOccupied(c-1,r+1) == true && isOccupied(c-2,r+2) == false && whiteJ == true)
              {
                  whiteJ = false;
                  int c1 = c-2;
                  int r1 = r+2;
                  possMoves.add(c1+","+r1);
              }
          }
          if (c+2 <8 && r+2<8)
          {
              if(isOccupied(c+1,r+1) == true && isOccupied(c+2,r+2) == false && whiteJ== true)
              {
                  whiteJ = false;
                  int c1 = c+2;
                  int r1 = r+2;
                  possMoves.add(c1+","+r1);
              }
          }
          if(c-1 >-1 &&  r-1>-1)
          {
              
              if(isOccupied(c-1,r-1) == false && possAfterJ == false)
              {
                  int c1 = c-1;
                  int r1 = r-1;
                  possMoves.add(c1+","+ r1);
                 
              }
          }
          if(c+1 < 8 && r-1>-1)
          {
              if(isOccupied(c+1,r-1) == false && possAfterJ == false) 
              {
                  int c1 = c+1;
                  int r1 = r-1;
                  possMoves.add(c1 +","+ r1);
                 
              }
          }
          if (c- 2 > -1 && r-2>-1)
          {
              if(isOccupied(c-1,r-1) == true && isOccupied(c-2,r-2) == false && whiteJ == true)
              {
                  whiteJ = false;
                  int c1 = c-2;
                  int r1 = r-2;
                  possMoves.add(c1+","+r1);
              }
          }
          if (c+2 <8 && r-2>-1)
          {
              if(isOccupied(c+1,r-1) == true && isOccupied(c+2,r-2) == false && whiteJ == true)
              {
                  whiteJ =false;
                  int c1 = c+2;
                  int r1 = r-2;
                  possMoves.add(c1+","+r1);
              }
          }
      }
       if( a.getValue() == 3)// whiteking
      {
          if( c-1>-1 && r+1<8)
          {
              
              if(isOccupied(c-1,r+1) == false && possAfterJ == false)
              {
                  int c1 = c-1;
                  int r1 = r+1;
                  possMoves.add(c1+","+r1);
              }
          }
          if(c+1 < 8 && r+1<8)
          {
              if(isOccupied(c+1,r+1) == false && possAfterJ == false) 
              {
                  int c1 = c+1;
                  int r1 = r+1;
                  possMoves.add(c1+","+r1);
                 
              }
          }
          if (c- 2 > -1 && r+2 <8)
          {
              if(isOccupied(c-1,r+1) == true && isOccupied(c-2,r+2) == false && redJ == true)
              {
                  redJ = false;
                  int c1 = c-2;
                  int r1 = r+2;
                  possMoves.add(c1+","+r1);
              }
          }
          if (c+2 <8 && r+2<8)
          {
              if(isOccupied(c+1,r+1) == true && isOccupied(c+2,r+2) == false && redJ== true)
              {
                  redJ = false;
                  int c1 = c+2;
                  int r1 = r+2;
                  possMoves.add(c1+","+r1);
              }
          }
          if(c-1 >-1 &&  r-1>-1)
          {
              
              if(isOccupied(c-1,r-1) == false && possAfterJ == false)
              {
                  int c1 = c-1;
                  int r1 = r-1;
                  possMoves.add(c1+","+ r1);
                 
              }
          }
          if(c+1 < 8 && r-1>-1)
          {
              if(isOccupied(c+1,r-1) == false && possAfterJ == false) 
              {
                  int c1 = c+1;
                  int r1 = r-1;
                  possMoves.add(c1 +","+ r1);
                 
              }
          }
          if (c- 2 > -1 && r-2>-1)
          {
              if(isOccupied(c-1,r-1) == true && isOccupied(c-2,r-2) == false && redJ == true)
              {
                  redJ = false;
                  int c1 = c-2;
                  int r1 = r-2;
                  possMoves.add(c1+","+r1);
              }
          }
          if (c+2 <8 && r-2>-1)
          {
              if(isOccupied(c+1,r-1) == true && isOccupied(c+2,r-2) == false && redJ == true)
              {
                  redJ =false;
                  int c1 = c+2;
                  int r1 = r-2;
                  possMoves.add(c1+","+r1);
              }
          }
      }
      temp = a;
      //System.out.println(possMoves.size());
      if( possMoves.size() >0 )
          counter = 1;
      return possMoves;
      
     

  }
  public void doMoves(Piece a, int c, int r)
  {
     //System.out.println("gets to here");

     if(jumpOver== false)
     {
     grid[a.getCol()][a.getRow()] = null;
     a.setCR(c,r);
     grid[c][r] = a;
     
     counter = 0;
     mover++;
     //System.out.println("mover:" + mover);
     checkPromo(temp);
     temp = null;
     tempArray.clear();
     possAfterJ = false;
     repaint();
     }
     else if (jumpOver == true)
     {
         possAfterJ = true;
         jumpOver = false;
         counter = 0;
         tempArray.clear();
         grid[a.getCol()][a.getRow()] = null;
         int deletedC = (c+a.getCol())/2;
         int deletedR = (r+a.getRow())/2;
         //System.out.println(deletedC);
         //System.out.println(deletedR);
         grid[deletedC][deletedR] = null;
         for (int x = 0; x < whites.size(); x++)
         {
             if(whites.get(x).getRow() == deletedR && whites.get(x).getCol() == deletedC)
             {
                 whites.remove(x);
             }
         }
         for (int x = 0; x < reds.size(); x++)
         {
             if(reds.get(x).getRow() == deletedR && reds.get(x).getCol() == deletedC)
             {
                 reds.remove(x);
             }
         }
         a.setCR(c,r);
         grid[c][r] = a;
         checkPromo(temp);
         if(Click(a).isEmpty())
         {
             mover++;
             temp = null;
         }
         else
         {
             tempArray = Click(a);
             counter = 1;
         }
         repaint();
     }
     if(mover%2 == 0)
         message.setText("RED TURN");
     else
         message.setText("WHITE TURN");
     checkOver();
  }
  
  public void checkOver()
  {
      if(whites.isEmpty())
      {
          gameOver = true;
          message.setText("RED WINS");
      }
       if(reds.isEmpty())
      {
          gameOver = true;
          message.setText("WHITE WINS");
      }
  }
  
  public void checkPromo(Piece a)
  {
      if(a.getValue() == 0)//red
      {
          if(a.getRow() == 7)
          {
             a.setImage("images/redking.jpg");
             a.setValue(2);
          }
          
      }
      else if (a.getValue() == 1)//white
      {
          if(a.getRow() ==0)
          {
              a.setImage("images/whiteking.jpg");
              a.setValue(3);
          }
      }
  }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }
        
    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        return; //To change body of generated methods, choose Tools | Templates.
    }



}

    






    


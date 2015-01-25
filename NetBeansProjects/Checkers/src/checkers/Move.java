/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers;

/**
 *
 * @author Antonio
 */
public class Move extends CheckersBoard
{
    private int r1,r2;
    private int c1,c2;
    
    
    public Move(int row1, int col1)
    {
        r1 = row1;
        c1 = col1;
     
    }
    
    public int getR1()
    {
        return r1;
    }
    
    public int getR2()
    {
        return r2;
    }
    
    public int getC1()
    {
        return c1;
    }
    
    public int getC2()
    {
        return c2;
    }
    
    @Override
    public boolean inside(int x, int y)
    {
        //System.out.println(x + ">" + this.x + "&&" + x + "<" + this.x+105 + "&&" + y +">" + this.y + "&&" + this.y+105 + ">"+ y);
        //System.out.println(this.y +  "vs" + y);
        if ((x > r1 && x < r1+105) && (y > c1 && c1+105 > y))
        {
           // System.out.println("success");
            return true;
                    
        }
            
        else
            return false;
    }
            
    public boolean isJump()
    {
        return (Math.abs( r1 - r2) == 2);
    }
    
    @Override
    public String toString()
    {
        return "Move:" + r1+ "," + c1;
    }
    

            
            
            
            
 }

/*  private void getPieces()
  {
    
       for (int row = 0; row < 3; row++) 
      {
         for (int col = 0; col < 8; col++) 
         {
             if ( row % 2 == col % 2 )
             {

                   String s = "images/red.jpg";
                 Piece c = new Piece(s,0,4 + col*100, 4 + row*100 );
                 reds.add(c); 
               
               
        
             }
         }  
      
    }
   
          for (int row = 5; row < 8; row++) 
      {
         for (int col = 0; col < 8; col++) 
         {
             if ( row % 2 == col % 2 )
             {

                   String s = "images/white.jpg";
                 Piece c = new Piece(s,1,4 + col*100, 4 + row*100 );
                 whites.add(c); 
               
               
        
             }
         }  
      }  
    
  }
  
  public void newGame()
  {
      message.setText("RedSTARTS");
      whites.clear();
      reds.clear();
      getPieces();
      mover = 1;
      //possMoves = doMoves(
      nrow = -1;
      gameO = false;
      //newGameButton.setEnabled(false);
      resignButton.setEnabled(true);
      counter = 0;
      //repaint();
      
  }

  public void actionPerformed(ActionEvent e)
  {
    Object src = e.getSource();
    if( src == newGameButton)
    {
      newGame();
    }
    else if (src == resignButton)
    {
        Resign();
    }
  }

  
  public void Resign()
  {
     gameO = true;
     if(mover%2 == 0)
         message.setText("RED WINS");
     else
         message.setText("WHITE WINS");
  }
  
  public boolean isGameWon()
  {
      // 
      if(reds.size() == 0)
      {
          whoWon = "red";
          gameO = true;
          return gameO;
          
      }
      else if (whites.size() == 0)
      {
          whoWon = "white";
          gameO = true;
          return gameO; 
      }
      else
      {
          gameO = false;
          return gameO;
      }
  }
  
  public void gameOver(String str)
  {
      message.setText(str);
      newGameButton.setEnabled(true);
      resignButton.setEnabled(false);
      gameO = true;
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
               g.fillRect(2 + col*100, 2 + row*100, 105, 105);
               
         }}
      
             
               for (int x = 0; x < whites.size();x++)
               {
                  
                 g.drawImage(whites.get(x).getImage(), whites.get(x).getX(), whites.get(x).getY(),100,100,this);
                 
               }
             
            
               for (int x = 0; x < reds.size();x++)
               {
                 
                 g.drawImage(reds.get(x).getImage(), reds.get(x).getX(), reds.get(x).getY(),100,100, this);
                 
               }
             
      }  
     
  public boolean isOccupied(int x,int y)
  {
      for(int p = 0; p <whites.size(); p++)
                {
                    if( whites.get(p).inside(x, y))
                    {
                        return true;
                    }
                }
      for(int p = 0; p <reds.size(); p++)
                {
                    if(reds.get(p).inside(x, y))
                    {
                        return true;
                    }
                }
      return false;
      
  }
  public ArrayList<Move> Click(Piece a) {
      
      //System.out.println("pass1");
      ArrayList<Move> possMoves = new ArrayList<Move>();
      int x = a.getX();
      int y = a.getY();
      if(a.getValue() == 0)//red
      {
          if(x+105 < 845)
          {
              
              if(isOccupied(x+105,y+105) == false )
              {
                  possMoves.add(new Move(x+105,y+105));
              }
          }
          if(x - 105 > 4)
          {
              if(isOccupied(x-105,y+105) == false) 
              {
                  possMoves.add(new Move(x-105,y+105));
              }
          }
      }
      else if(a.getValue() == 1)//white
      {
          if(x+105 < 845)
          {
              
              if(isOccupied(x+105,y-105) == false )
              {
                  possMoves.add(new Move(x+105,y+105));
              }
          }
          if(x - 105 > 4)
          {
              if(isOccupied(x-105,y-105) == false) 
              {
                  possMoves.add(new Move(x-105,y+105));
              }
          }
      }
      temp = a;
      System.out.println(possMoves.size());
      if( possMoves.size() >0 )
          counter = 1;
      return possMoves;
      
     

  }
  public void doMoves(Piece a, Move b)
  {
      System.out.println("gets to here");
//     System.out.println("aa= " + b.getR1());
//     System.out.println("bb= " + b.getC1());
     a.setXY(b.getC1(), b.getR1());
     if ( b.isJump() == true)
     {
          //Click(a,)
     }
     temp = null;
     counter = 0;
     mover++;
  
     tempArray.clear();
     repaint();
  }
  
    @Override
    public void mousePressed(MouseEvent me) {
        
    }
    
    public boolean whiteMove()
    {
        if (mover%2 == 0)
        {
            
            return true;
        }
        else
        {
            
            return false;
        }
               
    } 

    @Override
    public void mouseClicked(MouseEvent me) {
        
        if(gameO == true)
        {
           message.setText("Make new game");
        }
        else
        {
            if(counter == 0)
            {
            int mx = ((me.getX() -2 )/105);
            int my = ((me.getY() - 2)/105);
            //left 
            
            //System.out.println("test");
            if(me.getButton() == MouseEvent.BUTTON1 && (isGameWon() == false) && (whiteMove() == true))
            {
                //ystem.out.println("a");
                for(int x = 0; x <whites.size(); x++)
                {
                    if( whites.get(x).inside(me.getX(), me.getY()))
                    {
                        //System.out.println("true");
                        //Click(whites.get(x));
                         tempArray = Click(whites.get(x));
                         return;
                    }
                }
            }
            if(me.getButton() == MouseEvent.BUTTON1 && (isGameWon() == false) && (whiteMove() == false))
            {
                //System.out.println("b");
                for(int x = 0; x <reds.size(); x++)
                {
                    if( reds.get(x).inside(me.getX(), me.getY()))
                    {
                        System.out.println("First Click");
                        tempArray = Click(reds.get(x));
                        return;
                     
                    }
                }
            }}
        
        System.out.println(tempArray.size());
        if(counter == 1 && temp != null && !(tempArray.isEmpty()))
        {
//            System.out.println(temp);
            System.out.println("Click 2" + tempArray);
            for(int x = 0; x < tempArray.size();x++)
            {
                System.out.println(me.getX()+","+me.getY());
                System.out.println(tempArray.get(x).inside(me.getX(),me.getY()));
                if (tempArray.get(x).inside(me.getX(),me.getY()))
                {
                    counter++;
                    if( counter ==2 )
                    {
                    counter = 0;
                    //System.out.println("TEST3");
                    doMoves(temp,tempArray.get(x));
                        System.out.println("moving to " + tempArray.get(x));
                     }
                }
              
            }
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
*/



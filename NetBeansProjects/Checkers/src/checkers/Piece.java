/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
/**
 *
 * @author Antonio
 */
public class Piece extends Object
{
    private Image a;
    private int col,row,numVal;
    private String b;
    
     public Piece (String fileName, int z, int c, int r)
    {
        
        col = c;
        row = r;
        //System.out.println(x);
        //System.out.println(y);
        a = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource(fileName));
        b = fileName.substring(0,2);
        numVal = z;
        //    0 = red 1 = white 2 = redking/ whiteking
    }
     
    public void setValue(int a)
    {
        numVal = a;
    }
    public void setImage( String s)
    {
        a = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource(s));
    }
    public int getX()
    {
        return col*100;
    }
    
    public int getY()
    {
        return row*100;
    }
    
    public int getValue()
    {
        return numVal;
    }
    
    public int getRow()
    {
        return row;
        //return (getY()/105+1);
    }
    
    public int getCol()
    {
        return col;
        //return (getX()/105+1);
    }
    
    public Image getImage()
    {
        return a;
    }

    public void setCR(int c, int r)
    {
        this.col = c;
        this.row = r;
        
    }
    
    public boolean inside(int c, int r)
    {
        //System.out.println(x + ">" + this.x + "&&" + x + "<" + this.x+105 + "&&" + y +">" + this.y + "&&" + this.y+105 + ">"+ y);
        //System.out.println(this.y +  "vs" + y);
        //if ((x > this.x && x < this.x+105) && (y > this.y && this.y+105 > y))
  
        if(col == c && row == r)
        {
            //System.out.println("success");
            return true;
                    
        }
            
        else
            return false;
    }
    
    
    public int jumpable(Piece aa)
    {
       // numVal -> 0 = bottom side going up, 1 = topside going bottom, 2= kings (both sides)
       // jumpable -> return 1 = can Jump and return 0 = cannot Jump 
        //if( numVal == 0 && ((aa.getX() < this.getX() && aa.getY() < this.getY()) || (aa.getX() > this.getX() && aa.getY() > this.getY())))
       return 0;
    }
    
    @Override
    public String toString()
    {
        return "Move(c,r):" + col+ "," + row;
    }
    
   
}

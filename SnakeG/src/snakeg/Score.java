/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

         /**
	 * @author Liranda Krasniqi
	 */
 	
public class Score 
{
    private int score;
    private int fruitEaten;
    public Score()
    {
        score = 0;
    }
    public void IncrementScore()
    {
        score+=100;
    }
    
     public void IncrementFruit()
    {
       fruitEaten++;
    }
     
    public int getfruitEaten()
    {
        return fruitEaten;
    }
    
    public int getScore()
    {
        return score;
    }
    
   
    public  void save() throws IOException 
    {
        ObjectOutputStream os;
        os = new ObjectOutputStream(new FileOutputStream ("snakeGame.dat"));
        os.writeObject(score);
        os.close();
    }
    
    
}
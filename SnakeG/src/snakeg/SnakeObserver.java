/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;

/**
 *
 * @author Liranda Krasniqi
 */
public class SnakeObserver 
{
    public Apple apple;
   public Score score;
    
    public SnakeObserver()
    {
        apple = new Apple();
        score = new Score();
    }
    
    public void notifyApple()
    {
        apple.moveApple();
    }
    
 
     /** notifies class score to increment score and number
       * of eaten fruits
       **/
    public void notifyScore()
    {
        score.IncrementScore();
        score.IncrementFruit();
    }
}

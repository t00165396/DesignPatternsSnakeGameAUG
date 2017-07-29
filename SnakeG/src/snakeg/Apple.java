/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
   * @author Liranda Krasniqi
*/
public class Apple
{
    Point apple;
    private static  int windowWidth = 800;
    private static int windowHeight = 600;
    Random position =new Random();//random class
    
    public Apple()
    {
        apple = new Point(20,10);
    }
    
    public Point get_apple()
    {
        return apple;
    }
    
       /** an object of the Random class called position is created 
        *this object is then used to set a random  x and y coordinate for the apple
        *the apple will then be displayed randomly on the screen
       **/				
	public void moveApple() {
		apple.x = position.nextInt((windowWidth/15)-4)+2;
		apple.y = position.nextInt((windowHeight/15)-5)+3;
	}
        
        /**draws the apple
	 * apple is  a Point
	 *@param g, a Graphics object
	 */
	public void drawApple(Graphics g) {
	        g.setColor(Color.RED);
		g.fillOval(apple.x*15, apple.y*15, 15, 15);
	}
    
}
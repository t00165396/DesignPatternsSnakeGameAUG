/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author Liranda Krasniqi.
 */
public class Snake 
{
    private LinkedList<Point> snake;//a linkedList with point objects
    Snake()
    {
        snake = new LinkedList<Point>();
        snake.addFirst(new Point(20,20));
    }
    
    /**
	 *this method uses getLast() method which belong to the linkedlist class
	 *the getLast() gets the last point of the linkedlist in this case it would bethe tail of the snake
	 *a new Point is then added to the tail to grow the snake
	 *@param int n (number)
	 */
 		public void growSnake (int n) {
		while(n > 0) {
			Point tail=snake.getLast();
			snake.add(new Point(tail));
			n--;
		}
                }
                
                public LinkedList<Point> getSnake()
                {
                    return snake;
                }
                
            public void moveSnake(int dx, int dy) 
            { 												
	        for(int i = snake.size()-1; i >= 1; i--) 
                {
			snake.get(i).setLocation(snake.get(i-1));
		}
		snake.getFirst().x += dx;
		snake.getFirst().y += dy;
	    }
            
            public void drawSnake (Graphics g) 
        {
		for(int i = 0; i < snake.size(); i++) 
                {
			g.setColor(Color.GREEN);
			Point p = snake.get(i);
			g.fillOval(p.x*15, p.y*15, 15, 15);
		}
	}
}




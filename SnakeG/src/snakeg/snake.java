/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;//bufferStrategy
import java.util.*;
import javax.swing.*;
import java.io.*;  

public class snake extends JFrame  implements KeyListener  {
	
	/**
	 * @author Liranda Krasniqi
	 */
 	
	 private static  int windowWidth = 800;
	 private static int windowHeight = 600;
	 private LinkedList<Point> snake;//a linkedList with point objects
	 private int dx;
	 int dy;
	 private Point apple;
	 private int score;
	 private int fruitEaten;
	 Random position =new Random();//random class
	 menu myMenu;


	public static void main(String args[])
	{
		snake gui=new snake();
	
	}
	
	
	 /** 
    * Class constructor.
    */	
	  public snake() {
		
		super("Snake Game");
		setSize(800,600);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		this.createBufferStrategy(2);
		this.addKeyListener(this);
		snake = new LinkedList<Point>();//creates the linkedlist object
		snake.addFirst(new Point(20,20));//  Returns the first element in this list.in this case it would be thehead of the snake								
		growSnake(5);	
		dx = 0;
		dy = 0;	
		apple = new Point(20,10);
			
		while(true) {
			long time = System.currentTimeMillis();//belongs to the system class and is used to return time in milliseconds										
			game();//this method calls all of the othe rmethods that are needed to start game
			while(System.currentTimeMillis()-time < 90) {
				//if time is less than 90 do nothing
				 
			}
		  }
		}//end of constructor
		
			
		/**
		 
 * Validates the snake moves 
 *calls methods to draw grow and move snake
 * draws all of the graphics to the screen
 *calls the methods ot draw and move apple
 * Use {@link moveSnake(int, int)} to move the snake.
 * Use {@link BlackBackground()} to draw black background to the screen.
 * Use {@link growSnake(int)} to grow  the snake.
 * Use {@link moveApplee()} to move the apple at a random position on the screen.
 */

	
		public void game() {
		drawBlackBackground();	
		moveSnake(dx, dy);
		Point head=snake.getFirst();
		//if snake has eaten apple
		if(head.equals(apple)) {
			moveApple();
			growSnake(3);
			score+=100;
			fruitEaten++;
			
		}
		else
			// check if any walls have been hit
		if(head.x <= 0 || 
			head.x >= windowWidth/15 || 
			head.y <= 1.5 || 
			head.y >= windowHeight/15.5) {
			
				gameOver();
					
		}
				
		//check if the snake has hit itself
	if(snake.size()>=7)	{
	
      for(int i =1;i<snake.size();i++)
		{
			if(head.equals(snake.get(i)))
			{
				gameOver();
				
			}
		}
		
		}
		 
		
	}//end of game method
	
	/**
	 *This method is called when the snake has hit the wall or hit itself
	 *it asks the user if they would like to play another game 
	 *it also save their score
	 * Use {@link save()} to save name and socore.
	 **/
	public void gameOver()
	{
		JOptionPane.showMessageDialog(null,"Game over");
				 myMenu=new menu();
				myMenu.addScore();
				try{
					save();
					JOptionPane.showMessageDialog(null,"file saved succesfully");
				}
				catch(Exception h)
				{
					JOptionPane.showMessageDialog(null,"Error in saving");
				}
				
				myMenu.display();
				
			int newGame = JOptionPane.showConfirmDialog(null,"Would you like to play another game","Another Game?",JOptionPane.YES_NO_OPTION);
				
				if(newGame==0)//If yes selected
				{	
					snake s2=new snake();
				}
					if(newGame==1)//If no selected
				{	
				
				System.exit(0);
				}
	}
	
	/**
	 *draws black background to the screen
	 * Use {@link drawApple(g)} to  draw apple on the black background.
	 * Use {@link drawSnake(g)} to draw the snake.
	 * Use {@link Scores(g)} to draw the scores.
	 */
		public void drawBlackBackground() {
		// this is the code for drawing 
		BufferStrategy buffer = this.getBufferStrategy();//bufferStyrategy allows you to draw everything before it is displayed
														//onto the screen
		Graphics g = null;
		
			g = buffer.getDrawGraphics();//The getDrawGraphics  will draw to one of the buffers
											
			// creates the black background
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, windowWidth, windowHeight);//gives the rect the same height and width as window
			
			//snake and food are drawn on to the black rectangle
			drawApple(g);
			drawSnake(g);
			Scores(g);

		
		// Shows the contents of the backbuffer on the screen.
		buffer.show();	
	}
		
	/** the snake is  a linkedlist with Point objects
	 * the for loop, loops around every element in the linkedlist and draws  green ovals to make up the snake
	 *@param g, a Graphics object
	 */
	public void drawSnake (Graphics g) {
		for(int i = 0; i < snake.size(); i++) {
			g.setColor(Color.GREEN);
			Point p = snake.get(i);
			g.fillOval(p.x*15, p.y*15, 15, 15);
		}
	}
	/**draws the apple
	 * apple is  a Point
	 *@param g, a Graphics object
	 */
		public void drawApple(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(apple.x*15, apple.y*15, 15, 15);
	}
	/** draws two strings to the screen -the Score and the Fruit Eaten
	 *@param g, a Graphics object
	 */
	public void Scores(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Score: " + score, 10, 60);
		g.setColor(Color.white);
		g.drawString("Fruit Eaten: " + fruitEaten, 80, 60);
		
	}
	
	/** 
	 *this method moves the snake around the screen 
	 *@param int dx 
	 *@param int dy 
	 *it starts at the last element of the snake and moves
	 *backwards through the linkedlist until its at the second element(the first element is the head)
	 *everytime it passes an element , the element inherits the coordinates of the element before it.
	 *to move the snake dx and dy is added to the x and y coordinate of the head.
	  */
		public void moveSnake(int dx, int dy) { 												
		for(int i = snake.size()-1; i >= 1; i--) {
			snake.get(i).setLocation(snake.get(i-1));
		}
		snake.getFirst().x += dx;
		snake.getFirst().y += dy;
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
 

/** an object of the Random class called position is created 
 *this object is then used to set a random  x and y coordinate for the apple
 *the apple will then be displayed randomly on the screen
 **/				
	public void moveApple() {
		apple.x = position.nextInt((windowWidth/15)-4)+2;
		apple.y = position.nextInt((windowHeight/15)-5)+3;
	}
	
	 public  void save() throws IOException {
      	ObjectOutputStream os;
      	os = new ObjectOutputStream(new FileOutputStream ("snakeGame.dat"));
      	os.writeObject(score);
      	os.close();
      }
			
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_DOWN) {
			dy=1;
			dx=0;
		} else if(key == KeyEvent.VK_UP) {
			dy=-1;
		    dx=0;
		} else if(key == KeyEvent.VK_RIGHT) {
			dy=0;
		    dx=1;
		} else if(key == KeyEvent.VK_LEFT) {
		dy=0;
		dx=-1;
		}
	}
 
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
}




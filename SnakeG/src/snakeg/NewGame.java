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
import snakeg.Apple;
import snakeg.SnakeObserver;
import snakeg.Score;
import snakeg.menu;

/**
	 * @author Liranda Krasniqi
	 */
public class NewGame extends JFrame  implements KeyListener  
{
    
         private static  int windowWidth = 800;
	 private static int windowHeight = 600;
         private static int firstPlay = 0;
         private static String Name;
	 private int dx;
	 int dy;
	 menu myMenu;
         SnakeObserver snakeObserver;
         snakeDirection snakedirection;
         Snake snake;
         SnakeSound sound;
     	public static void main(String args[])
	{
		NewGame gui = new NewGame();
	}
	
	
	 /** 
    * Class constructor.
    */	
	  public NewGame() {
               
		super("Snake Game");
		setSize(800,600);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		this.createBufferStrategy(2);
		this.addKeyListener(this);
                snake = new Snake();
                sound = new SnakeSound();
		//creates the linkedlist object
		//  Returns the first element in this list.in this case it would be thehead of the snake								
		snake.growSnake(5);	
		dx = 0;
		dy = 0;	
                /// ask for the player name only if it is his first game
                if (firstPlay == 0)
                {
                    System.out.println("Hi");
                     Name = (JOptionPane.showInputDialog("Please enter your name:"));
                     firstPlay = 1;
                }
               
                
		snakeObserver = new SnakeObserver();
		while(true) {
			long time = System.currentTimeMillis();//belongs to the system class and is used to return time in milliseconds										
			game();//this method calls all of the othe rmethods that are needed to start game
			while(System.currentTimeMillis()-time < 90) {
				//if time is less than 90 do nothing
				 
			}
		  }
		}//end of constructor
        public void actionPerformed (ActionEvent e) 
        {
            if (e.getActionCommand() .equals ("Cancel"))
            {
             System.exit(0);
            }
           
        }
		
			
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
		snake.moveSnake(dx, dy);
		Point head=snake.getSnake().getFirst();
		//if snake has eaten apple
		if(head.equals(snakeObserver.apple.get_apple())) 
                {
                        sound.playSound();
                        snakeObserver.notifyApple();
                        snakeObserver.notifyScore();
			snake.growSnake(3);	
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
	if(snake.getSnake().size()>=7)	
        {
	     for(int i =1;i<snake.getSnake().size();i++)
	     {
		if(head.equals(snake.getSnake().get(i)))
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
	 * Use {@link save()} to save name and score.
	 **/
	public void gameOver()
	{
		JOptionPane.showMessageDialog(null,"Game over");
				myMenu = new menu(Name);
				myMenu.addScore(snakeObserver.score.getScore());
				try
                                {
                                    snakeObserver.score.save();
				    JOptionPane.showMessageDialog(null,"file saved succesfully");
				}
				catch(Exception h)
				{
					JOptionPane.showMessageDialog(null,"Error in saving");
				}
				
				//myMenu.display();
				
			int newGame = JOptionPane.showConfirmDialog(null,"Would you like to play another game","Another Game?",JOptionPane.YES_NO_OPTION);
				
				if(newGame==0)//If yes selected
				{	
				    NewGame s2=new NewGame();
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
			snakeObserver.apple.drawApple(g);
			snake.drawSnake(g);
			Scores(g);
                        Name(g);

		
		// Shows the contents of the backbuffer on the screen.
		buffer.show();	
	}
		
	/** the snake is  a linkedlist with Point objects
	 * the for loop, loops around every element in the linkedlist and draws  green ovals to make up the snake
	 *@param g, a Graphics object
	 */
	
	
	/** draws two strings to the screen -the Score and the Fruit Eaten
	 *@param g, a Graphics object
	 */
        
        public void Name(Graphics g)
        {
             g.drawString("Player: "+ Name , 180, 60);
        }
	public void Scores(Graphics g) 
        {
		g.setColor(Color.white);
		g.drawString("Score: " + snakeObserver.score.getScore(), 10, 60);
		g.setColor(Color.white);
		g.drawString("Fruit Eaten: " + snakeObserver.score.getfruitEaten(), 80, 60);
               
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
           

 public void keyPressed(KeyEvent e) 
        {
		int key = e.getKeyCode();
            
		if(key == KeyEvent.VK_DOWN) 
                {
		    snakedirection = new Down();
                    dx = snakedirection.getDX();
                    dy = snakedirection.getDY();
		} 
                else if(key == KeyEvent.VK_UP) 
                {
		    snakedirection = new Up();
                    dx = snakedirection.getDX();
                    dy = snakedirection.getDY();
		} 
                else if(key == KeyEvent.VK_RIGHT) 
                {
		    snakedirection = new Right();
                    dx = snakedirection.getDX();
                    dy = snakedirection.getDY();
		} 
                else if(key == KeyEvent.VK_LEFT) 
                {
		    snakedirection = new Left();
                    dx = snakedirection.getDX();
                    dy = snakedirection.getDY();
		}
	}
 
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	
 }


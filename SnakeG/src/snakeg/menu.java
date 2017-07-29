/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;



import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;  
public class menu extends JFrame implements ActionListener,Serializable{
    
     JMenu fileMenu,snakeMenu;
      ArrayList<Player> snakeScore;
      int count; 
      	  Player p;
      	
      	public static void  main(String arg[])
      	{
      		menu  m=new menu();
      	}
      	
   
    // constructor
    public menu() {
        newSystem();
        //set the frame default properties
        setTitle     ( "Snake Game" );
        setSize      ( 400,200 );
        setLocation  ( 100,100 );
       
        //register 'Exit upon closing' as a default close operation
        setDefaultCloseOperation( EXIT_ON_CLOSE );
       
        createFileMenu();
        createSnakeMenu();
        //and add them to the menubar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(snakeMenu);
     }     
    
      public  void newSystem() {
      	 snakeScore = new ArrayList<Player>();
      	count = 0;
      }
      
      
      public  void save() throws IOException {
      	try{
      	
      	ObjectOutputStream os;
      	os = new ObjectOutputStream(new FileOutputStream ("snakeGame.dat"));
      	os.writeObject(snakeScore);
      	os.close();}
      	catch (Exception e)
      	{JOptionPane.showMessageDialog(null,"Error in saving file");
      	}
      }
      public  void open() {
      	try{
      	  ObjectInputStream is;
      	  is = new ObjectInputStream(new FileInputStream ("snakeGame.dat"));
          snakeScore  = (ArrayList<Player>) is.readObject(); 
      	  is.close();
      	}
      	catch(Exception e){
      		JOptionPane.showMessageDialog(null,"open didn't work");
      		e.printStackTrace();
      	}
      	
      } // end open()
      
      
     
      public  void addScore(){
      	
    	  Player p= new Player();
      	snakeScore.add(p);
      	
      	p.setName(JOptionPane.showInputDialog("Please enter your name:"));
      	snakeScore.add(p);
      	
      //	p.setScore(JOptionPane.showInputDialog("Please enter you score:"));
      	snakeScore.add(p);
      	
      	
      	
      }
      
      
      
      public  void display(){
      	JTextArea area = new JTextArea();
      	 String result="";
      	count=snakeScore.size();
      	if (count>0) {
      	  area.setText("Name: ");
      	    	  for(int i = 0; i < snakeScore.size(); i++) 
    {
       result=snakeScore.get(i)+"\n";
    }
    area.append(result);
      	  showMessage(area);
      	  
      	}
      	
    
      	else
      	    showMessage("No score was added ");
      } // end display
      
      public void actionPerformed (ActionEvent e) {
      	if (e.getActionCommand() .equals ("Quit")){
      	 showMessage("Thank you for playing the game");
      	 System.exit(0);
      	}
      	else if (e.getActionCommand() .equals ("Add")){
      	   addScore(); // branch to a separate method
      	}
      	else if (e.getActionCommand() .equals ("Display")){
           display();
      	}
      
      	else if (e.getActionCommand() .equals ("Save")){
      	
      	 try{
      	 	save();
      	 	showMessage("Data saved successfully");
      	 } // try
      	 catch (IOException f){
      	 	showMessage("Not able to save the file:\n"+
      	 	"Check the console printout for clues to why ");
      	 	f.printStackTrace();
      	 }// catch
      	}// else if
      	
      	else if (e.getActionCommand() .equals ("Open")){
      	 //open();
         display();
      	}
      	else
      	  showMessage("I have no idea what you clicked");
      } // actionPerformed
      
        private void createFileMenu(){
         // create the menu
      	fileMenu = new JMenu("File");
      	// declare a menu item (re-usable)
      	JMenuItem item;
      	item = new JMenuItem("Save");
      	item.addActionListener(this);
      	fileMenu.add(item);
      	item = new JMenuItem("Open");
      	item.addActionListener(this);
      	fileMenu.add(item);
      	
      	fileMenu.addSeparator();
      	item = new JMenuItem("Quit");
      	item.addActionListener(this);
      	fileMenu.add(item);
      }
      
      private void createSnakeMenu(){
      	// create the menu
      	snakeMenu = new JMenu("Score");
      	// declare a menu item (re-usable)
      	JMenuItem item;
      	
      	item = new JMenuItem("Add");
      	item.addActionListener(this);
      	snakeMenu.add(item);
      	
      	item = new JMenuItem("Display");
      	item.addActionListener(this);
      	snakeMenu.add(item);
      }
       /** utility methods to make the code simpler */
      public void showMessage (String s){
      	JOptionPane.showMessageDialog(null,s);
      }
      
      public void showMessage (JTextArea s){
      	JOptionPane.showMessageDialog(null,s);
      }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;

public class Player {
	private String name;
	private int score;

	
	// 'accessor' methods to return a copy of an attribute
	public int getScore() { return score;}
	public  String getName() { return name;}
	
	// 'mutator' methods to change the value of an attribute
	public void setScore (int score) {
				this.score = score;
	}
	
	public void setName( String name) {
				this.name = name;
	}
	
	
	
	public Player(String name, int score) {
				setName(name);
				setScore(score);
			
	}
	
	/** no-args constructor, for use as in Person p = new Person()
	 *  to create a default Person
	 */
	public Player() {
				this("Not Given",0);
	}
	
		
	/* Every object should have one of these, to give a quick
	 * String summary of the values of all the object's attributes
	 */
	public String toString() {
				return getName() + " " + getScore();
	}
}
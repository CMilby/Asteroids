package me.cmilby;

import java.awt.TextField;

public class ScoreKeeper 
{ 
	private static ScoreKeeper instance = null; 
    public int score; 
    public TextField scoreLabel;
    
	private ScoreKeeper() {
		this.scoreLabel = new TextField("Score: " + score + ", Lives: 3");
		this.scoreLabel.setBounds(10, 10, 500, 50);
	} 
	public static ScoreKeeper getInstance() 
	{ 
		if (instance == null) 
			instance = new ScoreKeeper(); 
		
		return instance; 
	}
	
	public void addPoint(int lives) {
		score++;
		this.updateLabel(lives);
	}
	
	public void reset(int lives) {
		score = 0;
		this.updateLabel(lives);
	}
	
	public void updateLabel(int lives) {
		this.scoreLabel.setText("Score: " + score + ", Lives: " + lives);
	}
} 

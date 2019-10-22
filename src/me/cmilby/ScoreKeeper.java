package me.cmilby;

import java.awt.TextField;

public class ScoreKeeper 
{ 
	private static ScoreKeeper instance = null; 
    public int score; 
    public TextField scoreLabel;
    
	private ScoreKeeper() {
		this.scoreLabel = new TextField("Score: " + score);
		this.scoreLabel.setBounds(10, 10, 100, 50);
	} 
	public static ScoreKeeper getInstance() 
	{ 
		if (instance == null) 
			instance = new ScoreKeeper(); 
		
		return instance; 
	}
	
	public void addPoint() {
		score++;
		this.updateLabel();
	}
	
	public void reset() {
		score = 0;
		this.updateLabel();
	}
	
	private void updateLabel() {
		this.scoreLabel.setText("Score: " + score);
	}
} 

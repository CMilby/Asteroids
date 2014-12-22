package me.cmilby;

import java.awt.Color;

public class AsteroidsGame extends Game {

	private static final long serialVersionUID = 1L;

	public AsteroidsGame() {
		super();
		
		setBackground(Color.BLACK);
	}
	
	@Override
	public void init() {
		addEntity((new Entity()).addComponent(new ShipComponent()));
	
		addEntity((new Entity()).addComponent(new AsteroidComponent(2)));
	}
}

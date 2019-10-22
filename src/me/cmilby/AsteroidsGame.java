package me.cmilby;

import java.awt.Color;

public class AsteroidsGame extends Game {

	private static final long serialVersionUID = 1L;
	private int lives = 3;

	public AsteroidsGame() {
		super();
		setBackground(Color.BLACK);
	}
	
	@Override
	public void init() {
		addEntity(new ShipEntity());
		for (int i = 0; i < 5; i++)
			addEntity(new AsteroidEntity(Util.getRandomNumber(1, 3)));	
	}
	
	@Override
	public void handleUpdate(float delta) {
		super.handleUpdate(delta);
		
		// Ship To Asteroid Collision
		Entity ship = getRoot().getAllChildren().get(0);
		for (int i = 1; i < getRoot().getAllChildren().size(); i++) {
			Entity collisionEntity = getRoot().getAllChildren().get(i);
			if (collisionEntity instanceof AsteroidEntity) {
				if (ship.hasCollided(collisionEntity)) 
					reset();
			}
		}
	
		// Asteroid To Bullet Collision
		for (int i = 1; i < getRoot().getAllChildren().size(); i++) {
			Entity asteroid = getRoot().getAllChildren().get(i);
			if (asteroid instanceof AsteroidEntity) {
				for (int j = 0; j < ship.getAllChildren().size(); j++) {
					if (asteroid.hasCollided(ship.getAllChildren().get(j))) {
						handleBulletToAsteroid((AsteroidEntity) asteroid, (BulletEntity) ship.getAllChildren().get(j), ship);
						ScoreKeeper.getInstance().addPoint(lives);
					}
				}
			}
		}
	}
	
	private void reset() {
		getRoot().getAllChildren().clear();
		lives--;
		ScoreKeeper.getInstance().updateLabel(lives);
		if (lives <= 0) {
			ScoreKeeper.getInstance().reset(lives);
		}
		addEntity(new ShipEntity());
		for (int i = 0; i < 5; i++)
			addEntity(new AsteroidEntity(Util.getRandomNumber(1, 3)));	
	}
	
	private void handleBulletToAsteroid(AsteroidEntity asteroid, Entity bullet, Entity ship) {
		getRoot().getAllChildren().remove(asteroid);
		ship.getAllChildren().remove(bullet);
		for (int i = 0; i < Util.getRandomNumber(1, 2); i++) 
			addEntity(new AsteroidEntity(Util.getRandomNumber(1, 3)));
		for (int i = 1; i < asteroid.getShape().sides(); i++)
			addEntity(new FragmentEntity(asteroid.getShape().getPoint(i - 1), asteroid.getShape().getPoint(i), asteroid.getVelocity()));
	}
}

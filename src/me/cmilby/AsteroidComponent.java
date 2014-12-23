package me.cmilby;

import java.awt.Color;
import java.awt.Graphics;

public class AsteroidComponent extends EntityComponent {

	public static final int MIN_COORD = -100;
	public static final int MAX_COORD = 700;
	
	private int size;
	private Vector2f velocity;
	private Vector2f position;
	 
	public AsteroidComponent(int size) {
		this(size, Util.getOffscreenPoint());
	}
	
	public AsteroidComponent(int size, Vector2f position) {
		super();
		this.size = size;
		this.position = position;
		float angle = Util.getRandomNumber(-360, 360);
		this.velocity = new Vector2f((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle)));
	}
	
	@Override
	public void setParent(Entity parent) {
		super.setParent(parent);
		getTransform().setPosition(position);
		getTransform().setRotation((Math.random() > 0.5) ? (float) -(Math.random() / 2) : (float) (Math.random() / 2));
		createAsteroid(size);
	}
	
	@Override
	public void handleUpdate(float delta) {
		super.handleUpdate(delta);
		getTransform().setPosition(getTransform().getPosition().add(velocity));
		rotate();
		for (int i = 0; i < getShape().sides(); i++) 
			getShape().setPoint(i, getShape().getPoint(i).add(velocity));
		wrapAround();
	}
	
	@Override
	public void handleRender(Graphics g) {
		super.handleRender(g);
		g.setColor(Color.WHITE);
		getShape().renderPolygon(g);
	}
	
	private void createAsteroid(int size) {
		int sides = 0;
		int min = 40;
		int max = 55;
		
		switch (size) {
		case 1:
			// Small Asteroid: 7 - 12 sides | 40 - 55 from center
			sides = Util.getRandomNumber(7, 12);
			break;
		case 2:
			// Medium Asteroid: 13 - 18 sides | 60 - 75 from center
			sides = Util.getRandomNumber(13, 18);
			min = 60;
			max = 75;
			break;
		case 3:
			// Large Asteroid: 19 - 24 sides | 80 - 95 from center
			sides = Util.getRandomNumber(19, 24);
			min = 80;
			max = 95;
			break;
		}
		
		for (int i = 0; i < sides; i++) {
			int setY = Util.getRandomNumber(min, max);
			Vector2f point = Util.rotate(getTransform().getPosition().getX(), getTransform().getPosition().getY(), 
					getTransform().getPosition().getX(), getTransform().getPosition().getY() + setY, (360.0 / sides) * i);
			getShape().addPoint(point);
		}
	}
	
	private void rotate() {
		for (int i = 0; i < getShape().sides(); i++) 
			getShape().setPoint(i, Util.rotate(getTransform().getPosition().getX(), getTransform().getPosition().getY(), 
					getShape().getPoint(i).getX(), getShape().getPoint(i).getY(), getTransform().getRotation()));
	}
	
	private void wrapAround() {
		Vector2f position = getTransform().getPosition();
		if (position.getX() < MIN_COORD) {
			getTransform().setPosition(new Vector2f(MAX_COORD, position.getY()));
			for (int i = 0; i < getShape().sides(); i++)
				getShape().setPoint(i, new Vector2f(getShape().getPoint(i).getX() - MIN_COORD + MAX_COORD, getShape().getPoint(i).getY()));
			float angle = Util.getRandomNumber(-270, -90);
			velocity = new Vector2f((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle)));
		}
		
		if (position.getX() > MAX_COORD) {
			getTransform().setPosition(new Vector2f(MIN_COORD, position.getY()));
			for (int i = 0; i < getShape().sides(); i++)
				getShape().setPoint(i, new Vector2f(getShape().getPoint(i).getX() - MAX_COORD + MIN_COORD, getShape().getPoint(i).getY()));
			float angle = Util.getRandomNumber(90, 270);
			velocity = new Vector2f((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle)));
		}
		
		if (position.getY() < MIN_COORD) {
			getTransform().setPosition(new Vector2f(position.getX(), MAX_COORD));
			for (int i = 0; i < getShape().sides(); i++) 
				getShape().setPoint(i, new Vector2f(getShape().getPoint(i).getX(), getShape().getPoint(i).getY() - MIN_COORD + MAX_COORD));
			float angle = Util.getRandomNumber(-180, 0);
			velocity = new Vector2f((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle)));
		}
		
		if (position.getY() > MAX_COORD) {
			getTransform().setPosition(new Vector2f(position.getX(), MIN_COORD));
			for (int i = 0; i < getShape().sides(); i++) 
				getShape().setPoint(i, new Vector2f(getShape().getPoint(i).getX(), getShape().getPoint(i).getY() - MAX_COORD + MIN_COORD));
			float angle = Util.getRandomNumber(0, 180);
			velocity = new Vector2f((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle)));
		}
	}	
}

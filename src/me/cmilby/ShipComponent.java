package me.cmilby;

import java.awt.Color;
import java.awt.Graphics;

public class ShipComponent extends EntityComponent {
	
	private Input input;
	private Vector2f velocity;
	
	private boolean canShoot;
	private float lastShot;
	
	public ShipComponent() {
		super();
		this.input = Input.getInstance();
		this.velocity = new Vector2f();
		this.canShoot = true;
		this.lastShot = 0.0f;
	}
	
	@Override
	public void setParent(Entity parent) {
		super.setParent(parent);
		getTransform().setPosition(new Vector2f(300, 300));
	}
	
	@Override
	public void handleInput(float delta) {
		super.handleInput(delta);
		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
			velocity.setX(velocity.getX() + (float) Math.sin(getTransform().getRotation()) * (delta / 4.0f));
			velocity.setY(velocity.getY() - (float) Math.cos(getTransform().getRotation()) * (delta / 4.0f));
			if (velocity.getX() > 2.5f)
				velocity.setX(2.5f);
			if (velocity.getX() < -2.5f)
				velocity.setX(-2.5f);
			if (velocity.getY() > 2.5f)
				velocity.setY(2.5f);
			if (velocity.getY() < -2.5f)
				velocity.setY(-2.5f);
		}
		
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			rotateLeft();
		}
		
		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
			// No effect
		}
		
		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			rotateRight();
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if (!velocity.equals(new Vector2f(0.0f, 0.0f)) && canShoot) {
				getParent().addChild((new Entity()).addComponent(new BulletComponent(getTransform().getRotation(), getShipTip())));
				canShoot = false;
			}
		}
	}
	
	@Override
	public void handleUpdate(float delta) {
		super.handleUpdate(delta);
		getTransform().setPosition(getTransform().getPosition().add(velocity));
		wrapAround();
		lastShot += delta;
		if (lastShot >= (60 * delta)) {
			canShoot = true;
			lastShot = 0.0f;
		}
	}
	
	@Override
	public void handleRender(Graphics g) {
		super.handleRender(g);
		g.setColor(Color.WHITE);
		getShape().clear();
		getShape().addPoint((float) (getTransform().getPosition().getX() + 2 * 9.0 * Math.sin(getTransform().getRotation())), 
				(float) (getTransform().getPosition().getY() - 2 * 9.0 * Math.cos(getTransform().getRotation())));
		getShape().addPoint((float) (getTransform().getPosition().getX() + 9.0 * Math.cos(getTransform().getRotation()) - 9.0 * Math.sin(getTransform().getRotation())), 
				(float) (getTransform().getPosition().getY() + 9.0 * Math.cos(getTransform().getRotation()) + 9.0 * Math.sin(getTransform().getRotation())));
		getShape().addPoint((float) (getTransform().getPosition().getX() - 9.0 * Math.cos(getTransform().getRotation()) - 9.0 * Math.sin(getTransform().getRotation())), 
				(float) (getTransform().getPosition().getY() + 9.0 * Math.cos(getTransform().getRotation()) - 9.0 * Math.sin(getTransform().getRotation())));
		getShape().renderPolygon(g);
	}
	
	private Vector2f getShipTip() {
		return new Vector2f((float) (getTransform().getPosition().getX() + 2 * 9.0 * Math.sin(getTransform().getRotation())), 
							(float) (getTransform().getPosition().getY() - 2 * 9.0 * Math.cos(getTransform().getRotation())));
	}
	
	private void wrapAround() {
		Vector2f position = getTransform().getPosition();
		if (position.getX() < -15)
			getTransform().getPosition().setX(615);
		if (position.getX() > 615)
			getTransform().getPosition().setX(-15);
		if (position.getY() < -15)
			getTransform().getPosition().setY(615);
		if (position.getY() > 615)
			getTransform().getPosition().setY(-15);
	}
	
	private void rotateLeft() {
		getTransform().setRotation(getTransform().getRotation() - (float) Math.toRadians(0.25));
		if (getTransform().getRotation() < 0) {
			getTransform().setRotation(getTransform().getRotation() + (float) (2 * Math.PI));
		}
	}
	
	private void rotateRight() {
		getTransform().setRotation(getTransform().getRotation() + (float) Math.toRadians(0.25));
		if (getTransform().getRotation() > 2 * Math.PI) {
			getTransform().setRotation(getTransform().getRotation() - (float) (2 * Math.PI));
		}
	}
}

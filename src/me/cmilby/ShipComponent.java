package me.cmilby;

import java.awt.Color;
import java.awt.Graphics;

public class ShipComponent extends EntityComponent {
	
	private Input input;
	private Vector2f velocity;
	
	public ShipComponent() {
		super();
		this.input = Input.getInstance();
		this.velocity = new Vector2f();
	}
	
	@Override
	public void setParent(Entity parent) {
		super.setParent(parent);
		getTransform().setPosition(new Vector2f(300, 300));
	}
	
	@Override
	public void handleInput(float delta) {
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
			// <<ShootType>>
		}
	}
	
	@Override
	public void handleUpdate(float delta) {
		getTransform().setPosition(getTransform().getPosition().add(velocity));
		wrapAround();
	}
	
	@Override
	public void handleRender(Graphics g) {
		g.setColor(Color.WHITE);
		Polygon2f shape = new Polygon2f();
		float angle = getTransform().getRotation();
		Vector2f position = getTransform().getPosition();
		shape.addPoint((float) (position.getX() + 2 * 9.0 * Math.sin(angle)), (float) (position.getY() - 2 * 9.0 * Math.cos(angle)));
		shape.addPoint((float) (position.getX() + 9.0 * Math.cos(angle) - 9.0 * Math.sin(angle)), (float) (position.getY() + 9.0 * Math.cos(angle) + 9.0 * Math.sin(angle)));
		shape.addPoint((float) (position.getX() - 9.0 * Math.cos(angle) - 9.0 * Math.sin(angle)), (float) (position.getY() + 9.0 * Math.cos(angle) - 9.0 * Math.sin(angle)));
		shape.render(g);
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

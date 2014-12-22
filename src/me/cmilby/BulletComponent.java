package me.cmilby;

import java.awt.Color;
import java.awt.Graphics;

public class BulletComponent extends EntityComponent {

	public static final int BULLET_SIZE = 4;
	
	private Vector2f velocity;
	private Vector2f position;
	
	public BulletComponent(float rotation, Vector2f position) {
		super();
		this.velocity = new Vector2f((float) Math.sin(rotation), (float) -Math.cos(rotation)); 
		this.position = position;
	}
	
	@Override
	public void setParent(Entity parent) {
		super.setParent(parent);
		getTransform().setPosition(position);
	}
	
	@Override
	public void handleUpdate(float delta) {
		super.handleUpdate(delta);
		getTransform().setPosition(getTransform().getPosition().add(velocity));
	}
	
	@Override
	public void handleRender(Graphics g) {
		super.handleRender(g);
		getShape().clear();
		g.setColor(Color.WHITE);
		getShape().addPoint(new Vector2f(getTransform().getPosition().getX() - (BULLET_SIZE / 2), getTransform().getPosition().getY() - (BULLET_SIZE / 2)));
		getShape().addPoint(new Vector2f(getTransform().getPosition().getX() - (BULLET_SIZE / 2), getTransform().getPosition().getY() + (BULLET_SIZE / 2)));
		getShape().addPoint(new Vector2f(getTransform().getPosition().getX() + (BULLET_SIZE / 2), getTransform().getPosition().getY() + (BULLET_SIZE / 2)));
		getShape().addPoint(new Vector2f(getTransform().getPosition().getX() + (BULLET_SIZE / 2), getTransform().getPosition().getY() - (BULLET_SIZE / 2)));
		getShape().renderPolygon(g);
	}
}

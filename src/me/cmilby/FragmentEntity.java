package me.cmilby;

import java.awt.Color;
import java.awt.Graphics;

public class FragmentEntity extends Entity {

	public static final int MAX_COLOR = 255;
	
	private Vector2f velocity;
	private Vector2f origin;
	
	public FragmentEntity(Vector2f p1, Vector2f p2, Vector2f velocity) {
		super();
		this.velocity = velocity;
		this.origin = Util.midpoint(p1, p2);
		getTransform().setPosition(origin);
		getTransform().setRotation((Math.random() > 0.5) ? (float) -(Math.random()) : (float) (Math.random()));
		getShape().addPoint(p1);
		getShape().addPoint(p2);
	}
	
	@Override
	public void handleUpdate(float delta) {
		getTransform().setPosition(getTransform().getPosition().add(velocity));
		for (int i = 0; i < getShape().getPoints().size(); i++) {
			getShape().setPoint(i, Util.rotate(getTransform().getPosition().getX(), getTransform().getPosition().getY(), 
					getShape().getPoint(i).getX(), getShape().getPoint(i).getY(), getTransform().getRotation()));
			getShape().setPoint(i, getShape().getPoint(i).add(velocity));
		}
	}
	
	@Override
	public void handleRender(Graphics g) {
		super.handleRender(g);
		int distance = Util.clamp((int) Math.abs(Util.distance(getShape().getPoint(0), origin)), 255, 0);
		g.setColor(new Color(MAX_COLOR - distance, MAX_COLOR - distance, MAX_COLOR - distance));
		for (int i = 1; i < getShape().getPoints().size(); i++) 
			g.drawLine((int) getShape().getPoint(i - 1).getX(), (int) getShape().getPoint(i - 1).getY(), 
					(int) getShape().getPoint(i).getX(), (int) getShape().getPoint(i).getY());
	}
}

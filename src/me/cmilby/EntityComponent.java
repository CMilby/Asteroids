package me.cmilby;

import java.awt.Color;
import java.awt.Graphics;

public class EntityComponent {

	private Entity parent;
	
	public EntityComponent() {
		
	}
	
	public EntityComponent(Entity parent) {
		this.parent = parent;
	}
	
	public void setParent(Entity parent) {
		this.parent = parent;
	}
	
	public void handleInput(float delta) {
		
	}
	
	public void handleUpdate(float delta) {
		
	}
	
	public void handleRender(Graphics g) {
		if (CoreGame.DEBUG && getShape().sides() > 0) {
			g.setColor(Color.RED);
			getShape().renderBoundingBox(g);
		}
	}
	
	public Entity getParent() {
		return this.parent;
	}
	
	public Transform getTransform() {
		return this.parent.getTransform();
	}
	
	public Polygon2f getShape() {
		return this.parent.getShape();
	}
}

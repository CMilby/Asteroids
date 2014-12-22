package me.cmilby;

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
		
	}
	
	public Entity getParent() {
		return this.parent;
	}
	
	public Transform getTransform() {
		return this.parent.getTransform();
	}
}

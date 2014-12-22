package me.cmilby;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Entity {

	private Transform transform;
	
	private List<Entity> children;
	private List<EntityComponent> components;
	
	public Entity() {
		this.transform = new Transform();
		children = new ArrayList<Entity>();
		components = new ArrayList<EntityComponent>();
	}
	
	public Entity addChild(Entity child) {
		children.add(child);
		return this;
	}
	
	public Entity addComponent(EntityComponent component) {
		component.setParent(this);
		components.add(component);
		return this;
	}
	
	private void input(float delta) {
		for (EntityComponent e : components) 
			e.handleInput(delta);
	}
	
	public void handleInput(float delta) {
		input(delta);
		for (Entity e : children)
			e.handleInput(delta);
	}
	
	private void update(float delta) {
		for (EntityComponent e : components) 
			e.handleUpdate(delta);
	}
	
	public void handleUpdate(float delta) {
		update(delta);
		for (Entity e : children) 
			e.handleUpdate(delta);
	}
	
	private void render(Graphics g) {
		for (EntityComponent e : components) 
			e.handleRender(g);
	}
	
	public void handleRender(Graphics g) {
		render(g);
		for (Entity e : children)
			e.handleRender(g);
	}
	
	public Transform getTransform() {
		return this.transform;
	}
}

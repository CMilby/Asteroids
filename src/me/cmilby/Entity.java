package me.cmilby;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Entity {

	private Transform transform;
	private Polygon2f shape;
	
	private List<Entity> children;
	private List<EntityComponent> components;
	
	public Entity() {
		this.transform = new Transform();
		this.shape = new Polygon2f();
		children = new CopyOnWriteArrayList<Entity>();
		components = new CopyOnWriteArrayList<EntityComponent>();
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
	
	public Rectangle getBoundingBox() {
		int minX = (int) Util.minX(shape.getPoints());
		int minY = (int) Util.minY(shape.getPoints());
		return new Rectangle(minX, minY, (int) Util.maxX(shape.getPoints()) - minX, (int) Util.maxY(shape.getPoints()) - minY);
	}
	
	public boolean areBoundingBoxesOverlapping(Entity entity) {
		return getBoundingBox().intersects(entity.getBoundingBox());
	}
	
	public boolean hasCollided(Entity entity) {
		if (areBoundingBoxesOverlapping(entity)) 
			return doLinesIntersect(entity);
		return false;
	}
	
	public boolean doLinesIntersect(Entity entity) {
		for (Line2D line1 : getOutlineLines()) 
			for (Line2D line2 : entity.getOutlineLines())
				if (line1.intersectsLine(line2) || line2.intersectsLine(line1))
					return true;
		return false;
	}
	
	public List<Line2D> getOutlineLines() {
		List<Line2D> lineList = new ArrayList<Line2D>();
		for (int i = 0; i < shape.sides(); i++) 
			lineList.add((i == 0) ? new Line2D.Float(shape.getPoint(shape.sides() - 1).getX(), shape.getPoint(shape.sides() - 1).getY(), shape.getPoint(0).getX(), shape.getPoint(0).getY()) 
					: new Line2D.Float(shape.getPoint(i - 1).getX(), shape.getPoint(i - 1).getY(), shape.getPoint(i).getX(), shape.getPoint(i).getY()));
		return lineList;
	}
	
	public Transform getTransform() {
		return this.transform;
	}
	
	public Polygon2f getShape() {
		return this.shape;
	}
}

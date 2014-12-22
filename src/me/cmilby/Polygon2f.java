package me.cmilby;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Polygon2f {

	private List<Vector2f> points;
	
	public Polygon2f() {
		this.points = new ArrayList<Vector2f>();
	}
	
	public Polygon2f(List<Vector2f> points) {
		this.points = points;
	}
	
	public void addPoint(Vector2f point) {
		points.add(point);
	}
	
	public void addPoint(float x, float y) {
		addPoint(new Vector2f(x, y));
	}
	
	public Vector2f getPoint(int index) {
		return points.get(index);
	}
	
	public void setPoint(int index, Vector2f point) {
		points.set(index, point);
	}
	
	public List<Vector2f> getPoints() {
		return this.points;
	}
	
	public int sides() {
		return points.size();
	}
	
	public void renderPolygon(Graphics g) {
		for (int i = 0; i < points.size(); i++) {
			if (i == 0) 
				g.drawLine((int) points.get(points.size() - 1).getX(), (int) points.get(points.size() - 1).getY(), (int) points.get(0).getX(), (int) points.get(0).getY());
			else 
				g.drawLine((int) points.get(i - 1).getX(), (int) points.get(i - 1).getY(), (int) points.get(i).getX(), (int) points.get(i).getY());
		}
	}
	
	public void renderBoundingBox(Graphics g) {
		int minX = (int) Util.minX(points);
		int minY = (int) Util.minY(points);
		g.drawRect(minX, minY, (int) Util.maxX(points) - minX, (int) Util.maxY(points) - minY);
	}
	
	public void clear() {
		points.clear();
	}
}

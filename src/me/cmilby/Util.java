package me.cmilby;

import java.util.List;

public class Util {

	private Util() {
		// Prevents Instantiation
	}
	
	public static int clamp(int value, int max, int min) {
		if (value > max)
			return max;
		if (value < min)
			return min;
		return value;
	}

	public static boolean isPointOffscreen(Vector2f point) {
		return point.getX() < -50 || point.getX() > 650 || point.getY() < -50 || point.getY() > 650;
	}
	
	public static Vector2f midpoint(Vector2f p1, Vector2f p2) {
		return midpoint(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	public static Vector2f midpoint(float x1, float y1, float x2, float y2) {
		return new Vector2f((x2 + x1) / 2.0f, (y2 + y1) / 2.0f);
	}
	
	public static float distance(Vector2f p1, Vector2f p2) {
		return (float) Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
	}
	
	public static Vector2f rotate(double aC, double bC, double a, double b, double angle) {
		angle = Math.toRadians(angle);
		a -= aC;
		b -= bC;
		double[] ans = new double[] { ((a * Math.cos(angle)) - (b * Math.sin(angle))) + aC, 
				((a * Math.sin(angle)) + (b * Math.cos(angle))) + bC };
		return new Vector2f((float) ans[0], (float) ans[1]);
	}
	
	public static int getRandomNumber(int min, int max) {
		return (int)(Math.random() * (max - min + 1) + min);
	}
	
	public static Vector2f getOffscreenPoint() {
		Vector2f point = new Vector2f(300, 300);
		int value = getRandomNumber(1, 4);
		switch (value) {
		case 1:
			// Top 
			point = new Vector2f(getRandomNumber(-50, 650), -50);
			break;
		case 2:
			// Bottom
			point = new Vector2f(getRandomNumber(-50, 650), 650);
			break;
		case 3:
			// Left
			point = new Vector2f(-50, getRandomNumber(-50, 650));
			break;
		case 4:
			// Right
			point = new Vector2f(650, getRandomNumber(-50, 650));
			break;
		}
		return point;
	}
	
	public static float minX(List<Vector2f> points) {
		if (points.size() == 0)
			return 0.0f;
		float minX = points.get(0).getX();
		for (int i = 1; i < points.size(); i++) 
			if (points.get(i).getX() < minX)
				minX = points.get(i).getX();
		return minX;
	}
	
	public static float minY(List<Vector2f> points) {
		if (points.size() == 0)
			return 0.0f;
		float minY = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) 
			if (points.get(i).getY() < minY)
				minY = points.get(i).getY();
		return minY;
	}
	
	public static float maxX(List<Vector2f> points) {
		if (points.size() == 0)
			return 0.0f;
		float maxX = points.get(0).getX();
		for (int i = 1; i < points.size(); i++) 
			if (points.get(i).getX() > maxX)
				maxX = points.get(i).getX();
		return maxX;
	}
	
	public static float maxY(List<Vector2f> points) {
		if (points.size() == 0)
			return 0.0f;
		float maxY = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) 
			if (points.get(i).getY() > maxY)
				maxY = points.get(i).getY();
		return maxY;
	}
}

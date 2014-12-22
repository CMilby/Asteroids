package me.cmilby;

public class Vector2f {

	private float x;
	private float y;
	
	public Vector2f() {
		this.x = this.y = 0;
	}
	
	public Vector2f(Vector2f vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f add(Vector2f vector) {
		return new Vector2f(x + vector.x, y + vector.y);
	}
	
	public Vector2f sub(Vector2f vector) {
		return new Vector2f(x - vector.x, y - vector.y);
	}
	
	public Vector2f mul(float scalar) {
		return new Vector2f(x * scalar, y * scalar);
	}
	
	public Vector2f div(float scalar) {
		return new Vector2f(x / scalar, y / scalar);
	}
	
	public boolean equals(Vector2f vector) {
		return (x == vector.x && y == vector.y);
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
}

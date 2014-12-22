package me.cmilby;

public class Transform {
	
	private Vector2f position;
	private float rotation;
	
	public Transform() {
		this(new Vector2f(), 0.0f);
	}
	
	public Transform(Vector2f position, float rotation) {
		this.position = position;
		this.rotation = rotation;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
	
	public float getRotation() {
		return this.rotation;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
}

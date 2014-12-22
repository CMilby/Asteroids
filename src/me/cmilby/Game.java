package me.cmilby;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Entity root;
	
	public Game() {
		this.root = new Entity();
		
		Input input = Input.getInstance();
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		
		setFocusable(true);
		requestFocus();
	}
	
	public void init() {
		
	}
	
	public void addEntity(Entity entity) {
		root.addChild(entity);
	}
	
	public void handleInput(float delta) {
		root.handleInput(delta);
	}
	
	public void handleUpdate(float delta) {
		root.handleUpdate(delta);
	}
	
	public void handleRender() {
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		root.handleRender(g);
	}
}

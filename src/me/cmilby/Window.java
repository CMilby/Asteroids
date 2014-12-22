package me.cmilby;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;
	
	public Window(String title, int width, int height) {	
		frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addComponent(Component c) {
		frame.add(c);
	}
	
	public void setResizable(boolean resizable) {
		frame.setResizable(resizable);
	}
	
	public void setFullscreen(boolean fullscreen) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		if (fullscreen) {
			frame.setSize((int) tk.getScreenSize().getWidth(), (int) tk.getScreenSize().getHeight()); 
		} else {
			frame.setSize((int) tk.getScreenSize().getWidth() / 2, (int) tk.getScreenSize().getHeight() / 2);
			frame.setLocationRelativeTo(null);
		}
	}
	
	public String getTitle() {
		return frame.getTitle();
	}
	
	public int getWidth() {
		return frame.getContentPane().getWidth();
	}
	
	public int getHeight() {
		return frame.getContentPane().getHeight();
	}
}

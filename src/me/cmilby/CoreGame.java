package me.cmilby;

public class CoreGame {

	public static final boolean DEBUG = true;
	
	private Game game;
	
	private boolean isRunning;
	private float frameTime;
	
	public CoreGame(float frameRate, Window window, Game game) {
		this.game = game;
		this.frameTime = 1.0f / frameRate;
		this.isRunning = false;
		window.addComponent(game);
	}

	public void start() {
		if (isRunning)
			return;
		isRunning = true;
		
		game.init();
		
		double currentTime = Time.getCurrentTime();
		double lastTime = 0.0f;
		double lag = 0.0f;
		double lastSecond = currentTime;
		
		int frames = 0;
		boolean render = false;
		
		while (isRunning) {
			lastTime = currentTime;
			currentTime = Time.getCurrentTime();
			lag += (currentTime - lastTime);
			
			if (currentTime - lastSecond >= 1.0f) {
				lastSecond = currentTime;
				if (DEBUG)
					System.out.println("Frames: " + frames);
				frames = 0;
			}
			
			game.handleInput(frameTime);
			
			while (lag >= frameTime) {
				render = true;
				
				game.handleUpdate(frameTime);
				
				lag -= frameTime;
			}
			
			if (render) {
				frames++;
				
				game.handleRender();
				
				render = false;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void stop() {
		if (!isRunning)
			return;
		isRunning = false;
	}
}

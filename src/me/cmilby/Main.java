package me.cmilby;

public class Main {

	public static void main(String[] args) {
		Game game = new AsteroidsGame();
		Window window = new Window("Asteroids", 600, 600);
		CoreGame core = new CoreGame(60.0f, window, game);
		core.start();
	}
}

package me.cmilby;

import java.awt.Color;

public class AsteroidsGame extends Game {

	private static final long serialVersionUID = 1L;

	public AsteroidsGame() {
		super();
		
		setBackground(Color.BLACK);
	}
	
	@Override
	public void init() {
		addEntity((new Entity()).addComponent(new ShipComponent()));
	
		for (int i = 0; i < 5; i++)
			addEntity((new Entity()).addComponent(new AsteroidComponent(Util.getRandomNumber(1, 3))));	
	}
	
	@Override
	public void handleUpdate(float delta) {
		super.handleUpdate(delta);
		
		/*for (Entity e : getRoot().getAllChildren()) {
			for (EntityComponent comp : e.getAllComponents()) {
				if (comp instanceof ShipComponent) {
					for (Entity e1 : e.getAllChildren()) {
						for (EntityComponent comp1 : e1.getAllComponents()) {
							if (comp1 instanceof AsteroidComponent && comp.getParent().hasCollided(comp1.getParent())) {
								System.out.println("Asteoroid to ship");
							} else if (comp instanceof BulletComponent) {
								System.out.println("Bullet");
							}
						}
					}
				} else if (comp instanceof BulletComponent) {
					System.out.println("Bullet");
					if (Util.isPointOffscreen(comp.getTransform().getPosition())) {
						e.getAllComponents().remove(comp);
						System.out.println("Removed Bullet");
						break;
					}
					
					for (Entity e1 : getRoot().getAllChildren()) {
						for (EntityComponent comp1 : e1.getAllComponents()) {
							if (comp1 instanceof AsteroidComponent && comp.getParent().hasCollided(comp1.getParent())) {
								if (comp1.getShape().sides() <= 12) {
									// Fragments
								} else {
									int num = Util.getRandomNumber(2, 3);
									for (int i = 0; i < num; i++)
										getRoot().addChild((new Entity()).addComponent(new AsteroidComponent((comp1.getShape().sides() <= 18) ? 1 : 2, comp1.getTransform().getPosition())));
									e1.getAllComponents().remove(comp1);
									e.getAllComponents().remove(comp);
								}
							}
						}
					}
				}
			}
		}*/
	}
}

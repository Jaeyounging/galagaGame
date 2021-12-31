import java.awt.Image;

public class AlienSprite extends Sprite {
	private GalagaGame game;
	
	public AlienSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dx = 0;
		dy = 2;
	}
	
	@Override
	public void move() {
		if (y > 800) {
			game.enemyEscape = true;			
		}
		super.move();
	}

}

import java.awt.Image;
// import java.util.concurrent.TimeUnit;

public class StarshipSprite extends Sprite {
	private GalagaGame game;
	
	public StarshipSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);		
		this.game = game;
		dx = 0;
		dy = 0;
	}
	
	@Override
	public void move() {
		if ((dx < 0) && (x < 50)) {
			return;
		}
		
		if ((dx > 0) && (x > 1500)) {
			return;
		}
		
		if ((dy < 0) && (y < 50)) {
			return;
		}
		
		if ((dy > 0) && (y > 750)) {
			return;
		}
		
		super.move();
	}

	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof AlienSprite == true) { // A instanceof B: A객체가 B클래스에 속하는 객체라면 true 반환
			if (game.playerLife > 0) {
				game.playerLife--;
			}
		}
	}
}

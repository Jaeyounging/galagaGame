import java.awt.Image;

public class ShotSprite extends Sprite {
	private GalagaGame game;
	
	// 생성자
	public ShotSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		dx = 0;
		dy = -10;
	}
	
	@Override
	public void move() {
		super.move();
		
		// 미사일이 영역을 벗어나면 제거
		if (y < -100) {
			game.removeSprite(this);
		}
	}
	
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof AlienSprite == true) { // other가 AlienSprite 클래스의 객체라면 
			game.removeSprite(this); // 미사일 제거
			game.removeSprite(other); //적 제거
			game.score += 10;
			game.numberOfEnemy--;
		}
	}

}

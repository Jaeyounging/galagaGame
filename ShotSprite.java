import java.awt.Image;

public class ShotSprite extends Sprite {
	private GalagaGame game;
	
	// ������
	public ShotSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		dx = 0;
		dy = -10;
	}
	
	@Override
	public void move() {
		super.move();
		
		// �̻����� ������ ����� ����
		if (y < -100) {
			game.removeSprite(this);
		}
	}
	
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof AlienSprite == true) { // other�� AlienSprite Ŭ������ ��ü��� 
			game.removeSprite(this); // �̻��� ����
			game.removeSprite(other); //�� ����
			game.score += 10;
			game.numberOfEnemy--;
		}
	}

}

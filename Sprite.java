import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
	protected int x, y;
	protected int dx, dy; // �����ð����� �����̴� x, y�� �Ÿ�
	private Image image;
	
	// ������(image, x, y �� ����)
	public Sprite(Image image, int x, int y) { 
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public int GetWidth() {
		return image.getWidth(null);
	}
	
	public int GetHeight() {
		return image.getHeight(null);
	}
	
	// ��������Ʈ �̹��� ȭ�鿡 �׸���
	public void Draw(Graphics g) { 
		g.drawImage(image, x, y, null);
	}
	
	public void move() {
		x += dx;
		y += dy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	// �浹 ���� Ȯ��(�浹 �� true, �浹 �� ���� �� false)
	public boolean checkCollision(Sprite other) { 
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
		
		myRect.setBounds(x, y, GetWidth(), GetHeight());
		otherRect.setBounds(other.getX(), other.getY(), other.GetWidth(), other.GetHeight());
		
		return myRect.intersects(otherRect);
	}
	
	// �浹 ���� �� ��� ó�� �Ұ���
	public void handleCollision(Sprite ohter) {
		
	}
	
}

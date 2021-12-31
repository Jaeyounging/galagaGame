import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
	protected int x, y;
	protected int dx, dy; // 단위시간마다 움직이는 x, y의 거리
	private Image image;
	
	// 생성자(image, x, y 값 지정)
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
	
	// 스프라이트 이미지 화면에 그리기
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

	// 충돌 여부 확인(충돌 시 true, 충돌 안 했을 때 false)
	public boolean checkCollision(Sprite other) { 
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
		
		myRect.setBounds(x, y, GetWidth(), GetHeight());
		otherRect.setBounds(other.getX(), other.getY(), other.GetWidth(), other.GetHeight());
		
		return myRect.intersects(otherRect);
	}
	
	// 충돌 했을 때 어떻게 처리 할건지
	public void handleCollision(Sprite ohter) {
		
	}
	
}

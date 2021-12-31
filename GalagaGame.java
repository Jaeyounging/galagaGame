import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.util.concurrent.TimeUnit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GalagaGame extends JPanel implements KeyListener {
	
	private boolean gameIsRunningNow = true;
	
	private ArrayList sprites = new ArrayList();
	private Sprite starship, alien, shot;
	
	private BufferedImage alienImage;
	private BufferedImage shotImage;
	private BufferedImage shipImage;
	
	private int playerUnitMove = 10; // 플레이어의 단위 이동 거리
	private int missileLaunchingUnitTime = 5; // 미사일이 나오는 시간
	private int missileTimer = 0;
	
	public int playerLife = 1;
	public boolean enemyEscape = false;
	public int score = 0;
	public int numberOfEnemy = 15;
	public int i, p, s; // 컨트롤 변수

	public GalagaGame() { // 생성자
		JFrame frame = new JFrame("Galaxy Shooting Game");
		
		frame.setSize(1600, 850);
		frame.add(this);
		frame.setResizable(false); 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 이미지 읽기
		try {
			alienImage = ImageIO.read(new File("C:\\Users\\Jaeyoung\\Desktop\\alien.png"));
			shotImage = ImageIO.read(new File("C:\\Users\\Jaeyoung\\Desktop\\bullet.png"));
			shipImage = ImageIO.read(new File("C:\\Users\\Jaeyoung\\Desktop\\spaceship.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.requestFocus(); // 키보드 입력을 받을 수 있게
		addKeyListener(this); // 키보드 입력을 받는 것
		
		this.initSprites(); // 게임화면 초기화 - 플레이어와 적 생성
		
	}
		
	public void initSprites() {
		starship = new StarshipSprite(this, shipImage, 800, 750);
		sprites.add(starship);
			
		for (i = 1; i <= numberOfEnemy; i++) {
			alien = new AlienSprite(this, alienImage, 200 + (i*70), 30);
			sprites.add(alien);
		}
	}
		
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
		
	public void fire() {
		shot = new ShotSprite(this, shotImage, starship.getX()+20, starship.getY()-20);
		sprites.add(shot);
	}
	
	//@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 1600, 850);
		
		for (i = 0; i < sprites.size(); i++) {
			Sprite sprite = (Sprite)sprites.get(i);
			sprite.Draw(g);
		}
		
		// 점수 화면에 보여주기
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Default", Font.BOLD, 25));
		g.drawString("SCORE: " + score, 1400, 100);
		
		// 플레이어의 생명 화면에 보여주기
		g.drawString("LIFE PACK: " + playerLife, 1400, 200);	
		if ((playerLife <= 0) || enemyEscape == true) {
			g.setColor(Color.red);
			g.setFont(new Font("Dafault", Font.BOLD, 100));
			g.drawString("Y O U  F A I L E D", 400, 400);
		}
		
		// 살아있는 적의 수 화면에 보여주기
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Default", Font.BOLD, 25));
		g.drawString("ENEMY : " + numberOfEnemy, 1400, 300);
		if (numberOfEnemy == 0) {
			g.setColor(Color.green);
			g.setFont(new Font("Dafault", Font.BOLD, 100));
			g.drawString("Y O U  W I N", 400, 400);			
		}
	}
	
	public void gameLoop() {
		while (gameIsRunningNow == true) {
			missileTimer++;
			
			for (i = 0; i < sprites.size(); i++) {
				Sprite sprite = (Sprite)sprites.get(i);
				sprite.move();
			}
			
			for (p = 0; p < sprites.size(); p++) {
				for(s = p+1; s < sprites.size(); s++) {
					Sprite me = (Sprite)sprites.get(p);
					Sprite other = (Sprite)sprites.get(s);
					
					if (me.checkCollision(other) == true) {
						me.handleCollision(other);
						other.handleCollision(me);
					}
				}
			}
			
			repaint();
			
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (Exception e) {}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			starship.setDx(-playerUnitMove);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			starship.setDx(playerUnitMove);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			starship.setDy(-playerUnitMove);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			starship.setDy(playerUnitMove);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if ((playerLife > 0) && (missileTimer > missileLaunchingUnitTime)) {
				fire();
				missileTimer = 0;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			starship.setDx(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			starship.setDx(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			starship.setDy(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			starship.setDy(0);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		GalagaGame g = new GalagaGame();
		g.gameLoop();

	}	

}

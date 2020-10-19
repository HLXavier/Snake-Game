import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game implements Runnable, KeyListener{

	private JFrame frame;
	private Canvas canvas;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	
	public int width = 600;
	public int height = 600;
	public static int snakeSize = 25;
	
	Apple apple = new Apple();
	Snake snake = new Snake();
	
	public boolean isRunning;
	
	public Game() {
		initFrame();
		isRunning = true;
		canvas.addKeyListener(this);
		thread = new Thread(this);
		thread.start();
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public void initFrame() {
		frame = new JFrame("Snake");
		frame.setSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void tick() {
		apple.tick();
		snake.tick();
		
		if(snake.x[0] == apple.x && snake.y[0] == apple.y) {
			System.out.println("Ponto :D");
			snake.points+=1;
			apple.ate = true;
		} 

		collision();
	}
	
	public void collision() {
		for(int i = snake.points; i>0; i--) {
			if((snake.x[0] == snake.x[i]) && (snake.y[0] == snake.y[i])) {
				System.out.println("Perdeu ;-;");
				stop();
			}
		}
	}
	
	public void render() {
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		g.setColor(new Color(69, 69, 69));
		for(int i = 0; i<width; i++) {
			g.drawLine(0, i*snakeSize, width, i*snakeSize);
			g.drawLine(i*snakeSize, 0, i*snakeSize, height);
		}
		//Draw
		snake.render(g);
		apple.render(g);
		g.drawString("Pontos: " + (snake.points-6), 265, 100);
		//End Draw
		bs.show();
		g.dispose();
	}
	
	public void stop() {
		isRunning = false;
	}

	@Override
	public void run() {
		while(isRunning) {
			tick();
			render();
			try {
				thread.sleep(1000/8);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			snake.up = true;
			snake.down = false;
			snake.right = false;
			snake.left = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.up = false;
			snake.down = true;
			snake.right = false;
			snake.left = false;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			snake.up = false;
			snake.down = false;
			snake.right = true;
			snake.left = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.up = false;
			snake.down = false;
			snake.right = false;
			snake.left = true;
		}
	}

	public void keyReleased(KeyEvent e) {
	}
	
	
}

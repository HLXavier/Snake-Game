import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple {
	
	public int width = 25;
	public int height = 25;
	public int x;
	public int y;
	
	public boolean ate = true;
	
	public void tick() {
		if(ate) {
			x = new Random().nextInt(24)*25;
			y = new Random().nextInt(24)*25;
			ate = false;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 25, 25);
	}
	
}


import java.awt.Color;
import java.awt.Graphics;

public class Snake {

	public int[] x = new int[24*24];
	public int[] y = new int[24*24];
	public int points = 6;
	
	public boolean up, down, right, left;
	
	public Snake() {
		right = true;
		x[0] = 25;
	}
	
	public void tick() {
		
		for(int i = points; i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
			
			if(up) {
				y[0]-=25;
			}
			if(down) {
				y[0]+=25;
			}
			if(right) {
				x[0]+=25;
			}
			if(left) {
				x[0]-=25;
			}
			
			if(x[0]>600) {
				x[0] = 0;
			}
			if(x[0]<0) {
				x[0] = 600;
			}
			if(y[0]>600) {
				y[0] = 0;
			}
			if(y[0]<0) {
				y[0] = 600;
			}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<points; i++) {
			
			if(i == 0) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], 25, 25);
			} else {
				g.setColor(new Color(45, 180, 0));
				g.fillRect(x[i], y[i], 25, 25);
			}
		}
	}
	
}
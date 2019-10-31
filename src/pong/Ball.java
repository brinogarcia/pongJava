package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	public double x,y;
	public int width, height;
	
	public double dx, dy;
	public double speed = 1.7;
	public String pontoPlayer = "";
	public String pontoInimigo = "";
	public int countInimigo =0;
	public int countPlayer =0;
	
	public Ball(int x, int y) {
		this.x= x;
		this.y = y;
		this.width = 3;
		this.height = 3;
		
		int angle =  new Random().nextInt(120 - 45) + 46;
		
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {

		
		
		if(x+(dx*speed)+ width >= Game.WIDTH ) {
			dx*=-1;
		}else if(x+(dx*speed) < 0) {
			dx*=-1;
		}
		
		if(y >= Game.HEIGHT) {
			countPlayer++;
			dy*=-1;
		
		}else if(y < 0) {
			countInimigo++;
			dy*=-1;
			
		}
		
		Rectangle bounds =  new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)),width,height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y,Game.player.width,Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y, Game.enemy.width,Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle =  new Random().nextInt(120 - 45) + 46;
			
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy >0)
				dy*=-1;
		}else if(bounds.intersects(boundsEnemy)) {
			int angle =  new Random().nextInt(120 - 45) + 46;
			
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy <0)
				dy*=-1;
		}
		
		x+=dx*speed;
		y+=dy*speed;
	}
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval((int)x, (int)y, width, height);
		
		pontoInimigo = String.valueOf(countInimigo);
		pontoPlayer = String.valueOf(countPlayer);
		g.setFont(new Font("Arial",Font.BOLD,16));
		g.setColor(Color.white);
		g.drawString(pontoInimigo, 0, 20);

		g.setFont(new Font("Arial",Font.BOLD,16));
		g.setColor(Color.white);
		g.drawString(pontoPlayer, 0, 100);
	}

}

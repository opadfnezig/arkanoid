package arkanoid;

import acm.graphics.*;
import acm.program.*;
import java.awt.*; 
import java.awt.event.*; 
public class Ball {
	GOval ball;
	public static int ball_speed = 5;
	public static int ball_radius = 20;
	private static int width;
	private static int height;

	public Ball(int x, int y, int ball_radius) {
		ball = new GOval(x, y, ball_radius, ball_radius);
	}

	public GOval getBall() {
		return ball;
	}

	public void ballMoving() {
		while (ball.getX() < height) {
			// smoveBall();
			//checkForCollision();
			//pause(DELAY);
		}
	}
	public void mouseClicked(MouseEvent e) { 
		ball.move(ball_speed, -ball_speed); 
	}

	public void move(int ball_speed) {
		ball.move(ball_speed, -ball_speed);
		
	}
	public void right(int windowWidth) {
		if (ball.getX() <= windowWidth - ball.getWidth())
			ball.move(10, 0);
	}

	public void left() {
		if (ball.getX() >= 0)
			ball.move(-10, 0);
	}

}

package arkanoid;

import java.awt.RenderingHints.Key;
import java.awt.event.*;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Arkanoid extends GraphicsProgram
{
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 800;
	
	private boolean end;
	private boolean pause;
	
	private Ball ball;
	private Board board;
	private Menu menu;
	
	public void run()
	{
		setup();
		addMouseListeners();
		addKeyListeners();
		while(end)
		{
			logic();
		}
	}
	
	public void setup()
	{
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		end = false;
		pause = false;
		
		Level level = new Level(1,WINDOW_WIDTH,WINDOW_HEIGHT);
		add(level,0,0);
		
		//menu = new Menu();
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		if(menu != null)
		{
			if(menu.pressButton(new GPoint(e.getPoint())))
			{
				remove(menu);
				menu = null;
			}
		}
		else
		{
			pause = !pause;
		}
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(menu == null && !pause)
		{
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				board.moveRight();
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
				board.moveLeft();
		}
	}
	
	public void logic()
	{
		if(menu == null && !pause)
		{
			ball.moveBall();
			checkCollsion();
		}
	}
	
	public void checkCollsion()
	{
		if(ball.getX() <= 0)
			ball.hit(true);
		else if(ball.getX()+ball.getWidth() >= WINDOW_WIDTH)
			ball.hit(true);
		if(ball.getY() + ball.getHeight() >= WINDOW_HEIGHT-20 && ball.getX()-ball.getWidth() > board.getX() && ball.getX() < board.getX()+ board.getWidth())
			ball.hit(false);
		GObject collObj = getElementAt(ball.getX(), ball.getY());
		if(collObj.getClass() == Brick.class)
		{
			remove(collObj);
			ball.hit(false);
		}
	}
}

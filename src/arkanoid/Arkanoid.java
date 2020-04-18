package arkanoid;

import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.io.File;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import arkanoid.Bonus.BonusType;

public class Arkanoid extends GraphicsProgram
{
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 800;
	
	private boolean end;
	private boolean pause;
	
	private int ballCount = 3;
	
	private Ball ball;
	private Board board;
	private Menu menu;
	
	private RandomGenerator r_gen;
	
	private Sound s_hit;
	
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
		
		s_hit = new Sound(new File("hit.wav"));
		
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
			checkBonus();
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
			if(r_gen.nextInt()%5 == 0)
				add(new Bonus("", BonusType.BALL), ball.getX(), ball.getY());
			ball.hit(false);
		}
	}
	
	public void checkBonus()
	{
		GObject collObj = getElementAt(board.getX(), board.getY());
	}
}

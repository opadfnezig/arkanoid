package arkanoid;

import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.io.File;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import arkanoid.Bonus.BonusType;

public class Arkanoid extends GraphicsProgram
{
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 820;
	
	private boolean end;
	private boolean pause;
	
	private int ballCount = 3;
	
	private Ball ball;
	private Board board;
	private Menu menu;
	
	private Level lvl = new Level(1,WINDOW_WIDTH,WINDOW_HEIGHT);
	
	private RandomGenerator r_gen;
	
	public void run()
	{
		setup();
		addMouseListeners();
		addKeyListeners();
		add(lvl);
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
		if(ball.getX()+ball.getWidth() >= WINDOW_WIDTH)
			ball.hit(true);
		if(ball.getY() + ball.getHeight() >= WINDOW_HEIGHT-20 && ball.getX()-ball.getWidth() > board.getX() && ball.getX() < board.getX()+ board.getWidth())
			ball.hit(false);
		if(ball.getY() > getHeight()-20)
			remove(ball);
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
		for(int i = 0; i < board.getWidth(); i+=Bonus.WIDTH)
		{
			GObject collObj = this.getElementAt(board.getX()+Bonus.WIDTH*i, board.getY());
			if(collObj.getClass() == Bonus.class)
			{
				Bonus bonus = (Bonus)collObj;
				switch(bonus.getBonusType())
				{
				case BALL:
					ballCount++;
					break;
				case BOARD_EXTENDER:
					board.grow();
					break;
				case BOARD_CONSTRICTER:
					board.decriase();
					break;
				}
				remove(bonus);
			}
		}
		
	}
}

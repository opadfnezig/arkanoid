package arkanoid;


import java.awt.event.*;


import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import arkanoid.Bonus.BonusType;

public class Arkanoid extends GraphicsProgram
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 820;
	
	public static final int BRICK_WIDTH = 100;
	public static final int BRICK_HEIGHT = 40;
	
	private boolean end;
	private boolean pause;
	
	private int bricks;
	private int ballCount;
	
	private Ball ball;
	private Board board;
	private Bonus bonus;
	
	private Menu menu;
	
	private RandomGenerator r_gen;
	
	public void run()
	{
		setup();
		addMouseListeners();
		addKeyListeners();
		while(!end)
		{
			logic();
			this.pause(10);
		}
	}
	
	public void setup()
	{
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		end = false;
		pause = false;
		
		menu = new Menu(new GImage("images/background.jpg"), new GImage("images/logo.png"), new GImage("images/start.png"));
		add(menu, 0 ,0);
		ball = null;
		board = null;
		bonus = null;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		if(menu != null)
		{
			if(menu.pressButton(new GPoint(e.getX(), e.getY())))
			{
				remove(menu);
				menu = null;
				setupLevel(1);
			}
		}
		else
		{
			pause = !pause;
		}
		
	}
	
	public void setupLevel(int lv)
	{
		bricks = 0;
		switch(lv)
		{
		case 1:
			GImage back = new GImage("backgroundlvl.png");
			back.scale(WINDOW_WIDTH/back.getWidth(), WINDOW_HEIGHT/back.getHeight());
			this.add(back, 0, 0);
			for(int i = 0;i<WINDOW_HEIGHT/BRICK_HEIGHT/2;i++)
			{
				for(int j = 0;j<WINDOW_WIDTH/BRICK_WIDTH;j++)
				{
					this.add(new Brick("images/cow.png",BRICK_WIDTH,BRICK_HEIGHT),j*BRICK_WIDTH,i*BRICK_HEIGHT);
					bricks++;
				}
			}
		break;
		}
		
		board = new Board("images/board.png", 100, 10);
		ball = new Ball("images/ball.png", 10, 2, -Math.PI/4);
		add(board, WINDOW_WIDTH/2-board.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight());
		add(ball, WINDOW_WIDTH/2-ball.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight()-ball.getHeight());
		
		ballCount = 3;
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(menu == null && !pause && board != null)
		{
			if(e.getKeyCode() == KeyEvent.VK_RIGHT && board.getX()+board.getWidth() < WINDOW_WIDTH)
				board.moveRight();
			if(e.getKeyCode() == KeyEvent.VK_LEFT && board.getX() > 0)
				board.moveLeft();
		}
	}
	
	public void logic()
	{
		if(menu == null && !pause)
		{
			if(ball != null)
			{
				ball.moveBall();
				checkCollsion();
			}
			//checkBonus();
			checkWinOrLose();
		}
	}
	
	public void checkCollsion()
	{
		if(ball.getX() <= 0)
			ball.hit(true);
		if(ball.getX()+ball.getWidth() >= WINDOW_WIDTH)
			ball.hit(true);
		if(ball.getY() + ball.getHeight() >= WINDOW_HEIGHT-20-board.getHeight() && ball.getX() > board.getX() && ball.getX() < board.getX()+ board.getWidth() && ball.getAngle() < Math.PI)
			ball.hit(false);
		if(ball.getY() > WINDOW_HEIGHT-20-board.getHeight())
			remove(ball);
		if(ball.getY() <= 0)
			ball.hit(false);
		GObject collObj = this.getElementAt(ball.getX(), ball.getY());
		if(collObj != null)
		{
			if(collObj.getClass() == Brick.class)
			{
				remove(collObj);
				if(collObj.getY()+collObj.getHeight() < ball.getY())
					ball.hit(true);
				else
					ball.hit(false);
			}
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
	
	public void checkWinOrLose()
	{
		if(bricks == 0)
		{
			
		}
		if(ballCount < 1)
		{
			
		}
		if(ball == null && ballCount > 0)
		{
			remove(board);
			board = new Board("images/board.png", 100, 10);
			ball = new Ball("images/ball.png", 10, 2, Math.PI/4);
			add(board, WINDOW_WIDTH/2-board.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight());
			add(ball, WINDOW_WIDTH/2-ball.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight()+ball.getHeight());
			ballCount--;
		}
		
			
	}
}

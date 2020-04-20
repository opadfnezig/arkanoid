package arkanoid;


import java.awt.event.*;
import java.util.Random;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.SoundClip;

public class Arkanoid extends GraphicsProgram
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 820;
	
	public static final int BRICK_WIDTH = 100;
	public static final int BRICK_HEIGHT = 40;
	
	//закінчення гри
	private boolean end;
	private boolean pause;
	
	//кількість цеглинок
	private int bricks;
	//кількість життів
	private int ballCount;
	private int level;
	
	private Ball ball;
	private Board board;
	private Bonus bonus;
	
	private Menu menu;
	
	private Random rand;
	
	private GameBar gameBar;
	
	SoundClip clip = new SoundClip();
	
	public void run()
	{
		clip.play();
		setup();
		addMouseListeners();
		addKeyListeners();
		while(!end)
		{
			logic();
			this.pause(5);
		}
	}
	
	public void setup()
	{
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		rand = new Random();
		
		end = false;
		pause = false;
		
		level = 1;
		bricks = 0;
		ballCount = 3;
		
		menu = new Menu(new GImage("images/background.png"), new GImage("images/logo.png"), new GImage("images/start.png"));
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
				setupLevel(level);
			}
		}
		else
			pause = !pause;
		
	}
	
	public void setupLevel(int lv)
	{
		bricks = 0;
		GImage back = new GImage("backgroundlvl.png");
		back.scale(WINDOW_WIDTH/back.getWidth(), WINDOW_HEIGHT/back.getHeight());
		this.add(back, 0, 0);
		switch(lv)
		{
		case 1:
			for(int i = 0;i<WINDOW_HEIGHT/BRICK_HEIGHT/2;i+=2)
			{
				for(int j = 0;j<WINDOW_WIDTH/BRICK_WIDTH;j++)
				{
					this.add(new Brick("images/brick.png",BRICK_WIDTH,BRICK_HEIGHT),j*BRICK_WIDTH,i*BRICK_HEIGHT);
					bricks++;
				}
			}
			break;
		case 2:
			for(int i = 0;i<WINDOW_HEIGHT/BRICK_HEIGHT/2;i++)
			{
				for(int j = 0;j<WINDOW_WIDTH/BRICK_WIDTH;j++)
				{
					this.add(new Brick("images/brick.png",BRICK_WIDTH,BRICK_HEIGHT),j*BRICK_WIDTH,i*BRICK_HEIGHT);
					bricks++;
				}
			}
			break;
		case 3:
			for(int i = 0;i<WINDOW_HEIGHT/BRICK_HEIGHT/2;i++)
			{
				for(int j = 0;j<WINDOW_WIDTH/BRICK_WIDTH;j++)
				{
					if(i%2 == 0)
						this.add(new Brick("images/brick.png",BRICK_WIDTH,BRICK_HEIGHT),j*BRICK_WIDTH,i*BRICK_HEIGHT);
					else
					{
						if(j%2 == 0)
							this.add(new UnbreakableBrick("images/brick-Unbreakable.png",BRICK_WIDTH,BRICK_HEIGHT),j*BRICK_WIDTH,i*BRICK_HEIGHT);
						else
							this.add(new Brick("images/brick.png",BRICK_WIDTH,BRICK_HEIGHT),j*BRICK_WIDTH,i*BRICK_HEIGHT);
					}
					bricks++;
				}
			}
			break;
		}

		board = new Board("images/board.png", 100, 10);
		ball = new Ball("images/ball.png", 10, 1, 2*Math.PI-Math.PI/4);
		add(board, WINDOW_WIDTH/2-board.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight());
		add(ball, WINDOW_WIDTH/2-ball.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight()-ball.getHeight());
		
		gameBar = new GameBar(WINDOW_WIDTH, level);
		gameBar.setBallCount(ballCount);
		add(gameBar,0,WINDOW_HEIGHT-20);
		
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
			if(bonus != null)
			{
				bonus.move();
				checkBonus();
			}
			if(bricks == 0)
				pause(1000);
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
		if(ball.getY() <= 0)
			ball.hit(false);
		GObject colObj = this.getElementAt(ball.getX()+ball.getWidth()/2, ball.getY());
		if(colObj != null)
		{
			if(colObj.getClass() == Brick.class)
			{
				bricks--;
				remove(colObj);
				ball.hit(false);
				if(bonus == null && rand.nextInt(3) == 0)
	            {
					bonus = Bonus.getRandomBonus();
	                 add(bonus, ball.getX(), ball.getY());
	            }
			}
			else if(colObj.getClass() == UnbreakableBrick.class)
				ball.hit(false);
		}
		colObj = this.getElementAt(ball.getX(), ball.getY()+ball.getHeight()/2);
		if(colObj != null)
		{
			if(colObj.getClass() == Brick.class) 
			{
				bricks--;
				remove(colObj);
				ball.hit(true);
				if(bonus == null && rand.nextInt(3) == 0)
	            {
					bonus = Bonus.getRandomBonus();
	                 add(bonus, ball.getX(), ball.getY());
	            }
			}
			else if(colObj.getClass() == UnbreakableBrick.class)
				ball.hit(true);
		}
		colObj = this.getElementAt(ball.getX()+ball.getWidth(), ball.getY()+ball.getHeight()/2);
		if(colObj != null)
		{
			if(colObj.getClass() == Brick.class)
			{
				bricks--;
				remove(colObj);
				ball.hit(true);
				if(bonus == null && rand.nextInt(3) == 0)
	            {
					bonus = Bonus.getRandomBonus();
	                 add(bonus, ball.getX(), ball.getY());
	            }
			}
			else if(colObj.getClass() == UnbreakableBrick.class)
				ball.hit(true);
		}
		colObj = this.getElementAt(ball.getX()+ball.getWidth()/2, ball.getY()+ball.getHeight());
		if(colObj != null)
		{
			if(colObj.getClass() == Brick.class)
			{
				bricks--;
				remove(colObj);
				ball.hit(false);
				if(bonus == null && rand.nextInt(3) == 0)
	            {
					bonus = Bonus.getRandomBonus();
	                 add(bonus, ball.getX(), ball.getY());
	            }
			}
			else if(colObj.getClass() == UnbreakableBrick.class)
				ball.hit(false);
		}
		if(ball.getY() > WINDOW_HEIGHT-20)
		{
			remove(ball);
			ball = null;
		}
	}
	
	public void checkBonus()
	{
		if(bonus.getX()+bonus.getWidth() > board.getX() && bonus.getX() < board.getX()+board.getWidth() && bonus.getY()+bonus.getHeight() > board.getY())
		{
			switch(bonus.getBonusType())
			{
			case BALL:
				ballCount++;
				gameBar.setBallCount(ballCount);
				break;
			case BOARD_EXTENDER:
				board.grow();
				break;
			case BOARD_CONSTRICTER:
				board.decriase();
				break;
			}
			remove(bonus);
			bonus = null;
		}
		else if(bonus.getY() > WINDOW_HEIGHT-20)
		{
			remove(bonus);
			bonus = null;
		}
	}
	
	public void checkWinOrLose()
	{
		if(ball == null)
		{
			if(ballCount > 0)
			{
				remove(board);
				board = new Board("images/board.png", 100, 10);
				ball = new Ball("images/ball.png", 10, 1, 2*Math.PI-Math.PI/4);
				add(board, WINDOW_WIDTH/2-board.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight());
				add(ball, WINDOW_WIDTH/2-ball.getWidth()/2, WINDOW_HEIGHT-20-board.getHeight()-ball.getHeight());;
				ballCount--;
				gameBar.setBallCount(ballCount);
			}
			else
			{
				this.removeAll();
				menu = new Menu(new GImage("images/backgroundLose.jpg"), new GImage("images/logoLose.png"), new GImage("images/restart.png"));
				add(menu, 0 ,0);
			}	
		}
		if(bricks == 0)
		{
			if(level > 2)
			{
				end = true;
				removeAll();
				GImage theEnd = new GImage("theEnd.png");
				theEnd.scale(WINDOW_WIDTH/theEnd.getWidth(), WINDOW_HEIGHT/theEnd.getHeight());
				add(theEnd, 0, 0);
			}
			else
			{
				this.removeAll();
				menu = new Menu(new GImage("images/backgroundNext.png"), new GImage("images/logoNext.png"), new GImage("images/next.png"));
				add(menu, 0 ,0);
				level++;
			}
		}
	}
}

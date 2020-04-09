package arkanoid;

import java.awt.RenderingHints.Key;
import java.awt.event.*;

import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Arkanoid extends GraphicsProgram
{
	public static int width;
	public static int height;
	private static int brickCount; 
	private static int xSpacing;
	private static int ySpacing;
	private static int spacing;
	
	private static int brickLineLengh = 10;
	float lvl = 1;
	private static Brick bricks[];
	
	private static Board board;
	
	private static boolean ballStart = false;
	private static Ball ball;
	public void run()
	{
		levelSetup();
		addMouseListeners();
		add(new Lvl1Bricks(width,100,40));
		//render();
		
	}
	
	private void render()
	{
		while(true)
		{
			if (ballStart)
				ball.move(Ball.ball_speed); 
			pause(16);
			
		}
	}

	public void init()
	{
		addMouseListeners();
		addKeyListeners();
		width = 1000;
		height = 800;
		this.setSize(width,height);
		//bricks
		Brick.setWidth(50);
		Brick.setHeight(20);
		brickCount = 40;
		xSpacing = Brick.getWidth() + spacing;
		ySpacing = Brick.getHeight() + spacing;
		spacing = 10;
		bricks = new Brick[brickCount];
		board = new Board(0,height-30,Board.boardWidth,Board.boardHeight);
		ball = new Ball(Board.boardWidth/2 - Ball.ball_radius/2,height-30-Board.boardHeight, Ball.ball_radius);
		add(board.getBoard());
		add(ball.getBall());
	}
	
	private void levelSetup()
	{
		
				
	}
	//метод провірки на колізію. Повертає Brick в випадку зіткнення, якщо зіткнення немає повертає null
	public Brick checkForCollision(int x,int y)
	{
		for(int i = 0;i<bricks.length;++i)
			if((bricks[i]!=null)&&bricks[i].ifPointInBrick(x,y)&&(bricks[i].isVisible()))
				return bricks[i];
		return null;
	}
	public void mouseClicked(MouseEvent arg0)
	{
		ballStart = true;
		for(int i = 0;i<bricks.length;++i)
			if(bricks[i].ifPointInBrick(arg0.getX(), arg0.getY()))
				bricks[i].setVisible(false);
	}
	
	public void keyPressed(KeyEvent arg0)
	{
		switch(arg0.getKeyCode())
		{
		case KeyEvent.VK_RIGHT:
			board.right(width);
			if(ballStart == false) 
				ball.right(width);
			break;
		case KeyEvent.VK_LEFT:
			board.left();
			if(ballStart == false)
				ball.left();
			break;
		}
        
	}
	
	private void placeBricksLvl1()
	{
		for(int  y = 1,bricksPlaced = 0;(y < brickCount/brickLineLengh+1)&&(brickCount - bricksPlaced != 0);++y)
			for(int x = 0;(x<brickLineLengh+1)&&(brickCount - bricksPlaced != 0);++x, ++bricksPlaced)
			{
				bricks[bricksPlaced] = new Brick(new GPoint(x*xSpacing,y*ySpacing));
				add(bricks[bricksPlaced].getGRect());
			}
		
	}
}

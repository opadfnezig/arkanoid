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
	private static Brick bricks[];
	
	private static UserInput inputListener;
	private static Board board;
	public void run()
	{
		levelSetup();
		
		//render();
	}
	
	private void render()
	{
		while(true)
		{
			
			pause(16);
			
		}
	}

	public void init()
	{
		//listeners
		//inputListener = new UserInput();
		//addMouseListener(inputListener);
		//addKeyListener(inputListener);
		addMouseListeners();
		addKeyListeners();
		//window
		width = 1000;
		height = 500;
		this.setSize(width,height);
		//bricks
		Brick.setWidth(50);
		Brick.setHeight(20);
		brickCount = 40;
		xSpacing = Brick.getWidth() + spacing;
		ySpacing = Brick.getHeight() + spacing;
		spacing = 10;
		bricks = new Brick[brickCount];
		board = new Board(0,height-30,100,20);
		add(board.getBoard());
	}
	
	private void levelSetup()
	{
		for(int  y = 1,bricksPlaced = 0;(y < brickCount/brickLineLengh+1)&&(brickCount - bricksPlaced != 0);++y)
			for(int x = 0;(x<brickLineLengh+1)&&(brickCount - bricksPlaced != 0);++x, ++bricksPlaced)
			{
				bricks[bricksPlaced] = new Brick(new GPoint(x*xSpacing,y*ySpacing));
				add(bricks[bricksPlaced].getGRect());
			}
				
	}
	//метод провірки на колізію. Повертає Brick в випадку зіткнення, якщо зіткнення немає повертає null
	public Brick checkForCollision(int x,int y)
	{
		for(int i = 0;i<bricks.length;++i)
			if(bricks[i].ifPointInBrick(x,y)&&(bricks[i].isVisible()))
				return bricks[i];
		return null;
	}
	public void mouseClicked(MouseEvent arg0)
	{
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
			break;
		case KeyEvent.VK_LEFT:
			board.left();
			break;
		}
        
	}
	
}

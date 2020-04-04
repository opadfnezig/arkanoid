package arcanoid;

import java.awt.event.*;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Arcanoid extends GraphicsProgram implements MouseListener
{
	public static int width;
	public static int height;
	private static int brickCount; 
	
	private static int xSpacing;
	private static int ySpacing;
	
	
	private static int brickLineLengh = 10;
	private static Brick bricks[];
	
	public void run()
	{
		setup();
		init();
		levelSetup();
		
		//render();
	}
	
	public void init()
	{
		addMouseListeners();
	}
	
	private void render()
	{
		while(true)
		{
			
			pause(16);
			
		}
	}

	private void setup() 
	{
		width = 1000;
		height = 500;
		Brick.setWidth(50);
		Brick.setHeight(20);
		this.setSize(width,height);
		brickCount = 15;
		xSpacing = ySpacing = 10;
		
		bricks = new Brick[15];
	}
	
	private void levelSetup()
	{
		/*Brick brick = new Brick(new GPoint(50,50));
		add(brick.getGRect());*/
		//for(int y = ySpacing;y < (brickCount/brickLineLengh+1)*ySpacing;y += ySpacing)
			for(int x = xSpacing,bricksPlaced = 0;(x<brickLineLengh*xSpacing+1)&&(brickCount - bricksPlaced != 0);x += xSpacing+Brick.getWidth(), ++bricksPlaced)
			{
				bricks[bricksPlaced] = new Brick(new GPoint(x,xSpacing));
				add(bricks[bricksPlaced].getGRect());
			}
				
	}
	
	public static void onClick(int x,int y)
	{
		
	}
	
	public void mouseClicked(MouseEvent arg0)
	{
		for(int i = 0;i<bricks.length;++i)
			if(bricks[i].ifPointInBrick(arg0.getX(), arg0.getY()))
				bricks[i].setVisible(false);
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}

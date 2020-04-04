package arcanoid;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Arcanoid extends GraphicsProgram
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
		for(int y = ySpacing,bricksPlaced = 0;y < (brickCount/brickLineLengh+1)*ySpacing;y += ySpacing)
			for(int x = xSpacing;(x<brickLineLengh*xSpacing+1)&&(brickCount - bricksPlaced != 0);x += xSpacing, ++bricksPlaced)
			{
				bricks[bricksPlaced] = new Brick(new GPoint(x,y));
				add(bricks[bricksPlaced].getGRect());
			}
				
	}
	
	public static void onClick(int x,int y)
	{
		for(int i = 0;i<bricks.length;++i)
			if(bricks[i].ifPointInBrick(x, y))
				bricks[i].setVisible(false);
	}		
}

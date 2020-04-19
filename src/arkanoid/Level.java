package arkanoid;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Level extends GCompound
{
	public static final int BRICK_WIDTH = 100;
	public static final int BRICK_HEIGHT = 20;
	public int bricks;
	
	public Level (int lvlNumber,int wWidth,int wHeight)
	{
		bricks = 0;
		switch(lvlNumber)
		{
		case 1:
			GImage back = new GImage("backgroundlvl.png");
			back.scale(wWidth/back.getWidth(), wHeight/back.getHeight());
			this.add(back, 0, 0);
			for(int i = 0;i<wHeight/BRICK_HEIGHT/2;i++)
			{
				for(int j = 0;j<wWidth/BRICK_WIDTH;j++)
				{
					this.add(new Brick("images/cow.png",BRICK_WIDTH,BRICK_HEIGHT),j*BRICK_WIDTH,i*BRICK_HEIGHT);
					bricks++;
				}
			}
		break;
		}
	}
	
	public int getBricks()
	{
		return bricks;
	}
	
	
}

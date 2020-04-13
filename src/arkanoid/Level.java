package arkanoid;

import acm.graphics.GCompound;

public class Level extends GCompound
{
	int brickHeight, brickWidth;
	Level (int lvlNumber,int wWidth,int wHeight)
	{
		switch(lvlNumber)
		{
		case 1:
			brickWidth = 100;
			brickHeight = 40;
			for(int i = 0;i<wHeight/brickHeight/2;++i)
				for(int j = 0;j<wWidth/brickWidth;++j)
					this.add(new Brick("images/cow.png",brickWidth,brickHeight),j*brickWidth,i*brickHeight);
		break;
		}
	}
	
	
}

package arkanoid;

import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Lvl1Bricks extends GCompound
{
	int wHeight, wWidth, brickHeight, brickWidth;

	public Lvl1Bricks(int wWidth, int brickWidth, int brickHeight)
	{
		for (int i = 0; i < 400; i += brickHeight)
			for (int j = 0; j < wWidth; j += brickWidth)
				this.add(new GRect(j, i, brickWidth, brickHeight));
	}
}

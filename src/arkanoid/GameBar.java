package arkanoid;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;

public class GameBar extends GCompound
{
	int ballCount;
	
	public GameBar(int wWidth)
	{
		add(new GLine(0,0,wWidth,0));
		add(new GImage("*/images/ball.png"),0,0);
		add(new GLabel("x " + ballCount,20, 0));
	}
	
	
}

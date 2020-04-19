package arkanoid;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;

public class GameBar extends GCompound
{
	int ballCount = 0;
	GLabel label;
	public void setBallCount(int ballCount) 
	{
		this.ballCount = ballCount;
		label.setLabel("x " + ballCount);
		
	}

	public GameBar(int wWidth)
	{
		label = new GLabel("x " + ballCount,25, 15);
		GRect rect = new GRect(0,0,wWidth,20);
		rect.setFillColor(Color.white);
		rect.setFilled(true);
		add(rect);
		add(new GLine(0,0,wWidth,0));
		add(new GImage("ball.png"),0,0);
		add(label);
	}
	
	
}

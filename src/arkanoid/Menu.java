package arkanoid;

import java.util.prefs.BackingStoreException;

import acm.graphics.*;

import java.awt.event.*;

public class Menu extends GCompound {
	
	private GImage startButton, logotype, background;
	
	public Menu(GImage back, GImage log, GImage strBut)
	{
		background = back;
		logotype = log;
		startButton = strBut;
		add(background, 0, 0);
		add(logotype, background.getWidth()/2 - logotype.getWidth()/2, background.getHeight()/2-logotype.getHeight());
		add(startButton, background.getWidth()/2 - startButton.getWidth()/2, background.getHeight()/2+startButton.getHeight()+10);
	}
	
	public boolean pressButton(GPoint point)
	{
		return startButton.contains(point);
	}
}

package arkanoid;

import java.util.prefs.BackingStoreException;

import acm.graphics.*;

import java.awt.event.*;
/**
 * клас реалізує меню
 * @author ThorIgor
 *
 */
public class Menu extends GCompound {
	
	private GImage startButton, logotype, background;
	/**
	 * 
	 * @param back фон
	 * @param log логотип
	 * @param strBut кнопка
	 */
	public Menu(GImage back, GImage log, GImage strBut)
	{
		background = back;
		background.scale(Arkanoid.WINDOW_WIDTH/back.getWidth(), Arkanoid.WINDOW_HEIGHT/back.getHeight());
		logotype = log;
		logotype.scale(Arkanoid.WINDOW_WIDTH/2/log.getWidth(), Arkanoid.WINDOW_HEIGHT/4/log.getHeight());
		startButton = strBut;
		startButton.scale(Arkanoid.WINDOW_WIDTH/4/strBut.getWidth(), Arkanoid.WINDOW_HEIGHT/8/strBut.getHeight());
		add(background, 0, 0);
		add(logotype, background.getWidth()/2 - logotype.getWidth()/2, background.getHeight()/2-logotype.getHeight());
		add(startButton, background.getWidth()/2 - startButton.getWidth()/2, background.getHeight()/2+startButton.getHeight()+10);
	}
	/**
	 * 
	 * @param point точка нажаття миші
	 * @return чи попав гравець на кнопку
	 */
	public boolean pressButton(GPoint point)
	{
		return startButton.contains(point);
	}
}

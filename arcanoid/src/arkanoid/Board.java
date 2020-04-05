package arkanoid;


import java.awt.Event.*;

import acm.graphics.GRect;

public class Board
{
	int boardWidth;
	int boardHeight;
	GRect rect;
	public Board(int x,int y,int boardWidth,int boardHeight)  { rect = new GRect(x,y,boardWidth,boardHeight); }
	
	public GRect getBoard() {return rect;}
	public void right(int windowWidth)
	{
		if(rect.getX() <= windowWidth-rect.getWidth())
		rect.move(10,0);
	}
	
	public void left()
	{
		if(rect.getX() >= 0)
			rect.move(-10,0);
	}
}

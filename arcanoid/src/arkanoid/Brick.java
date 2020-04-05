package arkanoid;

import acm.graphics.GPoint;
import acm.graphics.GRect;

public class Brick
{
	private static int width;
	private static int height;
	private GRect rect;
	public Brick(GPoint centerCoords) 
	{
		rect = new GRect(centerCoords.getX(),centerCoords.getY(),width,height); 
	}
	
	public void setVisible(boolean value) { rect.setVisible(value); }	
	public boolean isVisible() { return rect.isVisible(); }
	
	public GRect getGRect() { return rect; } 
	
	public static int getWidth() { return width; }
	public static int getHeight() { return height; }
	public static void setWidth(int width_) { width = width_; }
	public static void setHeight(int height_) { height = height_; }
	
	
	
	public boolean ifPointInBrick(int x,int y)
	{
		if(rect.contains(x,y))
			return true;
		return false;
	}
	
}

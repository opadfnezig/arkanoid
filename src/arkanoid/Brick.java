package arkanoid;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GRect;
/**
 * клас реалізує цеглинки
 * @author opadfnezig
 */
public class Brick extends GImage
{
	private int width;
	private int height;
	/**
	 * @param path шлях до зображення
	 * @param width ширина об'єкту
	 * @param height висота об'єкту
	 */
	public Brick(String path, int width,int height)
	{
		super(path);
		this.scale(width/this.getWidth(), height/this.getHeight());
		this.width = width;
		this.height = height;
	}
	
}

package arkanoid;
/**
 * клас реалізує підвид цеглинок, цеглинки які неможливо знищити
 * @author opadfnezig
 *
 */
public class UnbreakableBrick extends Brick
{
	/**
	 * 
	 * @param path шлях до картинки
	 * @param width ширина об'єкту
	 * @param height висота об'єкту
	 */
	public UnbreakableBrick(String path, int width,int height)
	{
		super(path,width,height);
	}
}

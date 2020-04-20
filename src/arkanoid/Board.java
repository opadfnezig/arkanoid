package arkanoid;

import acm.graphics.*;
/**
 * ���� ������ ����� ���� ���� �������
 * @author ThorIgor
 */
public class Board extends GImage{
	//���������� �����
	public static final double GROW_K = 1.3;
	
	private int width;
	private int height;
	
	private double speed;
	/**
	 * 
	 * @param path ���� �� ��������
	 * @param w	������
	 * @param h	������
	 */
	public Board (String path, int w, int h)
	{
		super(path);
		this.scale(w/this.getWidth(), h/this.getHeight());
		width = w;
		height = h;
		speed = 10;
	}
	
	public void moveRight()
	{
		this.move(speed, 0);
	}
	
	public void moveLeft()
	{
		this.move(-speed, 0);
	}
	
	public void grow()
	{
		this.scale(GROW_K, 1);
	}
	
	public void decrease()
	{
		this.scale((double)1/GROW_K, 1);
	}
}

package arkanoid;

import java.awt.Event.*;

import acm.graphics.*;

public class Board extends GImage{
	public static final double GROW_K = 1.5;
	
	private int width;
	private int height;
	
	public Board (String path, int w, int h)
	{
		super(path);
		this.scale(w/this.getWidth(), h/this.getHeight());
		width = w;
		height = h;
	}
	
	public void grow()
	{
		this.scale(GROW_K, 1);
	}
	
	public void decriase()
	{
		this.scale((double)2/3, 1);
	}
}

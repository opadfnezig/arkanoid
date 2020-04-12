package arkanoid;

import java.awt.RenderingHints.Key;
import java.awt.event.*;

import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Arkanoid extends GraphicsProgram
{
	private boolean end;
	
	private Ball ball;
	private Board board;
	private Menu menu;
	
	public void run()
	{
		setup();
		while(end)
		{
			draw();
			input();
			logic();
		}
	}
	
	public void setup()
	{
		
	}
	
	public void draw()
	{
		
	}
	
	public void input()
	{
		
	}
	
	public void logic()
	{
		
	}
}

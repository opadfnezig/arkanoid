package arkanoid;

import acm.graphics.GImage;
import acm.graphics.GPoint;

public class Bonus extends GImage
{
	public static enum BonusType{
		BOARD_EXTENDER,
		BOARD_CONSTRICTER,
		BALL
	}
	private static final GPoint moveVector = new GPoint(0,1);
	private BonusType type;
	private static int moveSpeed;
	
	public Bonus(String path,Bonus.BonusType type) 
	{
		super(path); 
		this.type = type;
	}
	
	public void move() { this.move(moveVector.getX()*moveSpeed,moveVector.getY()*moveSpeed); }
}

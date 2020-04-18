package arkanoid;

import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GPoint;

public class Bonus extends GImage
{	
	public static enum BonusType{
		BALL,
		BOARD_EXTENDER,
		BOARD_CONSTRICTER
	}
	private static final GPoint moveVector = new GPoint(0,1);
	private BonusType type;
	private static int moveSpeed;
	
	
	public Bonus(String path,Bonus.BonusType type) 
	{
		super(path); 
		this.type = type;
	}

	public static Bonus getRandomBonus()
	{
		Random rand = new Random();
		return new Bonus("",intToEnumConverter(rand.nextInt(3)));
	}
	
	private static BonusType intToEnumConverter(int i)
	{
		switch(i)
		{
			case 0:
				return BonusType.BALL;
			case 1:
				return BonusType.BOARD_EXTENDER;
			case 2:
				return BonusType.BOARD_CONSTRICTER;
		}
		return null;
	}
	
	public void move() { this.move(moveVector.getX()*moveSpeed,moveVector.getY()*moveSpeed); }
}

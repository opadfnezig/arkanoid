package arkanoid;

import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GPoint;

public class Bonus extends GImage
{
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	private static final int MOVE_SPEED = 5;
	
	public static enum BonusType{
		BALL,
		BOARD_EXTENDER,
		BOARD_CONSTRICTER
	}
	private static final GPoint moveVector = new GPoint(0,1);
	private BonusType type;
	
	
	
	public Bonus(String path,Bonus.BonusType type) 
	{
		super(path); 
		this.type = type;
	}

	public static Bonus getRandomBonus()
	{
		Random rand = new Random();
		switch(rand.nextInt()%3)
		{
		case 0:
			return new Bonus("Ball-Bonus.png", BonusType.BALL);
		case 1:
			return new Bonus("Extender-Bonus.png", BonusType.BOARD_EXTENDER);
		case 2:
			return new Bonus("Constricter-Bonus.png", BonusType.BOARD_CONSTRICTER);
		}
		return null;
		
	}
	
	public BonusType getBonusType() { return type; }
	
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
	
	public void move() { this.move(moveVector.getX()*MOVE_SPEED,moveVector.getY()*MOVE_SPEED); }
}

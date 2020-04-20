package arkanoid;

import acm.graphics.*;
/**
 * клас реал≥зуЇ м'€ч 
 * @author ThorIgor
 *
 */
public class Ball extends GImage{
	private double speed, angle;
	private double radius;
	
	//startAngle in radians
	/**
	 * 
 	* @param path шл€х до картинки
 	* @param r рад≥ус
 	* @param startSpeed швитк≥сть
 	* @param startAngle кут
 	*/
	public Ball(String path, double r, double startSpeed, double startAngle)
	{
		super(path);
		this.scale(r/this.getWidth(), r/this.getHeight());
		radius = r;
		speed = startSpeed;
		angle = startAngle;
	}
	
	public void moveBall()
	{
		this.move(speed*Math.cos(angle), speed*Math.sin(angle));
		speed+=0.0001;
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	/**
	 * метод м≥н€Ї кут руху м'€ча залежно в≥д того в €ку повехню в≥н вдар€Їтьс€ та €кий кут в≥н маЇ в цей момент
	 * @param vertical в €ку поверхню вдар€Їтьс€ м'€ч
	 */
	public void hit(boolean vertical)
	{
		if(vertical)
		{
			if(angle >= 0 && angle <= Math.PI/2)
				angle = Math.PI - angle;
			else if(angle >= Math.PI/2 && angle <= Math.PI)
				angle = Math.PI - angle;
			else if(angle >= Math.PI && angle <= 3*Math.PI/2)
				angle+=2*(3*Math.PI/2 - angle); 
			else
				angle = (2*Math.PI - angle) + Math.PI;
		}
		else
		{
			if(angle >= 0 && angle <= Math.PI/2)
				angle = 2*Math.PI - angle;
			else if(angle >= Math.PI/2 && angle <= Math.PI)
				angle+=2*(Math.PI - angle);
			else if(angle >= Math.PI && angle <= 3*Math.PI/2)
				angle = (3*Math.PI/2 - angle) + Math.PI/2;
			else
				angle = 2*Math.PI - angle;
		}
	}
}

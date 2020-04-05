package arkanoid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserInput implements KeyListener, MouseListener
{
	public void mouseClicked(MouseEvent arg0)
	{
		
		
		/*for(int i = 0;i<bricks.length;++i)
			if(bricks[i].ifPointInBrick(arg0.getX(), arg0.getY()))
				bricks[i].setVisible(false);*/
	}
	
	public void keyPressed(KeyEvent arg0)
	{
		int key = arg0.getKeyCode();
        System.out.println(key);
        
		/*bricks[0].setVisible(false);
		int key = arg0.getKeyCode();
		switch(key)
		{
		case KeyEvent.VK_RIGHT:
			
		break;
		}	*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		System.out.println("mouse press");
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
}

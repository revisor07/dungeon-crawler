// Creates VisibleObject that has x, y position, and a rectangle
import java.awt.*;
abstract public class VisibleObject
{
	private int x;
	private int y;
	protected Rectangle rect;
	
	abstract public void act();
	
	// none
	// returns rect
	public Rectangle getRect()
	{
		return rect;
	}
	
	// x >= 0
	// sets x to given value
	public void setX(int x)
	{
		this.x = x;
	}
	
	// y >= 0
	// sets y to given value
	public void setY(int y)
	{
		this.y = y;
	}
	
	// none
	// returnes x
	public int getX()
	{
		return x;
	}
	
	// none
	// returns y
	public int getY()
	{
		return y;
	}
}

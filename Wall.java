// Creates wall that is a type of VisibleObject
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Wall extends VisibleObject
{
	private BufferedImage wallImg;
	private Canvas cnvs;
	private Graphics2D g;
	private int size;
	
	public Wall(Game game, int size, int x, int y)
	{
		try
		{
			wallImg = ImageIO.read(new File("images/wall.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}	
		this.size = size;
		setX(x);
		setY(y);
		cnvs = game.getCanvas();
		g = game.getGraphics();
		rect = new Rectangle(x, y, size, size);
		game.addRect(rect);
		game.addObject(this);
		g.drawImage(wallImg, getX(), getY(), size, size, cnvs);
	}
	
	// none
	// redraws wall
	public void act()
	{
		g.clearRect(getX(), getY(), size, size);
		g.drawImage(wallImg, getX(), getY(), size, size, cnvs);
	}
}

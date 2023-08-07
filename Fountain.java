// Creates Fountain that is a type of VisibleObject
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Fountain extends VisibleObject
{
	private BufferedImage fountainImg;
	private Game game;
	private Canvas cnvs;
	private Graphics2D g;
	private Player player;
	private int heal = 1;
	private BufferedImage playerImg;
	private int size = 80;
	
	public Fountain(Game game, int x, int y)
	{
		try
		{
			fountainImg = ImageIO.read(new File("images/fountain.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}		
		setX(x);
		setY(y);
		this.game = game;
		player = game.getPlayer();
		playerImg = player.getPlayerImg();
		cnvs = game.getCanvas();
		g = game.getGraphics();
		rect = new Rectangle(getX(),getY(), size, size);
		game.addRect(rect);
		game.addObject(this);
		g.drawImage(fountainImg, getX(), getY(), size, size, cnvs);
	}
	
	// redraws fountain and heals player if close enought
	public void act()
	{
		g.clearRect(getX(), getY(), fountainImg.getWidth(), fountainImg.getHeight());
		g.drawImage(fountainImg, getX(), getY(), size, size, cnvs);
		if(game.distance(rect, new Rectangle(player.getX(), player.getY(), playerImg.getWidth(), playerImg.getHeight())) <= game.getInteractDist())
		{
			if(player.getHp()+heal <= player.getMaxHp())
				player.setHp(player.getHp()+heal);
			else
				player.setHp(player.getMaxHp());
		}	
	}
}

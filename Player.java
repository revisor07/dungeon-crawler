// Creates player controlled by keyboard
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.lang.*; 

public class Player extends LivingBeing implements KeyListener
{
	private BufferedImage playerStillImg;
	private BufferedImage playerWalkImg;
	private BufferedImage playerAttack1Img;
	private BufferedImage playerAttack2Img;
	private BufferedImage playerAttack3Img;
	private Canvas cnvs;
	private BufferStrategy strategy;
	private Graphics2D g;
	private int step;
	private Game game;

	public Player(Game game, int x, int y)
	{
		setMaxHp(100);
		setHp(getMaxHp());
		setDmg(30);
		setDef(10);
		setX(x);
		setY(y);
		this.game = game;
		step = game.getStep();
		cnvs = game.getCanvas();
		strategy = game.getStrategy();
		g = game.getGraphics();
		try
		{
			playerStillImg = ImageIO.read(new File("images/playerStill.png"));
			playerWalkImg = ImageIO.read(new File("images/playerWalk.png"));
			playerAttack1Img = ImageIO.read(new File("images/playerAttack1.png"));
			playerAttack2Img = ImageIO.read(new File("images/playerAttack2.png"));
			playerAttack3Img = ImageIO.read(new File("images/playerAttack3.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}	
		g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
		strategy.show();		
	}
	
	// none
	// redraws player
	public void act()
	{
		g.clearRect(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight());
		g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
	}
	
	// x,y >= 0
	// moves player to different location
	public void move(int x, int y)
	{
		setX(x);
		setY(y);
		g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
	}
	
	// none
	// returns playerStillImg
	public BufferedImage getPlayerImg()
	{
		return playerStillImg;
	}
	
	// none
	// moves player or lets him attack depending on keyboard keys
	public void keyPressed(KeyEvent e)
	{	
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			try
			{
				g.clearRect(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight());
				setY(getY()-step);
				// can not go through the obstacle
				for(Rectangle obstacle: game.getRectangles())
					if(obstacle.intersects(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight()))
						setY(getY()+step);
				g.drawImage(playerWalkImg, getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(60);
				g.clearRect(getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight());
				g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
			
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			try
			{	
				g.clearRect(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight());
				setY(getY()+step);
				// can not go through the obstacle
				for(Rectangle obstacle: game.getRectangles())
					if(obstacle.intersects(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight()))
						setY(getY()-step);
				g.drawImage(playerWalkImg, getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(60);
				g.clearRect(getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight());
				g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			try
			{
				g.clearRect(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight());
				setX(getX()-step);
				// can not go through the obstacle
				for(Rectangle obstacle: game.getRectangles())
					if(obstacle.intersects(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight()))
						setX(getX()+step);
				g.drawImage(playerWalkImg, getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(60);
				g.clearRect(getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight());
				g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			try
			{
				g.clearRect(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight());
				setX(getX()+step);
				// can not go through the obstacle
				for(Rectangle obstacle: game.getRectangles())
					if(obstacle.intersects(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight()))
						setX(getX()-step);
				g.drawImage(playerWalkImg, getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(60);
				g.clearRect(getX(), getY(), playerWalkImg.getWidth(), playerWalkImg.getHeight());
				g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
				strategy.show();
				Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			try
			{
			    g.clearRect(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight());
	   			g.drawImage(playerAttack1Img, getX(), getY(), playerAttack1Img.getWidth(), playerAttack1Img.getHeight(), cnvs);
	   			strategy.show();
	   			Thread.sleep(80);
	   			
	   			g.clearRect(getX(), getY(), playerAttack1Img.getWidth(), playerAttack1Img.getHeight());
	   			g.drawImage(playerAttack2Img, getX(), getY(), playerAttack2Img.getWidth(), playerAttack2Img.getHeight(), cnvs);
	   			strategy.show();
	   			Thread.sleep(80);
	   			
	   			g.clearRect(getX(), getY(), playerAttack2Img.getWidth(), playerAttack2Img.getHeight());
	   			g.drawImage(playerAttack3Img, getX(), getY(), playerAttack3Img.getWidth(), playerAttack3Img.getHeight(), cnvs);
	   			strategy.show();
	   			// hit enemies
	   			for(Enemy enemy: game.getEnemies())
	   				if(game.distance(enemy.getRect(), new Rectangle(getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight())) <= game.getInteractDist())
	   					if (getDmg() > enemy.getDef())
	   						enemy.setHp(enemy.getHp()+enemy.getDef()-getDmg()); 
	   			Thread.sleep(80);
	   			// return to normal
	   			g.clearRect(getX(), getY(), playerAttack3Img.getWidth(), playerAttack3Img.getHeight());
	   			g.drawImage(playerStillImg, getX(), getY(), playerStillImg.getWidth(), playerStillImg.getHeight(), cnvs);
	   			strategy.show();
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
		// cheat
		else if (e.getKeyCode() == KeyEvent.VK_C)
		{
			setDef(1000);
			setDmg(1000);
		}	
		game.update();
	}
	
	// none
	// none
    public void keyReleased(KeyEvent e)
    {
    }
    
    // none
    // none
    public void keyTyped(KeyEvent e)
    {
    }
}

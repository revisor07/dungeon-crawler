// Creates skeleton that is a type of enemy
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.lang.*;

public class Skeleton extends Enemy
{	
	public Skeleton(Game game, int x, int y, Item item)
	{
		setMaxHp(100);
		setHp(getMaxHp());
		setDmg(20);
		setDef(5);
		setX(x);
		setY(y);
		try
		{
			enemyStillImg = ImageIO.read(new File("images/skeletonStill.png"));
			enemyAttack1Img = ImageIO.read(new File("images/skeletonAttack1.png"));
			enemyAttack2Img = ImageIO.read(new File("images/skeletonAttack2.png"));
			enemyAttack3Img = ImageIO.read(new File("images/skeletonAttack3.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		this.game = game;
		rect = new Rectangle(getX(),getY(),enemyStillImg.getWidth(), enemyStillImg.getHeight());
		game.addRect(rect);
		cnvs = game.getCanvas();
		strategy = game.getStrategy();
		g = game.getGraphics();
		g.drawImage(enemyStillImg, getX(), getY(), enemyStillImg.getWidth(), enemyStillImg.getHeight(), cnvs);
		game.addObject(this);
		game.addEnemy(this);
		player = game.getPlayer();
		playerImg = player.getPlayerImg();
		this.item = item;
	}
	
	// none
	// attacks player if close enough
	public void act()
	{
		if(game.distance(getRect(), new Rectangle(player.getX(), player.getY(), playerImg.getWidth(), playerImg.getHeight())) <= game.getInteractDist() && wait==false)
		{
	   		if (getDmg() > player.getDef())
	   			player.setHp(player.getHp()+player.getDef()-getDmg());
	   		try
	   		{
	   			g.clearRect(getX(), getY(), enemyStillImg.getWidth(), enemyStillImg.getHeight());
	   			g.drawImage(enemyAttack1Img, getX(), getY(), enemyAttack1Img.getWidth(), enemyAttack1Img.getHeight(), cnvs);
	   			strategy.show();
	   			Thread.sleep(60);
	   			
	   			g.clearRect(getX(), getY(), enemyAttack1Img.getWidth(), enemyAttack1Img.getHeight());
	   			g.drawImage(enemyAttack2Img, getX(), getY()-25, enemyAttack2Img.getWidth(), enemyAttack2Img.getHeight(), cnvs);
	   			strategy.show();
	   			Thread.sleep(60);
	   			
	   			g.clearRect(getX(), getY()-25, enemyAttack2Img.getWidth(), enemyAttack2Img.getHeight());
	   			g.drawImage(enemyAttack3Img, getX(), getY(), enemyAttack3Img.getWidth(), enemyAttack3Img.getHeight(), cnvs);
	   			strategy.show();
	   			Thread.sleep(60);

	   			g.clearRect(getX(), getY(), enemyAttack3Img.getWidth(), enemyAttack3Img.getHeight());
	   			g.drawImage(enemyStillImg, getX(), getY(), enemyStillImg.getWidth(), enemyStillImg.getHeight(), cnvs);
	   			strategy.show();
	   		}
	   		catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}	
		}
		else
		{
			g.clearRect(getX(), getY(), enemyStillImg.getWidth(), enemyStillImg.getHeight());
			g.drawImage(enemyStillImg, getX(), getY(), enemyStillImg.getWidth(), enemyStillImg.getHeight(), cnvs);
		}
			
		if(wait)
			wait = false;
		else
			wait = true;	
	}
}

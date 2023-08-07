// Creates enemy that will attack player if close enough
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

abstract public class Enemy extends LivingBeing
{
	protected BufferedImage enemyStillImg;
	protected BufferedImage enemyWalkImg;
	protected BufferedImage enemyAttack1Img;
	protected BufferedImage enemyAttack2Img;
	protected BufferedImage enemyAttack3Img;
	protected Game game;
	protected Canvas cnvs;
	protected Graphics2D g;
	protected BufferStrategy strategy;
	protected Player player;
	protected boolean wait = true;
	protected Item item;
	protected BufferedImage playerImg;
	
	// none
	// deletes enemy form the map
	public void die()
	{
		g.clearRect(getX(), getY(), enemyStillImg.getWidth(), enemyStillImg.getHeight());
		game.kill(this);
		strategy.show();
		putOnItem(item);
	}
	
	// none
	// returns true if enemy is dead, otherwise false
	public boolean isDead()
	{
		if(getHp() <= 0)
			return true;
		else 
			return false;
	}
	
	// none
	// adds item's stats to players stats
	public void putOnItem(Item item)
	{
		if(item.getStat()>0)
		{
			if(item instanceof Weapon)
			{
				player.setDmg(player.getDmg()+item.getStat());
				
				Object[] options = {"Ok"};
				String message = "You have found a "+item.getName()+", +"+item.getStat()+" damage";
				int response = JOptionPane.showOptionDialog(null, message,
				"Dungeon Crawler", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			}
				
			else if(item instanceof Armor)
			{
				player.setDef(player.getDef()+item.getStat());
					
				Object[] options = {"Ok"};
				String message = "You have found a "+item.getName()+", +"+item.getStat()+" defence";
				int response = JOptionPane.showOptionDialog(null, message,
				"Dungeon Crawler", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);	
			}
		}
	}
}

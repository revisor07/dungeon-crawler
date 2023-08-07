// Game creates setup and methods
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

public class Game 
{
	private int HEIGHT = 900; // height of the window
	private int WIDTH = 1000; // width of the window
	private Canvas cnvs;
	private BufferStrategy strategy;
	private Graphics2D g;
	private int step = 8; // size of a players one step
	private ArrayList<VisibleObject> objects = new ArrayList<VisibleObject>(); // all objects that act
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>(); // all enemies in  current level
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>(); // rectangles that player can not go through
	private int maxLevels = 2;
	private int levelOn = 1;
	private Player player;
	private Map map;
	private Double interactDist = 10.0; // distance a which two objects can interact with each other
	
	// none
	// creates setup for the game
	public void start()
	{
		startScreen();
	
		//sets window
		JFrame window = new JFrame("Dungeon Crawler");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setLocationRelativeTo(null); // Centers window on desktop
		window.setVisible(true);
		JPanel panel = (JPanel) window.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);	
		
		//sets graphics
		cnvs = new Canvas();
		cnvs.setBounds(0, 0, WIDTH, HEIGHT);
		panel.add(cnvs);
		cnvs.setIgnoreRepaint(true); // Will use accelerated graphics 
		window.pack(); // Matches up all the window sizes
		cnvs.setBounds(0, 0, WIDTH, HEIGHT);	
		cnvs.setIgnoreRepaint(true); // Will use accelerated graphics 			
		cnvs.createBufferStrategy(2);
		strategy = cnvs.getBufferStrategy();	
		g = (Graphics2D)strategy.getDrawGraphics();
		
		// creates player on the first level
		player = new Player(this, 500,500);
		map = new Map(this);
		map.level1();
		window.addKeyListener(player);
		updateTopPanel();
		strategy.show();
	}
	
	// none
	// Throws game over message
	public void gameOverScreen()
	{
		Object[] options = {"Restart level"};
		String message = "You died";
		int response = JOptionPane.showOptionDialog(null, message,
		"Dungeon Crawler", JOptionPane.DEFAULT_OPTION, 
		JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	// none
	// throws start game message
	public void startScreen()
	{
		Object[] options = {"Start"};
		String message = "Welcome to Dungeon Crawler";
		int response = JOptionPane.showOptionDialog(null, message,
		"Dungeon Crawler", JOptionPane.DEFAULT_OPTION, 
		JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	// none
	// throws go to the next level message
	public void nextLevelScreen()
	{
		Object[] options = {"Next Level"};
		String message = "Level Complete!";
		int response = JOptionPane.showOptionDialog(null, message,
		"Dungeon Crawler", JOptionPane.DEFAULT_OPTION, 
		JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	// none
	// throws game completed message and exits the program
	public void gameCompleteScreen()
	{
		Object[] options = {"Exit"};
		String message = "You have completed the game!";
		int response = JOptionPane.showOptionDialog(null, message,
		"Dungeon Crawler", JOptionPane.DEFAULT_OPTION, 
		JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(response == 0)
			System.exit(1);
	}
	
	// none
	// delets enemy from the game
	public void kill(Enemy enemy)
	{
		rectangles.remove(enemy.getRect());
		objects.remove(enemy);
		enemies.remove(enemy);
	}
	
	// none
	// removes dead enemies, lets every actor to act, checks if win or lose
	public void update()
	{
		int i = 0;
		while(i < enemies.size())
		{
			Enemy enemy = enemies.get(i);
			if(enemy.isDead())
				enemy.die();	
			else
				i++;
		}
		for(VisibleObject object: objects)
			object.act();
		player.act();
		
		if(player.getHp() <= 0)
		{
			g.clearRect(player.getX(), player.getY(), player.getPlayerImg().getWidth(), player.getPlayerImg().getHeight());
			strategy.show();
			player.setHp(player.getMaxHp());
			gameOverScreen();
			map.newLevel(levelOn);
		}
		else if(enemies.size() == 0 && levelOn == maxLevels)
			gameCompleteScreen();
		else if (enemies.size() == 0)
		{
			player.setHp(player.getMaxHp());
			nextLevelScreen();
			levelOn++;
			map.newLevel(levelOn);
		}
		updateTopPanel();
		strategy.show();
	}
	
	// none
	// creates top panel with defence, health, damage, and level
	public void updateTopPanel()
	{
		g.clearRect(0,0,WIDTH,50);
		Font f = new Font("Dialog", Font.PLAIN, 25);
		g.setFont(f);
		g.drawString("Level: "+levelOn, 850, 34);
		g.drawString("Health: "+player.getHp(), 595, 34);
		g.drawString("Defence: "+player.getDef(), 315, 34);
		g.drawString("Damage: "+player.getDmg(), 50, 34);
	}
	
	// none
	// returns distance betwen two rectangles
	public double distance(Rectangle rect1, Rectangle rect2)
	{	
		double r1x = rect1.getX();
		double r1y = rect1.getY();
		double r1w = rect1.getWidth();
		double r1h = rect1.getHeight();
		double r2x = rect2.getX();
		double r2y = rect2.getY();
		double r2w = rect2.getWidth();
		double r2h = rect2.getHeight();
		
		if(r2x+r2w<r1x) //left
		{
			if(r2y+r2h < r1y) // upper left
				return Math.sqrt(Math.pow(r2x+r2w-r1x,2)+Math.pow(r2y+r2h-r1y,2));
			else if(r2y > r1y+r1h) // down left
				return Math.sqrt(Math.pow(r2x+r2w-r1x,2)+Math.pow(r2y-r1y-r1h,2));
			else // middle left
				return r1x-r2x-r2w;
		}
		else if(r2x>r1x+r1w) //right
		{
			if(r2y+r2h < r1y) // upper right
				return Math.sqrt(Math.pow(r2x-r1x-r1w,2)+Math.pow(r2y+r2h-r1y,2));
			else if(r2y > r1y+r1h) // down right
				return Math.sqrt(Math.pow(r2x-r1x-r1w,2)+Math.pow(r2y-r1y-r1h,2));
			else // middle right
				return r2x-r1x-r1w;
		}
		else if(r2y+r2h <= r1y) // middle up
			return r1y-r2y-r2h;
		else if(r2y >= r1y+r1h) // middle down
			return r2y-r1y-r1h;
		return 70;
	}
	
	// none
	// returns interactDist
	public double getInteractDist()
	{
		return interactDist;
	}
	
	// none
	// returns player
	public Player getPlayer()
	{
		return player;
	}
	
	// none
	// adds enemy to enemyies arraylist
	public void addEnemy(Enemy enemy)
	{
		enemies.add(enemy);
	}
	
	// none
	// adds obstacle to rectangle arraylist
	public void addRect(Rectangle obstacle)
	{
		rectangles.add(obstacle);
	}
	
	// none
	// adds object to objects arraylist
	public void addObject(VisibleObject object)
	{
		objects.add(object);
	}
	
	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	
	// none
	// returns rectangles arraylist
	public ArrayList<Rectangle> getRectangles()
	{
		return rectangles;
	}
	
	// none
	// returns objects arraylist
	public ArrayList<VisibleObject> getObjects()
	{
		return objects;
	}
	
	// none
	// returns step
	public int getStep()
	{
		return step;
	}
	
	// none
	// returns g
	public Graphics2D getGraphics()
	{
		return g;
	}
	
	// none
	// returns strategy
	public BufferStrategy getStrategy()
	{
		return strategy;
	}
	
	// none
	// returns cnvs
	public Canvas getCanvas()
	{
		return cnvs;
	}
	
	// none
	// returns HEIGHT
	public int getHeight()
	{
		return HEIGHT;
	}
	
	// none
	// returns WIDTH
	public int getWidth()
	{
		return WIDTH;
	}
}

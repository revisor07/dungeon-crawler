// Creates map with methods to creates objects in a game
import java.awt.*;
import java.awt.image.*;

public class Map
{
	private Game game;
	private Graphics2D g;
	private int HEIGHT;
	private int WIDTH;
	private Player player;
	private int wallSize = 100;
	
	public Map(Game game)
	{
		this.game = game;
		g = game.getGraphics();
		HEIGHT = game.getHeight();
		WIDTH = game.getWidth();
		player = game.getPlayer();
	}
	
	// none
	// creates wall frame along the window edges
	public void makeWallFrame()
	{
		// up
		for(int x = 0; x <= WIDTH-100; x+=100)
			new Wall(game, wallSize, x,50);
		//down
		for(int x = 0; x <= WIDTH-100; x+=100)
			new Wall(game, wallSize, x, 800);
		//right
		for(int y = 100; y <= HEIGHT-100; y+=100)
			new Wall(game, wallSize, 0,y);
		//left
		for(int y = 100; y <= HEIGHT-100; y+=100)
			new Wall(game, wallSize, 900,y);
	}
	
	// 0 < level <= maxLevels
	// cleares everything and creates new level
	public void newLevel(int level)
	{
		g.clearRect(0, 0,  WIDTH, HEIGHT);
		game.getRectangles().clear();
		game.getObjects().clear();
		chooseLevel(level);
		player.move(500, 500);
		game.update();
	}
	
	// 0 < level <= maxLevels
	// creates given level
	public void chooseLevel(int level)
	{
		if(level == 1)
			level1();
		else if(level == 2)
			level2();
	}
	
	// none
	// creates level1
	public void level1()
	{
		makeWallFrame();
		new Skeleton(game, 500,600, new Weapon(34, "sword"));
		new Titan(game, 200,400, new Weapon(34, "sword"));
		new Fountain(game,300,300);
	}
	
	// none
	// creates level2
	public void level2()
	{
		makeWallFrame();
		new Skeleton(game, 500,600, new Weapon(34, "sword"));
		new Hydra(game, 200,400, new Weapon(34, "sword"));
	}
}

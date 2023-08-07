// Creates item that can improve players stat
public class Item
{
	protected int stat;
	protected Player player;
	protected Game game;
	protected String name;
	
	// none
	// returns stat
	public int getStat()
	{
		return stat;
	}
	
	// none
	// returns name
	public String getName()
	{
		return name;
	}
}
// Creates livingBeign that has damage, defence and health, and is a type of VisibleObject 
abstract public class LivingBeing extends VisibleObject
{
	private int maxHp;
	private int hp;
	private int def;
	private int dmg;
	
	// none
	// returns hp
	public int getHp()
	{
		return hp;
	}
	
	// none
	// returns def
	public int getDef()
	{
		return def;
	}
	
	// none
	// returns dmg
	public int getDmg()
	{
		return dmg;
	}
	
	// hp > 0
	// sets maxHp to given value
	public void setMaxHp(int hp)
	{
		this.maxHp = hp;
	}
	
	// none
	// returns maxHp
	public int getMaxHp()
	{
		return maxHp;
	}
	
	// hp > 0
	// sets hp to given value
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	
	// def >= 0
	// sets def to given value
	public void setDef(int def)
	{
		this.def = def;
	}
	
	// dmg >= 0
	// sets dmg to given value
	public void setDmg(int dmg)
	{
		this.dmg = dmg;
	}
		
}

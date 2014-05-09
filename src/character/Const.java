package character;

public final class Const
{
	//races
	public static final int HUMAN 		= 0;
	public static final int ELF 		= 1;
	public static final int DWARF 		= 2;
	public static final int HOBBIT 		= 3;
	
	//genders
	public static final int MALE 		= 0;
	public static final int FEMALE 		= 1;
	
	//statuses
	public static final int OKAY 		= 0;
	public static final int STUNNED 	= 1;
	public static final int STONED 		= 2;
	public static final int POISONED 	= 3;
	public static final int AGED 		= 4;
	public static final int CRAZY 		= 5;
	public static final int DEAD 		= 6;
	
	//alignment
	public static final int LIGHT 		= 0;
	public static final int DARK 		= 1;
	
	//class
	public static final int COMMONER 	= 0;
	public static final int FIGHTER 	= 1;
	public static final int RANGER 		= 2;
	public static final int ROGUE 		= 3;
	public static final int TRADER 		= 4;
	public static final int LEARNED 	= 5;
	public static final int HEALER 		= 6;
	public static final int BARD 		= 7;
	public static final int MYSTICAL 	= 8;
	public static final int SPIRITUAL 	= 9;
	public static final int NOBLE 		= 10;
	
	//generates numbers based on rolling certain number of dice with certain number of sides
	public static int rollDice(int numOfDice, int numOfSides)
	{
		java.util.Random rand = new java.util.Random();
		int num = 0;
		
		for(;numOfDice>0;numOfDice--)
		{
			int rnum;
			do
			{
				rnum = rand.nextInt(numOfSides+1);
			}
			while(rnum==0);
			num+=rnum;
		}
		return num;
	}
	//overloaded to include modifier input
	public static int rollDice(int numOfDice, int numOfSides, int modifier)
	{
		java.util.Random rand = new java.util.Random();
		int num = 0;
		
		for(;numOfDice>0;numOfDice--)
		{
			int rnum;
			do
			{
				rnum = rand.nextInt(numOfSides+1);
			}
			while(rnum==0);
			num+=rnum;
		}
		return num+modifier;
	}
	
}

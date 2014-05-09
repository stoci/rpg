package character;

public class Model implements IActions
{
	//core attributes
	private int strength,dexterity,twitch,constitution,intelligence,wisdom,commonSense,spirtuality,charisma,luck;
	
	//other attributes
	private String name; 
	private int age, race, status, level, xp, gender, alignment, profession, rank, gold;
	
	//points c=current m=max
	private int cHitPoints, mHitPoints, cMagicPoints, mMagicPoints, 
	cSkillPoints, mSkillPoints, cPrayerPoints, mPrayerPoints, cBardPoints, mBardPoints;
	
	//armor class b=base, c=current
	private int bArmorClass, cArmorClass;
	
	//number attacks per round
	private int bAttPerRound, cAttPerRound; 
	
	//resurrection modifier
	private double resModifier;
	

	@Override
	public void useWeapon() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useMagic() {
		// TODO Auto-generated method stub
		
	}

}

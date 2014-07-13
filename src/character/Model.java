/*
 * 
 * This class is the basis for every character that is created in the game.
 * 
 * Author: Josh Blitz
 * 
 */

package character;

public class Model implements IActions
{
	//class
	private String charClass;
	
	//base stats
	private int strength,dexterity,twitch,constitution,intelligence,wisdom,commonSense,spirituality,charisma,luck;
	
	//current stats
	private int cStrength,cDexterity,cTwitch,cConstitution,cIntelligence,cWisdom,cCommonSense,cSpirituality,cCharisma,cLuck;
	
	//other attributes
	private String name, race, gender, alignment, profession; 
	private int age, status, level, xp, rank, gold;
	
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
	
	// returns mean of base stats
	public double meanBaseStats()
	{
		double mean = (this.strength+this.dexterity+this.twitch+this.intelligence+this.wisdom
				+this.commonSense+this.spirituality+this.charisma+this.luck+this.constitution)/10.0;
		return mean;
	}
	// returns base Strength
	public int getStrength() {
		return strength;
	}
	//returns base Dexterity
	public int getDexterity() {
		return dexterity;
	}
	//returns base Twitch
	public int getTwitch() {
		return twitch;
	}
	//returns base Constitution
	public int getConstitution() {
		return constitution;
	}
	//returns base Intelligence
	public int getIntelligence() {
		return intelligence;
	}
	//returns base Wisdom
	public int getWisdom() {
		return wisdom;
	}
	//returns base CommonSense
	public int getCommonSense() {
		return commonSense;
	}
	//returns base Spirituality
	public int getSpirituality() {
		return spirituality;
	}
	//returns base Charisma
	public int getCharisma() {
		return charisma;
	}
	//returns base Luck
	public int getLuck() {
		return luck;
	}
	//returns the name
	public String getName() {
		return name;
	}
	//sets the name
	public void setName(String name) {
		this.name = name;
	}
	//returns the age
	public int getAge() {
		return age;
	}
	//returns the race
	public String getRace() {
		return race;
	}
	//sets the race
	public void setRace(String race) {
		this.race = race;
	}
	//returns the status
	public int getStatus() {
		return status;
	}
	//sets the status
	public void setStatus(int status) {
		this.status = status;
	}
	//returns the character level
	public int getLevel() {
		return level;
	}
	// returns the XP
	public int getXp() {
		return xp;
	}
	//returns Gender
	public String getGender() {
		return gender;
	}
	//sets Gender
	public void setGender(String gender) {
		this.gender = gender;
	}
	//returns Alignment
	public String getAlignment() {
		return alignment;
	}
	//sets Alignment
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	//returns Profession
	public String getProfession() {
		return profession;
	}
	//sets Profession
	public void setProfession(String profession) {
		this.profession = profession;
	}
	//returns Rank
	public int getRank() {
		return rank;
	}
	//returns gold
	public int getGold() {
		return gold;
	}
	//returns current Hit Points
	public int getcHitPoints() {
		return cHitPoints;
	}
	//return max Hit Points
	public int getmHitPoints() {
		return mHitPoints;
	}
	//returns current Magic Points
	public int getcMagicPoints() {
		return cMagicPoints;
	}
	//returns max Magic Points
	public int getmMagicPoints() {
		return mMagicPoints;
	}
	//returns current Skill Points
	public int getcSkillPoints() {
		return cSkillPoints;
	}
	//return max Skill Points 
	public int getmSkillPoints() {
		return mSkillPoints;
	}
	//returns current Prayer Points
	public int getcPrayerPoints() {
		return cPrayerPoints;
	}
	//returns max Prayer Points
	public int getmPrayerPoints() {
		return mPrayerPoints;
	}
	//returns current Bard Points 
	public int getcBardPoints() {
		return cBardPoints;
	}
	//returns max Bard Points
	public int getmBardPoints() {
		return mBardPoints;
	}
	//returns base Armor Class
	public int getbArmorClass() {
		return bArmorClass;
	}
	//returns current Armor Class
	public int getcArmorClass() {
		return cArmorClass;
	}
	//returns base Attacks Per Round
	public int getbAttPerRound() {
		return bAttPerRound;
	}
	//returns current Attacks Per Round
	public int getcAttPerRound() {
		return cAttPerRound;
	}
	//returns resModifier
	public double getResModifier() {
		return resModifier;
	}
	//returns the Character Class
	public String getCharClass() {
		return charClass;
	}
	//sets the Character Class
	public void setCharClass(String charClass) {
		this.charClass = charClass;
	}
	//returns current Strength
	public int getcStrength() {
		return cStrength;
	}
	//returns current Dexterity
	public int getcDexterity() {
		return cDexterity;
	}
	//returns current Twitch
	public int getcTwitch() {
		return cTwitch;
	}
	//returns the current Constitution
	public int getcConstitution() {
		return cConstitution;
	}
	//returns current Intelligence
	public int getcIntelligence() {
		return cIntelligence;
	}
	//returns current Wisdom
	public int getcWisdom() {
		return cWisdom;
	}
	//returns current CommonSense
	public int getcCommonSense() {
		return cCommonSense;
	}
	//returns current Spirituality
	public int getcSpirituality() {
		return cSpirituality;
	}
	//returns current Charisma
	public int getcCharisma() {
		return cCharisma;
	}
	//returns current Luck
	public int getcLuck() {
		return cLuck;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public void setTwitch(int twitch) {
		this.twitch = twitch;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public void setCommonSense(int commonSense) {
		this.commonSense = commonSense;
	}

	public void setSpirituality(int spirituality) {
		this.spirituality = spirituality;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public void setcStrength(int cStrength) {
		this.cStrength = cStrength;
	}

	public void setcDexterity(int cDexterity) {
		this.cDexterity = cDexterity;
	}

	public void setcTwitch(int cTwitch) {
		this.cTwitch = cTwitch;
	}

	public void setcConstitution(int cConstitution) {
		this.cConstitution = cConstitution;
	}

	public void setcIntelligence(int cIntelligence) {
		this.cIntelligence = cIntelligence;
	}

	public void setcWisdom(int cWisdom) {
		this.cWisdom = cWisdom;
	}

	public void setcCommonSense(int cCommonSense) {
		this.cCommonSense = cCommonSense;
	}

	public void setcSpirituality(int cSpirituality) {
		this.cSpirituality = cSpirituality;
	}

	public void setcCharisma(int cCharisma) {
		this.cCharisma = cCharisma;
	}

	public void setcLuck(int cLuck) {
		this.cLuck = cLuck;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setcHitPoints(int cHitPoints) {
		this.cHitPoints = cHitPoints;
	}

	public void setmHitPoints(int mHitPoints) {
		this.mHitPoints = mHitPoints;
	}

	public void setcMagicPoints(int cMagicPoints) {
		this.cMagicPoints = cMagicPoints;
	}

	public void setmMagicPoints(int mMagicPoints) {
		this.mMagicPoints = mMagicPoints;
	}

	public void setcSkillPoints(int cSkillPoints) {
		this.cSkillPoints = cSkillPoints;
	}

	public void setmSkillPoints(int mSkillPoints) {
		this.mSkillPoints = mSkillPoints;
	}

	public void setcPrayerPoints(int cPrayerPoints) {
		this.cPrayerPoints = cPrayerPoints;
	}

	public void setmPrayerPoints(int mPrayerPoints) {
		this.mPrayerPoints = mPrayerPoints;
	}

	public void setcBardPoints(int cBardPoints) {
		this.cBardPoints = cBardPoints;
	}

	public void setmBardPoints(int mBardPoints) {
		this.mBardPoints = mBardPoints;
	}

	public void setbArmorClass(int bArmorClass) {
		this.bArmorClass = bArmorClass;
	}

	public void setcArmorClass(int cArmorClass) {
		this.cArmorClass = cArmorClass;
	}

	public void setbAttPerRound(int bAttPerRound) {
		this.bAttPerRound = bAttPerRound;
	}

	public void setcAttPerRound(int cAttPerRound) {
		this.cAttPerRound = cAttPerRound;
	}

	public void setResModifier(double resModifier) {
		this.resModifier = resModifier;
	}
}

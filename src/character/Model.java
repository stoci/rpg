package character;

public class Model implements IActions
{
	//class
	private String charClass;
	
	//base stats
	private int strength,dexterity,twitch,constitution,intelligence,wisdom,commonSense,spirtuality,charisma,luck;
	
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
	
	// method to easily roll all base stats for Model instance
	public void rollBaseStats(int numOfDice,int numOfSides)
	{
		this.strength = Const.rollDice(numOfDice,numOfSides);
		this.dexterity = Const.rollDice(numOfDice, numOfSides);
		this.twitch = Const.rollDice(numOfDice, numOfSides);
		this.intelligence = Const.rollDice(numOfDice, numOfSides);
		this.wisdom = Const.rollDice(numOfDice, numOfSides);
		this.commonSense = Const.rollDice(numOfDice, numOfSides);
		this.spirtuality = Const.rollDice(numOfDice, numOfSides);
		this.charisma = Const.rollDice(numOfDice, numOfSides);
		this.luck = Const.rollDice(numOfDice, numOfSides);
		this.constitution = Const.rollDice(numOfDice, numOfSides);
	}
	
	// returns mean of base stats
	public double meanBaseStats()
	{
		double mean = (this.strength+this.dexterity+this.twitch+this.intelligence+this.wisdom
				+this.commonSense+this.spirtuality+this.charisma+this.luck+this.constitution)/10.0;
		return mean;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getTwitch() {
		return twitch;
	}

	public void setTwitch(int twitch) {
		this.twitch = twitch;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCommonSense() {
		return commonSense;
	}

	public void setCommonSense(int commonSense) {
		this.commonSense = commonSense;
	}

	public int getSpirtuality() {
		return spirtuality;
	}

	public void setSpirtuality(int spirtuality) {
		this.spirtuality = spirtuality;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getcHitPoints() {
		return cHitPoints;
	}

	public void setcHitPoints(int cHitPoints) {
		this.cHitPoints = cHitPoints;
	}

	public int getmHitPoints() {
		return mHitPoints;
	}

	public void setmHitPoints(int mHitPoints) {
		this.mHitPoints = mHitPoints;
	}

	public int getcMagicPoints() {
		return cMagicPoints;
	}

	public void setcMagicPoints(int cMagicPoints) {
		this.cMagicPoints = cMagicPoints;
	}

	public int getmMagicPoints() {
		return mMagicPoints;
	}

	public void setmMagicPoints(int mMagicPoints) {
		this.mMagicPoints = mMagicPoints;
	}

	public int getcSkillPoints() {
		return cSkillPoints;
	}

	public void setcSkillPoints(int cSkillPoints) {
		this.cSkillPoints = cSkillPoints;
	}

	public int getmSkillPoints() {
		return mSkillPoints;
	}

	public void setmSkillPoints(int mSkillPoints) {
		this.mSkillPoints = mSkillPoints;
	}

	public int getcPrayerPoints() {
		return cPrayerPoints;
	}

	public void setcPrayerPoints(int cPrayerPoints) {
		this.cPrayerPoints = cPrayerPoints;
	}

	public int getmPrayerPoints() {
		return mPrayerPoints;
	}

	public void setmPrayerPoints(int mPrayerPoints) {
		this.mPrayerPoints = mPrayerPoints;
	}

	public int getcBardPoints() {
		return cBardPoints;
	}

	public void setcBardPoints(int cBardPoints) {
		this.cBardPoints = cBardPoints;
	}

	public int getmBardPoints() {
		return mBardPoints;
	}

	public void setmBardPoints(int mBardPoints) {
		this.mBardPoints = mBardPoints;
	}

	public int getbArmorClass() {
		return bArmorClass;
	}

	public void setbArmorClass(int bArmorClass) {
		this.bArmorClass = bArmorClass;
	}

	public int getcArmorClass() {
		return cArmorClass;
	}

	public void setcArmorClass(int cArmorClass) {
		this.cArmorClass = cArmorClass;
	}

	public int getbAttPerRound() {
		return bAttPerRound;
	}

	public void setbAttPerRound(int bAttPerRound) {
		this.bAttPerRound = bAttPerRound;
	}

	public int getcAttPerRound() {
		return cAttPerRound;
	}

	public void setcAttPerRound(int cAttPerRound) {
		this.cAttPerRound = cAttPerRound;
	}

	public double getResModifier() {
		return resModifier;
	}

	public void setResModifier(double resModifier) {
		this.resModifier = resModifier;
	}

	public String getCharClass() {
		return charClass;
	}

	public void setCharClass(String charClass) {
		this.charClass = charClass;
	}


}

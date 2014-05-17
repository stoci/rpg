package character;

public class Model implements IActions
{
	//class
	private int charClass;
	
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

	public int getRace() {
		return race;
	}

	public void setRace(int race) {
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public int getProfession() {
		return profession;
	}

	public void setProfession(int profession) {
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

	public int getCharClass() {
		return charClass;
	}

	public void setCharClass(int charClass) {
		this.charClass = charClass;
	}

}

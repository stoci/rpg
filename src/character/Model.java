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
	//constants
	private static final int SECONDARY_BASE = 3 + Const.rollDice(3, 2, -1);
	
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
	private int cHit, mHit, cMystic, mMystic, 
	cSkill, mSkill, cPrayer, mPrayer, cBard, mBard;
	
	//armor class b=base, c=current
	private int bArmorClass, cArmorClass;
	
	//number attacks per round
	private int bAttPerRound, cAttPerRound; 
	
	//resurrection modifier
	private double resModifier;
	
	public Model()
	{
		super();
		bArmorClass=cArmorClass=12;
		level=1;
	}
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

	public String getCharClass() {
		return charClass;
	}

	public void setCharClass(String charClass) {
		this.charClass = charClass;
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

	public int getSpirituality() {
		return spirituality;
	}

	public void setSpirituality(int spirituality) {
		this.spirituality = spirituality;
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

	public int getcStrength() {
		return cStrength;
	}

	public void setcStrength(int cStrength) {
		this.cStrength = cStrength;
	}

	public int getcDexterity() {
		return cDexterity;
	}

	public void setcDexterity(int cDexterity) {
		this.cDexterity = cDexterity;
	}

	public int getcTwitch() {
		return cTwitch;
	}

	public void setcTwitch(int cTwitch) {
		this.cTwitch = cTwitch;
	}

	public int getcConstitution() {
		return cConstitution;
	}

	public void setcConstitution(int cConstitution) {
		this.cConstitution = cConstitution;
	}

	public int getcIntelligence() {
		return cIntelligence;
	}

	public void setcIntelligence(int cIntelligence) {
		this.cIntelligence = cIntelligence;
	}

	public int getcWisdom() {
		return cWisdom;
	}

	public void setcWisdom(int cWisdom) {
		this.cWisdom = cWisdom;
	}

	public int getcCommonSense() {
		return cCommonSense;
	}

	public void setcCommonSense(int cCommonSense) {
		this.cCommonSense = cCommonSense;
	}

	public int getcSpirituality() {
		return cSpirituality;
	}

	public void setcSpirituality(int cSpirituality) {
		this.cSpirituality = cSpirituality;
	}

	public int getcCharisma() {
		return cCharisma;
	}

	public void setcCharisma(int cCharisma) {
		this.cCharisma = cCharisma;
	}

	public int getcLuck() {
		return cLuck;
	}

	public void setcLuck(int cLuck) {
		this.cLuck = cLuck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public int getcHit() {
		return cHit;
	}

	public void setcHit(int cHit) {
		this.cHit = cHit;
	}

	public int getmHit() {
		return mHit;
	}

	public void setmHit(int mHit) {
		this.mHit = mHit;
	}

	public int getcMystic() {
		return cMystic;
	}

	public void setcMystic(int cMystic) {
		this.cMystic = cMystic;
	}

	public int getmMystic() {
		return mMystic;
	}

	public void setmMystic(int mMystic) {
		this.mMystic = mMystic;
	}

	public int getcSkill() {
		return cSkill;
	}

	public void setcSkill(int cSkill) {
		this.cSkill = cSkill;
	}

	public int getmSkill() {
		return mSkill;
	}

	public void setmSkill(int mSkill) {
		this.mSkill = mSkill;
	}

	public int getcPrayer() {
		return cPrayer;
	}

	public void setcPrayer(int cPrayer) {
		this.cPrayer = cPrayer;
	}

	public int getmPrayer() {
		return mPrayer;
	}

	public void setmPrayer(int mPrayer) {
		this.mPrayer = mPrayer;
	}

	public int getcBard() {
		return cBard;
	}

	public void setcBard(int cBard) {
		this.cBard = cBard;
	}

	public int getmBard() {
		return mBard;
	}

	public void setmBard(int mBard) {
		this.mBard = mBard;
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

}

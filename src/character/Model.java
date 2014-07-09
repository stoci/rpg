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
	
	//class constructor initiates all int variables to 0
	public Model() {
		super();
		this.strength=0;this.dexterity=0;this.twitch=0;this.constitution=0;this.intelligence=0;this.wisdom=0;this.commonSense=0;this.spirituality=0;
			this.charisma=0;this.luck=0;this.cStrength=0;this.cDexterity=0;this.cTwitch=0;this.cConstitution=0;this.cIntelligence=0;this.cWisdom=0;
			this.cCommonSense=0;this.cSpirituality=0;this.cCharisma=0;this.cLuck=0;this.age=0;this.status=0;this.level=0;this.xp=0;this.rank=0;this.gold=0;
			this.cHitPoints=0;this.mHitPoints=0;this.cMagicPoints=0;this.mMagicPoints=0;this.cSkillPoints=0;this.mSkillPoints=0;this.cPrayerPoints=0;
			this.mPrayerPoints=0;this.cBardPoints=0;this.mBardPoints=0;this.bArmorClass=0;this.cArmorClass=0;this.bAttPerRound=0;this.cAttPerRound=0;
			this.resModifier=0;
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
	// returns base Strength
	public int getStrength() {
		return strength;
	}
	//modifies base Strength
	public void modStrength(int mod) {
		this.strength = strength + mod;
	}
	//returns base Dexterity
	public int getDexterity() {
		return dexterity;
	}
	//modifies base Dexterity
	public void modDexterity(int mod) {
		this.dexterity = dexterity + mod;
	}
	//returns base Twitch
	public int getTwitch() {
		return twitch;
	}
	//modifies base Twitch
	public void modTwitch(int mod) {
		this.twitch = twitch + mod;
	}
	//returns base Constitution
	public int getConstitution() {
		return constitution;
	}
	//modifies base Constitution
	public void modConstitution(int mod) {
		this.constitution = constitution + mod;
	}
	//returns base Intelligence
	public int getIntelligence() {
		return intelligence;
	}
	//modifies base Intelligence
	public void modIntelligence(int mod) {
		this.intelligence = intelligence + mod;
	}
	//returns base Wisdom
	public int getWisdom() {
		return wisdom;
	}
	//modifies base Wisdom
	public void modWisdom(int mod) {
		this.wisdom = wisdom + mod;
	}
	//returns base CommonSense
	public int getCommonSense() {
		return commonSense;
	}
	//modifies base CommonSense
	public void modCommonSense(int mod) {
		this.commonSense = commonSense + mod;
	}
	//returns base Spirituality
	public int getSpirituality() {
		return spirituality;
	}
	//modifies base Spirituality
	public void modSpirituality(int mod) {
		this.spirituality = spirituality + mod;
	}
	//returns base Charisma
	public int getCharisma() {
		return charisma;
	}
	//modifies base Charisma
	public void modCharisma(int mod) {
		this.charisma = charisma + mod;
	}
	//returns base Luck
	public int getLuck() {
		return luck;
	}
	//modifies base Luck
	public void modLuck(int mod) {
		this.luck = luck + mod;
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
	//modifies the age
	public void modAge(int mod) {
		this.age = age + mod;
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
	//modifies the character level
	public void modLevel(int mod) {
		this.level = level + mod;
	}
	// returns the XP
	public int getXp() {
		return xp;
	}
	//modifies the XP
	public void modXp(int mod) {
		this.xp = xp + mod;
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
	//modifies Rank
	public void modRank(int mod) {
		this.rank = rank + mod;
	}
	//returns gold
	public int getGold() {
		return gold;
	}
	//modifies gold
	public void modGold(int mod) {
		this.gold = gold + mod;
	}
	//returns current Hit Points
	public int getcHitPoints() {
		return cHitPoints;
	}
	//modifies current Hit Points
	public void modcHitPoints(int mod) {
		this.cHitPoints = cHitPoints + mod;
	}
	//return max Hit Points
	public int getmHitPoints() {
		return mHitPoints;
	}
	//modifes max Hit Points
	public void modmHitPoints(int mod) {
		this.mHitPoints = mHitPoints + mod;
	}
	//returns current Magic Points
	public int getcMagicPoints() {
		return cMagicPoints;
	}
	//modifies current Magic Points 
	public void modcMagicPoints(int mod) {
		this.cMagicPoints = cMagicPoints + mod;
	}
	//returns max Magic Points
	public int getmMagicPoints() {
		return mMagicPoints;
	}
	//modifies max Magic Points
	public void modmMagicPoints(int mod) {
		this.mMagicPoints = mMagicPoints + mod;
	}
	//returns current Skill Points
	public int getcSkillPoints() {
		return cSkillPoints;
	}
	//modifies current Skill Points
	public void modcSkillPoints(int mod) {
		this.cSkillPoints = cSkillPoints + mod;
	}
	//return max Skill Points 
	public int getmSkillPoints() {
		return mSkillPoints;
	}
	//modifies max Skill Points
	public void modmSkillPoints(int mod) {
		this.mSkillPoints = mSkillPoints + mod;
	}
	//returns current Prayer Points
	public int getcPrayerPoints() {
		return cPrayerPoints;
	}
	//modifies current Prayer Points
	public void modcPrayerPoints(int mod) {
		this.cPrayerPoints = cPrayerPoints + mod;
	}
	//returns max Prayer Points
	public int getmPrayerPoints() {
		return mPrayerPoints;
	}
	//modifies max Prayer Points
	public void modmPrayerPoints(int mod) {
		this.mPrayerPoints = mPrayerPoints + mod;
	}
	//returns current Bard Points 
	public int getcBardPoints() {
		return cBardPoints;
	}
	//modifies current Bard Points
	public void modcBardPoints(int mod) {
		this.cBardPoints = cBardPoints + mod;
	}
	//returns max Bard Points
	public int getmBardPoints() {
		return mBardPoints;
	}
	//modifies max Bard Points
	public void modmBardPoints(int mod) {
		this.mBardPoints = mBardPoints + mod;
	}
	//returns base Armor Class
	public int getbArmorClass() {
		return bArmorClass;
	}
	//modifies base Armor Class
	public void modbArmorClass(int mod) {
		this.bArmorClass = bArmorClass + mod;
	}
	//returns current Armor Class
	public int getcArmorClass() {
		return cArmorClass;
	}
	//modifies current Armor Class
	public void modcArmorClass(int mod) {
		this.cArmorClass = cArmorClass + mod;
	}
	//returns base Attacks Per Round
	public int getbAttPerRound() {
		return bAttPerRound;
	}
	//modifies base Attacks Per Round
	public void modbAttPerRound(int mod) {
		this.bAttPerRound = bAttPerRound + mod;
	}
	//returns current Attacks Per Round
	public int getcAttPerRound() {
		return cAttPerRound;
	}
	//modifies current Attacks Per Round
	public void modcAttPerRound(int mod) {
		this.cAttPerRound = cAttPerRound + mod;
	}
	//returns resModifier
	public double getResModifier() {
		return resModifier;
	}
	//modifies resModifies
	public void modResModifier(double mod) {
		this.resModifier = resModifier + mod;
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
	//modifies current Strength
	public void modcStrength(int mod) {
		this.cStrength = cStrength + mod;
	}
	//returns current Dexterity
	public int getcDexterity() {
		return cDexterity;
	}
	//modifies current Dexterity
	public void modcDexterity(int mod) {
		this.cDexterity = cDexterity + mod;
	}
	//returns current Twitch
	public int getcTwitch() {
		return cTwitch;
	}
	//modifies current Twitch
	public void modcTwitch(int mod) {
		this.cTwitch = cTwitch + mod;
	}
	//returns the current Constitution
	public int getcConstitution() {
		return cConstitution;
	}
	//modifies the current Constitution
	public void modcConstitution(int mod) {
		this.cConstitution = cConstitution + mod;
	}
	//returns current Intelligence
	public int getcIntelligence() {
		return cIntelligence;
	}
	//modifies current Intelligence
	public void modcIntelligence(int mod) {
		this.cIntelligence = cIntelligence + mod;
	}
	//returns current Wisdom
	public int getcWisdom() {
		return cWisdom;
	}
	//modifies current Wisdom
	public void modcWisdom(int mod) {
		this.cWisdom = cWisdom + mod;
	}
	//returns current CommonSense
	public int getcCommonSense() {
		return cCommonSense;
	}
	//modifies current CommonSense
	public void modcCommonSense(int mod) {
		this.cCommonSense = cCommonSense + mod;
	}
	//returns current Spirituality
	public int getcSpirituality() {
		return cSpirituality;
	}
	//modifies current Spirituality
	public void modcSpirituality(int mod) {
		this.cSpirituality = cSpirituality + mod;
	}
	//returns current Charisma
	public int getcCharisma() {
		return cCharisma;
	}
	//modifies current Charisma
	public void modcCharisma(int mod) {
		this.cCharisma = cCharisma + mod;
	}
	//returns current Luck
	public int getcLuck() {
		return cLuck;
	}
	//modifies current Luck
	public void modcLuck(int cLuck) {
		this.cLuck = cLuck;
	}


}

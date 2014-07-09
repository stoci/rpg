package character;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.JsonValue;


// class wraps JSON objects read in from data.json
public class BonusWrapper
{
	// type: race/class/profession/gender/alignment
	private String type;
	
	//name: specific type
	private String name;
	
	//bonuses
	private int st,dx,tw,cn,in,wi,cs,sp,ch,lk,ac,hit,magicPoints,prayer,skill,bard,gold;
	
	public BonusWrapper()
	{
		super();
	}

	public BonusWrapper(Set<Entry<String, JsonValue>> entrySet)
	{
		//iterate through Set of Entries and initialize BonusWrapper
		Iterator<Entry<String, JsonValue>> i =entrySet.iterator();
		while(i.hasNext())
		{
			Entry<String, JsonValue> pair = i.next();
			switch(pair.getKey().toLowerCase())
			{
				case "type":this.type=pair.getValue().toString();break;
				case "name":this.name=pair.getValue().toString();break;
				case "st":this.st=Integer.parseInt(pair.getValue().toString());break;
				case "dx":this.dx=Integer.parseInt(pair.getValue().toString());break;
				case "tw":this.tw=Integer.parseInt(pair.getValue().toString());break;
				case "cn":this.cn=Integer.parseInt(pair.getValue().toString());break;
				case "in":this.in=Integer.parseInt(pair.getValue().toString());break;
				case "wi":this.wi=Integer.parseInt(pair.getValue().toString());break;
				case "cs":this.cs=Integer.parseInt(pair.getValue().toString());break;
				case "sp":this.sp=Integer.parseInt(pair.getValue().toString());break;
				case "ch":this.ch=Integer.parseInt(pair.getValue().toString());break;
				case "lk":this.lk=Integer.parseInt(pair.getValue().toString());break;
				case "ac":this.ac=Integer.parseInt(pair.getValue().toString());break;
				case "hit":this.hit=Integer.parseInt(pair.getValue().toString());break;
				case "mystic":this.magicPoints=Integer.parseInt(pair.getValue().toString());break;
				case "prayer":this.prayer=Integer.parseInt(pair.getValue().toString());break;
				case "skill":this.skill=Integer.parseInt(pair.getValue().toString());break;
				case "bard":this.bard=Integer.parseInt(pair.getValue().toString());break;
				case "gold":this.gold=Integer.parseInt(pair.getValue().toString());break;
				default:break;
			}
		}
//		System.out.println(this.gold);
	}
	
	//overridden toString() method for debug purposes
	@Override
	public String toString()
	{
		
		return "["+getType()+","+getName()+","+getSt()+","+getDx()+","+getTw()+","+
				getCn()+","+getIn()+","+getWi()+","+getCs()+","+getSp()+","+getCh()
				+","+getLk()+","+getAc()+","+getHit()+","+getMagicPoints()+","+getPrayer()
				+","+getSkill()+","+getBard()+","+getGold()+"]";
	}
	//getters & setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSt() {
		return st;
	}

	public void setSt(int st) {
		this.st = st;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getTw() {
		return tw;
	}

	public void setTw(int tw) {
		this.tw = tw;
	}

	public int getCn() {
		return cn;
	}

	public void setCn(int cn) {
		this.cn = cn;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	public int getWi() {
		return wi;
	}

	public void setWi(int wi) {
		this.wi = wi;
	}

	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public int getLk() {
		return lk;
	}

	public void setLk(int lk) {
		this.lk = lk;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getMagicPoints() {
		return magicPoints;
	}

	public void setMagicPoints(int mystic) {
		this.magicPoints = mystic;
	}

	public int getPrayer() {
		return prayer;
	}

	public void setPrayer(int prayer) {
		this.prayer = prayer;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getBard() {
		return bard;
	}

	public void setBard(int bard) {
		this.bard = bard;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
}

package character;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import javax.json.JsonValue;

public class RangeWrapper
{
	private String type,name;
	
	private int upper, lower, bonus;
	
	public RangeWrapper(Set<Entry<String, JsonValue>> entrySet)
	{
		//iterate through Set of Entries and initialize BonusWrapper
		Iterator<Entry<String, JsonValue>> i =entrySet.iterator();
		while(i.hasNext())
		{
			Entry<String, JsonValue> pair = i.next();
			switch(pair.getKey().toLowerCase())
			{
				case "type":this.type=pair.getValue().toString().substring(1, pair.getValue().toString().length()-1);break;
				case "name":this.name=pair.getValue().toString().substring(1, pair.getValue().toString().length()-1);break;
				case "upper":this.upper=Integer.parseInt(pair.getValue().toString());break;
				case "lower":this.lower=Integer.parseInt(pair.getValue().toString());break;
				case "bonus":this.bonus=Integer.parseInt(pair.getValue().toString());break;
				default:break;
			}
		}
//		System.out.println(this.gold);
	}
	
	//overridden toString() method for debug purposes
	@Override
	public String toString()
	{
		
		return "["+getType()+","+getName()+","+getLower()+","+getUpper()+","+getBonus()+"]";
	}

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

	public int getUpper() {
		return upper;
	}

	public void setUpper(int upper) {
		this.upper = upper;
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
}

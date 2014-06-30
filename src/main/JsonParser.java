package main;

import java.io.FileReader;
import java.util.*;

import javax.json.*;

import character.BonusWrapper;

class JsonParser
{
	
	public void parseBonusFile()
	{
		try {
			JsonReader reader = Json.createReader(new FileReader("./data/bonus.json"));;
			JsonArray arr = reader.readArray();

//			System.out.println(arr);
			List<JsonObject> x = arr.getValuesAs(JsonObject.class);

			/* iterate through objects in bonus JSON array */
			for (JsonObject obj : x) {
//				System.out.println(obj.entrySet().toArray());
				MainFSM.bonuses.add(new BonusWrapper(obj.entrySet()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}

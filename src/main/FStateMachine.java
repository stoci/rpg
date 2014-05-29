package main;

import java.io.*;
import java.util.*;

import javafx.application.Platform;
import character.*;

import javax.json.*;

/*finite state machine implementation of game states
 * build character progression: Race, gender, class, profession, alignment, age*/
class FStateMachine
{
	/*points to character being created*/
	Model m;
	
	/*validChoices but contains full words instead of letters*/
	ArrayList<String> fullOptions = new ArrayList<String>();
	
	/*used by JSON reader methods*/
	ArrayList<String> items = new ArrayList<String>();
	
	/*welcome screen -- hard-coded*/
	void begin()
	{
		Game.textDescr.setText("Welcome to Proving Grounds!\n(C)ontinue\n(E)xit");
		Game.validChoices.add("c"); Game.validChoices.add("e");
		Game.state=0;
		
		switch(Game.userInput)
		{
			case "c": clear(); state1(); break;
			case "e": Platform.exit(); break;	
			default:return;
		}
	}
	
	/*main selection screen -- hard-coded*/
	private void state1()
	{
		Game.textDescr.setText("Please select an action.\n(C)reate a character\n(E)nter the arena\n(B)ack");
		Game.validChoices.add("c");Game.validChoices.add("e");Game.validChoices.add("b");
		Game.state=1;
		
		switch(Game.userInput)
		{
			case "c": clear(); state2(); break;
			case "e": clear(); System.out.println("Entering the Arena!"); break;
			case "b": clear(); checkState(Game.state-1); break;
			default:return;
		}
	}
	/*Character creation reads JSON files from ./data directory -- NOT hard-coded*/
	/*choose race*/
	private void state2() 
	{
		Game.state=2;
		readJSONArray();
		m = new Model();
		
		/*iterate through validChoices to check if any equals userInput*/
		for(int i=0; i<Game.validChoices.size(); i++)
		{
			if(Game.userInput.equals(Game.validChoices.get(i)))
			{
				System.out.println(fullOptions.get(i));
				m.setRace(fullOptions.get(i));
				clear();state3();break;
			}
			else if(Game.userInput.equals("b"))
			{
				clear();
				checkState(Game.state-1);break;
			}
		}		
	}
	
	/*choose gender*/
	private void state3() 
	{
		Game.state=3;
		readJSONArray();
		
		/*iterate through validChoices to check if any equals userInput*/
		for(int i=0; i<Game.validChoices.size(); i++)
		{
			if(Game.userInput.equals(Game.validChoices.get(i)))
			{
				System.out.println(fullOptions.get(i));
				m.setGender(fullOptions.get(i));
				clear();state4();break;
			}
			else if(Game.userInput.equals("b"))
			{
				clear();
				checkState(Game.state-1);break;
			}
		}
	}

	/*choose class*/
	private void state4() 
	{
		Game.state=4;
		readJSONArray();
		
		/*iterate through validChoices to check if any equals userInput*/
		for(int i=0; i<Game.validChoices.size(); i++)
		{
			if(Game.userInput.equals(Game.validChoices.get(i)))
			{
				System.out.println(fullOptions.get(i));
				m.setCharClass(fullOptions.get(i));
				clear();state5();break;
			}
			else if(Game.userInput.equals("b"))
			{
				clear();
				checkState(Game.state-1);break;
			}
		}
		
	}
	
	/*choose profession*/
	private void state5() 
	{
		Game.state=5;
		readJSONObject();
		
		/*iterate through validChoices to check if any equals userInput*/
		for(int i=0; i<Game.validChoices.size(); i++)
		{
			if(Game.userInput.equals(Game.validChoices.get(i)))
			{
				System.out.println(fullOptions.get(i));
				m.setProfession(fullOptions.get(i));
				clear();state6();break;
			}
			else if(Game.userInput.equals("b"))
			{
				clear();
				checkState(Game.state-1);break;
			}
		}
	}
	
	/*choose alignment*/
	private void state6() 
	{
		Game.state=6;
		readJSONArray();
		
		/*iterate through validChoices to check if any equals userInput*/
		for(int i=0; i<Game.validChoices.size(); i++)
		{
			if(Game.userInput.equals(Game.validChoices.get(i)))
			{
				System.out.println(fullOptions.get(i));
				m.setAlignment(fullOptions.get(i));
				clear();state7();break;
			}
			else if(Game.userInput.equals("b"))
			{
				clear();
				checkState(Game.state-1);break;
			}
		}
	}
	/*choose age*/
	private void state7() 
	{
	}

	/*state controller -- checks state field to determine which method/state to enter*/
	void checkState(int ...state)
	{
		/*if no argument passed, initialize array with current state*/
		if(state.length==0)state = new int[]{Game.state};
		switch(state[0])
		{
			case 0: begin(); break;
			case 1: state1(); break;
			case 2: state2(); break;
			case 3: state3(); break;
			case 4: state4(); break;
			case 5: state5(); break;
			case 6: state6(); break;
			default:return;
		}
	}
	
	/*clear userInput field to prevent drop through*/
	private void clear()
	{
		Game.userInput="";
		fullOptions.clear();
	}
	
	/*read user-moddable .json files containing only arrays depending on current Game.state*/
	private void readJSONArray()
	{
		try
		{
			JsonReader reader=null;
			items.clear();
			/*generate GUI text choices*/
			StringBuilder output = new StringBuilder();
			/*current game state decides which file to read in*/
			switch(Game.state)
			{
				case 2: output.append("Choose Race!\n\n");reader = Json.createReader(new FileReader("./data/races.json"));break;
				case 3: output.append("Choose Gender!\n\n");reader = Json.createReader(new FileReader("./data/genders.json"));break;
				case 4: output.append("Choose Class!\n\n");reader = Json.createReader(new FileReader("./data/classes.json"));break;
				case 6: output.append("Choose Alignment!\n\n");reader = Json.createReader(new FileReader("./data/alignments.json"));break;
				default:return;
			}
			
			JsonArray arr = reader.readArray();
			
			/*convert all JsonValues into Strings -- trim quotes and check length isn't ridiculous*/
			for(JsonValue v : arr)
			{
				String s = v.toString();
				if(s.length()>25) throw new Exception("Value in JSON too long! Keep identifiers under 25 letters.");
				items.add(s.substring(1,s.length()-1)); fullOptions.add(s.substring(1,s.length()-1));
			}
			finishJSON();
		}
		catch(Exception e){e.printStackTrace();}
		finally{}
	}
	
	/*read JSON file containing object maps*/
	private void readJSONObject()
	{
		try
		{
			JsonReader reader=null; JsonArray arr = null;
			items.clear();

			/*current game state decides which file to parse then read in top level object*/
			switch(Game.state)
			{
				case 5: reader=Json.createReader(new FileReader("./data/professions.json"));
						arr = reader.readObject().getJsonArray("professions");break;
				default:return;
			}
			
			//System.out.println(arr);			
			List<JsonObject> x = arr.getValuesAs(JsonObject.class); //System.out.println(x);
			
			/*iterate through objects in profession*/
			for(JsonObject obj : x)
			{
				/* object contains chosen charclass*/
				if(obj.containsKey(m.getCharClass()))
				{
					/*convert all JsonValues into Strings -- trim quotes and check length isn't ridiculous*/
					for(JsonValue v : obj.getJsonArray(m.getCharClass()))
					{
						String s = v.toString();
						if(s.length()>25) throw new Exception("Value in JSON too long! Keep inputs under 25 letters.");
						items.add(s.substring(1,s.length()-1)); fullOptions.add(s.substring(1,s.length()-1));
					}
				}
			}
			finishJSON();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{}
	}
	
	private void finishJSON() throws Exception
	{			
		/*generate GUI text choices*/
		StringBuilder output = new StringBuilder();
		
		ArrayList<String> validChoices = new ArrayList<String>();
		validChoices.add("b");
		
		/*dynamic processing actions -- must set Game.validChoices and Game.textDescr*/
		/*iterate over choice Strings*/
		for(String s : items)
		{
			/*check each letter in s against each letter in validChoice ArrayList*/
			middle:for(int i = 0; i < s.length(); i++)
			{
				/*letter to be checked against valid array*/
				String letter = String.valueOf(s.charAt(i)).toLowerCase();
				/*check valid ArrayList for letter*/
				for(int j = 0; j < validChoices.size(); j++)
					/*match found -- try next letter*/
					if(letter.equals(validChoices.get(j)))
					{
						continue middle;
					}
				/*no match found returns control to middle loop -- add to valid choices array*/
				validChoices.add(letter); break middle;
			}
		}
		/*remove "b" for now*/
		validChoices.remove(0);
		
		for(int i = 0; i<items.size(); i++)
		{
			/*retrieve first choice FULL word*/
			StringBuilder sb = new StringBuilder(items.get(i).toLowerCase());
			/*get index of letter that needs to be wrapped*/
			int index = sb.indexOf(validChoices.get(i));
			//System.out.println(index);
			/*reset first letter to uppercase by subtracting 32*/ 
			sb.setCharAt(0, (char)(sb.charAt(0)-32));
			String mod = sb.insert(index, "(").insert(index+2,")").toString();
			output.append(mod+"\n");
		}
		output.append("\n(B)ack");
		
		/*dump all dynamically generated choices to GUI --add "b"*/
		validChoices.add("b");
		Game.validChoices = validChoices;
		Game.textDescr.setText(output.toString());
		//for(String s : Game.validChoices)System.out.print(s);System.out.println(); 

	}
}

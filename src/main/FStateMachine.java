package main;

import java.io.*;
import java.util.ArrayList;

import javafx.application.Platform;
import character.*;

import javax.json.*;

/*finite state machine implementation of game states
 * build character progression: Race, gender, class, profession, alignment, age*/
class FStateMachine
{
	/*points to character being created*/
	Model m;
	
	/*welcome screen*/
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
	
	/*main selection screen*/
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
	
	/*choose race*/
	private void state2() 
	{
		Game.state=2;
		readJSON();
		
		m = new Model();
		switch(Game.userInput)
		{
			case "h": clear(); m.setRace(Const.HUMAN); state3(); break;
			case "e": clear(); m.setRace(Const.ELF); state3(); break;
			case "d": clear(); m.setRace(Const.DWARF); state3(); break;
			case "o": clear(); m.setRace(Const.HOBBIT); state3(); break;
			case "b": clear(); checkState(Game.state-1); break;
			default:return;
		}
		
	}
	
	/*choose gender*/
	private void state3() 
	{
		Game.state=3;
		readJSON();
		
		switch(Game.userInput)
		{
			case "m": clear(); m.setGender(Const.MALE); state4(); break;
			case "f": clear(); m.setGender(Const.FEMALE); state4(); break;
			case "b": clear(); checkState(Game.state-1); break;
			default:return;
		}
	}

	/*choose class*/
	private void state4() 
	{
		Game.state=4;
		readJSON();
		
		switch(Game.userInput)
		{
			case "c": clear(); m.setCharClass(Const.COMMONER); state5(); break;
			case "f": clear(); m.setCharClass(Const.FIGHTER); state5(); break;
			case "r": clear(); m.setCharClass(Const.RANGER); state5(); break;
			case "o": clear(); m.setCharClass(Const.ROGUE); state5(); break;
			case "t": clear(); m.setCharClass(Const.TRADER); state5(); break;
			case "l": clear(); m.setCharClass(Const.LEARNED); state5(); break;
			case "h": clear(); m.setCharClass(Const.HEALER); state5(); break;
			case "a": clear(); m.setCharClass(Const.BARD); state5(); break;
			case "m": clear(); m.setCharClass(Const.MYSTICAL); state5(); break;
			case "s": clear(); m.setCharClass(Const.SPIRITUAL); state5(); break;
			case "n": clear(); m.setCharClass(Const.NOBLE); state5(); break;
			case "b": clear(); checkState(Game.state-1); break;
			default:return;
		}
		
	}
	
	/*choose profession*/
	private void state5() 
	{
		Game.state=5;
		readJSON();
		
		switch(Game.userInput){
		case "n": clear(); state6(); break;
		case "b": clear(); checkState(Game.state-1); break;
		default:return;
		}
	}
	
	/*choose alignment*/
	private void state6() 
	{
		Game.state=6;
		readJSON();
		
		switch(Game.userInput){
		case "b": clear(); checkState(Game.state-1); break;
		default:return;
		}
	}
	/*choose age*/
	private void state7() 
	{
	}

	/*checks state field to determine which method/state to enter*/
	void checkState(int ...state)
	{
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
		Game.validChoices.clear();
	}
	
	/*read user-moddable .json files depending on which state*/
	private void readJSON()
	{
		try
		{
			JsonReader reader=null;
			/*current game state decides which file to read in*/
			switch(Game.state)
			{
				case 2: reader = Json.createReader(new FileReader("./data/races.json"));break;
				case 3: reader = Json.createReader(new FileReader("./data/genders.json"));break;
				case 4: reader = Json.createReader(new FileReader("./data/classes.json"));break;
				case 5: reader = Json.createReader(new FileReader("./data/professions.json"));break;
				case 6: reader = Json.createReader(new FileReader("./data/alignments.json"));break;
				default:return;
			}
			
			JsonArray jsonst = reader.readArray();
			ArrayList<String> items = new ArrayList<String>();
			for(JsonValue v : jsonst)
			{
				String s = v.toString();
				items.add(s.substring(1,s.length()-1));
			}
			//System.out.println(jsonst);System.out.println(items);
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
			
			/*generate GUI text choices*/
			StringBuilder output = new StringBuilder();
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
			output.append("(B)ack");
			
			/*dump all dynamically generated choices to GUI --add "b"*/
			validChoices.add("b");
			Game.validChoices = validChoices;
			Game.textDescr.setText(output.toString());
			//for(String s : Game.validChoices)System.out.print(s);System.out.println(); 
		}
		catch(Exception e){e.printStackTrace();}
		finally{}
	}
	
}

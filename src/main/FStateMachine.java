package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
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
	
	/*used for direct user input states*/
	TextField txtField;
	
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
				//System.out.println(fullOptions.get(i));
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
				//System.out.println(fullOptions.get(i));
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
				//System.out.println(fullOptions.get(i));
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
				//System.out.println(fullOptions.get(i));
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
		Game.state=6;Game.textDescr.setVisible(true);
		readJSONArray();
		
		/*iterate through validChoices to check if any equals userInput*/
		for(int i=0; i<Game.validChoices.size(); i++)
		{
			if(Game.userInput.equals(Game.validChoices.get(i)))
			{
				//System.out.println(fullOptions.get(i));
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
		Game.state=7;
		Game.textDescr.setVisible(false); final VBox ageLayout = new VBox();
		Label ageLabel = new Label("Enter Age: "); txtField = new TextField();
		ageLabel.setStyle("-fx-font-size: 20px;");txtField.requestFocus();
		ageLayout.getChildren().addAll(ageLabel,txtField);
		Game.grid.add(ageLayout, 0, 1, 1, 1);
		
		/*the only direct user input*/
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>(){
			public void handle(KeyEvent ke)
			{
				//System.out.println(ke.getCode());
				if(ke.getCode().equals(KeyCode.ENTER))
				{
					//System.out.println("working!!");
					//regex
					boolean b = Pattern.matches("[0-9]{2}", txtField.getText());
					if(b)
					{
						int age = Integer.parseInt(txtField.getText());
						if(age>=17 && age<=88){ageLayout.setVisible(false);m.setAge(age);state8();}
					}
					else{}
					
				}
				if(ke.getCode().equals(KeyCode.ESCAPE)){ageLayout.setVisible(false);checkState(Game.state-1);}
			}
		};
		txtField.setOnKeyReleased(keyEvent);
		// sets focus on txtField
		Platform.runLater(new Runnable() {
		     @Override
		     public void run() {
		         txtField.requestFocus();
		     }
		});
	}
	/*choose name*/
	private void state8()
	{
		Game.state=8;
		Game.textDescr.setVisible(false); final VBox nameLayout = new VBox();
		Label nameLabel = new Label("Enter name: "); txtField = new TextField();
		nameLabel.setStyle("-fx-font-size: 20px;");
		nameLayout.getChildren().addAll(nameLabel,txtField);
		Game.grid.add(nameLayout, 0, 1, 1, 1);
		/*the only direct user input*/
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>(){
			public void handle(KeyEvent ke)
			{
				//System.out.println(ke.getCode());
				if(ke.getCode().equals(KeyCode.ENTER))
				{
					//System.out.println("working!!");
					// regex
					boolean b = Pattern.matches("[1-9a-zA-Z]{1,14}", txtField.getText());
					if(b)
					{
						nameLayout.setVisible(false);m.setName(txtField.getText());state9();
					}
				}
				if(ke.getCode().equals(KeyCode.ESCAPE)){nameLayout.setVisible(false);checkState(Game.state-1);}
			}
		};
		txtField.setOnKeyReleased(keyEvent);
        
		Platform.runLater(new Runnable() {
		     @Override
		     public void run() {
		         txtField.requestFocus();
		     }
		});
	}
	
	private void state9()
	{
		Game.state=9; Game.textDescr.setVisible(true);
		Game.textDescr.setText("Ah.. yer Base Stats shall be . . .\n");
		Game.validChoices.add("k");Game.validChoices.add("r");Game.validChoices.add("b");
		
		switch(Game.userInput)
		{
			case "b": clear();checkState(Game.state-1); break;	
			default:return;
		}
	}
	private void sleep(long x)
	{
		Thread t = new Thread();
		try{TimeUnit.SECONDS.sleep(x);}
		catch(Exception e){e.printStackTrace();}
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
			case 7: state7(); break;
			case 8: state8(); break;
			case 9: state9(); break;
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
			finishJSON(output);
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
			/*generate GUI text choices*/
			StringBuilder output = new StringBuilder();
			items.clear();

			/*current game state decides which file to parse then read in top level object*/
			switch(Game.state)
			{
				case 5: reader=Json.createReader(new FileReader("./data/professions.json"));
						output.append("Choose Profession!\n\n");
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
			finishJSON(output);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{}
	}
	
	private void finishJSON(StringBuilder output) throws Exception
	{		
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

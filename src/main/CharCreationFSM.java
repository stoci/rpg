package main;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import character.Model;

public class CharCreationFSM
{
	//constants
	private final int NUMROLLS=50;
	
	// validChoices but contains full words instead of letters
	ArrayList<String> fullOptions = new ArrayList<String>();

	// used by JSON reader methods
	ArrayList<String> items = new ArrayList<String>();

	// used for direct user input states
	TextField txtField;
	
	//can be updated independent of state
	private VBox inputLayout;
	private Label inputLabel;
	
	// number of rolls allowed for states 9 and 10
	private int numRolls9 = NUMROLLS;
	private int prevNumRolls9;
	private int numRolls10 = NUMROLLS;
	private int prevNumRolls10;
	
	
	// set up event handlers for user input sections
	private EventHandler<KeyEvent> keyEventAge = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent ke) {
//			System.out.println(ke.getCode()+" state7");
			if (ke.getCode().equals(KeyCode.ENTER)) {
				// System.out.println("working!!");
				// regex
				boolean b = Pattern.matches("[0-9]{2}", txtField.getText());
				if (b) {
					int age = Integer.parseInt(txtField.getText());
					if (age >= 17 && age <= 88) {
						inputLayout.setVisible(false);
						MainFSM.m.setAge(age);
						checkState(Game.state=8);
					}
				} else {
				}

			}
			if (ke.getCode().equals(KeyCode.ESCAPE)) {
				inputLayout.setVisible(false);
				Game.textDescr.setVisible(true);
				checkState(Game.state=6);
				return;
			}
		}
	};
	private EventHandler<KeyEvent> keyEventName = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent ke) {
//			System.out.println(ke.getCode()+" state8");
			if (ke.getCode().equals(KeyCode.ENTER)) {
				// System.out.println("working!!");
				// regex
				boolean b = Pattern.matches("[1-9a-zA-Z]{1,14}",
						txtField.getText());
				if (b) {
					MainFSM.m.setName(txtField.getText());
					state9();
				}
			}
			if (ke.getCode().equals(KeyCode.ESCAPE)) {
				Game.state = 7;
				inputLabel.setText("Enter age: ");
				txtField.clear();txtField.requestFocus();
				txtField.setOnKeyReleased(keyEventAge);
				return;
			}
		}
	};
	
	
	/*
	 * Character creation reads JSON files from ./data directory -- 
	 * NOT hard-coded
	 */
	/* choose race */
	public void begin() {
		Game.state = 2;
		MainFSM.m = new Model();

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setRace(fullOptions.get(i));
				clear();checkState(Game.state = 3);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();Game.mainFSM.checkState(Game.state=1);
				return;
			}
		}
		readJSONArray();
	}

	/* choose gender */
	private void state3() {
		Game.state = 3;

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				//System.out.println(fullOptions.get(i));
				MainFSM.m.setGender(fullOptions.get(i));
				clear();checkState(Game.state=4);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();MainFSM.m = new Model();
				checkState(Game.state=2);
				return;
			}
		}
		readJSONArray();
	}

	/* choose class */
	private void state4() {
		Game.state = 4;

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setCharClass(fullOptions.get(i));
				clear();checkState(Game.state=5); 
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();checkState(Game.state=3); 
				return;
			}
		}
		readJSONArray();

	}

	/* choose profession */
	private void state5() {
		Game.state = 5;

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setProfession(fullOptions.get(i));
				clear();checkState(Game.state=6);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();checkState(Game.state=4);
				return;
			}
		}
		readJSONObject();
	}

	/* choose alignment */
	private void state6() {
		Game.state = 6;


		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setAlignment(fullOptions.get(i));
				clear();checkState(Game.state=7);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();checkState(Game.state=5);
				return;
			}
		}
		readJSONArray();
	}

	/* input age */
	private void state7() {
		Game.state = 7;
		initLayout();

		if(Game.state==8)txtField.setOnKeyReleased(keyEventName);
		else if(Game.state==7)txtField.setOnKeyReleased(keyEventAge);
	}

	/* input name */
	private void state8() {
		Game.state = 8;
		initLayout();
		

		if(Game.state==8)txtField.setOnKeyReleased(keyEventName);
		else if(Game.state==7)txtField.setOnKeyReleased(keyEventAge);
	}

	/* reroll state where base stats are chosen */
	private void state9() {
		Game.state = 9;
		inputLayout.setVisible(false);
		Game.textDescr.setVisible(true);

		// Timeline object that runs on UI thread allowing timed events to occur
		// remove for now
		/*
		 * Timeline ellipsis = new Timeline(new KeyFrame(Duration.seconds(1),new
		 * EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) {
		 * //System.out.println("this is called every 1 seconds on UI thread");
		 * Game.textDescr.appendText(" ."); }
		 * }));ellipsis.setCycleCount(3);ellipsis.playFromStart();
		 */
		Game.validChoices.add("k");
		Game.validChoices.add("r");
		Game.validChoices.add("escape");
		// stats set OR reroll OR exit
		switch (Game.userInput) {
		case "k":
			clear();
			prevNumRolls9 = numRolls9;
			checkState(Game.state=10);
			return;
		case "r":
			if(numRolls9==0)return;
			clear();numRolls9--;checkState();
			return;
		case "escape":
			Game.state = 8;
			Game.textDescr.setVisible(false);
			inputLayout.setVisible(true);
			clear();txtField.clear();txtField.requestFocus();numRolls9=NUMROLLS;
			return;
		default:
			break;
		}
		
		if ((prevNumRolls9!=numRolls9) && numRolls9 > 0) {
			MainFSM.m.rollBaseStats(3, 3, 2);
		}
		Game.textDescr.setText("Ah.. yer Base Stats shall be. . .");
		String output = String
				.format("\n\n# of rolls left:%3s\n\n%-12s%-10s%-10s\n%-12s%-10s%-10s\n"
						+ "%-12s%-10s%-10s\n%-12s%-10s%-10s\n%-12s%-10s\n\n(K)eep\n(R)eroll\n\n(Esc)ape",
						numRolls9, "Physical", "Mental", "Ineffable",
						"ST " + MainFSM.m.getcStrength(), "IN " + MainFSM.m.getcIntelligence(),
						"SP " + MainFSM.m.getcSpirituality(), "TW " + MainFSM.m.getcTwitch(),
						"WI " + MainFSM.m.getcWisdom(), "CH " + MainFSM.m.getcCharisma(), "DX "
								+ MainFSM.m.getcDexterity(), "CS " + MainFSM.m.getcCommonSense(),
						"LK " + MainFSM.m.getcLuck(), "CN " + MainFSM.m.getcConstitution(), "AVG "+MainFSM.m.meanBaseStats());
		Game.textDescr.appendText(output);
	}

	/* other attributes determined */
	private void state10() {
		Game.state = 10;
		Game.validChoices.add("k");
		Game.validChoices.add("r");
		Game.validChoices.add("escape");
		switch (Game.userInput) {
		case "k":
			prevNumRolls10 = numRolls10;
			clear();checkState(Game.state=11);
			return;
		case "r":
			if(numRolls10==0)return;
			clear();numRolls10--;checkState();
			return;
		case "escape":
			clear();numRolls10=NUMROLLS;
			checkState(Game.state=9);
			return;
		default:
			break;
		}
		Game.textDescr.setText("..and ye shall begin with these. . .");
		String output = String.format("\n\n# of rolls left:%3s\n\n%-15s%-3s%-15s%-3s\n%-15s%-3s\n%-15s%-3s"
				+ "\n%-15s%-3s%-15s%-3s\n\n%22s\n\n(K)eep\n(R)eroll\n\n(Esc)ape",numRolls10,
				"Mystic Points",99,"Hit Points",99,"Prayer Points",99,"Skill Points",99,
				"Bard Points",99,"Gold Pieces",99,"Armor Class "+99);
		Game.textDescr.appendText(output);
	}
	
	private void state11()
	{
		Game.state = 11;
		Game.validChoices.add("escape");
		switch (Game.userInput) {
		case "escape":
			Game.textDescr.setVisible(true);
			clear();checkState(Game.state=10);
			return;
		default:
			break;
		}
		Game.textDescr.setText("State11 Placeholder\n\n(Esc)ape");
	}

	/*
	 * state controller -- checks state field to determine which method/state to
	 * enter
	 */
	void checkState(int... state) {
		/* if no argument passed, initialize array with current state */
		if (state.length == 0)
			state = new int[] { Game.state };
		switch (state[0]) {
		case 2:
			begin();
			break;
		case 3:
			state3();
			break;
		case 4:
			state4();
			break;
		case 5:
			state5();
			break;
		case 6:
			state6();
			break;
		case 7:
			state7();
			break;
		case 8:
			state8();
			break;
		case 9:
			state9();
			break;
		case 10:
			state10();
			break;
		case 11:
			state11();
			break;
		default:
			return;
		}
	}

	/* clear userInput field to prevent drop through */
	private void clear() {
		Game.userInput = "";
		Game.validChoices.clear();
		fullOptions.clear();
	}

	/*
	 * read user-moddable .json files containing only arrays depending on
	 * current Game.state
	 */
	private void readJSONArray() {
		try {
			JsonReader reader = null;
			items.clear();
			/* generate GUI text choices */
			StringBuilder output = new StringBuilder();
			/* current game state decides which file to read in */
			switch (Game.state) {
			case 2:
				output.append("Choose Race!\n\n");
				reader = Json.createReader(new FileReader("./data/races.json"));
				break;
			case 3:
				output.append("Choose Gender!\n\n");
				reader = Json
						.createReader(new FileReader("./data/genders.json"));
				break;
			case 4:
				output.append("Choose Class!\n\n");
				reader = Json
						.createReader(new FileReader("./data/classes.json"));
				break;
			case 6:
				output.append("Choose Alignment!\n\n");
				reader = Json.createReader(new FileReader(
						"./data/alignments.json"));
				break;
			default:
				return;
			}

			JsonArray arr = reader.readArray();

			/*
			 * convert all JsonValues into Strings -- trim quotes and check
			 * length isn't ridiculous
			 */
			for (JsonValue v : arr) {
				String s = v.toString();
				if (s.length() > 25)
					throw new Exception(
							"Value in JSON too long! Keep identifiers under 25 letters.");
				items.add(s.substring(1, s.length() - 1));
				fullOptions.add(s.substring(1, s.length() - 1));
			}
			finishJSON(output);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/* read JSON file containing object maps */
	private void readJSONObject() {
		try {
			JsonReader reader = null;
			JsonArray arr = null;
			/* generate GUI text choices */
			StringBuilder output = new StringBuilder();
			items.clear();

			/*
			 * current game state decides which file to parse then read in top
			 * level object
			 */
			switch (Game.state) {
			case 5:
				reader = Json.createReader(new FileReader(
						"./data/professions.json"));
				output.append("Choose Profession!\n\n");
				arr = reader.readObject().getJsonArray("professions");
				break;
			default:
				return;
			}

			// System.out.println(arr);
			List<JsonObject> x = arr.getValuesAs(JsonObject.class); // System.out.println(x);

			/* iterate through objects in profession */
			for (JsonObject obj : x) {
				/* object contains chosen charclass */
				if (obj.containsKey(MainFSM.m.getCharClass())) {
					/*
					 * convert all JsonValues into Strings -- trim quotes and
					 * check length isn't ridiculous
					 */
					for (JsonValue v : obj.getJsonArray(MainFSM.m.getCharClass())) {
						String s = v.toString();
						if (s.length() > 25)
							throw new Exception(
									"Value in JSON too long! Keep inputs under 25 letters.");
						items.add(s.substring(1, s.length() - 1));
						fullOptions.add(s.substring(1, s.length() - 1));
					}
				}
			}
			finishJSON(output);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	private void finishJSON(StringBuilder output) throws Exception {
		ArrayList<String> validChoices = new ArrayList<String>();
		validChoices.add("escape");

		/*
		 * dynamic processing actions -- must set Game.validChoices and
		 * Game.textDescr
		 */
		/* iterate over choice Strings */
		for (String s : items) {
			/*
			 * check each letter in s against each letter in validChoice
			 * ArrayList
			 */
			middle: for (int i = 0; i < s.length(); i++) {
				/* letter to be checked against valid array */
				String letter = String.valueOf(s.charAt(i)).toLowerCase();
				/* check valid ArrayList for letter */
				for (int j = 0; j < validChoices.size(); j++)
					/* match found -- try next letter */
					if (letter.equals(validChoices.get(j))) {
						continue middle;
					}
				/*
				 * no match found returns control to middle loop -- add to valid
				 * choices array
				 */
				validChoices.add(letter);
				break middle;
			}
		}
		/* remove "escape" for now */
		validChoices.remove(0);

		for (int i = 0; i < items.size(); i++) {
			/* retrieve first choice FULL word */
			StringBuilder sb = new StringBuilder(items.get(i).toLowerCase());
			/* get index of letter that needs to be wrapped */
			int index = sb.indexOf(validChoices.get(i));
			// System.out.println(index);
			/* reset first letter to uppercase by subtracting 32 */
			sb.setCharAt(0, (char) (sb.charAt(0) - 32));
			String mod = sb.insert(index, "(").insert(index + 2, ")")
					.toString();
			output.append(mod + "\n");
		}
		output.append("\n(Esc)ape");

		/* dump all dynamically generated choices to GUI --add "escape" */
		validChoices.add("escape");
		Game.validChoices = validChoices;
		Game.textDescr.setText(output.toString());
		// for(String s :
		// Game.validChoices)System.out.print(s);System.out.println();

	}
	
	// used for managing textfield/label pair in age/name
	private void initLayout()
	{
		Game.textDescr.setVisible(false);
		txtField = new TextField();
		inputLayout = new VBox();
		// age
		if(Game.state==7)
		{
			inputLabel = new Label("Enter age: ");
		}
		// name
		else
		{
			inputLabel = new Label("Enter name: ");
		}
		inputLabel.setStyle("-fx-font-size: 20px;");
		inputLayout.getChildren().addAll(inputLabel, txtField);
		if(!Game.grid.getChildren().contains(inputLayout))Game.grid.add(inputLayout, 0, 1, 1, 1);
		txtField.requestFocus();
	}
}

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

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import character.Model;

public class CharCreationFSM
{
	/* validChoices but contains full words instead of letters */
	ArrayList<String> fullOptions = new ArrayList<String>();

	/* used by JSON reader methods */
	ArrayList<String> items = new ArrayList<String>();

	/* used for direct user input states */
	TextField txtField;
	
	private VBox inputLayout;
	private Label inputLabel;
	private int numRolls = 5;
	
	
	/*
	 * Character creation reads JSON files from ./data directory -- NOT
	 * hard-coded
	 */
	/* choose race */
	public void begin() {
		Game.state = 2;
		readJSONArray();
		MainFSM.m = new Model();

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setRace(fullOptions.get(i));
				clear();
				state3();
			} else if (Game.userInput.equals("escape")) {
				clear();		
				Game.textDescr.setText("Please select an action.\n(C)reate a character\n(E)nter the arena\n\n(Esc)ape");
				Game.validChoices.add("c");	Game.validChoices.add("e");	Game.validChoices.add("escape");
				Game.state = 1;return;
			}
		}
		
	}

	/* choose gender */
	private void state3() {
		Game.state = 3;
		readJSONArray();

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				//System.out.println(fullOptions.get(i));
				MainFSM.m.setGender(fullOptions.get(i));
				clear();
				state4();
				break;
			} else if (Game.userInput.equals("escape")) {
				clear();Game.state = 2;	readJSONArray();
				MainFSM.m = new Model();
				return;
			}
		}
	}

	/* choose class */
	private void state4() {
		Game.state = 4;
		readJSONArray();

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setCharClass(fullOptions.get(i));
				clear();
				state5();
				break;
			} else if (Game.userInput.equals("escape")) {
				clear();Game.state = 3;
				readJSONArray(); return;
			}
		}

	}

	/* choose profession */
	private void state5() {
		Game.state = 5;
		readJSONObject();

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setProfession(fullOptions.get(i));
				clear();
				state6();
				break;
			} else if (Game.userInput.equals("escape")) {
				clear();
				Game.state = 4;
				readJSONArray();return;
			}
		}
	}

	/* choose alignment */
	private void state6() {
		Game.state = 6;
		Game.textDescr.setVisible(true);
		readJSONArray();

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				MainFSM.m.setAlignment(fullOptions.get(i));
				clear();
				state7();
				break;
			} else if (Game.userInput.equals("escape")) {
				clear();
				Game.state = 5;
				readJSONObject();return;
			}
		}
	}

	/* choose age */
	private void state7() {
		Game.state = 7;
		Game.textDescr.setVisible(false);
		initLayout();

		/* the only direct user input */
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				// System.out.println(ke.getCode());
				if (ke.getCode().equals(KeyCode.ENTER)) {
					// System.out.println("working!!");
					// regex
					boolean b = Pattern.matches("[0-9]{2}", txtField.getText());
					if (b) {
						int age = Integer.parseInt(txtField.getText());
						if (age >= 17 && age <= 88) {
							inputLayout.setVisible(false);
							MainFSM.m.setAge(age);
							state8();
						}
					} else {
					}

				}
				if (ke.getCode().equals(KeyCode.ESCAPE)) {
					inputLayout.getChildren().clear();
					Game.grid.getChildren().remove(inputLayout);
					Game.state = 6;
					//txtField.setVisible(false);inputLabel.setVisible(false);
					//inputLayout.setVisible(false);
					//Game.textDescr.setVisible(true);
					readJSONArray();
					return;
				}
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

	/* choose name */
	private void state8() {
		Game.state = 8;
		Game.textDescr.setVisible(false);
		initLayout();
		
		/* the only direct user input */
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				// System.out.println(ke.getCode());
				if (ke.getCode().equals(KeyCode.ENTER)) {
					// System.out.println("working!!");
					// regex
					boolean b = Pattern.matches("[1-9a-zA-Z]{1,14}",
							txtField.getText());
					if (b) {
						inputLayout.setVisible(false);
						MainFSM.m.setName(txtField.getText());
						state9();
					}
				}
				if (ke.getCode().equals(KeyCode.ESCAPE)) {
					Game.state = 7;inputLabel.setText("Enter age: ");txtField.requestFocus();
				}
			}
		};
		txtField.setOnKeyReleased(keyEvent);
	}

	/* reroll state where base stats are chosen */
	private void state9() {
		Game.state = 9;
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
		if (numRolls >= 0) {
			MainFSM.m.rollBaseStats(3, 4);
		}
		Game.textDescr.setText("Ah.. yer Base Stats shall be");
		String output = String
				.format("\n\n# of rolls left:%3s\n\n%-12s%-10s%-10s\n%-12s%-10s%-10s\n"
						+ "%-12s%-10s%-10s\n%-12s%-10s%-10s\n%-12s\n\n(K)eep\n(R)eroll\n\n(Esc)ape",
						numRolls, "Physical", "Mental", "Ineffable",
						"ST " + MainFSM.m.getStrength(), "IN " + MainFSM.m.getIntelligence(),
						"SP " + MainFSM.m.getSpirtuality(), "TW " + MainFSM.m.getTwitch(),
						"WI " + MainFSM.m.getWisdom(), "CH " + MainFSM.m.getCharisma(), "DX "
								+ MainFSM.m.getDexterity(), "CS " + MainFSM.m.getCommonSense(),
						"LK " + MainFSM.m.getLuck(), "CN " + MainFSM.m.getConstitution());
		Game.textDescr.appendText(output);

		Game.validChoices.add("k");
		Game.validChoices.add("r");
		Game.validChoices.add("escape");
		// stats set OR reroll OR exit
		switch (Game.userInput) {
		case "k":
			state10();
			break;
		case "r":
			--numRolls;
			clear();
			state9();
			break;
		case "escape":
			Game.state = 8;
			Game.textDescr.setVisible(false);
			inputLayout.setVisible(true);txtField.clear();txtField.requestFocus();
			return;
		default:
			return;
		}
	}

	/* other attributes determined */
	private void state10() {
		Game.state = 10;
		Game.textDescr.setText("State10\n\n(Esc)ape");
		Game.validChoices.add("escape");
		switch (Game.userInput) {
		case "escape":
			clear();
			
		default:
			return;
		}
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
		Game.grid.add(inputLayout, 0, 1, 1, 1);
		txtField.requestFocus();
	}
}

/*
 * 
 * "State machine" class containing the character creation steps.
 * 
 * Author: Josh Blitz
 * 
 */

package main;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import character.BonusWrapper;
import character.Const;
import character.Model;

class CharCreationFSM
{
	//constants
	private final int NUMROLLS=50;
	private final int SEC_BASE = 3;
	
	// validChoices but contains full words instead of letters
	static ArrayList<String> fullOptions = new ArrayList<String>();

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
		new JSONReader().readJSONArray();
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
		new JSONReader().readJSONArray();
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
		new JSONReader().readJSONArray();

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
		new JSONReader().readJSONObject();
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
		new JSONReader().readJSONArray();
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
			rollBaseStats(3, 3, 2); 
			applyBaseBonuses();
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
	// secondary stats determined
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
		rollSecondaryStats(3, 3, 2, -1);
		applySecondaryBonuses();
		Model m = MainFSM.m;
		String output = String.format("\n\n# of rolls left:%3s\n\n%-15s%-3s%-15s%-3s\n%-15s%-3s\n%-15s%-3s"
				+ "\n%-15s%-3s%-15s%-3s\n\n%22s\n\n(K)eep\n(R)eroll\n\n(Esc)ape",numRolls10,
				"Mystic Points",m.getmMystic(),"Hit Points",m.getmHit(),"Prayer Points",m.getmPrayer(),"Skill Points",m.getmSkill(),
				"Bard Points",m.getmBard(),"Gold Pieces",m.getGold(),"Armor Class "+m.getbArmorClass());
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
	
	// determines base stats
	private void rollBaseStats(int numOfDice,int numOfSides, int modifier)
	{
		//rolls for base stats
		MainFSM.m.setStrength(Const.rollDice(numOfDice,numOfSides,modifier));
//		System.out.println("roll base stats  "+MainFSM.m.getStrength());
		MainFSM.m.setDexterity(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setTwitch(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setIntelligence(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setWisdom(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setCommonSense(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setSpirituality(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setCharisma(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setLuck(Const.rollDice(numOfDice, numOfSides,modifier));
		MainFSM.m.setConstitution(Const.rollDice(numOfDice, numOfSides,modifier));
		
		//sets current stats to base stats
		MainFSM.m.setcStrength(MainFSM.m.getStrength());
		MainFSM.m.setcDexterity(MainFSM.m.getDexterity());
		MainFSM.m.setcTwitch(MainFSM.m.getTwitch());
		MainFSM.m.setcIntelligence(MainFSM.m.getIntelligence());
		MainFSM.m.setcWisdom(MainFSM.m.getWisdom());
		MainFSM.m.setcCommonSense(MainFSM.m.getCommonSense());
		MainFSM.m.setcSpirituality(MainFSM.m.getSpirituality());
		MainFSM.m.setcCharisma(MainFSM.m.getCharisma());
		MainFSM.m.setcLuck(MainFSM.m.getLuck());
		MainFSM.m.setcConstitution(MainFSM.m.getConstitution());
		
	}
	
	// determines base secondary stats
	private void rollSecondaryStats(int base, int numOfDice, int numOfSides, int modifier)
	{
		Model m = MainFSM.m;
		
//		m.setcArmorClass(SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier));
		m.setmHit(SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier));
		m.setmPrayer(SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier));
		m.setmSkill(SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier));
		m.setmBard(SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier));
		m.setmMystic(SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier));
		// gold handled differently
		m.setGold(Const.rollDice(2,3));
		
		// set current to maximum for now
//		m.setbArmorClass(m.getcArmorClass());
		m.setcHit(m.getmHit());
		m.setcPrayer(m.getmPrayer());
		m.setcSkill(m.getmSkill());
		m.setcBard(m.getmBard());
		m.setcMystic(m.getmMystic());
	}
	
	// adds bonuses (bonus.json) to ten base stats
	private void applyBaseBonuses()
	{		
		ArrayList<BonusWrapper> bonuses = MainFSM.bonuses; 
		String race = MainFSM.m.getRace();
		String gender = MainFSM.m.getGender();
		String profession = MainFSM.m.getProfession();
		String alignment = MainFSM.m.getAlignment();
		int age = MainFSM.m.getAge();
		Model m = MainFSM.m;
		
		//set age for bonus application
		if(age<=24)age=24;
		else if(age<=35)age=35;
		else if(age<=49)age=49;
		else if(age<=69)age=69;
		else if(age<=88)age=88;
		
		// iterate through list of wrappers
		for(BonusWrapper bw : bonuses)
		{
			// get "name" of wrapper
			String name=bw.getName();
			// String version of age for easy comparison
			String ageStr = String.valueOf(age);
			
			// if any wrapper's name matches a user choice, then apply that wrapper's bonuses
			if(name.equals(race)||name.equals(gender)||name.equals(profession)||name.equals(alignment)||name.equals(ageStr))
			{
				// set bonuses using cXXXX. don't modify base 
				m.setcStrength(m.getcStrength()+bw.getSt());m.setcDexterity(m.getcDexterity()+bw.getDx());
				m.setcTwitch(m.getcTwitch()+bw.getTw());m.setcIntelligence(m.getcIntelligence()+bw.getIn());
				m.setcWisdom(m.getcWisdom()+bw.getWi());m.setcCommonSense(m.getcCommonSense()+bw.getCs());
				m.setcSpirituality(m.getcSpirituality()+bw.getSp());m.setcCharisma(m.getcCharisma()+bw.getCh());
				m.setcLuck(m.getcLuck()+bw.getLk());m.setcConstitution(m.getcConstitution()+bw.getCn());
			}
		}

	}
	
	// adds bonuses (bonus.json) to seven secondary stats -- AC,Hit,Mystic,Prayer,SKill,Bard,Gold
	private void applySecondaryBonuses()
	{		
		ArrayList<BonusWrapper> bonuses = MainFSM.bonuses; 
		String race = MainFSM.m.getRace();
		String gender = MainFSM.m.getGender();
		String profession = MainFSM.m.getProfession();
		String alignment = MainFSM.m.getAlignment();
		int age = MainFSM.m.getAge();
		Model m = MainFSM.m;
		
		//set age for bonus application
		if(age<=24)age=24;
		else if(age<=35)age=35;
		else if(age<=49)age=49;
		else if(age<=69)age=69;
		else if(age<=88)age=88;
		
		// iterate through list of wrappers
		for(BonusWrapper bw : bonuses)
		{
			// get "name" of wrapper
			String name=bw.getName();
			// String version of age for easy comparison
			String ageStr = String.valueOf(age);
			
			// if any wrapper's name matches a user choice, then apply that wrapper's bonuses
			if(name.equals(race)||name.equals(gender)||name.equals(profession)||name.equals(alignment)||name.equals(ageStr))
			{
				// set bonuses using mXXXX, current will equal base for now
				m.setcArmorClass(m.getcArmorClass()+bw.getAc());m.setmHit(m.getmHit()+bw.getHit());
				m.setmMystic(m.getmMystic()+bw.getMystic());m.setmPrayer(m.getmPrayer()+bw.getPrayer());
				m.setmSkill(m.getmSkill()+bw.getSkill());m.setmBard(m.getmBard()+bw.getBard());
				m.setGold(m.getGold()+bw.getGold());
			}
		}
		
		// modify gold according to table in step #12
		int gold = m.getGold();
		

	}

}

package main;

import java.util.ArrayList;

import javafx.application.Platform;
import character.BonusWrapper;
import character.Model;

/*finite state machine implementation of game states
 * build character progression: Race, gender, class, profession, alignment, age*/
public class MainFSM {
	
	/* points to character being created in CharCreationFSM*/
	static Model m;
	
	// stores bonuses by specific type (name)
	static ArrayList<BonusWrapper> bonuses = new ArrayList<BonusWrapper>();
	
	
	public static ArrayList<BonusWrapper> getBonuses() {
		return bonuses;
	}

	/* welcome screen -- hard-coded */
	public void enter() {
		new JsonParser().parseBonusFile();
		Game.textDescr
				.setText("Welcome to Proving Grounds!\n(C)ontinue\n(Q)uit");
		Game.validChoices.add("c");
		Game.validChoices.add("q");
		Game.state = 0;

		switch (Game.userInput) {
		case "c":
			clear();checkState(Game.state=1);
			break;
		case "q":
			Platform.exit();
			break;
		default:
			return;
		}
	}

	/* main selection screen -- hard-coded */
	private void state1() {
		Game.textDescr
				.setText("Please select an action.\n(C)reate a character\n(E)nter the arena\n\n(Esc)ape");
		Game.validChoices.add("c");
		Game.validChoices.add("e");
		Game.validChoices.add("escape");
		Game.state = 1;

		switch (Game.userInput) {
		case "c":
			clear();
			Game.charCreationFSM.begin();
//			System.out.println("back inside mainFSM");
			break;
		case "e":
//			clear();
//			System.out.println("Entering the Arena!");
			break;
		case "escape":
			clear();
			checkState(Game.state=0);
			break;
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
		case 0:
			enter();
			break;
		case 1:
			state1();
			break;
		case 2:
			//state2();
			break;

		default:
			return;
		}
	}

	/* clear userInput field to prevent drop through */
	private void clear() {
		Game.userInput = "";
		Game.validChoices.clear();
	}

}

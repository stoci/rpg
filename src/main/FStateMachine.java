package main;

import character.*;

class FStateMachine
{
	/*points to character being created*/
	Model m;
	
	/*welcome screen*/
	void begin()
	{
		Game.textOutput.setText("Welcome to Proving Grounds!\n(C)ontinue\n(E)xit");
		Game.validChoices = new String[]{"c","e"};
		Game.state=0;
		
		switch(Game.userInput)
		{
			case "c": clear(); state1(); break;
			case "e": System.exit(0); break;	
			default:return;
		}
	}
	
	/*main selection screen*/
	private void state1()
	{
		Game.textOutput.setText("Please select an action.\n(C)reate a character\n(E)nter the arena\n(B)ack");
		Game.validChoices = new String[]{"c","e","b"};
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
		Game.textOutput.setText("Race?\n(H)uman\n(E)lf\n(D)warf\nH(o)bbit\n(B)ack");
		Game.validChoices = new String[]{"h","e","d","o","b"};
		Game.state=2;
		
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
		Game.textOutput.setText("Gender?\n(M)ale\n(F)emale\n(B)ack");
		Game.validChoices = new String[]{"m","f","b"};
		Game.state=3;
		
		switch(Game.userInput)
		{
			case "m": clear(); m.setGender(Const.MALE); state4(); break;
			case "f": clear(); m.setGender(Const.FEMALE); state4(); break;
			case "b": clear(); checkState(Game.state-1); break;
			default:return;
		}
	}



	private void state4() {
		// TODO Auto-generated method stub
		
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
			default:return;
		}
	}
	
	/*clear userInput field to prevent drop through*/
	private void clear()
	{
		Game.userInput="";
	}
}

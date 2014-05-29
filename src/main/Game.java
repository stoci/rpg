package main;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.geometry.*;
import main.FStateMachine;


public class Game extends Application 
{	
	/*empty string when no valid key has been pressed*/
	static String userInput = "";
	/* area where options are displayed*/
	static TextArea textDescr;
	/*contains STATE represented by an integer*/
	static int state = 0;
	
	/*contains array of valid choices based on what is presented to user*/
	static ArrayList<String> validChoices = new ArrayList<String>();
	//static String[] validChoices = new String[25];
	
	FStateMachine rpg = new FStateMachine();
	
	public static void main(String[] args) { launch(args);}
	
	/* */
	@Override
	public void start(Stage stage) {
		try {
			/*
			int x=0;
			while(x<1000)
			{
				System.out.println(Const.rollDice(3, 3));x++;
			}*/

			/*SET LAYOUT FOR SCENE -- USE GRIDLAYOUT*/
			GridPane grid = new GridPane();
			grid.gridLinesVisibleProperty();
			//grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			Scene scene = new Scene(grid,1024,768);
			//Battle log label
			//Text logLabel = new Text("Game Log");
			//logLabel.setFont(Font.font("Comic Sans", FontWeight.NORMAL, 20));
			//grid.add(logLabel, 0, 0);
			/*MENU BAR SETUP*/
			MenuBar menuBar = new MenuBar();
			Menu menuFile = new Menu("File");
			Menu menuAbout = new Menu("About");
			menuBar.getMenus().addAll(menuFile,menuAbout);
			grid.add(menuBar,0,0,2,1);
			/*IMAGE DISPLAY AREA*/
			ImageView img = new ImageView();
			img.setImage(new Image("http://lh5.ggpht.com/_vlyvBf4tMXM/TG7_YMJWICI/AAAAAAAACZQ/91X2G-w81hI/s800/yugoloths2.jpg"));
			img.setFitHeight(800); img.setFitWidth(600); img.setPreserveRatio(true);
			grid.add(img,1,1,1,1);
			/*TEXT DESCRIPTION AREA -- DISPLAY ONLY*/
			textDescr = new TextArea("changeable text area");
			textDescr.setStyle("-fx-font-size: 20px;");
			textDescr.setMinWidth(400); textDescr.setEditable(false);
			grid.add(textDescr,0,1,1,1);
			/*CHARACTER TABLEVIEW -- DISPLAY ONLY*/
			TableView charTable = new TableView();
			//TableColumn numCol = new TableColumn("#");
			TableColumn nameCol = new TableColumn("Name");
			TableColumn lvlCol = new TableColumn("Lvl");
			TableColumn acCol = new TableColumn("AC");
			TableColumn hpCol = new TableColumn("HP");
			TableColumn mpCol = new TableColumn("MP");
			TableColumn ppCol = new TableColumn("PP");
			TableColumn spCol = new TableColumn("SP");
			TableColumn bpCol = new TableColumn("BP");
			TableColumn profCol = new TableColumn("Prof");
			TableColumn statusCol = new TableColumn("STA");
			TableColumn goldCol = new TableColumn("Gold");
			TableColumn wpnCol = new TableColumn("Wpn");
			charTable.getColumns().addAll(nameCol,lvlCol,acCol,hpCol,mpCol,ppCol,spCol,bpCol,profCol,statusCol,goldCol,wpnCol);
			grid.add(charTable,0,2,2,1);
			
			
			/*the only direct user input*/
			EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>(){
				public void handle(KeyEvent ke)
				{
					//System.out.println(ke.getCharacter());
					/*call validateInput method to set userInput field if key is in validChoices array*/
					/*returns true if userInput changed -- reduce CPU cycles compared to previous code*/
					if(validateInput(ke.getCharacter().toLowerCase()))
						rpg.checkState();				
				}
			};
			/*KEYINPUT HANDLER -- add identical handlers to scene and Text display area*/
			scene.setOnKeyTyped(keyEvent);
			textDescr.setOnKeyTyped(keyEvent);
			/*SET STAGE UP*/
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Proving Grounds");
			stage.focusedProperty();
			stage.show();
			
			/*enter the finite state machine*/
			rpg.begin();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*checks every key typed against validChoice array -- return true if userInput changed*/
	private boolean validateInput(String ch)
	{
		for(String s : Game.validChoices)
			if(ch.equals(s))
			{
				userInput=ch;
				//System.out.print(userInput);
				return true;
			}
		return false;
	}

}
	

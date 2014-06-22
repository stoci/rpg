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
import main.MainFSM;


public class Game extends Application 
{	
	// empty string when no valid key has been pressed
	static String userInput = "";

	// contains STATE represented by an integer
	static int state = 0;
	
	// contains array of valid choices based on what is presented to user
	static ArrayList<String> validChoices = new ArrayList<String>();
	
	// necessary FSMs -- add more as they are developed
	static MainFSM mainFSM = new MainFSM();
	static CharCreationFSM charCreationFSM = new CharCreationFSM();
	
	// GUI elements
	private Scene scene;
	private MenuBar menuBar;
	private ImageView img;
	static TextArea textDescr;
	static GridPane grid;
	
	public static void main(String[] args) { launch(args);}
	
	/* */
	@Override
	public void start(Stage stage) {
		try {			
			// general layout of game
			initializeLayout();
			
			// add top of layout MenuBar
			initializeMenuBar();
			
			// add image display area
			initializeImageArea();
			
			// add choice/text display area
			initializeTextDisplay();
			
			// add table for displaying party
			initializePartyTable();
			
			// assemble stage and display window
			initializeStage(stage);
			
			
			// event handler for the keyboard
			EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>(){
				public void handle(KeyEvent ke)
				{
//					System.out.println(ke.getCode().toString());
//					System.out.println(validChoices);
					
					/*call validateInput method to set userInput field if key is in validChoices array*/
					/*returns true if userInput changed -- reduce CPU cycles compared to previous code*/
					if(validateInput(ke.getCode().toString().toLowerCase()))
					{
						if(Game.state>=2 && Game.state<=11)charCreationFSM.checkState();
						else mainFSM.checkState(state);
					}
				}
			};
			// add event handler defined above to the scene 
			scene.setOnKeyReleased(keyEvent);
			
			// enter the game
			mainFSM.enter();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeLayout()
	{
		/*SET LAYOUT FOR SCENE -- USE GRIDLAYOUT*/
		grid = new GridPane();
		grid.gridLinesVisibleProperty();grid.setStyle("-fx-background-color: gainsboro");
		//grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		scene = new Scene(grid,1024,768);
	}
	
	private void initializeMenuBar()
	{
		/*MENU BAR SETUP*/
		menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuAbout = new Menu("About");
		menuBar.getMenus().addAll(menuFile,menuAbout);
		grid.add(menuBar,0,0,2,1);
	}
	private void initializeImageArea()
	{
		/*IMAGE DISPLAY AREA*/
		img = new ImageView();
		img.setImage(new Image("http://www.tentonhammer.com/image/view/248528"));
		img.setFitHeight(750); img.setFitWidth(550); img.setPreserveRatio(true);
		grid.add(img,1,1,1,1);
	}
	private void initializeTextDisplay()
	{
		/*TEXT DESCRIPTION AREA -- DISPLAY ONLY*/
		textDescr = new TextArea();
		textDescr.setStyle("-fx-font-size: 19px;-fx-font-family: monospace");
		textDescr.setMinWidth(400); textDescr.setEditable(false);
		grid.add(textDescr,0,1,1,1);
	}
	private void initializePartyTable()
	{
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
	}
	private void initializeStage(Stage stage)
	{
		/*SET STAGE UP*/
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Proving Grounds");
		stage.focusedProperty();
		stage.show();
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
	

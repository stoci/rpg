package main;
	
import character.Const;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.geometry.*;


public class Game extends Application 
{	
	static String userInput = "";
	static Text textOutput = new Text("changeable area awaits user input to change");
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	void waitforInput()
	{
		
	}
	
	
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
			TextFlow textDescr = new TextFlow(textOutput);
			textDescr.setMinWidth(400);
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

			/*KEYINPUT HANDLER*/
			scene.setOnKeyTyped(new EventHandler<KeyEvent>(){
				public void handle(KeyEvent ke)
				{
					userInput = String.valueOf(ke.getCharacter());
					System.out.print(userInput);
					//txt1.setText(userInput);
				}
			});
			
			/*SET STAGE UP*/
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Proving Grounds");
			stage.show();
			while(true)	begin();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//starting point for game (use recursive method calls to maintain state?)
	private void begin() 
	{
		textOutput.setText("Welcome to Proving Grounds!\n(C)ontinue\n(E)xit");
		//need to wait for user input...not sure how

	}

	private void charCreation() {
		textOutput.setText("Let's create a character?\n(Y)es\n(N)o");
		if(userInput.equalsIgnoreCase("y"))
		{
			
		}
		if(userInput.equalsIgnoreCase("n"))return;
		
	}
	
	//possible implementation of wait for user input Service
	class UserInputService extends Service<String>
	{
		final String[] sArr;
		UserInputService(String...arr)
		{
			sArr = arr;
		}
		@Override
		protected Task<String> createTask() {
			return new Task<String>(){

				@Override
				protected String call() throws Exception {
					updateMessage("waiting for userInput to change");
					while(true)
					{
						for(String s : sArr)
							if(userInput.equalsIgnoreCase(s))return userInput;
					}
				}
				
			};
		}
		
	}
}

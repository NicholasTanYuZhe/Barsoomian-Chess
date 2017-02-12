//To run the game
public class mvcGame
{
	//To create object of GUI, Logic, SaveLoad and Controller to run the game 
	public static void main(String[] args)
	{
		GUI gameGui = new GUI();
		Logic theLogic = new Logic();
		SaveLoad theSaveLoad = new SaveLoad();
		Controller gameController = new Controller(gameGui, theLogic, theSaveLoad);
	}
}
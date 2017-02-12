import java.io.*;

@SuppressWarnings("unchecked")

public class SaveLoad
{
	public void save(String[][] board, int player, int moveCounter)
	{
		try
		{
			//Open a file to write to .sav.
			FileOutputStream saveBoard = new FileOutputStream("SaveBoard.sav");
			FileOutputStream savePlayer = new FileOutputStream("SavePlayer.sav");
			FileOutputStream saveMoveCounter = new FileOutputStream("SaveMoveCounter.sav");
			//Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream boardFile = new ObjectOutputStream(saveBoard);
			ObjectOutputStream playerFile = new ObjectOutputStream(savePlayer);
			ObjectOutputStream moveCounterFile = new ObjectOutputStream(saveMoveCounter);

			//Writing data into respective save files.
			boardFile.writeObject(board);
			playerFile.writeObject(player);
			moveCounterFile.writeObject(moveCounter);

			//Close the files after finished saving.
			boardFile.close();
			playerFile.close();
			moveCounterFile.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}
	}

	//Load board from save file
	public String[][] loadBoard()
	{
		String[][] sequence = new String[8][5];
		try
		{
			//Open the .sav file to load
			FileInputStream loadFile = new FileInputStream("SaveBoard.sav");
			//Create ObjectInputStream to load objects from save file
			ObjectInputStream load = new ObjectInputStream(loadFile);
			//Cast the object to String type
			sequence = (String[][])load.readObject();
			//Close the file
			load.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}
		return sequence;
	}

	//Load player from save file
	public int loadPlayer()
	{
		int num = 0;
		try
		{
			//Open the .sav file to load
			FileInputStream loadFile = new FileInputStream("SavePlayer.sav");
			//Create ObjectInputStream to load objects from save file
			ObjectInputStream load = new ObjectInputStream(loadFile);
			//Cast the object to int type
			num = (int)load.readObject();
			//Close the file
			load.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}
		return num;
	}

	//Load moveCounter from save file
	public int loadMoveCounter()
	{
		int num = 0;
		try
		{
			//Open the .sav file to load
			FileInputStream loadFile = new FileInputStream("SaveMoveCounter.sav");
			//Create ObjectInputStream to load objects from save file
			ObjectInputStream load = new ObjectInputStream(loadFile);
			//Cast the object to int type
			num = (int)load.readObject();
			//Close the file
			load.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}
		return num;
	}
}
//To do all the logic part of the board
public class Logic
{
	int counter = 1;

    String board[][] = {{"s","c","h","c","s"},
        				{" ","a","a","a"," "},
        				{" "," "," "," "," "},
        				{" "," "," "," "," "},
        				{" "," "," "," "," "},
        				{" "," "," "," "," "},
        				{" ","A","A","A"," "},
        				{"S","C","H","C","S"}};

    //To add counter for moving piece
	public void addCounter(){
		if(counter == 1)
			counter++;
		else
			counter--;
	}
	//To get the counter for move piece
	public int getCounter(){
		return counter;
	}

	//To check is any of the player have win the game
	public Boolean checkWin(int player)
	{
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<5; j++)
			{
				if(player == 0)
				{
					if(board[i][j].equals("h"))
					{
						return false;
					}
				}
				else if(player == 1)
				{
					if(board[i][j].equals("H"))
						return false;
				}
			}
		}
		return true;
	}

	//To move the piece for the board
	public Boolean move(int player, int oldX, int oldY, int posX, int posY)
	{
		int diffX, diffY;
		
		diffX = posX - oldX;
		diffY = posY - oldY;
		if(diffX < 0)
			diffX = diffX * -1;
		if(diffY < 0)
			diffY = diffY * -1;

		if(player == 0) // player 1
		{
			if(board[posX][posY].equals("S") || board[posX][posY].equals("A")  || board[posX][posY].equals("A.") || board[posX][posY].equals("C") || board[posX][posY].equals("H"))
			{
				return false;
			}
			else
			{
				if(board[oldX][oldY].equals("S"))
				{
					if(diffX > 2 || diffY > 2)
					{
						return false;
					}
					else
					{
						board[posX][posY] = board[oldX][oldY];
						board[oldX][oldY] = " ";
						return true;
					}
				}
				else if(board[oldX][oldY].equals("C"))
				{
					if(diffX != diffY)
					{
						return false;
					}
					else
					{
						board[posX][posY] = board[oldX][oldY];
						board[oldX][oldY] = " ";
						return true;
					}
				}
				else if(board[oldX][oldY].equals("A") || board[oldX][oldY].equals("A."))
				{
					diffX = posX - oldX;
					diffY = posY - oldY;
					if(board[oldX][oldY].equals("A"))
					{
						if(diffX > -3 && diffX < 0 && diffY == 0)
						{
							board[posX][posY] = board[oldX][oldY];
							board[oldX][oldY] = " ";
							return true;
						}
						else
						{
							return false;
						}
					}
					else
					{
						if(diffX < 3 && diffX > 0 && diffY == 0)
						{
							board[posX][posY] = board[oldX][oldY];
							board[oldX][oldY] = " ";
							return true;
						}
						else
						{
							return false;
						}
					}
				}
				else if(board[oldX][oldY].equals("H"))
				{
					if(diffX > 1 || diffY > 1)
					{
						return false;
					}
					else
					{
						board[posX][posY] = board[oldX][oldY];
						board[oldX][oldY] = " ";
						return true;
					}
				}
				else
					return false;
			}
		}
		else // player 2
		{
			if(board[posX][posY].equals("s") || board[posX][posY].equals("a") || board[posX][posY].equals("c") || board[posX][posY].equals("h"))
			{
				return false;
			}
			else
			{
				if(board[oldX][oldY].equals("s"))
				{
					if(diffX > 2 || diffY > 2)
					{
						return false;
					}
					else
					{
						board[posX][posY] = board[oldX][oldY];
						board[oldX][oldY] = " ";
						return true;
					}
				}
				else if(board[oldX][oldY].equals("c"))
				{
					if(diffX != diffY)
					{
						return false;
					}
					else
					{
						board[posX][posY] = board[oldX][oldY];
						board[oldX][oldY] = " ";
						return true;
					}
				}
				else if(board[oldX][oldY].equals("a") || board[oldX][oldY].equals("a."))
				{
					diffX = posX - oldX;
					diffY = posY - oldY;
					if(board[oldX][oldY].equals("a"))
					{
						if(diffX < 3 && diffX > 0 && diffY == 0)
						{
							board[posX][posY] = board[oldX][oldY];
							board[oldX][oldY] = " ";
							return true;
						}
						else
						{
							return false;
						}
					}
					else
					{
						if(diffX > -3 && diffX < 0 && diffY == 0)
						{
							board[posX][posY] = board[oldX][oldY];
							board[oldX][oldY] = " ";
							return true;
						}
						else
						{
							return false;
						}
					}
				}
				else if(board[oldX][oldY].equals("h"))
				{
					if(diffX > 1 || diffY > 1)
					{
						return false;
					}
					else
					{
						board[posX][posY] = board[oldX][oldY];
						board[oldX][oldY] = " ";
						return true;
					}
				}
				else
					return false;
			}
		}
	}

	//To check is the move valid or invalid
	public Boolean checkSide(int player, int x, int y)
	{
		if(board[x][y].equals(" "))
			return false;
		else
		{
			if(player == 0)
			{
				if(board[x][y].equals("a") || board[x][y].equals("s") || board[x][y].equals("c") || board[x][y].equals("h") || board[x][y].equals("a."))
				{
					return false;
				}
			}
			else
			{
				if(board[x][y].equals("A") || board[x][y].equals("S") || board[x][y].equals("C") || board[x][y].equals("H") || board[x][y].equals("A."))
				{
					return false;
				}
			}
			return true;
		}
	}

	//To return the board
	public String[][] getBoard()
	{
		return board;
	}

	//To return one particular piece
	public String getBoardNode(int x, int y)
	{
		return board[x][y];
	}

	//To set the board with position x and y
	public void setBoardNode(String temp, int x, int y)
	{
		board[x][y] = temp;
	}

	//To transform star to cross and vice versa
	public void transform()
	{
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<5; j++)
			{
				if(board[i][j].equals("S"))
					board[i][j] = "C'";
				else if(board[i][j].equals("C"))
					board[i][j] = "S'";
				else if(board[i][j].equals("s"))
					board[i][j] = "c'";
				else if(board[i][j].equals("c"))
					board[i][j] = "s'";
			}
		}
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<5; j++)
			{
				if(board[i][j].equals("S'"))
					board[i][j] = "S";
				else if(board[i][j].equals("C'"))
					board[i][j] = "C";
				else if(board[i][j].equals("s'"))
					board[i][j] = "s";
				else if(board[i][j].equals("c'"))
					board[i][j] = "c";
			}
		}
	}

	//To reset the whole board
	public void reset()
	{
		String tempBoard[][] = {{"s","c","h","c","s"},
        						{" ","a","a","a"," "},
        						{" "," "," "," "," "},
        						{" "," "," "," "," "},
        						{" "," "," "," "," "},
        						{" "," "," "," "," "},
        						{" ","A","A","A"," "},
        						{"S","C","H","C","S"}};
        board = tempBoard;
	}
}
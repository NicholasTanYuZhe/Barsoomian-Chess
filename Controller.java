import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;


public class Controller
{
	private GUI gameGui;
	private Logic theLogic;
	private SaveLoad theSaveLoad;
	ImageIcon empty = new ImageIcon("resources/Empty.PNG");
	ImageIcon rHeart = new ImageIcon("resources/redHeart.png");
	ImageIcon rCross = new ImageIcon("resources/redCross.png");
	ImageIcon rArrow = new ImageIcon("resources/redArrow.png");
	ImageIcon rStar = new ImageIcon("resources/redStar.png");
	ImageIcon gHeart = new ImageIcon("resources/greenHeart.png");
	ImageIcon gCross = new ImageIcon("resources/greenCross.png");
	ImageIcon gArrow = new ImageIcon("resources/greenArrow.png");
	ImageIcon gStar = new ImageIcon("resources/greenStar.png");
	ImageIcon rrArrow = new ImageIcon("resources/redArrowReverse.png");
	ImageIcon rgArrow = new ImageIcon("resources/greenArrowReverse.png");
	int oldX, oldY, posX, posY, player = 0;
	int tempOldX, tempOldY, tempPosX, tempPosY;
	Boolean move, win = false;
	String winner;
	String[][] tempBoard;
	int moveCounter = 0;

	public Controller(GUI gameGui, Logic theLogic, SaveLoad theSaveLoad)
	{
		this.gameGui = gameGui;
		this.theLogic = theLogic;
		this.theSaveLoad = theSaveLoad;
		
		this.gameGui.intGame( empty, rArrow, rStar, rCross, rHeart, gArrow, gStar, gCross, gHeart);
		this.gameGui.addMoveListener(new MoveListener());
		this.gameGui.optionPane();
	}

	class MoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();

			if(action == "New Game") 
			{
				gameGui.closeOptionPane();
			}
			else if(action == "Exit Game")
			{
				System.exit(0);
			}
			else if(action == "Save Game")
			{
				theSaveLoad.save(theLogic.getBoard(), player, moveCounter);
			}
			else if(action == "Load Game")
			{
				tempBoard = theSaveLoad.loadBoard();
				player = theSaveLoad.loadPlayer();
				moveCounter = theSaveLoad.loadMoveCounter();
				gameGui.setPlayer(player);
				ImageIcon tempIcon = new ImageIcon();
				for(int i=0; i<8; i++)
				{
					for(int j=0; j<5; j++)
					{
						theLogic.setBoardNode(tempBoard[i][j], i, j);
						if(tempBoard[i][j].equals("s"))
							tempIcon = gStar;
						else if(tempBoard[i][j].equals("c"))
							tempIcon = gCross;
						else if(tempBoard[i][j].equals("h"))
							tempIcon = gHeart;
						else if(tempBoard[i][j].equals("a"))
							tempIcon = gArrow;
						else if(tempBoard[i][j].equals("a."))
							tempIcon = rgArrow;
						else if(tempBoard[i][j].equals("S"))
							tempIcon = rStar;
						else if(tempBoard[i][j].equals("C"))
							tempIcon = rCross;
						else if(tempBoard[i][j].equals("H"))
							tempIcon = rHeart;
						else if(tempBoard[i][j].equals("A"))
							tempIcon = rArrow;
						else if(tempBoard[i][j].equals("A."))
							tempIcon = rrArrow;
						else if(tempBoard[i][j].equals(" "))
							tempIcon = empty;
						gameGui.setPic(tempIcon, i, j);
						gameGui.closeOptionPane();
					}
				}
			}
			else
			{
				JButton temp = (JButton)e.getSource();   //checks current
				if(theLogic.getCounter() == 1)
				{
					gameGui.setBackgroundGUI(temp, Color.BLUE);
					int tempX = gameGui.checkSideOldX(temp);
					int tempY = gameGui.checkSideOldY(temp);

					move = theLogic.checkSide(player, tempX, tempY);
					if(move)
					{
						theLogic.addCounter();
						gameGui.setOldX(tempX);
						gameGui.setOldY(tempY);
					}
					else
						gameGui.setBackgroundGUI(temp, Color.WHITE);
				}
				else
				{
					gameGui.setBackgroundGUI(temp, Color.BLACK);     //checks next step
					tempPosX = gameGui.checkSidePosX(temp);
					tempPosY = gameGui.checkSidePosY(temp);
					tempOldX = gameGui.checkSideOldX(temp);
					tempOldY = gameGui.checkSideOldY(temp);

					move = theLogic.move(player, tempOldX, tempOldY, tempPosX, tempPosY);
					if(move)
					{
						gameGui.setOldX(tempOldX);
						gameGui.setOldY(tempOldY);
						gameGui.setPosX(tempPosX);
						gameGui.setPosY(tempPosY);
						gameGui.movePiece();
						ImageIcon icon = new ImageIcon();
						String[][] tempBoard = theLogic.getBoard();
						System.out.println(tempPosX);
						if(tempPosX == 0)
						{
							if(tempBoard[tempPosX][tempPosY].equals("a."))
								theLogic.setBoardNode("a", tempPosX, tempPosY);
							else if(tempBoard[tempPosX][tempPosY].equals("A"))
								theLogic.setBoardNode("A.", tempPosX, tempPosY);
						}
						else if(tempPosX == 7)
						{
							if(theLogic.getBoardNode(tempPosX, tempPosY).equals("A."))
								theLogic.setBoardNode("A", tempPosX, tempPosY);
							else if(theLogic.getBoardNode(tempPosX, tempPosY).equals("a"))
								theLogic.setBoardNode("a.", tempPosX, tempPosY);
						}

						for(int i=0; i<8; i++)
						{
							for(int j=0; j<5; j++)
							{
								if(tempBoard[i][j].equals("s"))
										icon = gStar;
									else if(tempBoard[i][j].equals("c"))
										icon = gCross;
									else if(tempBoard[i][j].equals("h"))
										icon = gHeart;
									else if(tempBoard[i][j].equals("a"))
										icon = gArrow;
									else if(tempBoard[i][j].equals("a."))
										icon = rgArrow;
									else if(tempBoard[i][j].equals("S"))
										icon = rStar;
									else if(tempBoard[i][j].equals("C"))
										icon = rCross;
									else if(tempBoard[i][j].equals("H"))
										icon = rHeart;
									else if(tempBoard[i][j].equals("A"))
										icon = rArrow;
									else if(tempBoard[i][j].equals("A."))
										icon = rrArrow;
									else if(tempBoard[i][j].equals(" "))
										icon = empty;
									gameGui.setPic(icon, i, j);
							}
						}
						win = theLogic.checkWin(player);
						if(win)
						{
							if(player == 0)
								winner = "Player 1";
							else
								winner = "Player 2";
							gameGui.victory(winner);
							gameGui.optionPane();
							theLogic.reset();
							player = -1;
							moveCounter = -1;
							gameGui.newGame(empty, rArrow, rStar, rCross, rHeart, gArrow, gStar, gCross, gHeart);
						}
						player++;
						if(player == 2)
							player = 0;
						gameGui.setPlayer(player);
						if(moveCounter == 3)
						{
							theLogic.transform();
							tempBoard = theLogic.getBoard();
							ImageIcon tempIcon = new ImageIcon();
							for(int i=0; i<8; i++)
							{
								for(int j=0; j<5; j++)
								{
									if(tempBoard[i][j].equals("s"))
										tempIcon = gStar;
									else if(tempBoard[i][j].equals("c"))
										tempIcon = gCross;
									else if(tempBoard[i][j].equals("h"))
										tempIcon = gHeart;
									else if(tempBoard[i][j].equals("a"))
										tempIcon = gArrow;
									else if(tempBoard[i][j].equals("a."))
										tempIcon = rgArrow;
									else if(tempBoard[i][j].equals("S"))
										tempIcon = rStar;
									else if(tempBoard[i][j].equals("C"))
										tempIcon = rCross;
									else if(tempBoard[i][j].equals("H"))
										tempIcon = rHeart;
									else if(tempBoard[i][j].equals("A"))
										tempIcon = rArrow;
									else if(tempBoard[i][j].equals("A."))
										tempIcon = rrArrow;
									else if(tempBoard[i][j].equals(" "))
										tempIcon = empty;
									gameGui.setPic(tempIcon, i, j);
								}
							}
						}
						moveCounter++;
					}
					else
						gameGui.resetPiece(tempOldX, tempOldY, tempPosX, tempPosY);
					theLogic.addCounter();
					if(moveCounter == 4)
						moveCounter = 0;
				}
			}
		}
	}
}
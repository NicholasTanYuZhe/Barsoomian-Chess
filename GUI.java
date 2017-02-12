import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;
import javax.swing.border.*;
import javax.imageio.ImageIO;


//To display the whole GUI part
class GUI extends JFrame {
int gHeight=8, gLenght=5 ;
int oldX, oldY, posX, posY;

JButton gameBtn[][] = new JButton[gHeight][gLenght];
JLabel userTurn = new JLabel("Player: 1"); // Player display
JButton saveBtn = new JButton(); // Save button
JButton loadBtn = new JButton(); // Load button
JButton[] option = new JButton[3]; // New game, load game and exit game button for the option pane
JOptionPane optionPane = new JOptionPane(); // Menu Option Pane

    //To initialize the whole GUI
    public GUI(){
        super("Barsoomian Chess");
        //top JPanel
        JPanel jpTop = new JPanel(new BorderLayout());
        //bottom JPanel
        JPanel jpBtm = new JPanel (new GridLayout(gHeight,gLenght)); //set grid layout for board

        for(int i=0; i<gHeight;i++)
        {
            for(int j=0; j<gLenght;j++)
            {
                gameBtn[i][j]=new JButton();
                gameBtn[i][j].setBackground(Color.WHITE);
                jpBtm.add(gameBtn[i][j]);
            }
        }
        //Constraints for mainPanel
        GridBagConstraints constraints = new GridBagConstraints();

        // Save Button Panel
        JPanel savePanel = new JPanel(new GridBagLayout());
        saveBtn=new JButton("Save Game");
        constraints.gridx = 0; //Value for horizontal spacing
        constraints.gridy = 0; //Value for vertical spacing
        constraints.ipadx = 10; //Value for internal horizontal padding
        constraints.ipady = 10; //Value for internal vertical padding
        constraints.insets = new Insets(20,0,0,0); //Value for external padding
        savePanel.add(saveBtn, constraints);
        loadBtn = new JButton("Load Game");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.insets = new Insets(20,0,0,0);
        savePanel.add(loadBtn, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.insets = new Insets(20,0,0,0);
        savePanel.add(userTurn, constraints);
        jpBtm.add(savePanel, BorderLayout.EAST);
        // End Code for Creating Save Button Panel
        
        //Set the layout and add the panel to the layout
        this.setLayout(new BorderLayout());
        this.add(jpTop,BorderLayout.NORTH);
        this.add(jpBtm,BorderLayout.CENTER);
        this.add(savePanel,BorderLayout.EAST);

        //Set the border color to black
        jpBtm.setBorder(new LineBorder(Color.BLACK));
        setSize(650,640);
        setVisible(true);
        setResizable(true); // limits frame size for earlier testing
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        option[0] = new JButton("New Game");
        option[1] = new JButton("Load Game");
        option[2] = new JButton("Exit Game");
    }
    
    //To set the picture of the button of position x and y
    public void setPic(ImageIcon icon, int positioni, int positionj)
    {
        gameBtn[positioni][positionj].setIcon(icon);
    }

    public void intGame(ImageIcon empty, ImageIcon rArrow, ImageIcon rStar, ImageIcon rCross, ImageIcon rHeart, ImageIcon gArrow, ImageIcon gStar, ImageIcon gCross, ImageIcon gHeart)
    {
        for(int i=0; i<gHeight;i++)
        {
            for(int j=0; j<gLenght;j++)
                setPic(null,i,j);
        }
        setPic(gArrow,1,1);
        setPic(gArrow,1,2);
        setPic(gArrow,1,3);
        setPic(gStar,0,0);
        setPic(gCross,0,1);
        setPic(gHeart,0,2);
        setPic(gCross,0,3);
        setPic(gStar,0,4);

        setPic(rArrow,6,1);
        setPic(rArrow,6,2);
        setPic(rArrow,6,3);
        setPic(rStar,7,0);
        setPic(rCross,7,1);
        setPic(rHeart,7,2);
        setPic(rCross,7,3);
        setPic(rStar,7,4);
    }

    void addMoveListener(ActionListener moveListener)
    {
        saveBtn.addActionListener(moveListener);
        loadBtn.addActionListener(moveListener);
        for(int i=0; i<gHeight;i++)
        {
            for(int j=0; j<gLenght;j++)
                gameBtn[i][j].addActionListener(moveListener);
        }
        for(int i=0; i<3; i++)
            option[i].addActionListener(moveListener);
    }

    public void checkPiece()
    {
        // get icon   //past move
        for(int i=0; i<gHeight;i++)
            for(int j=0; j<gLenght;j++)
                if(gameBtn[i][j].getBackground() == Color.BLUE){
                    oldX = i;
                    oldY = j;
                }

        // paste icon   //future move
        for(int i=0; i<gHeight;i++)
            for(int j=0; j<gLenght;j++)
                if(gameBtn[i][j].getBackground() == Color.BLACK) // dont change icon if click on same buton
                {
                    posX = i;
                    posY = j;
                }

        // reset color   //past set to default color
        for(int i=0; i<gHeight;i++)
            for(int j=0; j<gLenght;j++){
                if(gameBtn[i][j].getBackground() == Color.BLUE)
                    gameBtn[i][j].setBackground(Color.WHITE);
                if(gameBtn[i][j].getBackground() == Color.BLACK)
                    gameBtn[i][j].setBackground(Color.WHITE);
            }
    }


    public void movePiece()
    {
        ImageIcon icon = null;
        // get icon   //past move
        if(gameBtn[oldX][oldY].getBackground() == Color.BLUE){
            icon = (ImageIcon)gameBtn[oldX][oldY].getIcon();
            setPic(null,oldX,oldY);
        }

        // paste icon   //future move
        if(gameBtn[posX][posY].getBackground() == Color.BLACK && icon!=null) // dont change icon if click on same buton
            setPic(icon,posX,posY);

        // reset color   //past set to default color
        gameBtn[oldX][oldY].setBackground(Color.WHITE);
        gameBtn[posX][posY].setBackground(Color.WHITE);
    }

    public int checkSideOldX(JButton temp)
    {
        int a = 0;
        for(int i=0; i<gHeight;i++)
            for(int j=0; j<gLenght;j++)
                if(gameBtn[i][j].getBackground() == Color.BLUE){
                    a= i;
                }
        return a;
    }

    public int checkSideOldY(JButton temp)
    {
        int a = 0;
        for(int i=0; i<gHeight;i++)
            for(int j=0; j<gLenght;j++)
                if(gameBtn[i][j].getBackground() == Color.BLUE){
                    a = j;
                }
        return a;
    }

    public int checkSidePosX(JButton temp)
    {
        int a = 0;
        for(int i=0; i<gHeight;i++)
            for(int j=0; j<gLenght;j++)
                if(gameBtn[i][j].getBackground() == Color.BLACK)
                    a = i;
        return a;
    }

    public int checkSidePosY(JButton temp)
    {
        int a =0;
        for(int i=0; i<gHeight;i++)
            for(int j=0; j<gLenght;j++)
                if(gameBtn[i][j].getBackground() == Color.BLACK)
                    a = j;
        return a;
    }

    public void optionPane()
    {
        optionPane.showOptionDialog(null, "What do you want to do", "Main menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
    }

    // Close Main Menu Panel
    public void closeOptionPane()
    {
        optionPane.getRootFrame().dispose();
    }

    public void victory(String winner)
    {
        JOptionPane.showMessageDialog(null, "Victory to " + winner + " !");
    }

    public void setBackgroundGUI(JButton temp, Color color)
    {
        temp.setBackground(color);
    }

    public Color getBackgroundGUI(JButton temp)
    {
        return temp.getBackground();
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public int getOldX()
    {
        return oldX;
    }

    public int getOldY()
    {
        return oldY;
    }

    public void setOldX(int x)
    {
        oldX = x;
    }

    public void setOldY(int y)
    {
        oldY = y;
    }

    public void setPosX(int x)
    {
        posX = x;
    }

    public void setPosY(int y)
    {
        posY = y;
    }

    public void resetPiece(int oldX, int oldY, int posX, int posY)
    {
        gameBtn[oldX][oldY].setBackground(Color.WHITE);
        gameBtn[posX][posY].setBackground(Color.WHITE);
    }

    public void setPlayer(int player)
    {
        if(player == 0)
            userTurn.setText("Player: 1");
        else
            userTurn.setText("Player: 2");
    }

    public void newGame(ImageIcon empty, ImageIcon rArrow, ImageIcon rStar, ImageIcon rCross, ImageIcon rHeart, ImageIcon gArrow, ImageIcon gStar, ImageIcon gCross, ImageIcon gHeart)
    {
        intGame(empty, rArrow, rStar, rCross, rHeart, gArrow, gStar, gCross, gHeart);
        userTurn.setText("Player: 1");
        saveBtn.setEnabled(true);
        loadBtn.setEnabled(true);
    }
}

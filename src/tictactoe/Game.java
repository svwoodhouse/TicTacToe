package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game implements Serializable
{
    // Private variables
    // Main frame and panel for the game
    private JFrame gameFrame;
    private JPanel gamePanel;
    
    // Jpanel that contains the tic tac toe buttons and the save buttons
    private JPanel saveGamePanel;
    private JPanel gameGridPanel;

    private String playersTurn = "Player 1";
    private String p1Name;
    private String p2Name;
    private boolean p1Turn;
    public boolean AIEnabled;
    public Game g;
    
    // JButton Array that contains Tic Tac Toe Game Buttons and the Save Buttons
    private JButton[][] buttons;
    private JButton[] saveButtons;
    
    private boolean gameOver;
    private String[][] gameBoard;
    
    // Red X and Blue O Icon
    private ImageIcon redX = new ImageIcon(getClass().getResource("red_X.png"));
    private ImageIcon blueO = new ImageIcon(getClass().getResource("o_icon.png"));
    
    //Variables for Online Play
    private String IPAddress = "localhost";
    private int portNumber = 22222;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ServerSocket serverSocket;
    private boolean accepted;
    
    // Variables for computer calculation
    private int depth = 4;
    private int playerNum;
    private int placesRemaining;
    private int value;
    
    // Constructor that sets up the game board
    public void setBoard(JFrame frame, JPanel p)
    {        
        p.setLayout(new BorderLayout());
        p.add(this.gameGridPanel,BorderLayout.CENTER);
        p.add(this.saveGamePanel, BorderLayout.SOUTH);
        p.revalidate();
        frame.getContentPane().add(p);
        frame.revalidate();
        
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(buttons[i][j].getText());
            }
            System.out.println();
        }
   
        ActionListener actionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                        //System.out.println(e.paramString());
                        
                    if(e.getSource() == saveButtons[0])
                    {
                        saveGame();
                    }
                    
                    else if(e.getSource() == saveButtons[1])
                    {
                        System.exit(0);
                    }
                    // If another player has already chosen this button, an error message appears
                    else{
                    for(int i = 0; i < 3; i++)
                    {
                        for(int j = 0; j < 3; j++)
                        {
                            if((e.getSource() == buttons[i][j]) && (gameBoard[i][j] == "X" || gameBoard[i][j] == "O"))
                            {
                                JOptionPane.showMessageDialog(gameFrame, "Error. Choose another spot"); 
                            }
                            
                            else if(e.getSource() == buttons[i][j])
                            {
                                if(p1Turn)
                                {
                                    buttons[i][j].setText("X");
                                    gameBoard[i][j] = "X";
                                    buttons[i][j].setIcon(resizeIcon(redX,180,160));
                                    p1Turn= false;
                                  
                                    if(playerWon("X"))
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Player One Won! Would you like to replay?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                         
                                        }
                                    }
                                    
                                    else if(isBoardFull())
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Game Board Full. Would you like to restart?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                        }
                                    }
                                    // If user selects the AI Mode
                                    else if(AIEnabled)
                                    {
                                        playComputer();
                                        p1Turn = true;
                                    }
                                }
                                
                                else
                                {
                                    buttons[i][j].setText("O");
                                    buttons[i][j].setIcon(resizeIcon(blueO,180,160));
                                    gameBoard[i][j] = "O";
                                    p1Turn = true;
                                    
                                    if(playerWon("O"))
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Player Two Won! Would you like to replay?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                        }
                                    }
                                    
                                    else if(isBoardFull())
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Game Board Full. Would you like to restart?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                        }
                                    }
                                }
                            
                            }
                        }
                    }
                    }
                    
                    for(int i = 0; i < 3; i++)
                    {
                        for(int j = 0; j < 3; j++)
                        {
                            if(buttons[i][j].getText() == null)
                                System.out.print("");
                            else
                                System.out.print(buttons[i][j].getText());
                        }
                            System.out.println();
                    }
                           System.out.println();
                }
            };
        
       for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                buttons[i][j].addActionListener(actionListener);
            }
        }
       

       
       saveButtons[0].addActionListener(actionListener);
       saveButtons[1].addActionListener(actionListener);

    }
    public Game(JFrame frame, JPanel p, String player_1, String player_2)
    {
        JPanel saveGridPanel = new JPanel();
        saveGridPanel.setLayout(new GridLayout(1,2));
        
        JPanel gameGridPanel = new JPanel();
        gameGridPanel.setLayout(new GridLayout(3,3));
        

        JButton[][] gameButtons = new JButton[3][3];
        JButton[] saveButtons = new JButton[2];
        
        // Fills the array to contain the game buttons
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                gameButtons[i][j] = new JButton();
                gameButtons[i][j].setBackground(Color.white);
                gameButtons[i][j].setOpaque(true);
            }
        }
        
        saveButtons[0] = new JButton("Save");
        saveButtons[1] = new JButton("Exit");

      //  p.setLayout(gameGrid);
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                gameGridPanel.add(gameButtons[i][j]);
            }
        }
        saveGridPanel.add(saveButtons[0]);
        saveGridPanel.add(saveButtons[1]);
        
        p.setLayout(new BorderLayout());
        p.add(gameGridPanel,BorderLayout.CENTER);
        p.add(saveGridPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(p);
        
        // Sets the private variables
        p1Name = player_1;
        p2Name = player_2;
        buttons = gameButtons;
        gameOver = false;
        gameFrame = frame;
        gamePanel = p;
        p1Turn = true;
        gameBoard = new String[3][3];
        this.saveGamePanel = saveGridPanel;
        this.gameGridPanel = gameGridPanel;
        this.saveButtons = saveButtons;
        
        ActionListener actionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {                             
                    if(e.getSource() == saveButtons[0])
                    {
                        saveGame();
                        System.exit(0);
                    }
                    else if(e.getSource() == saveButtons[1])
                    {
                        System.exit(0);
                    }
                    // If another player has already chosen this button, an error message appears
                    else{
                    for(int i = 0; i < 3; i++)
                    {
                        for(int j = 0; j < 3; j++)
                        {
                            if((e.getSource() == buttons[i][j]) && (buttons[i][j].getText() == "X" || buttons[i][j].getText() == "O"))
                            {
                                JOptionPane.showMessageDialog(gameFrame, "Error. Choose another spot"); 
                            }
                            
                            else if(e.getSource() == buttons[i][j])
                            {
                                if(p1Turn)
                                {
                                    buttons[i][j].setText("X");
                                    gameBoard[i][j] = "X";
                                    buttons[i][j].setIcon(resizeIcon(redX,180,160));
                                    p1Turn= false;
                                  
                                    if(playerWon("X"))
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Player One Won! Would you like to replay?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                         
                                        }
                                    }
                                    
                                    else if(isBoardFull())
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Game Board Full. Would you like to restart?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                        }
                                    }
                                    // If user selects the AI Mode
                                    else if(AIEnabled)
                                    {
                                        playComputer();
                                        p1Turn = true;
                                    }
                                }
                                
                                else
                                {
                                    buttons[i][j].setText("O");
                                    buttons[i][j].setIcon(resizeIcon(blueO,180,160));
                                    gameBoard[i][j] = "O";
                                    p1Turn = true;
                                    
                                    if(playerWon("O"))
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Player Two Won! Would you like to replay?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                        }
                                    }
                                    
                                    else if(isBoardFull())
                                    {
                                        int dialogResult = JOptionPane.showConfirmDialog(null, "Game Board Full. Would you like to restart?");
                                        if(dialogResult == JOptionPane.YES_OPTION)
                                        {
                                            for(int k = 0; k < 3; k++)
                                            {
                                                for(int l = 0; l < 3; l++)
                                                {
                                                    buttons[k][l].setText("");
                                                    buttons[k][l].setIcon(null);
                                                    gameBoard[k][l] = null;
                                                }
                                            }
                                            p1Turn = true;
                                        }
                                    }
                                }
                            
                            }
                        }
                    }
                    }
                }
            };
        
       for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                buttons[i][j].addActionListener(actionListener);
            }
        }
       
       saveButtons[0].addActionListener(actionListener);
       saveButtons[1].addActionListener(actionListener);
    }
   
    boolean isBoardFull()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(gameBoard[i][j] == null)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    boolean playerWon(String playerIcon)
    {
        // For loop to check if the palyer won by columns    
        for(int j = 0; j < 3; j++)
            {
                if((gameBoard[0][j] == playerIcon) && (gameBoard[1][j] == playerIcon) && (gameBoard[2][j] == playerIcon))
                    return true;
                
            }
        
        // For loop to check if the player won by rows
        for(int i = 0; i < 3; i++)
        {
            if((gameBoard[i][0] == playerIcon) && (gameBoard[i][1] == playerIcon) && (gameBoard[i][2] == playerIcon))
                return true;
        }
        
        // For loop to check if the palyer won diagonal
        if((gameBoard[1][1] == playerIcon) &&(gameBoard[0][0] == playerIcon) && (gameBoard[2][2] == playerIcon))
            return true;
        
        //Checks to see if the user won in the reverse diagonal direction
         if((gameBoard[1][1] == playerIcon) &&(gameBoard[0][2] == playerIcon) && (gameBoard[2][0] == playerIcon))
            return true;
         
            return false;
    }
    
    boolean connectToServer() throws IOException
    {
        try{
        socket = new Socket(IPAddress,portNumber);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        accepted = true;
        }catch(IOException e)
        {
            System.out.println("Unable to connect to the address: " + IPAddress + ":" + portNumber + " | Stating a server");
            return false;
        }
        System.out.println("Successfully connected to the server");
        return true;
    }
    
    private void startServer()
    {
        try {
            serverSocket = new ServerSocket(portNumber, 8, InetAddress.getByName(IPAddress));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private void listenForServer()
    {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
            System.out.println("Client has requested to join. Accepted.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight)
    {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    public void onlineMode()
    {
    }
    
    public void saveGame()
    {
        try {
                FileOutputStream fos = new FileOutputStream("src/savedData/savedGame.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this.g);
                oos.close();
                System.exit(0);
            } 
        catch(IOException ex) 
            {
                System.out.println(ex.toString());
            }
    }
    
    private void playComputer()
    {
        outerloop:
        for(int i = 0; i < this.gameBoard.length; i++)
        {
            for(int j = 0; j < this.gameBoard.length; j++)
            {
                if(this.gameBoard[i][j] == "" || this.gameBoard[i][j] == null)
                {
                    buttons[i][j].doClick();
                    p1Turn = true;
                    break outerloop;
                }
            }
        }
    }
    
};


package tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game 
{
    // Private variables
    private JFrame gameFrame;
    private JPanel gamePanel;
    private String playersTurn = "Player 1";
    private String p1Name;
    private String p2Name;
    private JButton[] buttons;
    private boolean gameOver;
    private String[] gameBoard = new String[9];
    
    //Variables for Online Play
    private String IPAddress = "localhost";
    private int portNumber = 22222;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ServerSocket serverSocket;
    
    // Constructo that sets up the game board
    public Game(JFrame frame, JPanel p, String player_1, String player_2)
    {
        GridLayout gameGrid = new GridLayout(3,3);
        JButton[] gameButtons = new JButton[9];
        
        // Array to contain the game buttons
        for(int i = 0; i < 9; i++)
        {
            gameButtons[i] = new JButton();
        }
        
        p.setLayout(gameGrid);
        for(int i = 0; i < 9; i++)
        {
            p.add(gameButtons[i]);
        }
        
        frame.getContentPane().add(p);
        
        // Sets the private variables
        p1Name = player_1;
        p2Name = player_2;
        buttons = gameButtons;
        gameOver = false;
        gameFrame = frame;
        gamePanel = p;
        
        ActionListener actionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // If another player has already chosen this button, an error message appears
                    for(int i = 0; i < buttons.length; i++)
                    {
                        if((e.getSource() == buttons[i]) && (buttons[i].getText() == "X" || buttons[i].getText() == "O"))
                        {
                            JOptionPane.showMessageDialog(gameFrame, "Error. Choose another spot"); 
                        }
                        else if(e.getSource() == buttons[i])
                        {
                            buttons[i].setText("X");
                            gameBoard[i] = "X";
                        }
                   }
                }
        };
        
       for(int i = 0; i < 9; i++)
        {
            buttons[i].addActionListener(actionListener);
        }
    }
   
    boolean isBoardFull()
    {
        for(int i = 0; i < gameBoard.length; i++)
        {
            if(gameBoard[i] == null)
            {
                return false;
            }
        }
        return true;
    }
    // Function for when its the next player to go
    public void playersTurn()
    {

    }
    
    public void checkButton()
    {
        
    }
    
    public void saveGame()
    {
        
    }
    
    public void LoadGame()
    {
        
    }
    
    public void setUpClient()
    {
        
    }
    
    public void setUpServer()
    {
        
    }
};

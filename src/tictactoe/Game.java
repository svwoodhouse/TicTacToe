package tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
         buttons[0].addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // If another player has already chosen this button, an error message appears
                    if(buttons[0].getText() == "X" || buttons[0].getText() == "O")
                    {
                       JOptionPane.showMessageDialog(gameFrame, "Error. Choose another spot"); 
                    }
                    else
                            {
                                buttons[0].setText("X");
                            }
                }
        });
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

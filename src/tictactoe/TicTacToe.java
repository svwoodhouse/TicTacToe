//--------------------------------------------------------------------------
// Names: Benny Lin, Sydnee Woodhouse
// Date: December 30, 2017
// Program: This is a program that allows the user to play tic-tac-toe
//--------------------------------------------------------------------------

package tictactoe;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame
{
    // Setup for the game application grid
    JFrame mainFrame = new JFrame("Tic Tac Toe");
    JPanel mainPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    
    JButton a = new JButton("Play Against AI");
    JButton b = new JButton("Play Against a Friend");
    JButton c = new JButton("Play Online");
    JButton d = new JButton("Load Game");
    JButton e = new JButton("How To Play");
    JButton f = new JButton("Exit");
    
    public static void main(String[] args)
    {
        new TicTacToe();
    }
    
    // Sets up the main menu of the game
    public TicTacToe()
    {
        // Sets the size of the frame and makes it visible
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
        // Adds the panel and its buttons to the frame
        buttonPanel.setLayout(new GridLayout(2,3));
        buttonPanel.add(a);
        buttonPanel.add(b);
        buttonPanel.add(c);
        buttonPanel.add(d);
        buttonPanel.add(e);
        buttonPanel.add(f);
        
        // Sets up the main JPanel containing all of the components on the main menu
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainFrame.getContentPane().add(mainPanel);
        
        // When the user clicks on the first option, it creates
        // a brand new panel for the game to be displayed on
        a.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Removes all the content from the main menu of the game
                // Creates the game grid
                // Adds players name to the game
                
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().revalidate();
                buttonPanel.removeAll();
                buttonPanel.revalidate();
                Game g = new Game(mainFrame,buttonPanel,"Player 1", "Computer");
            }
        });
        
        // When the user clicks on the first option, it creates
        // a brand new panel for the game to be displayed on
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Removes all the content from the main menu of the game
                // Creates the game grid
                // Adds players name to the game
                
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().revalidate();
                buttonPanel.removeAll();
                buttonPanel.revalidate();
                Game g = new Game(mainFrame,buttonPanel,"Player 1", "Player 2");
            }
        });
        
    }
}



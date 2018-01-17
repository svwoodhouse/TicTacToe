//--------------------------------------------------------------------------
// Names: Benny Lin, Sydnee Woodhouse
// Date: December 30, 2017
// Program: This is a program that allows the user to play tic-tac-toe
//--------------------------------------------------------------------------

//Imports & Packages
package tictactoe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Main class that runs the Main Menu for the Tic Tac Toe Game
public class TicTacToe extends JFrame
{
    // Setup for the main Game Frame and Game Panel
    JFrame mainFrame = new JFrame("Tic Tac Toe");
    JPanel mainPanel = new JPanel();
    
    // Panel that conatins the Main Menu buttons
    JPanel buttonPanel = new JPanel();
    
    // All the Main Menu buttons
    JButton a = new JButton("Play Against AI");
    JButton b = new JButton("Play Against a Friend");
    JButton c = new JButton("Play Online");
    JButton d = new JButton("Load Game");
    JButton e = new JButton("How To Play");
    JButton f = new JButton("Exit");
    
    
    // Tic Tac Toe game logo
    JLabel imgLabel = new JLabel(new ImageIcon(getClass().getResource("tictactoe_logo.png")));
    
    public static void main(String[] args)
    {
        new TicTacToe();
    }
    
    // Sets up the main menu of the game
    public TicTacToe()
    {
        // Sets the size of the frame and makes it visible
        mainFrame.setSize(600, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
        // Creates a GridLayout to contain the buttons in the JPanel
        // Adds the buttons to the buttonPanel
        a.setPreferredSize(new Dimension(100, 100));
        b.setPreferredSize(new Dimension(100, 100));
        c.setPreferredSize(new Dimension(100, 100));
        d.setPreferredSize(new Dimension(100, 100));
        e.setPreferredSize(new Dimension(100, 100));
        f.setPreferredSize(new Dimension(100, 100));
        
        buttonPanel.setLayout(new GridLayout(2,3));
        buttonPanel.add(a);
        buttonPanel.add(b);
        buttonPanel.add(c);
        buttonPanel.add(d);
        buttonPanel.add(e);
        buttonPanel.add(f);
        
        // Sets up the main JPanel containing all of the components on the main menu
        // Puts the JPanel containing the JButtons at the bottom and leaves the
        // tic tac toe icon at the top
        // Adds the main JPanel to the JFrame
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(imgLabel, BorderLayout.NORTH);
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
                mainPanel.removeAll();
                mainPanel.revalidate();
                Game g = new Game(mainFrame,mainPanel,"Player 1", "Computer");
                g.AIEnabled = true;
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
                mainPanel.removeAll();
                mainPanel.revalidate();
                Game g = new Game(mainFrame,mainPanel,"Player 1", "Player 2");
            }
        });
        
        // This button allows the user to play against another player online
        c.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Removes all the content from the main menu of the game
                // Creates the game grid
                // Adds players name to the game
                
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().revalidate();
                mainPanel.removeAll();
                mainPanel.revalidate();
                Game g = new Game(mainFrame,mainPanel,"Player 1", "Player 2");
            }
        });
        
    }
}



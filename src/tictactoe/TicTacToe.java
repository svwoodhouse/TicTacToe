//--------------------------------------------------------------------------
// Names: Benny Lin, Sydnee Woodhouse
// Date: December 30, 2017
// Program: This is a program that allows the user to play tic-tac-toe
//--------------------------------------------------------------------------

package tictactoe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame
{
    // Setup for the game application grid
    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel p = new JPanel();
    
    JButton a = new JButton("Play Against AI");
    JButton b = new JButton("Play Against a Friend");
    
    public static void main(String[] args)
    {
        new TicTacToe();
    }
    
    // Sets up the main menu of the game
    public TicTacToe()
    {
        // Sets the size of the frame and makes it visible
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Adds the panel and its buttons to the frame
        p.add(a);
        p.add(b);
        frame.getContentPane().add(p);
        
        // When the user clicks on the first option, it creates
        // a brand new panel for the game to be displayed on
        a.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Removes all the content from the main menu of the game
                // Creates the game grid
                // Adds players name to the game
                
                frame.getContentPane().removeAll();
                frame.getContentPane().revalidate();
                p.removeAll();
                p.revalidate();
                Game g = new Game(frame,p,"Player 1", "Computer");
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
                
                frame.getContentPane().removeAll();
                frame.getContentPane().revalidate();
                p.removeAll();
                p.revalidate();
                Game g = new Game(frame,p,"Player 1", "Player 2");
            }
        });
        
    }
}



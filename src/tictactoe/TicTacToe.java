//--------------------------------------------------------------------------
// Names: Benny Lin, Sydnee Woodhouse
// Date: December 30, 2017
// Program: This is a program that allows the user to play tic-tac-toe
//--------------------------------------------------------------------------

package tictactoe;
import javax.swing.*;

public class TicTacToe extends JFrame
{
    JPanel p = new JPanel();
    JButton a = new JButton("Play Against AI");
    JButton b = new JButton("Play Against a Friend");
    
    public static void main(String[] args){
        new TicTacToe();
    }
    
    public TicTacToe(){
        super("Tic Tac Toe");
        setSize(400,300);
        setResizable(true);
        p.add(a);
        p.add(b);
        add(p);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
}



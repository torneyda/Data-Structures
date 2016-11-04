//Dan Torney
//9-20-2016

import javax.swing.*;
import javax.swing.JOptionPane;

public class SetDT 
{

	public static void main(String[] args) 
	{ 
		
		 int variable=Integer.parseInt(JOptionPane.showInputDialog("MENU\n"
		 		+ "1. Play\n2. Quit"));
		 if(variable != 2)
		 {	 
			 Game game = new Game();
		   	 game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   	 game.setTitle("SET");
		   	 game.pack();
		   	 game.show();    
		 }

	}

}

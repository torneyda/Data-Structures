import javax.swing.JOptionPane;

public class Player 
{
	private int score;
	private String name;
	
	public Player(){}
	
	public Player(int num) //num is either 1 or 2
	{
		score=0;
		name=inputName(num);
		if(num==1)
			JOptionPane.showMessageDialog(null, 
					this.name + " will use the 'Q' key to declare a set.");
		else
			JOptionPane.showMessageDialog(null, 
					this.name + " will use the 'P' key to declare a set.");
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void changeScore(int change) //increments score by either +1 or -1
	{
		score+=change;
	}
	
	private String inputName(int num)
	{
		return JOptionPane.showInputDialog("Enter the name of player " + num);
	}
	
}

import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Game extends JFrame implements KeyListener
{
	//--------------- 2 Player objects --------------------
	Player Player1 = new Player(1);  
	Player Player2 = new Player(2); 
	
	//--------------- private instance variables  ---------
	private Timer timer;
    private DrawArea drawArea;
    private JButton restart;
    private JPanel canvas;
    private BufferedImage image;
    private int SetsFound;
    
    private ArrayList<Card> Deck = new ArrayList<Card>(); //Deck holds all 81 cards
    private Card[][] board = new Card[3][4]; //board is a 2-D array of card objects
    
    public Game()
    {
   	
    SetsFound=0;	
    	
    //Function from Dat-thanh on cropping buffered images	
    try 
    {
    	image = ImageIO.read(new File("SetCards.png"));
    } catch (IOException e) 
    {
    	e.printStackTrace();
    }
    	
     //Calling these methods that only need to be called once
     BuildDeck();
     DrawOGBoard();
     
     //Rest is stuff that makes KeyListener and drawArea work correctly
     addKeyListener(this);
   	 drawArea = new DrawArea();
   	 final TimerAction ta = new TimerAction();
   	 timer = new Timer(40, ta);
   	 timer.start();
   	 canvas = new JPanel();
   	 
   	 restart = new JButton("Restart");
   	 restart.addActionListener(
   		 new ActionListener()
   		 {
   			 public void actionPerformed(ActionEvent ae)
   			 {
   				 timer.start();
   			 }
   		 }
);
   	 
   	 
   	 canvas.add(drawArea);
   	 //canvas.add(restart);
   	 drawArea.requestFocus();
   	 this.setContentPane(canvas);

    } //end default constructor
    
    public void keyPressed(KeyEvent e)
    {
   	 
   	 if (timer.isRunning())
   	 {
   		 if (e.getKeyCode() == KeyEvent.VK_Q)  //Player 1 get key
   		 {
   			JFrame frame = new JFrame();
   			PlayGame(JOptionPane.showInputDialog(
   			        frame, 
   			        "Enter three numbers separated by a space",  //Content
   			        Player1.getName() ,   //Title
   			        JOptionPane.PLAIN_MESSAGE
   			    ), Player1); //Passes Player1 object
   		 }
   		 if (e.getKeyCode() == KeyEvent.VK_P)  //Player 2 get key
   		 {
   			JFrame frame = new JFrame();
   			PlayGame(JOptionPane.showInputDialog(
   			        frame, 
   			        "Enter three numbers separated by a space",  //Content
   			        Player2.getName(),   //Title
   			        JOptionPane.PLAIN_MESSAGE
   			    ), Player2); //Passes Player 2 object
   		 }
   	 }
   	 
    } //End of keyPressed
    
    //-------------- Necessary methods --------------------------
    public void keyReleased(KeyEvent e)
    {}
    public void keyTyped(KeyEvent e)
    {}
    
    public class TimerAction implements ActionListener
    {
   	 public void actionPerformed(ActionEvent ae)
   	 {
   		 drawArea.repaint();
   	 }
    }
    
    public class DrawArea extends JPanel
    {
     // -----------  regular images -------------------------	
   	 private ImageIcon background, blackCard;
    
   	 public DrawArea()
   	 {    
   		 //---------- Sets size of window and initializes images ---------
   		 this.setPreferredSize(new Dimension(1024,600));
   		 this.setMaximumSize(new Dimension(1024, 600));
   		 background = new ImageIcon("Black-square.png");
   		 blackCard = new ImageIcon("BlackCard.png");
   	 }
   	 
   	 public void paintComponent(Graphics g)
   	 {
   		 
   		super.paintComponents(g);
   		
   		background.paintIcon(this,g,0,0); //Paints background (black screen)
   		
   		//------ Creates a second graphics object and declares font --------
   		Graphics2D g0 = (Graphics2D)g;
   		g0.setColor(Color.cyan);
	    g0.setFont( new Font("Gulim",Font.BOLD, 24));
	   	
	    //-------- Writes the numbers above the cards (1-12) ---------------
	    int temp=1;
	    for(int i=0; i<3; i++)
	    	for(int j=0; j<4; j++)
	    	{
	    		g0.drawString(Integer.toString(temp), 262 + 150*j, 25 + 191*i);
	    		temp++;
	    	}
	    
	    //--------- Writes the score of both players in top left ---------
	    g0.drawString("SCORE", 15, 30);
	    g0.drawString("_______", 15, 32);
	    g0.drawString(Player1.getName() + ":\t" + 
	    Integer.toString(Player1.getScore()), 10, 70);
	    g0.drawString(Player2.getName() + ":\t" + 
	    Integer.toString(Player2.getScore()), 10, 110);
	    
	    //------ Writes the # of sets found by both players in top right ------
	    g0.drawString("Total Sets Found", 800, 30);
	    g0.drawString("_________________", 795, 32);
	    g0.drawString(Integer.toString(SetsFound), 890, 60);
	    
	    //-------- Writes the # of cards left in the deck in top right --------
	    g0.drawString("Cards left in Deck", 795, 100);
	    g0.drawString("_________________", 795, 102);
	    g0.drawString(Integer.toString(Deck.size()), 890, 130);
	    
	    //------------------- Draws the cards on the board -------------------
   	   	for(int i=0; i<3; i++)
   	   		for(int j=0; j<4; j++)
   	   			if(board[i][j].getShape()!=5) //If it is 5 it is empty spot
   	   				//Uses getSubimage to crop image and then draw it
   	   				g.drawImage(image.getSubimage((board[i][j].getShape()*300) + 
   	   						(board[i][j].getNumber()*100), 
   	   						(board[i][j].getColor()*(3*141) + 
   	   								(board[i][j].getShading()*141)), 100, 141), 
   	   								220 + (j*50) + (j*100), 30 + (i*50) + (i*141),
   	   								null);
   	   			else //Paints a black square where there is no card
   	   				blackCard.paintIcon(this,g,
   	   						220 + (j*50) + (j*100),30 + (i*50) + (i*141));
   			
   	    CheckBoard();
   	   		
   	 } //end of PaintComponent
  	 
  	 
    } //end of drawArea
    
    //-------------------   Build Deck   ---------------------------------
    
    //Fills the Deck (arraylist of Cards); called once in default Game constructor
    
    private void BuildDeck()
    {
    	for(int i=0; i<3; i++)
    		for(int j=0; j<3; j++)
    			for(int k=0; k<3; k++)
    	    		for(int l=0; l<3; l++)
    	    			Deck.add(new Card(i,j,k,l)); 
    	//Deck in order from left to right, top to bottom (setCards picture)	
    }
    
    
    //-------------------  Draw OG Board  ---------------------------------
    
    //Fills the original, first board of cards; called once in beginning
    
    private void DrawOGBoard()
    {
    	for(int i=0; i<3; i++)
    		for(int j=0; j<4; j++)
    		{
    			//Random int from 0 to Deck size-1 (size starts at 81)
    			int temp = (int)(Math.random()*Deck.size());
    			board[i][j]=Deck.get(temp);
    			Deck.remove(temp);
    		}
    	// ----------------- How to start with a board of no sets -------------
    	
    	/*board[0][0] = Deck.get(70);
    	board[0][1] = Deck.get(68);
    	board[0][2] = Deck.get(16);
    	board[0][3] = Deck.get(35);
    	
    	board[1][0] = Deck.get(23);
    	board[1][1] = Deck.get(7);
    	board[1][2] = Deck.get(1);
    	board[1][3] = Deck.get(72);
    	
    	board[2][0] = Deck.get(2);
    	board[2][1] = Deck.get(31);
    	board[2][2] = Deck.get(76);
    	board[2][3] = Deck.get(30);*/

    	CheckBoard();
    }
    
    
    
    //-------------------  Update Board  -----------------------------------
    
    //Updates Board after sets have been made or if cards are replaced
    //Called in methods PlayGame() and ReplaceBoard()
    
    //input is a string of numbers separated by spaces
    //remove- true means to remove cards from deck; false means replace them
    private void UpdateBoard(String input, boolean remove)
    {
    	//Splits the input into 3 numbers(still strings)
    	String numbs[] = input.split(" "); 
    	
    	//holds the cards from the board
    	Card cards[] = new Card[3];
    	
    	//translates the number 1-12 of the card to a Card object
    	for(int i=0; i<3; i++)
    		cards[i] = board[(Integer.parseInt(numbs[i])-1)/4]
    				[(Integer.parseInt(numbs[i])-1) %4];
    	
    	//Removes or replaces the cards on board that match the cards in the input
    	for(int q=0; q<3; q++)
    		for(int i=0; i<3; i++)
    			for(int j=0; j<4; j++)
    				if(board[i][j].equals(cards[q]))
    				{
    					if(Deck.size()>0)
    					{
	    					int temp = (int)(Math.random()*Deck.size());
	    					//Swaps cards so the board ones go back to deck
	    					//Used only when the cards need to be replaced
	    	    			if(!remove) 
	    	    				Deck.add(board[i][j]);
	    	    			board[i][j]=Deck.get(temp);
	    	    			Deck.remove(temp);
    					}
    					else //no deck left so replaces card with black square
    					{
    						//Signal to print a black square instead
    						board[i][j]=new Card(5,5,5,5); 
    					}
    				}
    } 
    
    
    //-------------------  Play Game  -------------------------------------
    
    //Calls the necessary game methods
    //Is called in the keyListener get keys for players 1 and 2
    
    private void PlayGame(String input, Player player)
    {
    	if(CheckValidSet(input))
    	{
    		JOptionPane.showMessageDialog(null, "Congrats! You found a set!");
    		UpdateBoard(input, true);
    		UpdateScore(1, player);
    		SetsFound++;
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Invalid set. Sorry!", "You Wrong",
    				JOptionPane.ERROR_MESSAGE);
    		UpdateScore(-1, player);
    	}
    	
    	if(!CheckBoard()) //If there are no available sets on the board
    	{	
    		if(Deck.size()>2)
    			ReplaceBoard();
    		else
    			GameOver();
    	}	
    }
    
    
    //------------------  Check Valid Set  --------------------------------
    
    //Checks to see if the set entered is valid
    
    private boolean CheckValidSet(String input)
    {
    	//Same as in UpdateBoard
    	String numbs[] = input.split(" "); 
    	Card cards[] = new Card[3];
    	
    	//If all 4 in checks is true it is a valid set
    	boolean checks[] = new boolean[4];
    	int count = 0;
    	
    	//Fills cards[] with the card objects from board
    	for(int i=0; i<3; i++)
    		cards[i] = board[(Integer.parseInt(numbs[i])-1)/4]
    				[(Integer.parseInt(numbs[i])-1) %4];
    	
    	//Makes all values of checks[] false
    	for(int i=0; i<4; i++)
    		checks[i]=false;
    	
    	//If any of the cards are the same it is invalid set
    	if(cards[0]==cards[1] || cards[0]==cards[2] || cards[1]==cards[2])
    		return false;
    	
    	//If any of the cards have 5 it means it is a black square; invalid set
    	if(cards[0].getColor()==5 || cards[1].getColor()==5 || 
    			cards[2].getColor()==5)
    		return false;
    	
    	//Checks Color for all same or all different
    	if((cards[0].getColor() == cards[1].getColor() && 
    			cards[1].getColor() == cards[2].getColor()) ||
    			((cards[0].getColor() != cards[1].getColor() && 
    			cards[1].getColor() != cards[2].getColor()) &&
    			cards[0].getColor() != cards[2].getColor()))
    		checks[0]=true;
    	
    	//Checks Number for all same or all different
    	if((cards[0].getNumber() == cards[1].getNumber() && 
    			cards[1].getNumber() == cards[2].getNumber()) ||
    			((cards[0].getNumber() != cards[1].getNumber() && 
    			cards[1].getNumber() != cards[2].getNumber()) &&
    			cards[0].getNumber() != cards[2].getNumber()))
    		checks[1]=true;
    	
    	//Checks Shape for all same or all different
    	if((cards[0].getShape() == cards[1].getShape() && 
    			cards[1].getShape() == cards[2].getShape()) ||
    			((cards[0].getShape() != cards[1].getShape() && 
    			cards[1].getShape() != cards[2].getShape()) &&
    			cards[0].getShape() != cards[2].getShape()))
    		checks[2]=true;
    	
    	//Checks Shading for all same or all different
    	if((cards[0].getShading() == cards[1].getShading() && 
    			cards[1].getShading() == cards[2].getShading()) ||
    			((cards[0].getShading() != cards[1].getShading() && 
    			cards[1].getShading() != cards[2].getShading()) &&
    			cards[0].getShading() != cards[2].getShading()))
    		checks[3]=true;
    	
    	//Tallys count for all trues in checks[]
    	for(int i=0; i<4; i++)
    		if(checks[i])
    			count++;
    	
    	if(count==4) //4 trues
    		return true;
    	else
    		return false;
    }
    
    
    //-----------------  Update Score  -----------------------------------
    
    //Updates the score; valid set is +1; invalid set is -1
    
    private void UpdateScore(int change, Player player)
    {
    	player.changeScore(change);
    }
    
    //-----------------  Check Board  ------------------------------------
    
    //Checks the board for sets to make sure there is always a set; 
    //returns false if there are no sets
    
    //Called all over the place to ensure there is always a set
    
    private boolean CheckBoard()
    {
    	int count=0;
    	
    	for(int i=1; i<13; i++)
    		for(int j=1; j<13; j++)
    			for(int k=1; k<13; k++)
    				if(CheckValidSet(Integer.toString(i) + " " + 
    			Integer.toString(j) + " " + Integer.toString(k)))
    						{	
    							count++;
    							//Can use to print out valid sets
    							//Helpful for testing game
    							//System.out.println(i + " " + j + " " + k);
    						}
    	if(count==0) //0 valid sets
    		return false;
  
    	return true;
    }
    
    //------------------  Game Over  ---------------------------------
    
    //Ends the game
    
    private void GameOver()
    {
    	String winner, loser;
    	int winScore, loseScore;
    	
    	//If it is a tie
    	if(Player1.getScore()==Player2.getScore())
    		JOptionPane.showMessageDialog(null, "It's a tie!", "GAME OVER",
    				JOptionPane.PLAIN_MESSAGE);
    	else 
    	{	
    		//Player 1 has a higher score
    		if(Player1.getScore()>Player2.getScore())
    		{
    			winner=Player1.getName();
    			loser=Player2.getName();
    			winScore=Player1.getScore();
    			loseScore=Player2.getScore();
    		}
    		//Player 2 has a higher score
    		else
    		{
    			winner=Player2.getName();
    			loser=Player1.getName();
    			winScore=Player2.getScore();
    			loseScore=Player1.getScore();
    		}
    		JOptionPane.showMessageDialog(null, 
    				winner + " beat " + loser + " by a score of " + winScore + 
    				" to " + loseScore + ".",
    				"GAME OVER", JOptionPane.PLAIN_MESSAGE);
    	}
    	timer.stop();
    	
    }
    
    //------------------  Replace Board  ---------------------------------
    
    //Replaces cards on board if there is no set
    
    private void ReplaceBoard()
    {
    	//Used to pull random numbers from; 1-12 
    	//values removed once called to avoid repeats
    	ArrayList<Integer> Numbers = new ArrayList<Integer>();
    	for(int i=1; i<13; i++)
    		Numbers.add(i);
    	
    	//Updates board with 3 random cards; 
    	//false to signify replacement not removal
    	UpdateBoard(Integer.toString(Numbers.remove((int)
    			(Math.random()*Numbers.size()))) + 
    			" " + Integer.toString(Numbers.remove((int)
    					(Math.random()*Numbers.size()))) + 
    			" " + Integer.toString(Numbers.remove((int)
    					(Math.random()*Numbers.size()))), false);
		//Prints to signify there was a change on the board; only way of knowing
    	System.out.println("Changed");
		CheckBoard();
    }
    
    
    
} //end of Game class

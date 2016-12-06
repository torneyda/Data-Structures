import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;


public class Agram 
{

	public static void main(String[] args) 
	{
		for(int q=0; q<5; q++)
		{
			String VALUE, SUIT = null;
			
			
			String input=JOptionPane.showInputDialog(
					"Input all 6 cards, each one separated by a single space");
			String[] cards = input.split(" ");
			//cards goes from indexes 0 to 5, each one containing a 2 character string
			
			String Osuit = cards[0].substring(1,2);
			String Ovalue = cards[0].substring(0,1);
			
			if(Ovalue.equals("A"))
				Ovalue="1";
			if(Ovalue.equals("T"))
				Ovalue="10";
			if(Ovalue.equals("J"))
				Ovalue="11";
			if(Ovalue.equals("Q"))
				Ovalue="12";
			if(Ovalue.equals("K"))
				Ovalue="13";
			
			int OvalueNum = Integer.parseInt(Ovalue); 
			
			//Checks to see if dealer has the suit
			boolean hasSuit=false;
			
			for(int i=1; i<6; i++)
			{
				if(cards[i].substring(1,2).equals(Osuit))
					hasSuit=true;
			}
			
			if(hasSuit)
			{
				//Adds all values with that suit to an list
				ArrayList<Integer> suitCards = new ArrayList<Integer>();
				
				for(int i=1; i<6; i++)
				{
					if(cards[i].substring(1,2).equals(Osuit))
					{
						String val = cards[i].substring(0,1);
						if(val.equals("A"))
							val="1";
						if(val.equals("T"))
							val="10";
						if(val.equals("J"))
							val="11";
						if(val.equals("Q"))
							val="12";
						if(val.equals("K"))
							val="13";
						
						int value = Integer.parseInt(val);
						
						suitCards.add(value);
					}
							
				}
				
				//Sorts the list into increasing order
				Collections.sort(suitCards);
				
				//valueNum becomes the smallest that is greater or the smallest value
				int valueNum=0;
				for(int i=0; i<suitCards.size(); i++)
					if(suitCards.get(i)>OvalueNum)
					{
						valueNum=suitCards.get(i);
						break;
					}
				if(valueNum==0)
					valueNum=suitCards.get(0);
				
				VALUE = valueNum + "";
				if(VALUE.equals("13"))
					VALUE="K";
				if(VALUE.equals("12"))
					VALUE="Q";
				if(VALUE.equals("11"))
					VALUE="J";
				if(VALUE.equals("10"))
					VALUE="T";
				if(VALUE.equals("1"))
					VALUE="A";
				
				SUIT=Osuit;
			}
			else
			{
				//Adds all values with that suit to an list
				ArrayList<Integer> cardsValue = new ArrayList<Integer>();
				
				for(int i=1; i<6; i++)
				{
					String val = cards[i].substring(0,1);
					if(val.equals("A"))
						val="1";
					if(val.equals("T"))
						val="10";
					if(val.equals("J"))
						val="11";
					if(val.equals("Q"))
						val="12";
					if(val.equals("K"))
						val="13";
					
					int value = Integer.parseInt(val);
					
					cardsValue.add(value);
							
				}
				
				Collections.sort(cardsValue);
				VALUE=cardsValue.get(0) + "";
				
				if(VALUE.equals("1"))
					VALUE="A";
				if(VALUE.equals("10"))
					VALUE="T";
				if(VALUE.equals("11"))
					VALUE="J";
				if(VALUE.equals("12"))
					VALUE="Q";
				if(VALUE.equals("13"))
					VALUE="K";
				
				for(int i=1; i<6; i++)
				{
					if(cards[i].substring(0,1).equals(VALUE))
						SUIT = cards[i].substring(1,2);
				}
			}
			
			System.out.println(q+1 + ". " + VALUE+SUIT);
			
		} //end of for loop

	} //end of main

} //end of class

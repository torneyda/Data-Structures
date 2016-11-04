import javax.swing.JOptionPane;

//Dan Torney
//11-2-16


public class LinkedList 
{

	public static void main(String[] args) 
	{
		//object link is used to call methods
		LinkedListKMC link = new LinkedListKMC(); 
		
		//0 at first and stays 0 unless choice 13 to end program
		int WeirdTemp=0;
		
		while(WeirdTemp==0)
		{
			//Displays menu and 
			String Sinput = JOptionPane.showInputDialog(
					"                  MENU\n\n"
					+ "1. addAtBack\n"
					+ "2. addAtFront\n"
					+ "3. print\n"
					+ "4. delete\n"
					+ "5. size\n"
					+ "6. insertPos\n"
					+ "7. insert\n"
					+ "8. getLast\n"
					+ "9. clear\n"
					+ "10. find\n"
					+ "11. removeDuplicates\n"
					+ "12. printBackwards\n"
					+ "13. End the program\n\n");
			
			int input = Integer.parseInt(Sinput);
			
			switch(input)
			{
				case 1://done
					link.addAtBack(Integer.parseInt(
							JOptionPane.showInputDialog("Input "
							+ "a value for addAtBack")));
					link.print();
					break;
				case 2: //done
					link.addAtFront(Integer.parseInt(
							JOptionPane.showInputDialog("Input "
							+ "a value for addAtFront")));
					link.print();
					break;
				case 3: //done
					link.print();
					break;
				case 4://done
					link.delete(Integer.parseInt(
							JOptionPane.showInputDialog("Input "
							+ "a value to delete")));
					link.print();
					break;
				case 5://done
					System.out.println(link.size());
					break;
				case 6://done
					link.insertPos(Integer.parseInt(
							JOptionPane.showInputDialog("Input "
							+ "a value to insert")),Integer.parseInt(
									JOptionPane.showInputDialog("Input "
							+ "a position in between 1 and " + (link.size()+1))));
					link.print();
					break;
				case 7://done
					link.insert(Integer.parseInt(
							JOptionPane.showInputDialog("Input "
							+ "a value to insert")),Integer.parseInt(
									JOptionPane.showInputDialog("Input "
							+ "a search value")));
					link.print();
					break;
				case 8://done
					System.out.println(link.getLast());
					break;
				case 9://done
					link.clear();
					link.print();
					break;
				case 10://done
					System.out.println(link.find(Integer.parseInt(
							JOptionPane.showInputDialog("Input "
							+ "a value to find"))));
					break;
				case 11://done
					link.removeDuplicates();
					link.print();
					break;
				case 12://done
					link.printBackwards(-1);
					System.out.println();
					break;
				case 13: //end the program
					WeirdTemp=1;
					break;
				default: //default case tells you to try again
					JOptionPane.showMessageDialog(null, "Invalid Choice", "ERROR",
		    				JOptionPane.ERROR_MESSAGE);
					break;
			}
		

		}
		
	} //end main

}

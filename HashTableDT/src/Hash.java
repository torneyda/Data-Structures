import javax.swing.JOptionPane;

//Dan Torney
//11-7-16

public class Hash 
{

	public static void main(String[] args) 
	{
		HashTable hash = new HashTable(Integer.parseInt(JOptionPane.showInputDialog(
				"How many buckets do you want?")));
		
		int WeirdTemp=0;
		
		//weirdTemp stays at 0 unless end program it changes to 1
		while(WeirdTemp==0)
		{
			
			String Sinput = JOptionPane.showInputDialog(
					"                  MENU\n\n"
					+ "1. delete\n"
					+ "2. insert\n"
					+ "3. printTable\n"
					+ "4. End program\n\n");
			
			int input = Integer.parseInt(Sinput);
			
			switch(input)
			{
				case 1:
					hash.delete(JOptionPane.showInputDialog("Input "
							+ "a key to be deleted"));
					break;
				case 2:
					hash.insert(JOptionPane.showInputDialog("Input a key"),
							new DataType(Double.parseDouble(JOptionPane.showInputDialog("Input a GPA")),
									JOptionPane.showInputDialog("Input a 1st name")));
					break;
				case 3:
					hash.printTable();
					break;
				case 4:
					WeirdTemp=1;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Invalid Choice", "ERROR",
		    				JOptionPane.ERROR_MESSAGE);
					break;
			}
			
		} //end of while loop

	} //end of main

}

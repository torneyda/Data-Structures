//Dan Torney
//10-21-16

import apcslib.*;
import chn.util.*;
import chndocs.*;


public class Amino {

	public static void main(String[] args) 
	{
		
		FileInput inCodon = new FileInput("codon_to_amino.txt");
		FileInput TestData = new FileInput("rna_info.txt");
		FileOutput outCodon = new FileOutput("amino_result_DT.txt");
		
		//FileInput TestData = new FileInput("H:\\DNA\\rna_info.txt");
		//FileInput inCodon = new FileInput("H:\\DNA\\codon_to_amino.txt");
		//FileOutput outCodon = new FileOutput("H:\\DNA\\amino_result_DT.txt");
		
		
		String codons[] = new String[20]; //All lines minus start and stop
		
		int lineCount=1;
		
		for(int i=0; i<20; i++)
			codons[i]=inCodon.readLine();
		
		String data = ""; 
		
		while(TestData.hasMoreLines())
		{
			int dataIndex=0;
			boolean hasStarted=false;
			data=TestData.readLine();
			
			//print line number then tab
			outCodon.print(Integer.toString(lineCount) + "    "); 
			
			while(dataIndex+2<data.length()) //There is always a section of 3 left
			{

					if(hasStarted)
					{
						String codon = data.substring(dataIndex, dataIndex+3);
						
						//end codons
						if(codon.equals("UAA") || codon.equals("UGA") || 
								codon.equals("UAG")) 
						{	
							if(dataIndex==0) //immediately following a start
								outCodon.print("null ");
							else
								outCodon.print("    ");
							hasStarted=false;
							data=data.substring(dataIndex+3); //removes that codon
							dataIndex=-3; //will reset to 0 because it adds 3 at end
						}
							
						
						for(int i=0; i<20; i++)
							if(codons[i].contains(codon))
								outCodon.print(codons[i].charAt(0));
							//prints the letter of the matching codon
					}
					else
					{
						//if there is no start or if it is at the end
						if(data.indexOf("AUG")==-1 || 
								data.indexOf("AUG")+3 == data.length()) 
						{
							break;
						}
						else
						{
							//starts after the start codon
							data=data.substring(data.indexOf("AUG")+3); 
							dataIndex=-3; //will reset to 0 at end when adds 3
							hasStarted=true;
						}
						
					}
					
				
				dataIndex+=3; //Goes to next one
				
			}
				
			lineCount++;
			outCodon.println();
		}
		
		outCodon.close();

		System.out.print("done");

	} //end of main

}

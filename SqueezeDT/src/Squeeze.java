//Dan Torney
//10/3/2016

import apcslib.*; 
import chn.util.*;


public class Squeeze 
{

	public Squeeze (FileInput OGFile, FileOutput AfterFile)
	{
		String fileChange; //Intermediate stage for reading in lines
		

		while(OGFile.hasMoreLines())
		{
			fileChange = OGFile.readLine();
			
			int Spaces=0;
			int temp=0;
			
			while(fileChange.length() > temp && ((int)fileChange.charAt(temp)==9 || (int)fileChange.charAt(temp)==32)) //Short circuiting
			{
				if((int)fileChange.charAt(temp)==32)
					Spaces++;
				if((int)fileChange.charAt(temp)==9)
					Spaces+=4;
				temp++;
			}
			
			AfterFile.println(Format.right(Spaces + "  " + fileChange.trim(), 2)); 
			
			
		}
		AfterFile.close();
	}
	
	public static void main(String[] args) 
	{
		FileInput inFile = new FileInput("before.txt"); // Input file
		FileOutput outFile = new FileOutput("afterDT.txt"); //Final file
		
		Squeeze squeeze = new Squeeze(inFile, outFile);
		
		System.out.print("done");
		
	}

}

// KMC
// Data Structures in Java

// KMC linked list class


public class LinkedListKMC
{

 	private ListNode first;
 	
 	
 	//-------------
 	// Constructor
 	//-------------
 	
 	public LinkedListKMC()
 	{
 		first = null;			// first is initially the external pointer
 	}
 	
 	
 	//-------------------------------
 	// Get first element in the list
 	//-------------------------------
 	
 	public Object getFirst()
 	{
 		if(first==null)
 			return null;
 		else
 			return first.getValue();

 	}
 	
 	
 	//---------------------
 	// Add at front of list
 	//---------------------
 	
 	public void addAtFront (Object value)
 	{
 		first = new ListNode(value, first); 	
 	}
 	
 	
 	
 	//---------------------
 	// Print the list
 	//---------------------
 	
 	public void print()
 	{
 		//if it is null print empty; otherwise print each one until null
 		if (first == null)
 			System.out.println("The list is empty.");
 		else
 		{
 			ListNode current = first;
 			while (current != null)
 			{
 				System.out.print(current.getValue() + " ");
 				current = current.getNext();
 			}
 			System.out.println();
 		}
 	
 	}
 	
 	//------------------- addAtBack -----------------
 	
 	public void addAtBack(Object value)
 	{
 		ListNode temp = first;
 		//if list is empty add to front
 		if(temp==null)
 			addAtFront(value);
 		else
 		{
	 		//goes to the last node and sets the next one to the new node
 			while(temp.getNext()!=null)
	 		{
	 			temp=temp.getNext();
	 		}
	 		temp.setNext(new ListNode(value, null));
 		}
 	}
 	
 	
 	//------------------- delete -----------------
 	
 	 public void delete(Object value)
 	 {
 		 ListNode temp=first;
 		 
 		 //if empty list nothing happens
 		 if(temp==null)
 			 return;

 		 //Goes through until the end of the linked list
 		 while(temp!=null)
 		 {
 			//Only used if the list has one value; clears if the value is deleted
 			 if(temp.getNext()==null)
 			 {
 				if(temp.getValue()==value)
 				{
 					clear();
 					return;
 				}
 				return;
 			 }

 			//If the value is in the very first node of the list you go over it 
 			if(temp==first && temp.getValue()==value)
 			{
 				first=first.getNext();
 				temp=first;
 			}
 			//If the next value is equal to it
 			else if(temp.getNext().getValue()==value)
 			{
 				temp.setNext(temp.getNext().getNext());
 			}
 			//cycles to the next node
 			else
 				temp=temp.getNext();
 		 }
 		 
 		 
 	 }
 	 
 	 
 	//------------------- Size -----------------
  	
  	public int size()
  	{
  		ListNode temp=first;
  		int count=0; //counts the nodes
  		while(temp!=null)
  		{
  			count++;
  			temp=temp.getNext();
  		}
  		return count;
  	}
  	
 	
    //------------------- insertPos -----------------
 	
   	public void insertPos(Object value, int pos)
   	{
   		ListNode temp=first;
   		
   		//very beginning of list or at the very end
   		if(pos==1)
   			addAtFront(value);
   		else if(pos==size()+1)
   			addAtBack(value);
   		else
   		{
   			//makes temp equal to the node in the list before pos
   			for(int i=2; i<pos; i++)
   			//temp is now right before where you want to put it
   				temp=temp.getNext(); 
   			ListNode p=new ListNode(value,temp.getNext());
   			temp.setNext(p);
   		}
   	}
   	
 	
    //------------------- insert -----------------
 	
   	public void insert(Object value, Object search)
   	{
   		//If the search value is not in the list
   		if(!find(search))
   			System.out.println("Search value is not in the list.");
   		
   		ListNode temp=first;
   		int pos=1;
   		int valPos=0; //pos of last occurrence
   		
   		//goes through the list and sets valPos to the last occurrence of search
   		while(temp!=null)
   		{
   			if(temp.getValue()==search)
   				valPos=pos;

   			temp=temp.getNext();
   			pos++;
   		}
   		
   		
   		insertPos(value,valPos);
   	}
   	
   	
    //------------------- getLast -----------------
 	
   	public Object getLast()
   	{
   		ListNode temp=first;
   		
   		if(temp==null)
   			return null;     
  
   		//goes until temp equals the last node
   		while(temp.getNext()!=null)
   		{
   			temp=temp.getNext();
   		}
   		
   		return temp.getValue();
   	}
   	
   	
    //------------------- clear -----------------
 	
   	public void clear()
   	{
   		//sets first to null
   		first=null;
   	}
   	
   	
    //------------------- find -----------------
 	
   	public boolean find(Object value)
   	{
   		ListNode temp=first;
   		
   		//goes through the whole list (if necessary)
   		while(temp!=null)
   		{
   			//if it is found return true
   			if(temp.getValue()==value)
   				return true;
   			temp=temp.getNext();
   		}
   		
   		return false;
   	}
   	
   	
    //------------------- remove duplicates -----------------
 	
   	public void removeDuplicates()
   	{
   		if(first==null)
   			return;
   		
   		ListNode temp = first;
   		ListNode after;
   		ListNode before=first;
   		
   		//while temp is not equal to the last node 
   		while(temp.getNext()!=null)
   		{
   			//after equals node after temp
   			after=temp.getNext();
   			//before is the always node right before after
   			before=temp;
   			
   			//checks temp against all nodes in list
   			while(after!=null)
	   		{
   				//if temp is equal to the next value, it removes that next one
	   			if(temp.getValue()==after.getValue())
	   			{
	   				before.setNext(after.getNext());
	   				after=after.getNext();
	   			}
	   			else
	   			{
	   				after=after.getNext();
	   				before=before.getNext();
	   			}
	   				
	   		}
	   		if(temp.getNext()!=null)
	   			temp=temp.getNext();
   		}
   	}
   	
   	
    //------------------- print backwards -----------------
 	
   	public void printBackwards(int value) //can take a parameter; utiize end
   	{
   		ListNode temp=first;
   		
   		if(value==-1) //first time
   		{
   			if(first==null)
   			{
   				System.out.print("The list is empty.");
   				return;
   			}
   			
   			while(value!=size())
   				value++; //leaves as the position of the last node
   		}
   		
   		if(value!=0) //base case
   		{
   			for(int i=1; i<value; i++)
   				temp=temp.getNext();
   			
   			System.out.print(temp.getValue() + " ");
   			
   			value--;
   			printBackwards(value);
   		}
   	}
   	
//-------------  Check for repeat key and first name  --------------------
   	
   	public boolean checkOK(String key, DataType data)
   	{
   		ListNode current = first;
   		for(int i=1; i<=size(); i++)
   		{
   			//If the key and the first name is equal to that of current,
   			//It is not ok, otherwise
   			if(((TableEntry)(current.getValue())).getKey().equals(key))
   				if(((TableEntry)(current.getValue())).getData().getName().equals(data.getName()))
   					return false;
   			current=current.getNext();
   		}
   		return true;
   	}
   	
   	
//-------------------------  Delete keys  ------------------------------
   	
// Basically combines the delete method with the checkOK method
   	
   	public void deleteKey(String key)
   	{
   		ListNode temp = first;
   		
   		//Goes through until the end of the linked list
		 while(temp!=null)
		 {
			//Only used if the list has one value; clears if the value is deleted
			 if(temp.getNext()==null)
			 {
				if(((TableEntry)(temp.getValue())).getKey().equals(key))
				{
					clear();
					return;
				}
				return;
			 }

			//If the value is in the very first node of the list you go over it 
			if(temp==first && ((TableEntry)(temp.getValue())).getKey().equals(key))
			{
				first=first.getNext();
				temp=first;
			}
			//If the next value is equal to it
			else if(((TableEntry)(temp.getNext().getValue())).getKey().equals(key))
			{
				temp.setNext(temp.getNext().getNext());
			}
			//cycles to the next node
			else
				temp=temp.getNext();
		 }
   	}
   	
   	
   	
} //end of class
import javax.swing.JOptionPane;


public class HashTable 
{
	private int size;
	private LinkedListKMC[] T;
	
	//creates the array T with the given size
	public HashTable(int tableSize)
	{
		size = tableSize;
		T = new LinkedListKMC[size];
	}
	
	//Returns the absolute value of the hashCode; needs to be positive so that
	//it can have a position in the array
	public int hash(String key)
	{
		return Math.abs(key.hashCode() % size);
	}
	
	//If the index of the array is null, do nothing;
	//Otherwise call the deleteKey method in the linkedlistKMC class
	public void delete(String Key)
	{
		if(T[hash(Key)]!=null)
		{
			T[hash(Key)].deleteKey(Key);
		}
	}
	
	//If the array index is null, aka empty, create a new linkedList and add it
	public void insert(String key, DataType data)
	{
		if(T[hash(key)]==null)
		{
			T[hash(key)]=new LinkedListKMC();
			T[hash(key)].addAtBack(new TableEntry(key, data));
		}
		//If it is not empty, first check to see if the same name already exists
		else if(T[hash(key)].checkOK(key, data))
				T[hash(key)].addAtBack(new TableEntry(key, data));

	}
	
	//calls the print from LinkedList, which ends up calling the toString of
	//TableEntry and DataType
	public void printTable()
	{
		for(int i=0; i<size; i++)
		{
			if(T[i]!=null)
			{
				T[i].print();
			}
		}
	}
	
	

} //end of class

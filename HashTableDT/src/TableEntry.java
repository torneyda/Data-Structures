
public class TableEntry 
{
	private String key;
	private DataType data;
	
	public TableEntry()
	{
		
	}
	
	public TableEntry(String theKey, DataType theData)
	{
		key=theKey;
		data=theData;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public DataType getData()
	{
		return data;
	}
	
	public String toString()
	{
		return "" + key + ", " + data;
	}
	
}

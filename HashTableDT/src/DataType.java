
public class DataType 
{
	private double GPA;
	private String firstName;
	
	public DataType()
	{
		
	}
	
	public DataType(double theGPA, String theFirstName)
	{
		GPA=theGPA;
		firstName=theFirstName;
	}
	
	public String getName()
	{
		return firstName;
	}
	
	public String toString()
	{
		return firstName + ": " + GPA + "  ";
	}
	
}

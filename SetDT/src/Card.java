
public class Card
{
	private int Number, Color, Shape, Shading;
	
	public Card()
	{
		Number = Color = Shape = Shading = 0;
	}
	
	public Card(int c, int S, int s, int n) //Specific Order
	{
		Number=n;
		Color=c;
		Shape=s;
		Shading=S;
	}
	
	public int getNumber()
	{
		return Number;
	}
	
	public int getShape()
	{
		return Shape;
	}
	
	public int getShading()
	{
		return Shading;
	}
	
	public int getColor()
	{
		return Color;
	}


}

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
 
public class QTree
{
	
	
	Scanner in;
	PrintStream out;
	
    //initializes the game
	public QTree(InputStream in,PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
		//Please initialize your data here
		String root = "Is it alive?";
	}
	
    
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		//??
		
        
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}

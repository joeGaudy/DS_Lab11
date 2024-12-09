import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Node
{
	private String Q;
	private Node N;
	private Node Y;
	
	public Node(String Q)
	{
		this.Q = Q;
		this.N = null;
		this.Y = null;
	}
}

public class QTree
{
	
	Scanner in;
	PrintStream out;
	private Node root;
	
    //initializes the game
	public QTree(InputStream in,PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
		//Please initialize your data here
		root = new Node("Is it alive?");
		root.N = new Node("Is it a rock?");
		root.Y = new Node("Is it a duck?");
	}
	
    
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		playGame(root.N);
	}
	
	private void playGame(Node node)
	{
		out(node.Q);
		R = in;
		if (R == "Y" || R == "y")
		{
			out(node.Y.Q);
		}
		else
		{
			out(node.N.Q);
		}
		
		R = in;

		if (R == "Y" || R == "y" && node.Y.Y == null)
		{				
			out(I_WIN);
			out(PLAY_AGAIN);
			if (R == "Y" || R == "y" && node.Y.Y == null)
			{
				playGame(root);
			}
		}
		
		if (R == "Y" || R == "y")
		{
			playGame(node.Y);
		}
		
		if (node.N.N == null)
		{
			out(WHAT_IS_THE_ANSWER);
			A = in;
			out(NEW_QUESTION + A);
			Q = in;
			out("Answering yes to " + Q + " means " + A + "?")
			R = in;
			if (R == "Y" || R == "y")
			{
				Node temp = node.Y;
				Node newQ = new Node(Q);
				newQ.N = temp;
				newQ.Y = IS_IT_A + A;
				node.Y = newQ;
				out(PLAY_AGAIN);
				if (R == "Y" || R == "y" && node.Y.Y == null)
				{
					playGame(root);playGame(node.Y);
				}
			}
			else
			{
				Node temp = node.N;
				Node newQ = new Node(Q);
				newQ.Y = temp;
				newQ.N = IS_IT_A + A;
				node.N = newQ;
				out(PLAY_AGAIN);
				if (R == "Y" || R == "y" && node.Y.Y == null)
				{
					playGame(root);
				}
			}
		}
		else
		{
			playGame(node.N);
		}
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}

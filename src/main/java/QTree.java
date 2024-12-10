import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class QTree
{
	
	Scanner in;
	PrintStream out;
	private Node root;
	
    //initializes the game
	
	public class Node
	{
		private Node prev;
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
	
	public QTree(InputStream in,PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
		//Please initialize your data here
		root = new Node("Is it alive?");
		root.N = new Node("Is it a Rock?");
		root.Y = new Node("Is it a Duck?");
		root.N.prev = root;
		root.Y.prev = root;
		root.prev = null;
	}
	
    
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		playGame(root, "");
	}
	
	private void playGame(Node node, String prevR)
	{
		out.println(node.Q);
		String Rprime; 
		Rprime = in.nextLine();
		
		if ((Rprime.equals("Y") || Rprime.equals("y")) && node.Y == null)
		{				
			out.println(Strings.I_WIN);
			out.println(Strings.PLAY_AGAIN);
			String R = in.nextLine();
			if (R.equals("Y") || R.equals("y"))
			{
				playGame(root,""); 
			}
			else
			{
				return;
			}
		}
		
		else if (node.Y == null)
		{
			String [] words = node.Q.split(" ");
			String word = words[3].substring(0,words[3].length()-1);
			out.println(Strings.WHAT_IS_THE_ANSWER);
			String A = in.nextLine();
			out.println(Strings.NEW_QUESTION + word + " and a " + A);
			String Q = in.nextLine();
			out.println("Answering yes to " + Q + " means " + A + "?");
			String R = in.nextLine();
			if (R.equals("Y") || R.equals("y"))
			{
				Node temp = node;
				Node newQ = new Node(Q);
				newQ.prev = node.prev;
				newQ.N = temp;
				newQ.Y = new Node(Strings.IS_IT_A + A + "?");
				newQ.N.prev = newQ;
				newQ.Y.prev = newQ;
				if (prevR.toLowerCase().equals("y"))
				{
					newQ.prev.Y = newQ;
				}
				else
				{
					newQ.prev.N = newQ;
				}
				out.println(Strings.THANKS);
				out.println(Strings.PLAY_AGAIN);
				R = in.nextLine();
				if (R.equals("Y") || R.equals("y"))
				{
					playGame(root,"");
				}
				else
				{
					return;
				}
			}
			else
			{
				Node temp = node;
				Node newQ = new Node(Q);
				newQ.prev = node.prev;
				newQ.Y = temp;
				newQ.N = new Node(Strings.IS_IT_A + A + "?");
				newQ.N.prev = newQ;
				newQ.Y.prev = newQ;
				if (prevR.toLowerCase().equals("y"))
				{
					newQ.prev.Y = newQ;
				}
				else
				{
					newQ.prev.N = newQ;
				}
				out.println(Strings.THANKS);
				out.println(Strings.PLAY_AGAIN);
				R = in.nextLine();
				if (R.equals("Y") || R.equals("y"))
				{
					playGame(root,"");
				}
				else
				{
					return;
				}
			}
		}
		else if (Rprime.equals("Y") || Rprime.equals("y"))
		{
			playGame(node.Y, Rprime);
		}
		else
		{
			playGame(node.N, Rprime);
		}
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}

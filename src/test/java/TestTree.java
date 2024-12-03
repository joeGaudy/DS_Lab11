import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class TestTree 
{
	
	Scanner comp;
	PrintStream me;
	boolean show = true; //if set to true, will print out respones to and from the game.  Set to false to make it faster.
	
    
    //initializes the Testing environment with piped streams (Queues)
	public TestTree() throws IOException
	{
		PipedOutputStream inputHandle = new PipedOutputStream();
		PipedInputStream input= new PipedInputStream(inputHandle);
		
		PipedOutputStream output = new PipedOutputStream();
		PipedInputStream outputHandle= new PipedInputStream(output);
		
		QTree game = new QTree(input,new PrintStream(output));

		Thread t = new Thread(()->{game.playGame();});
		t.start();

        comp = new Scanner(outputHandle);
		me = new PrintStream(inputHandle);

	
	}

    /*
        Helper methods for IO and testing
    
        These methods are beefed up versions of assert.  
    
    */
    
    //Use this to "check" if the string from the program is correct.
	public void check(String s)
	{
		String observed = comp.nextLine();
		if(show) {System.out.println("observed:"+observed);}
		//will not work with simple assert statements due to multithreading	
		if( ! observed.equals(s))
		{
			System.out.println("expected "+s+" but got "+observed);
			System.exit(1);
		}
	}
	
	public void say(String s)
	{
		me.println(s);
		me.flush(); //greatly increases speed of program, lets other side know there is new data.
		if(show) {System.out.println("said:"+s);}
	}
	
	
	public void run()
	{
		
		check(Strings.IS_IT_ALIVE);
        say("Y");
        //now what? Think of all the input and outputs here...
        check(Strings.IS_IT_A + Strings.DUCK + "?");
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("Y");
        check(Strings.IS_IT_A + Strings.DUCK + "?");
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Baby");
        check(Strings.NEW_QUESTION + Strings.DUCK + " and a " + "Baby");
        say("Does it say goo goo gah gah?");
        check("Answering yes to Does it say goo goo gah gah? means Baby?");
        say("Y");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it say goo goo gah gah?");
        say("Y");
        check("Is it a Baby?");
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it say goo goo gah gah?");
        say("Y");
        check("Is it a Baby?");
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Baby Duck");
        check(Strings.NEW_QUESTION + "Baby" + " and a " + "Baby Duck");
        say("Is it human?");
        check("Answering yes to Is it human? means Baby Duck?");
        say("N");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("N");
        check(Strings.IS_IT_A + Strings.ROCK + "?");
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Keyboard");
        check(Strings.NEW_QUESTION + Strings.ROCK + " and a " + "Keyboard");
        say("Can you type on it?");
        check("Answering yes to Can you type on it? means Keyboard?");
        say("Y");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("N");
        check("Can you type on it?");
        say("N");
        check(Strings.IS_IT_A + Strings.ROCK + "?");
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Notebook");
        check(Strings.NEW_QUESTION + Strings.ROCK + " and a " + "Notebook");
        say("Is it hard and round?");
        check("Answering yes to Is it hard and round? means Notebook?");
        say("N");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("N");
        check("Can you type on it?");
        say("N");
        check("Is it hard and round?");
        say("N");
        check("Is it a Notebook?");
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it say goo goo gah gah?");
        say("N");
        check("Is it a Duck?");
        say("N");
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Joe");
        check(Strings.NEW_QUESTION + "Duck" + " and a " + "Joe");
        say("Does it play Centre Football?");
        check("Answering yes to Does it play Centre Football? means Joe?");
        say("Y");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("Y");
        check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it say goo goo gah gah?");
        say("N");
        check("Does it play Centre Football?");
        say("Y");
        check("Is it a Joe?");
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("n");
        //close the streams at the end to enrue good behavior.
		comp.close();
		me.close();
	}





	public static void main(String[] args) 
	{
		System.out.print("Test is running");
		try
		{
			TestTree test = new TestTree();
			test.run();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		System.out.print("you there halt");
		
	}
	
	
}

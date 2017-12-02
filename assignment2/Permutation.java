import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation
{
	public static void main(String[] args)
	{
		int k=Integer.parseInt(args[0]);
		RandomizedQueue<String> rq=new RandomizedQueue<String>();
		while(!StdIn.isEmpty())
		{
			String s=StdIn.readString();
			rq.enqueue(s);
		}
		Iterator<String> iterator=rq.iterator();
		for(int i=0;i<k;i++)
		{
		    if(iterator.hasNext())
			StdOut.println(iterator.next());
		}
	}

}

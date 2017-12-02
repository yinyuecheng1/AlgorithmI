import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Node implementation.
public class RandomizedQueueNode<Item> implements Iterable<Item> 
{
	private int N; // number of items.
	private Node first;
	private class Node
	{
		Item item;
		Node next;
		Node pre;
	}
	public RandomizedQueueNode() // construct an empty randomized queue
	{
		N=0;
		first=null;
	}
	public boolean isEmpty()  // is the randomized queue empty?
	{return N==0;}
	public int size()   // return the number of items on the randomized queue
	{return N;}
	public void enqueue(Item item)   // add the item
	{
		if(item==null) throw new java.lang.IllegalArgumentException();
		Node oldfirst=first;
		Node first=new Node();
		first.item=item;
		if(oldfirst==null) return;
		else
		{
		  first.next=oldfirst;
		  oldfirst.pre=first;
		}
		N++;
		
	}
	public Item dequeue()  // remove and return a random item
	{
		if(isEmpty()) throw new java.lang.IllegalArgumentException();
		int rand=StdRandom.uniform(N);
		Node temp=first;
		for(int i=0;i<rand;i++)
		{
			temp=temp.next;
		}
		Node temp_prev=temp.pre;
		Node temp_next=temp.next;
		temp_prev.next=temp_next;
		Item item=temp.item;
		N--;
		return item;
	}
	public Item sample()  // return a random item (but do not remove it)
	{
		if(isEmpty()) throw new java.lang.IllegalArgumentException();
		int rand=StdRandom.uniform(N);
		Node temp=first;
		for(int i=0;i<rand;i++)
		{
			temp=temp.next;
		}
		Item item=temp.item;
		return item;
	}
	public Iterator<Item> iterator()  // return an independent iterator over items in random order
	{
		return new RandomListIterator();
	}
	private class RandomListIterator implements Iterator<Item>
	{
		Item[] a;
		int i;
		public RandomListIterator()
		{
		i=0;
		a=(Item[]) new Object[N];
		Node temp=first;
		for(int i=1;i<N;i++)
		{
			a[i]=temp.item;
			temp=temp.next;
		}
		StdRandom.shuffle(a);
		}
		public boolean hasNext()
		{return i<N; }
		public Item next()
		{
			return a[i];
		}
		
		
	}
	public static void main(String[] args) // unit testing (optional)
	{
		In in=new In(args[0]);
    	String[] s=in.readAllStrings();
    	RandomizedQueue<String> randomque=new RandomizedQueue<String>();
    	for(int i=0;i<s.length;i++)
    	{
    		randomque.enqueue(s[i]);
    	}
    	for(int j=0;j<5;j++)
    	{
    		randomque.dequeue();
    	}
    	Iterator<String> iter1=randomque.iterator();
    	Iterator<String> iter2=randomque.iterator();
    	Iterator<String> iter3=randomque.iterator();
    	StdOut.println("Iterator1:");
    	while(iter1.hasNext())
    	{
    		String ss=iter1.next();
    		StdOut.print(ss);
    	}
    	StdOut.println("");
        StdOut.println("Iterator2:");
        while(iter2.hasNext())
        {
        	String kk=iter2.next();
        	StdOut.print(kk);
        }
        StdOut.println("");
        StdOut.println("Iterator3:");
        while(iter3.hasNext())
        {
        	String kk=iter3.next();
        	StdOut.print(kk);
        }

    }
	
}
/**
 * �ڴ������
 * NodeǶ�����ڴ濪����16byte���౾��+3*8byte��Node��ָ��item��next��prev�����ÿ�����
 * +8byte����������ָ���ⲿ��Ŀ�����=48byte
 * RandomizedQueueNode��������16byte������������+4byte��ʵ��������+4byte������ֽڣ�+40Nbyte��String��
 * ��������ĺ���N��Item��String����RanmizedQueueNodeʹ���ڴ棺48N+40N+24=88N+24
 * Iterator����������Ҫ���Զ�����ڴ濪�������˸������顣
 * ʱ�����������itrator����������ÿ���������ǳ���ʱ�俪����
 */

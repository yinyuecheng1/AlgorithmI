import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly at random from items 
 * -in the data structure.
 * @author CalvinYin
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> 
{
	private Item[] a;// array implementation.
	private int N;// the number of elements in queue.
	private int head;
    public RandomizedQueue() // construct an empty randomized queue
    {
    	a=(Item[])new Object[1];
    	N=0;
    	head=0;
    }
    public boolean isEmpty() // is the randomized queue empty?
    {return N==0;}
    public int size() // return the number of items on the randomized queue
    {return N;}
    private void resizeEnque(int max)
    {
    	Item[] temp=(Item[]) new Object[max];
    	for(int i=0;i<head;i++)
    		temp[i]=a[i];
    	a=temp;
    }
    public void enqueue(Item item) // add the item.
    {
    	if(item==null) throw new java.lang.IllegalArgumentException();
    	if(head==a.length) resizeEnque(2*a.length);
    	a[head++]=item;
    	N++;
    }
    private void resizeDeque(int max)
    {
    	Item[] temp=(Item[]) new Object[max];
    	int j=0;
    	for(int i=0;i<a.length;i++)
    	{
    	   if(a[i]!=null && j<N) 
    	   temp[j++]=a[i];
       }
    	a=temp;
    	head=a.length;
    }
    public Item dequeue()  // remove and return a random item.
    {
    	if(isEmpty()) throw new java.util.NoSuchElementException("Empty!");
    	if(N>0 && N==a.length/4) resizeDeque(a.length/2);
    	int randomIdx=StdRandom.uniform(head);
    	while(a[randomIdx]==null)
    	{
    		randomIdx=StdRandom.uniform(head);
    	}
    	Item item=a[randomIdx];
    	a[randomIdx]=null;
    	N--;
    	return item; 	
    }
    public Item sample() // return a random item (but do not remove it).
    {
    	if(isEmpty()) throw new java.util.NoSuchElementException();
    	int randomIdx=StdRandom.uniform(head);
    	while(a[randomIdx]==null)
    	{
    		randomIdx=StdRandom.uniform(head);
    	}
    	Item item=a[randomIdx];
    	return item; 
    }
    
    public Iterator<Item> iterator() // return an independent iterator over items in random order
    {return new RandomIterator();}
    private class RandomIterator implements Iterator<Item>
    {
    	private Item[] b;
    	private int start=0;
    	public RandomIterator() 
    	{
    		b=(Item[]) new Object[N];
    		StdRandom.shuffle(a,0,a.length-1); //Rearranges the elements of a in uniformly random order.
    		int j=0;
    		for(int i=0;i<a.length;i++)
    		{
    				if(a[i]!=null && j<N)  b[j++]=a[i];
    		}
    	}

    	public boolean hasNext() { return start<N;}
    	public Item next() { return b[start++];}
    	
    }
    public static void main(String[] args)  // unit testing (optional)
    {
    	In in=new In(args[0]);
    	String[] s=in.readAllStrings();
    	RandomizedQueue<String> randomque=new RandomizedQueue<String>();
    	for(int i=0;i<s.length;i++)
    	{
    		randomque.enqueue(s[i]);
    	}
    	for(int j=0;j<3;j++)
    	{
    		randomque.dequeue();
    	}
    	Iterator<String> iter1=randomque.iterator();
    	Iterator<String> iter2=randomque.iterator();
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

    }
}
/*
 * ʱ�������׷���������iterator������������RandomIterator���⣬����ÿ�β�����ƽ̯ʱ�䶼�ǳ�����Iterator��hasNext����
 * ��next�������ǳ���������Constructor����������ʱ�俪��~2N
 * �ڴ����������N��Item��RandomizedQueueʹ���ڴ棺
 * ����Item[] a �Ŀ�����16byte���������������+4�����������䣩+4�������С��+8N�������Item�����ã�+xN��Item������
 * =24+��8+x��N��String����x=40
 * 2��int��ʵ��������4+4
 * ����RandomizedQueue������Ŀ�����16Byte������������+4Byte������ֽڣ�=20Byte
 * һ����52+48N �ֽڣ����㣩
 * ����ÿһ��Iterator������Ҫһ���������飬��СΪN�����ԵĶ����ڴ档
 * 
 * 
 */

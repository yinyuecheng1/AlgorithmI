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
 * 时间增长阶分析：除了iterator（）方法构建RandomIterator以外，其他每次操作的平摊时间都是常数；Iterator的hasNext（）
 * 和next（）都是常数开销，Constructor（）是线性时间开销~2N
 * 内存分析：含有N个Item的RandomizedQueue使用内存：
 * 数组Item[] a 的开销：16byte（数组对象本身开销）+4（数组对象填充）+4（数组大小）+8N（数组对Item的引用）+xN（Item开销）
 * =24+（8+x）N，String对象，x=40
 * 2个int型实例变量：4+4
 * 本身：RandomizedQueue对象本身的开销：16Byte（对象本身开销）+4Byte（填充字节）=20Byte
 * 一共：52+48N 字节（满足）
 * 对于每一个Iterator，都需要一个辅助数组，大小为N，线性的额外内存。
 * 
 * 
 */

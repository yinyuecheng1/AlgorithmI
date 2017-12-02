import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class DequeNode<Item> implements Iterable<Item> 
{
	private int N; //number of elements.
	private Node first;//deque head.
	private Node last;//deque tail.
	private class Node
	{//nested class. recursive data structure!
		Item item;
		Node next;
		Node prev;
		
	}
	public DequeNode()
	{
		first=null;
		last=null;
		N=0;
	}
    public boolean isEmpty()  // is the deque empty?
    {return N==0;}
    public int size()  // return the number of items on the deque
    {return N;}
    public void addFirst(Item item) // add the item to the front
    {
    	if (item==null) throw  new java.lang.IllegalArgumentException();
    	Node oldfirst=first;
    	first=new Node();
     	first.item=item;
    	if(oldfirst==null) last=first;
    	if(oldfirst!=null) 
    		{
    		first.next=oldfirst;
    		oldfirst.prev=first;
    		}
    	N++;
     }
    public void addLast(Item item) // add the item to the end.
    {
    	if (item==null) throw  new java.lang.IllegalArgumentException();
    	Node oldlast=last;
    	last=new Node();
    	last.item=item;
    	if(oldlast==null)  first=last;
    	if(oldlast!=null) 
    		{
    		oldlast.next=last;
    		last.prev=oldlast;
    		}
     	N++;
    }
    public Item removeFirst() // remove and return the item from the front
    {
    	if(N==0) throw new java.util.NoSuchElementException();
    	Item item=first.item;
    	first=first.next;
    	first.prev=null;
    	N--;
    	return item;
     }
    public Item removeLast() // remove and return the item from the end
    {
    	if(N==0) throw new java.util.NoSuchElementException();
    	Item item=last.item;
        last=last.prev;
        last.next=null;
    	N--;
    	return item;
    }
    public Iterator<Item> iterator()  // return an iterator over items in order from front to end
    {
    	return new OrderListIterator();
    }
    private class OrderListIterator implements Iterator<Item>
    {
    	private Node current=first;
    	public boolean hasNext()
    	{return current!=null; }
    	public void remove() {}
    	public Item next()
    	{
    		Item item=current.item;
    		current=current.next;
    		return item;
    	}
    	
    }
    public static void main(String[] args) // unit testing (optional)
    {
    	In in=new In(args[0]);
    	String[] strs=in.readAllStrings();
    	DequeNode<String> deque=new DequeNode<String>();
    	for(int i=0;i<strs.length;i++)
    	{
    		deque.addFirst(strs[i]);
    		deque.addLast(strs[i]);
    	}
    	for(int j=0;j<7;j++)
    	{
    	//deque.removeLast();
    	deque.removeFirst();
    	}
    	//String ss=deque.removeLast();
    	//StdOut.print("ss"+ss);
    	Iterator<String> itr=deque.iterator();
    	while(itr.hasNext())
    	{
    		String s=itr.next();
    		StdOut.print(s);
    	}
    }
}
/**
 * 内存分析（空间复杂度分析）：
 * int N 开销4 Byte。
 * Node嵌套类：16Byte的对象本身的开销+2*8Byte的引用+8Byte指向外部类的引用=40
 * 一个含有N个Item项的Deque一共需要使用：40N+Item（Item引用类型所耗内存）*N+16（Deque对象本身内存）+
 * 4（int型实例变量）+2*8（两个Node引用类型实例变量）+4（填充字节）=（40+x）N+40字节。
 * 
 * 
 * 时间复杂度分析：每种方法时间增长阶（内存访问次数）都是常数。
 * 
 */

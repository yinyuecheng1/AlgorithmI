import java.util.Iterator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>
{
	private Item[] a;
	private int N; // the number of deque elements.
	private int head;// pointer points the deque head.
	private int tail; // pointer points the deque tail;
	public Deque()  // construct an empty deque
	{
		a= (Item[]) new Object[1];
		N=0;
		tail=0;
		head=0;
	}
    public boolean isEmpty() // is the deque empty?
    {
    	return N==0;
    }
    public int size() // return the number of items on the deque
    {
    	return N;
    }
    private void resizeFront(int max) // 数组倍增，前半个数组为空。
    {
    	Item[] temp=(Item[]) new Object[max];
    	for (int i=0;i<N;i++)
    		temp[i+max/2]=a[i];
    	a=temp;
    	head=max/2-1;
    	tail=head+N+1;
    	
    }
    private void resizeEnd(int  max) // 数组倍增，后半个数组为空。
    {
    	Item[] temp=(Item []) new Object[max];
    	for(int i=0;i<N;i++) 
    		temp[i]=a[i+head+1];
    	a=temp;
    	tail=max/2;
    	head=tail-N-1;
    	
    }
    public void addFirst(Item item) // add the item to the front
    {
    	if (item==null) throw  new java.lang.IllegalArgumentException();
    	if(head==-1||N==a.length) resizeFront(2*N);
    	a[head--]=item;
    	N++;
    	
    }
    public void addLast(Item item) // add the item to the end
    {
    	if (item==null) throw  new java.lang.IllegalArgumentException();
    	if(tail==a.length||N==a.length) resizeEnd(2*N);
    	a[tail++]=item;
    	N++;
    }
    public Item removeFirst() // remove and return the item from the front
    {
    	if(N==0) throw new java.util.NoSuchElementException();
    	Item item=a[++head];
    	a[head]=null;
    	if(N>0 && N==a.length/4) resizeFront(N/2);
    	N--;
    	return item;
    }
    public Item removeLast()  // remove and return the item from the end
    {
    	if(N==0) throw new java.util.NoSuchElementException();
    	Item item=a[--tail];
    	a[tail]=null;
    	if(N>0 && N==a.length/4) resizeEnd(N/2);
    	N--;
    	return item;
    }
    public Iterator<Item> iterator()  // return an iterator over items in order from front to end
    {
    	return new OrderArrayIterator();
    }
    private class OrderArrayIterator implements Iterator<Item>
    {
    	private int k=head+1;
    	public boolean hasNext() {return k<tail ;}
    	public Item next() {return a[k++];}
    }
    
    public static void main(String[] args)  // unit testing (optional)
    {
    	In in=new In(args[0]);
    	String[] strs=in.readAllStrings();
    	Deque<String> deque=new Deque<String>();
    	for(int i=0;i<strs.length;i++)
    	{
    		deque.addFirst(strs[i]);
    		deque.addLast(strs[i]);
    	}
    	for(int j=0;j<10;j++)
    	{
    	deque.removeLast();
    	//deque.removeFirst();
    	}
    	Iterator<String> itr=deque.iterator();
    	while(itr.hasNext())
    	{
    		String s=itr.next();
    		StdOut.print(s);
    	}
    	
    	
    }
   
}
/*
 * 空间复杂度分析：
 * 数组：16(对象开销)+4(填充)+4（数组大小的一个int值）+8N（对象的引用）+Item*N（取决与Item的内存）
 * =24+（8+x）N
 * 其他实例变量：12
 * 一共：36+（8+x）N的 内存开销。
 * 
 * 
 * 时间复杂度分析：每个方法的时间增长阶数都是常数（平摊），
 * 添加满的数组和移除1/4满的数组的操作会导致时间增长阶为2N,
 * 但是平摊下来，还是常数时间复杂度。
 * 切忌addLast和addFirst交替进行！会导致N的时间增长阶。
 * */

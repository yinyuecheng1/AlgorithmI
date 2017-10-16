import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class PercolationWQU 
{
	private int N;// N*N grids.
	private int count; //count the number of open site.
	private int[] a;
	private WeightedQuickUnionUF uf;
	
	public PercolationWQU(int n)  // create n-by-n grid, with all sites blocked
	{
		if(n<=0) throw new java.lang.IllegalArgumentException();
		a=new int[n*n+2]; // 2 sites in top and bottom for assistant.
		N=n;
		uf=new WeightedQuickUnionUF(n*n+2);
		count=0;
		a[n*n]=1;
		a[n*n+1]=1;
		
		
		for(int i=0;i<n*n;i++)
		{ 
		  a[i]=0;
		}
	}
	public void open(int row, int col)  // open site (row, col) if it is not open already
	{
		if(row<1||row>N||col<1||col>N) throw new java.lang.IllegalArgumentException();
		if(isOpen(row,col)) return;
		else 
		{
			a[twoToOne(row,col)]=1;
			count++;
		}
		
		if(row==1) 
		{
			uf.union(twoToOne(row,col),N*N);// union the first row sites and top site.
			if(col==1)
			{
				if(isOpenRight(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col+1));
				if(isOpenDown(row,col)) uf.union(twoToOne(row,col), twoToOne(row+1,col));
				return;
			}
			if(col==N)
			{
				if(isOpenLeft(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col-1));
				if(isOpenDown(row,col)) uf.union(twoToOne(row,col), twoToOne(row+1,col));
				return;
			}
			else
			{
				if(isOpenLeft(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col-1));
				if(isOpenDown(row,col)) uf.union(twoToOne(row,col), twoToOne(row+1,col));
				if(isOpenRight(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col+1));
				return;
			}
		}
		if(row==N)
		{
			uf.union(twoToOne(row,col), N*N+1);
			if(col==1) 
			{
				if(isOpenUp(row,col)) uf.union(twoToOne(row,col), twoToOne(row-1,col));
			    if(isOpenRight(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col+1));
			    return;
			}
			if(col==N)
			{
				if(isOpenUp(row,col)) uf.union(twoToOne(row,col), twoToOne(row-1,col));
				if(isOpenLeft(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col-1));
				return;
			}
		
		   else
		  {
			if(isOpenLeft(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col-1));
			if(isOpenUp(row,col)) uf.union(twoToOne(row,col), twoToOne(row-1,col));
			if(isOpenRight(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col+1));
			return;
		  }
	 }
	else
	{
		if(col==1)
		{
			if(isOpenDown(row,col)) uf.union(twoToOne(row,col), twoToOne(row+1,col));
			if(isOpenUp(row,col)) uf.union(twoToOne(row,col), twoToOne(row-1,col));
			if(isOpenRight(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col+1));
			return;
		  }
		if(col==N)
		{
			if(isOpenDown(row,col)) uf.union(twoToOne(row,col), twoToOne(row+1,col));
			if(isOpenUp(row,col)) uf.union(twoToOne(row,col), twoToOne(row-1,col));
			if(isOpenLeft(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col-1));
			return;
		}
		else
		{
			if(isOpenDown(row,col)) uf.union(twoToOne(row,col), twoToOne(row+1,col));
			if(isOpenUp(row,col)) uf.union(twoToOne(row,col), twoToOne(row-1,col));
			if(isOpenLeft(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col-1));
			if(isOpenRight(row,col)) uf.union(twoToOne(row,col), twoToOne(row,col+1));	
			return;
		}
	}
}

	
	private boolean isOpenRight(int row,int col) // is the site in the right open?
	{return isOpen(row,col+1);}
	private boolean isOpenLeft(int row,int col) // is the site in the left open?
	{return isOpen(row,col-1);}
	private boolean isOpenUp(int row,int col) // is the site in the up open?
	{return isOpen(row-1,col);}
	private boolean isOpenDown(int row,int col) // is the site in the down open?
	{return isOpen(row+1,col);}
	
	private int twoToOne(int row,int col) // invert two coordinate to one.
	{
		int idx=(row-1)*N+col-1;
		return idx;
	}
	
	public boolean isOpen(int row, int col) // is site (row, col) open?
	{
		return a[twoToOne(row,col)]==1;
	}
	public boolean isFull(int row, int col) // is site (row, col) full?
	{
	  return uf.connected(twoToOne(row,col), N*N);
	}

	public int numberOfOpenSites()  // number of open sites
	{
		return count;
	}
	public boolean percolates()  // does the system percolate?
	{
		return uf.connected(N*N,N*N+1);
	}

	public static void main(String[] args)  // test client (optional)
	{
		
		int m=Integer.parseInt(args[0]);
		PercolationWQU perco=new PercolationWQU(m);
		while(!StdIn.isEmpty())
		{
			int row=StdIn.readInt();
			int col=StdIn.readInt();
			perco.open(row, col);
		}
		StdOut.println("is percolate?"+perco.percolates());
		
	}

}

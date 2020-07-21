package FinalProject;

import java.util.*;

public class MyPriorityQueue<E extends Comparable<E>> {
	Heap<E> myheap;
	Comparator<E> cmp = null;
	
	public MyPriorityQueue(int capacity)
	{
		myheap = new Heap<E>(capacity);
	}
	
	@SuppressWarnings("rawtypes")
	public MyPriorityQueue(ArrayList<E> input, Comparator cmp)
	{
		
		myheap = (cmp==null)? new Heap<E>(input): new Heap<E>(input, cmp);
	}
	
	public MyPriorityQueue(int size, PercentageComparator vPComparator) {
		myheap = (vPComparator==null)? new Heap<E>(size): new Heap<E>(size, vPComparator);
	}

	public MyPriorityQueue(int i, Comparator aRComparator) {
		myheap = (aRComparator==null)? new Heap<E>(i): new Heap<E>(i, aRComparator);
	}

	public boolean isEmpty(){
		return (myheap.getArray().length ==0);
	}
	
	
	
	public E poll()
	{
		E temp = myheap.myarray[0];
		myheap.size--;
		if(myheap.size>0)
		{
			myheap.myarray[0] = myheap.myarray[myheap.size];
			if (cmp ==null)
				myheap.trickleDown(0);
			else
				myheap.trickleDown(0, cmp);
		}
		return temp;
	}

	public Movie peek() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(Movie movie) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}

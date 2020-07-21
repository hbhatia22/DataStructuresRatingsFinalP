package FinalProject;

import java.util.*;

public class Heap<E extends Comparable<E>> {
	private ArrayList<E> list = new ArrayList<E>();
	int capacity = 1000;
	E[] myarray;
	int size = 0;
	
	public Heap(E[] arr) {
		for (int i = 0; i < arr.length; i++)
			add(arr[i]);
	}
	public Heap(ArrayList<E> arr) {
		for (int i = 0; i < arr.size(); i++)
			add(arr.get(i));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Heap(ArrayList<E> arr, Comparator cmp) {
		size = arr.size() -1;
		while(size >capacity){
			capacity <<= 1;
		}
		myarray = (E[]) new Comparable[capacity];
		if(size>0){
			arr.toArray(myarray);
			for(int i=size/2; i>=0; i--)
				maxheap(myarray, i, cmp);
		}

	}
	@SuppressWarnings("unchecked")
	public Heap(int capacity) {
		this.capacity = capacity;
		size = 0;
		this.myarray = (E[]) new Comparable[capacity];
	}
	
	public Heap(int capacity, PercentageComparator vPComparator) {
		this.capacity = capacity;
		size = 0;
		this.myarray = (E[]) new Comparable[capacity];
		while(size >capacity){
			capacity <<= 1;
		}
		myarray = (E[]) new Comparable[capacity];
		if(size>0){
			
			for(int i=size/2; i>=0; i--)
				maxheap(myarray, i, (Comparator) vPComparator);
		}
	}
	E[] getArray(){
		return myarray;
	}
	
	public void add(E e) {
		list.add(e);
		int currentIndex = list.size() - 1;
		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;
			// swap if the current index is greater than its parent
			if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0 ) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			}
			else
				break;

			currentIndex = parentIndex;
		}

	}
	// remove root from heap
	public E remove() {
		if(list.size()==0)
			return null;

		E removedObj = list.get(0);
		list.set(0, list.get(list.size()-1));
		list.remove(list.size()-1);
		int currentIndex = 0;
		while(currentIndex < list.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;
			if(leftChildIndex >= list.size())
				break;
			int maxIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
				if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0){
					maxIndex = rightChildIndex;
				}
			}
			if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
				E temp = list.get(maxIndex);
				list.set(maxIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
			}
			else
				break;
		}
		return removedObj;
	}
	@SuppressWarnings("unchecked")
	public void trickleDown(int itemPosition) {		
		int child;
		if(2*itemPosition+1 >= size)
		{
			child = itemPosition;
		}
		else if(2*itemPosition+2 == size)
		{
			child = 2*itemPosition+1;
		}
		else if(myarray[2*itemPosition+1].compareTo(myarray[2*itemPosition+2])>0)
		{
			child = 2*itemPosition+1;
		}
		else
		{
			child = 2*itemPosition+2;
		}
		if(myarray[itemPosition].compareTo(myarray[child])<0)
		{
			Object temp = myarray[itemPosition];
			myarray[itemPosition] = myarray[child];
			myarray[child] = (E)temp;
			trickleDown(child);
		}	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void trickleDown(int itemPosition, Comparator cmp)
	{		
		int child;
		if(2*itemPosition+1 >= size)
		{
			child = itemPosition;
		}
		else if(2*itemPosition+2 == size)
		{
			child = 2*itemPosition+1;
		}
		else if(cmp.compare(myarray[2*itemPosition+1], myarray[2*itemPosition+2])>=0)
		{
			child = 2*itemPosition+1;
		}
		else
		{
			child = 2*itemPosition+2;
		}
		if(cmp.compare(myarray[itemPosition], myarray[child])<0)
		{
			Object temp = myarray[itemPosition];
			myarray[itemPosition] = myarray[child];
			myarray[child] = (E)temp;
			trickleDown(child);
		}	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void trickleUp(int newPosition, Comparator cmp) {
		Object temp = myarray[newPosition];
		int parent = (newPosition-1)/2;
		while(newPosition> 0 && cmp.compare(myarray[parent], temp)<0)
		{
			myarray[newPosition] = myarray[parent];
			newPosition = parent;
			parent = (newPosition-1)/2;
		}
		myarray[newPosition] = (E)temp;
	}	
	
	@SuppressWarnings("unchecked")
	public void trickleUp(int newPosition) {
		Object temp = myarray[newPosition];
		int parent = (newPosition-1)/2;
		while(newPosition> 0 && myarray[parent].compareTo((E) temp)<0)
		{
			myarray[newPosition] = myarray[parent];
			newPosition = parent;
			parent = (newPosition-1)/2;
		}
		myarray[newPosition] = (E) temp;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void maxheap(E[] arr, int i, Comparator cmp) {
		int left = 2*i + 1;
		int right = 2*i + 2;
		int max = i;
		if(left<=size && (cmp.compare(arr[left], arr[i])>0))
			max = left;
		if(right<=size && (cmp.compare(arr[right], arr[max])>0))
			max = right;
		if(max!=i)
		{
			swap(arr, i, max);
			maxheap(arr, max, cmp);
		}
	}
	
	public void swap(E[] arr, int i, int j)
	{
		E tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	public int getSize() {
		return list.size();
	}

}

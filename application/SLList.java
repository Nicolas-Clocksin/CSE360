package application;

//This class implements a Singly Linked List

import java.util.*;

public class SLList<E>
{
	//------- data
	protected SLLNode<E> head;
	protected SLLNode<E> tail;

	//------- constructors
	public     SLList()
	{
		head = tail = null;
	}

	//------- methods
	//addFirst - adds an element to the front of the list
	public void addFirst(E theData)
	{
		//create a new SLLNode to hold theData
		SLLNode<E> temp = new SLLNode<E>(theData);

		if (head==null)   //is the list empty?
			head = tail = temp;
		else    //list is not empty
		{
			temp.next = head;
			head = temp;
		}
	}

	//addLast - adds an element to the end of the list
	public void addLast(E theData)
	{
		//create a new SLLNode to hold theData
		SLLNode<E> temp = new SLLNode<E>(theData);

		if (head == null)   //is the list empty?
			head = tail = temp;
		else    //list is not empty
		{
			tail.next = temp;
			tail = temp;
		}
	}

	//removeFirst - removes and returns the first element
	public E removeFirst()
	{
		//case1: list is empty
		if (head == null)
			throw new NoSuchElementException("can't removeFirst from empty list!");

		//case2: list has 1 element
		else if (head == tail)
		{
			E savedData = head.data;
			head = tail = null;
			return savedData;
		}

		//case3: list has many elements
		else
		{
			E savedData = head.data;
			head = head.next;
			return savedData;
		}
	}

	//removeLast - removes and returns the last element
	public E removeLast()
	{
		//case1: list is empty
		if (head == null)
			throw new NoSuchElementException("can't removeLast from empty list!");

		//case2: list has 1 element
		else if (head == tail)
		{
			E savedData = tail.data;
			head = tail = null;
			return savedData;
		}

		//case3: list has many elements
		else
		{
			//traverse the list, stopping at the node IN FRONT OF the last node
			SLLNode<E> cursor = head;
			while (cursor.next != tail)
				cursor = cursor.next;

			//when we get to here, cursor has stopped at the node before tail
			E savedData = tail.data;
			cursor.next = null;
			tail = cursor;
			return savedData;
		}

	}

	//contains - returns true if the list contains what is received
	public boolean contains(Object obj)
	{
		//traverse the list, seeing if any node has data that .equals obj
		SLLNode<E> cursor = head;
		while (cursor != null)
		{
			if (cursor.data.equals(obj))    //found it!
				return true;                //so return
			cursor = cursor.next;
		}

		return false;   //if we finished the loop and got to here, it was not found
}

	//remove - removes what is received from the list.  Returns true if it
	//                was actually found and removed
	public boolean remove(Object obj)
	{
		if (!this.contains(obj)) //return false if it is not contained (can't remove it)
			return false;

		//if we get to here, we know that the list contains obj somewhere
		//change the links and return true at the end.
		else if (head.data.equals(obj))    //is it at the front?   If so, remove it
		{
			this.removeFirst();
			return true;
		}

		else    //not at front, so traverse the list to find the one to be deleted (know its there)
		{
			SLLNode<E> doomed = head;
			while (!doomed.data.equals(obj))
				doomed = doomed.next;

			//now that it is found, we also need to find the node in front of it
			SLLNode<E> inFront = head;
			while (inFront.next != doomed)
				inFront = inFront.next;

			//now we have a pointer to the node to be deleted and the one in front
			               //draw it out to see how links must change...
			inFront.next = doomed.next;

			//also...if the one that was deleted was the tail, we must reset the tail
			if (doomed == tail)
				tail = inFront;
		}

		return true;   //found it; links have been changed

}

	//add - inserts what is received at the given index
	public void add(int index, E elt)
	{
		if (index < 0 || index > size())    //check if index is out of bounds
			throw new IllegalArgumentException("illegal index: " + index);

		//if we are adding at the front or the back, use existing methods
		if (index == 0)
			addFirst(elt);

		else if (index == size())
			addLast(elt);

		else    //if we get to here, it will go in the middle somewhere
		{
			// 1. create a new node to hold it
			SLLNode<E> temp = new SLLNode<E>(elt);

			// 2. traverse the list to find where it goes
			//     when we traverse, we will stop at the node IN FRONT OF where it goes
			SLLNode<E> cursor = head;
			for (int i=1; i<index; i++)
				cursor = cursor.next;

			// 3. cursor should have stopped at the node IN FRONT
			//    if where the new node goes.  Change the links so
			//    it is part of the list.  draw it out so see how
			//    the links change...
			temp.next = cursor.next;
			cursor.next = temp;
		}
	}

	//getFirst - returns the data from the head
	public E getFirst()
	{
		if (head == null)
			throw new NoSuchElementException("can't getFirst from empty list");
		return head.data;
	}

	//getLast - returns the data from the tail
	public E getLast()
	{
		if (head == null)
			throw new NoSuchElementException("can't getLast from empty list");
		return tail.data;
	}



	//isEmpty - returns true if the list is empty
	public boolean isEmpty()
	{
		return head == null;
	}

	//size - returns its size
	public int size()
	{
		int theSize = 0;
		SLLNode<E> cursor = head;

		while (cursor != null) //traverse the list, counting the elements
		{
			theSize++;
			cursor = cursor.next;
		}

		return theSize;
	}

	//toString - returns its representation as a String
	public String toString()
	{
		String answer = "";
      SLLNode<E> cursor = head;
      while (cursor != null)   //traverse the list, building up a String
		{
			answer = answer + cursor.data + "--->";
			cursor = cursor.next;
		}

		answer = answer + "(null)";  //the last node points to "(null)"

		return answer;
	}

	//------- "inner classes"
	//==============================
	class SLLNode<E>
	{
		//------- data
		protected E data;
		protected SLLNode<E> next;

		//------- constructors
		public SLLNode(E theData)
		{
			this.data = theData;
			next = null;
		}

		//------- methods
		public String toString()
		{
			return this.data.toString();
		}
	}	 //end of SLLNode

}   //end of SLList

/******************************************************************
@author Carol Arevalo 
@since 02/02/21

Stack- implementation as vector
******************************************************************/

import java.util.Vector;

public class stackVector<E> extends AbstractStack<E>
{
	protected Vector<E> data;

   /** 
   * post: constructs a new, empty stack
   * will be popped next if no intervening push
   * @return npne
   * @param E
   */
	public stackVector()
	{
		data = new Vector<E>();
	}


   /** 
   * post: item is added to stack
   * will be popped next if no intervening push
   * @return npne
   * @param E
   */
	public void push(E item)
		{
		data.add(item);
	}


	/** 
   * pre: stack is not empty
   * post: most recently pushed item is removed and returned.
   * @return E
   * @param none
   */
	public E pop()
	{
		return data.remove(size()-1);
	}

   /** 
   * pre: stack is not empty
   * post: top value (next to be popped) is returned
   * @return none
   * @param E
   */	public E peek()
	{
		return data.get(size() - 1);
	}
	


   /** 
   * post: returns the number of elements in the stack
   * @return int
   * @param none
   */
	public int size()
	{
		return data.size();
	}
  
   /** 
   * post:  returns true if and only if the stack is empty
   * @return boolean
   * @param none
   */
	public boolean empty()
	{
		return size() == 0;
	}


}
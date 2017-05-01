package database;

public class LinkedListNode<T> {
	private T data;
	private LinkedListNode<T> next;
	
	/**
	 * Set the data stored at this node.
	 * @param data data stored in the node
	 */
	public void setData( T data ){
		this.data = data;
	}

	 
	/**
	 * Get the data stored at this node.
	 * @return return the data stored in the node
	 */
	public T getData(){
		return data;
	}
	 
	/**
	 * Set the next pointer to passed node.
	 * @param next node which the current node points towards
	 */
	public void setNext( LinkedListNode<T> next ){
		this.next = next;
	}
	 
	/**
	 * Get (pointer to) next node.
	 * @return return the next node
	 */
	public LinkedListNode<T> getNext(){
		return next;
	}
	 
	/**
	 * Returns a String representation of this node.
	 * @return return a string representation of the node
	 */
	public String toString(){
		if(data == null){
			return "";
		}else
			return data.toString();
	}
}

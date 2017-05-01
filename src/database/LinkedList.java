package database;
public class LinkedList<T> {
	private LinkedListNode<T> head = new LinkedListNode<T>();

	/**
	 * Get data stored in head node of the list.
	 * 
	 * @return return the data stored in the head node of the list
	 */
	public T getFirst() {
		return head.getNext().getData();
	}

	/**
	 * Get the head node of the list.
	 * 
	 * @return return the head node of the list
	 */
	public LinkedListNode<T> getFirstNode() {
		return head.getNext();
	}

	/**
	 * Get data stored in last node of list.
	 * 
	 * @return if the list is empty, return null, if it is not, return the
	 *         recursion method to find the data in the last node.
	 */
	public T getLast() {
		if (head.getNext() == null)
			return null;
		else
			return getLast(head.getNext());
	}

	/**
	 * Get data stored in last node of list with recursion
	 * 
	 * @param node
	 *            go through every node in the list
	 * @return return the data stored in the last node of the list
	 */
	public T getLast(LinkedListNode<T> node) {
		// base case
		if (node.getNext() == null) {
			return node.getData();
		} else {
			return getLast(node.getNext());
		}
	}

	/**
	 * Get the tail node of the list.
	 * 
	 * @return if the list is empty, return null, if it is not, return the
	 *         recusion method to find the last node
	 */
	public LinkedListNode<T> getLastNode() {
		if (head.getNext() == null) {
			return null;
		} else
			return getLastNode(head.getNext());
	}

	/**
	 * get the tail node of the list using recursion
	 * 
	 * @param node
	 *            go through every node in the list
	 * @return return the last node in the list
	 */
	public LinkedListNode<T> getLastNode(LinkedListNode<T> node) {

		if (node.getNext() == null) {
			return node;
		} else {
			return getLastNode(node.getNext());
		}

	}

	/**
	 * Insert a new node with data at the head of the list.
	 * 
	 * @param data
	 *            the data in the node that is going to be insert in the front
	 */
	public void insertFirst(T data) {
  		LinkedListNode<T> second = new LinkedListNode<T>();
		second = head.getNext();
		LinkedListNode<T> first = new LinkedListNode<T>();
		first.setData(data);
		first.setNext(second);
		head.setNext(first);
	}

	/**
	 * Insert a new node with data after currentNode
	 * 
	 * @param currentNode
	 *            the node that is going to be inserted after
	 * @param data
	 *            data in the node that is going to be inserted
	 */
	public void insertAfter(LinkedListNode<T> currentNode, T data) {
		LinkedListNode<T> next;
		LinkedListNode<T> insert = new LinkedListNode<T>();
		next = currentNode.getNext();
		insert.setData(data);
		insert.setNext(next);
		currentNode.setNext(insert);
	}

	/**
	 * Insert a new node with data at the end of the list.
	 * 
	 * @param data
	 *            data in the node that is going to be inserted at the end of
	 *            the list
	 */
	public void insertLast(T data) {
		LinkedListNode<T> current = new LinkedListNode<T>();
		current = getLastNode();
		LinkedListNode<T> last = new LinkedListNode<T>();
		last.setData(data);
		if(current != null)
			current.setNext(last);
		else
			head.setNext(last);
	}

	/**
	 * Remove the first node
	 */
	public void deleteFirst() {
		head = head.getNext();
	}

	/**
	 * Remove the last node
	 */
	public void deleteLast() {
		LinkedListNode<T> last = getSecondLast(head);
		last.setNext(null);
	}

	/**
	 * Find the second last node in the linked list
	 * 
	 * @param secondLast
	 *            the second last LinkedListNode
	 * @return return the second last linked list node
	 */
	public LinkedListNode<T> getSecondLast(LinkedListNode<T> secondLast) {
		LinkedListNode<T> last = new LinkedListNode<T>();
		last = getLastNode();
		if (secondLast.getNext() == last) {
			return secondLast;
		} else {
			secondLast = secondLast.getNext();
			return getSecondLast(secondLast);
		}
	}

	/**
	 * Remove node following currentNode If no node exists (i.e., currentNode is
	 * the tail), do nothing
	 * 
	 * @param currentNode
	 *            the node before the one that is going to be deleted
	 */
	public void deleteNext(LinkedListNode<T> currentNode) {
		// the node going to be deleted
		LinkedListNode<T> delete = new LinkedListNode<T>();
		// node after the deleted node
		LinkedListNode<T> nextNext = new LinkedListNode<T>();
		delete = currentNode.getNext();
		if (delete != null) {
			nextNext = delete.getNext();
			delete.setNext(null);
			currentNode.setNext(nextNext);
		}
	}

	/**
	 * Return the number of nodes in this list.
	 */
	public int size() {
		return getSize(head);
	}

	/**
	 * Return the number of nodes in this list
	 * 
	 * @param node
	 *            recursive method
	 * @return Return the number of nodes in this list
	 */
	public int getSize(LinkedListNode<T> node) {
		if (node == getLastNode()) {
			return 0;
		}
		return 1 + getSize(node.getNext());
	}

	/**
	 * Check if list is empty.
	 * 
	 * @return true if list contains no items.
	 */
	public boolean isEmpty() {
		if (head.getNext() == null)
			return true;
		else
			return false;
	}

	/**
	 * Return a String representation of the list. If the list is empty, return
	 * "", if it is not, return the recursion method to print out the list
	 */
	public String toString() {
		if (isEmpty() == true) {
			return "";
		} else
			return toString(head.getNext());
	}

	/**
	 * Return a String representation of the list
	 * 
	 * @param node
	 *            for recursion
	 * @return return a string representation of the list
	 */
	String List = "";

	public String toString(LinkedListNode<T> node) {
		// base case
		if (node.getNext() == null) {
			return List += node.toString();
		} else {
			List += node.toString() + " ->";
			return toString(node.getNext());

		}

	}
}

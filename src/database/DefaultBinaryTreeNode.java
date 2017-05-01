package database;
/**
 * 
 * @author yu hu
 *DefaultBinaryTreeNode implements methods to setData, getData, setLeftChild, setRightChild, getLeftChild, getRightChild, isLeaf
 * @param <T> generic type
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> {

	private T data;
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	
	@Override
	  /**
	   * Get the data stored at this node.
	   * @return Object data.
	   */
	public T getData() {
		return data;
	}

	@Override
	  /**
	   * Set the data stored at this node.
	   */
	public void setData(T data) {
		this.data = data;
	}

	@Override
	  /**
	   * Get the left child.
	   * @return BinaryTreeNode that is left child,
	   * or null if no child.
	   */
	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	@Override
	  /**
	   * Get the right child.
	   * @return BinaryTreeNode that is right child,
	   * or null if no child.
	   */
	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}

	@Override
	  /**
	   * Set the left child.
	   */
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.leftChild = left;
	}

	@Override
	  /**
	   * Set the right child.
	   */
	public void setRightChild(BinaryTreeNode<T> right) {
		this.rightChild = right;
	}

	@Override
	 /**
	   * Tests if this node is a leaf (has no children).
	   * @return true if leaf node.
	   */
	public boolean isLeaf() {
		if(leftChild != null ||rightChild != null)
			return false;
		else
			return true;
	}

}

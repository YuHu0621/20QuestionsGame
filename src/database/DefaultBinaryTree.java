package database;
public class DefaultBinaryTree<T> implements BinaryTree<T>{

	private BinaryTreeNode<T> root;
	
	@Override
	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	@Override
	/**
	 * Set the root node for this tree.
	 */
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	@Override
	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	public boolean isEmpty() {
		if(root!= null)
			return false;
		else
			return true;
	}

	@Override
	/**
	 * Get the data of this tree using in order traversal.
	 * 
	 * @return in order List.
	 */
	public LinkedList<T> inorderTraversal() {
		LinkedList<T> inorderList = new LinkedList<T>();
		inorderTraversal(root, inorderList);
		return inorderList;
	}
	
	
	private void inorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal){
		if(node == null)
			return;
		else
		{
			inorderTraversal(node.getLeftChild(), traversal);
			traversal.insertLast(node.getData());
			inorderTraversal(node.getRightChild(), traversal);
		}
	}


	@Override
	/**
	 * Get the data of this tree using pre-order traversal.
	 * 
	 * @return pre-order List.
	 */
	public LinkedList<T> preorderTraversal() {
		LinkedList<T> preorderList = new LinkedList<T>();
		preorderTraversal(root, preorderList);
		return preorderList;
	}

	/**
	 * recursion to get preorder Traversal
	 * @param node node
	 * @param traversal LinkedList
	 */
	protected void preorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal){
		if(node == null)
			return;
		else
		{
			traversal.insertLast(node.getData());
			preorderTraversal(node.getLeftChild(), traversal);
			preorderTraversal(node.getRightChild(), traversal);
		}
	}
	@Override
	/**
	 * Get the data of this tree using post order traversal.
	 * 
	 * @return post order List.
	 */
	public LinkedList<T> postorderTraversal() {
		LinkedList<T> postorderList = new LinkedList<T>();
		postorderTraversal(root, postorderList);
		return postorderList;
	}

	/**
	 * recursion to get postorderTraversal
	 * @param node node
	 * @param traversal LinkedList
	 */
	protected void postorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal){
		if(node == null)
			return;
		else
		{
			postorderTraversal(node.getLeftChild(), traversal);
			postorderTraversal(node.getRightChild(), traversal);
			traversal.insertLast(node.getData());
		}
	}

	@Override
	/**
	 * Print the tree using inorder traversal.
	 * 
	 * @return inorder String
	 */
	public String inorderString() {
		LinkedList<T> inorder = new LinkedList<T>();
		inorder = inorderTraversal();
		return inorder.toString();
	}

	@Override
	/**
	 * Print the tree using preorder traversal.
	 * 
	 * @return preorder String
	 */
	public String preorderString() {
		LinkedList<T> preorder = new LinkedList<T>();
		preorder = preorderTraversal();
		return preorder.toString();
	}

	@Override
	/**
	 * Print the tree using postorder traversal.
	 * @return postorder String
	 */
	public String postorderString() {
		LinkedList<T> postorder = new LinkedList<T>();
		postorder = postorderTraversal();
		return postorder.toString();
	}

	public static void main (String args[]){
		DefaultBinaryTree<String> dwarf = new DefaultBinaryTree<String>();
		BinaryTreeNode<String> happy = new DefaultBinaryTreeNode<String>();
		BinaryTreeNode<String> dopey = new DefaultBinaryTreeNode<String>();
		BinaryTreeNode<String> doc = new DefaultBinaryTreeNode<String>();
		BinaryTreeNode<String> sleepy = new DefaultBinaryTreeNode<String>();
		BinaryTreeNode<String> bashful = new DefaultBinaryTreeNode<String>();
		BinaryTreeNode<String> grumpy = new DefaultBinaryTreeNode<String>();
		BinaryTreeNode<String> sneezy = new DefaultBinaryTreeNode<String>();
		
		//set data for each binary tree node
		happy.setData("Happy");
		doc.setData("Doc");
		sleepy.setData("Sleepy");
		bashful.setData("Bashful");
		grumpy.setData("Grumpy");
		sneezy.setData("Sneezy");
		dopey.setData("Dopey");
		
		//put in binary tree
		dwarf.setRoot(happy);
		happy.setLeftChild(dopey);
		dopey.setLeftChild(doc);
		dopey.setRightChild(grumpy);
		happy.setRightChild(sleepy);
		doc.setLeftChild(bashful);
		sleepy.setRightChild(sneezy);
		
		System.out.println(dwarf.inorderString());
		System.out.println(dwarf.preorderString());
		System.out.println(dwarf.postorderString());
	}
}

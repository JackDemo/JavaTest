package main;

public class ThreadBinaryTree<T> {

	ThreadBinaryNode<T> root;
	public ThreadBinaryTree() {
		this.root = null;
	}
	public ThreadBinaryTree(T[] prelist) {
		this.root = create(prelist);
		inorderThread(this.root);
	}
	private int i = 0;
	public ThreadBinaryNode<T> create(T[] prelist) {
		ThreadBinaryNode<T> p = null;
		if (i<prelist.length) {
			T data = prelist[i];
			i++;
			if (data!=null) {
				p = new ThreadBinaryNode<T>(data);
				p.left = create(prelist);
				p.right = create(prelist);
			}
		}
		return p;
	}
	private ThreadBinaryNode<T> front = null;
	public void inorderThread(ThreadBinaryNode<T> p) {
		if (p!=null) {
			inorderThread(p.left);
			if (p.left==null) {
				p.ltag = true;
				p.left = front;
			}
			if (p.right==null) {
				p.rtag= true;
			}
			if (front!=null&&front.rtag) {
				front.right = p;
			}
			front = p;
			inorderThread(p.right);
		}
	}
	public boolean isEmpty() {
		if (this.root==null) {
			return true;
		}
		return false;
	}
	/**
	 * 返回中序线索二叉树中序遍历的后继节点
	 * @param p 表示二叉树节点 
	 * @return 二叉树节点p在中序遍历中的后继节点
	 */
	public ThreadBinaryNode<T> inNext(ThreadBinaryNode<T> p) {
		if (p.rtag) {
			return p.right;
		}
		p = p.right;
		while (!p.ltag) {
			p = p.left;
		}
		return p;
	}
	/**
	 * 中根次序遍历中序线索二叉树
	 * 时间复杂度O（n~nlog2（n+1）向上取整）
	 */
	public void inorder() {
		ThreadBinaryNode<T> p = this.root;
		while (p!=null&&!p.ltag) {
			p = p.left;
		}
		while (p!=null) {
			System.out.print(p.data.toString());
			p = inNext(p);
		}
		System.out.println();
	}
	/**
	 * 返回中序线索二叉树中序遍历的前驱节点 
	 * @param p 表示二叉树节点 
	 * @return 二叉树节点p在中序遍历中的前驱节点
	 */
	public ThreadBinaryNode<T> inPre(ThreadBinaryNode<T> p) {
		if (p.ltag) {
			return p = p.left;
		}
		p = p.left;
		while (!p.rtag) {
			p = p.right;
		}
		return p;
	}
	/**
	 * 返回先序遍历情况下中序线索树的后继节点 
	 * @param p 表示二叉树节点 
	 * @return 二叉树节点p在先序遍历中的后继节点
	 */
	public ThreadBinaryNode<T> preNext(ThreadBinaryNode<T> p) {
		if (!p.ltag) {
			return p = p.left;
		}
		while (p.rtag&&p.right!=null) {
			p = p.right;
		}
		return p.right;
	}
	public static void main(String[] args) {
		String[] prelist = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
		BinaryTree myTree = new BinaryTree(prelist);
		myTree.inorder();
		ThreadBinaryTree myThreadBinaryTree = new ThreadBinaryTree(prelist);
		myThreadBinaryTree.inorder();
	}

}

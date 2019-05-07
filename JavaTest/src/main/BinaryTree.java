package main;

import java.util.Stack;

public class BinaryTree<T> {

	public BinaryNode<T> root;
	public BinaryTree() {
		this.root = null;
	}
	public boolean isEmpty() {
		return this.root == null;
	}
	
	public BinaryNode<T> insert(BinaryNode<T> parent,T x,boolean leftChild) {
		if(x==null) return null;
		if(leftChild)
			return parent.left = new BinaryNode<T>(x,parent.left,null);
		return parent.right = new BinaryNode<T>(x,null,parent.right);
	}
	
	public BinaryNode<T> insert(T x) {
		return this.root = new BinaryNode<T>(x,this.root,null);
	}
	
	public void remove(BinaryNode<T> parent,boolean leftChild) {
		if(leftChild) parent.left = null;
		parent.right = null;
	}
	
	public void clear() {
		this.root = null;
	}
	/**
	 * 先根遍历
	 * @param p
	 */
	public void preorder() {
		preorder(this.root);
		System.out.println();
	}
	public void preorder(BinaryNode<T> p) {
		if (p==null) {
			System.out.print("^ ");
		}
		if(p!=null)
		{
			System.out.print(p.data.toString()+" ");
			preorder(p.left);
			preorder(p.right);
		}

	}
	/**
	 * 中根遍历
	 * @param args
	 */
	public void inorder() {
		inorder(this.root);
		System.out.println();
	}
	public void inorder(BinaryNode<T> p) {
		if(p!=null)
		{
			inorder(p.left);
			System.out.print(p.data.toString());
			inorder(p.right);
		}
	}
	/**
	 * 后根遍历
	 * @param args
	 */
	public void postorder() {
		postorder(this.root);
		System.out.println();
	}
	public void postorder(BinaryNode<T> p) {
		if (p!=null) {
			postorder(p.left);
			postorder(p.right);
			System.out.print(p.data.toString());
		}
	}
	@Override
	public String toString() {
		
		return toString(this.root);
	}
	public String toString(BinaryNode<T> p) {
		if(p==null) return "^";
		return p.data.toString()+" "+toString(p.left)+toString(p.right);
	}
	private int i = 0;
	public BinaryTree(T[] prelist) {
		this.root = create(prelist);
	}
	private BinaryNode<T> create(T[] prelist) {
		BinaryNode<T> p =null;
		if(i<prelist.length) {
			T elem = prelist[i];
			i++;
			if(elem!=null) {
				p = new BinaryNode<T>(elem);
				p.left = create(prelist);
				p.right = create(prelist);
			}
		}
		return p;
	}
	/**
	 * 使用栈对一棵二叉树进行先序遍历的非递归算法
	 * @param args
	 */
	public void preorderTraverse() {
		Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
		BinaryNode<T> p = this.root;
		while (p!=null||!stack.isEmpty()) {
			if(p!=null) {
				System.out.print(p.data.toString()+" ");
				stack.push(p);
				p = p.left;
			}else {
				System.out.print("^ ");
				p = stack.pop();
				p = p.right;
			}
			
		}
	}
	public static void main(String[] args) {
		
//		int a = 5;
//		int b = a;
//		b=b-1;
//		System.out.println(a);
		
		String[] prelist = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
		BinaryTree myTree = new BinaryTree(prelist);
		System.out.println(myTree.toString());
		myTree.postorder();
		myTree.inorder();
		myTree.insert(myTree.root.left, "X",	 true);
		myTree.insert(myTree.root.right, "X",false);
		myTree.insert("Z");
		System.out.println(myTree.toString());
		myTree.preorder();
		myTree.preorderTraverse();
	}
}

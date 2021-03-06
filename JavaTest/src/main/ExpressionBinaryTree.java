package main;

public class ExpressionBinaryTree extends BinaryTree<ExpData> {

	public ExpressionBinaryTree() {
		super();
	}

	public ExpressionBinaryTree(String prelist) {
		this.root = create(prelist);
	}

	private int i = 0;

	private BinaryNode<ExpData> create(String prelist) {
		BinaryNode<ExpData> p = null;
		if (i < prelist.length()) {
			char ch = prelist.charAt(i);
			if (ch >= '0' && ch <= '9') {
				int value = 0;
				while (i < prelist.length() && ch != ' ') {
					value = value * 10 + ch - '0';
					i++;
					if (i < prelist.length()) {
						ch = prelist.charAt(i);
					}
				}
				p = new BinaryNode<ExpData>(new ExpData(value, ' '));
				i++;
			} else {
				p = new BinaryNode<ExpData>(new ExpData(0, prelist.charAt(i)));
				i++;
				p.left = create(prelist);
				p.right = create(prelist);

			}
		}
		return p;
	}

	public int toValue() {
		return this.toValue(this.root);
	}

	private int toValue(BinaryNode<ExpData> p) {
		if (p == null) {
			return 0;
		}
		if (!p.isLeaf()) {
			switch (p.data.oper) {
			case '+':
				p.data.value = toValue(p.left) + toValue(p.right);
				break;
			case '-':
				p.data.value = toValue(p.left) - toValue(p.right);
				break;
			case '*':
				p.data.value = toValue(p.left) * toValue(p.right);
				break;
			case '/':
				p.data.value = toValue(p.left) / toValue(p.right);
			}

		}
		return p.data.value;

	}

	public static void main(String[] args) {
		String prelist = "-+45 *-10 15 /+25 35 -60 40 11";
		ExpressionBinaryTree expressionBinaryTree = new ExpressionBinaryTree(prelist);
		System.out.println("preorder: ");
		expressionBinaryTree.preorder();
		System.out.println("value: " + expressionBinaryTree.toValue());
	}

}

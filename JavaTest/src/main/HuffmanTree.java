package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class HuffmanTree {

	private int n ;//叶子节点个数即text中出现过的字符个数
	private StringBuilder charset;// 字符集合
	private TriElement huftree[];// 静态三叉链表节点数组
	private Map<Character, String> code;//存储字符以及对应的编码
	
	public HuffmanTree(int[] weight) {
		this.charset = new StringBuilder();
		for (int i = 0; i < weight.length; i++) {
			charset.append((char) ('A' + i));
		}
		this.n = weight.length;
		huftree = new TriElement[2*n-1];
		for (int i = 0; i < n; i++) {
			huftree[i] = new TriElement(weight[i]);
		}
		for (int i = n; i < 2*n-1; i++) {
			int min = Integer.MAX_VALUE;
			int weakMin = min;
			int indexOne = -1;
			int indexTwo = -1;
			for (int j = 0; j < i; j++) {
				if (this.huftree[j].parent==-1) {
					if (this.huftree[j].data<min) {
						weakMin = min;
						indexTwo = indexOne;
						min = this.huftree[j].data;
						indexOne = j;
					}
					else if (this.huftree[j].data<weakMin) {
						weakMin = this.huftree[j].data;
						indexTwo = j;
					}
				}
			}
			this.huftree[indexOne].parent = i;
			this.huftree[indexTwo].parent = i;
			this.huftree[i] = new TriElement(min+weakMin, -1, indexOne, indexTwo);
		}
		//使用HashMap存储编码
		this.code =  new HashMap<Character, String>();
		for (int i = 0; i < n; i++) {
			code.put(this.charset.charAt(i), this.getCode(i));
		}
	}

	
	private String getCode(int i) {
		//int n = (int)Math.sqrt(this.huftree.length+1);
		Stack<Integer> hufCode = new Stack<Integer>();
		int child = i;
		int parent = this.huftree[child].parent;
		while (parent!=-1) {
			hufCode.push(huftree[parent].left==child?0:1);
			child = parent;
			parent = huftree[parent].parent;
		}
		StringBuilder code = new StringBuilder();
		while (!hufCode.isEmpty()) {
			code.append(hufCode.pop()+"");
		}
		return code.toString();
	}
	public String getCharCode(char ch) {
		return this.code.get(ch);
	}
	public String toString() {
		StringBuilder result = new StringBuilder();
//		for (int i = 0; i < n; i++) {
//			result.append(this.charset.charAt(i)+":   "+this.getCode(i)+"\n");
//		}
		for (Character key : this.code.keySet()) {
			result.append(key+":  "+this.code.get(key)+"\n");
		}
		return result.toString();
	}
	public String encode(String text) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			result.append(getCode(this.charset.indexOf(text.charAt(i)+"")));
			
		}
		return result.toString();
	}
	public String decode(String binary) {
		StringBuilder result = new StringBuilder();
		int node = this.huftree.length - 1;
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i)=='0') {
				node = huftree[node].left;
			}else {
				node = huftree[node].right;
			}
			if (huftree[node].isLeaf()) {
				result.append(this.charset.charAt(node));
				node = this.huftree.length-1;
			}
			
		}
		return result.toString();
	}
	public static void main(String[] args) {
		String text = "AAAABBBCDDBBAAA";
		int[] weight = {7,5,1,2};
		HuffmanTree hTree = new HuffmanTree(weight);
		System.out.println("A字母的编码是：");
		System.out.println(hTree.getCharCode('A'));
		System.out.println("相应字母对应的编码：");
		System.out.print(hTree.toString());
		
		System.out.println("字符串text编码后：");
		System.out.println(hTree.encode(text));
		System.out.println("字符串text解码后：");
		System.out.println(hTree.decode(hTree.encode(text)));
	}

}

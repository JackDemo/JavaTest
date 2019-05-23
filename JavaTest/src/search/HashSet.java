package search;
/**
 * ɢ��-װ�����ӣ�0.75��-ɢ�к���-������ͻ
 * ɢ�к�����Ϊ��1��������������ͨ��ȡɢ�б����ȷ�Χ�ڵ�������� ��2��ƽ��ȡ�з� ��3���۵���<br>
 * ������ͻ��ͬ��ʳ�ͻ���ͬ��ʳ�ͻ��1�����Ŷ�ַ�� ��2������ַ��
 * 
 * ɢ�б���  ��������ַ�������ͻ
 */

import java.util.LinkedList;

public class HashSet<T> {	
	private LinkedList<T>[] table;
	private int count = 0;
	private static final float LOAD_FACTOR = 0.75f;
	public HashSet(int length) {
		if (length<10) {
			length = 10;
		}
		this.table = new LinkedList[length];
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<T>();
		}
	}
	public HashSet() {
		this(16);
	}
	public HashSet(T[] values) {
		
	}
	private int hash(T x) {
		int key = Math.abs(x.hashCode());
		return key%this.table.length;
	}
	public T search(T key) {
		LinkedList<T> linkedList = this.table[this.hash(key)];
		int find= linkedList.indexOf(key);
		return find!=-1?linkedList.get(find):null;
	}
	public boolean add(T x) {
		if (this.count>=this.table.length*LOAD_FACTOR) {
			LinkedList<T>[] linkedList = this.table;
			this.table = new LinkedList[2*this.table.length];
			for (int i = 0; i < table.length; i++) {
				table[i] = new LinkedList<T>();
			}
			this.count = 0;
			for (int i = 0; i < linkedList.length; i++) {
				for (T data:linkedList[i])  {
					this.add(data);
				}
			}
		}
		boolean insert = false;
		if (!this.table[this.hash(x)].contains(x)) {
			insert = this.table[this.hash(x)].add(x);

		}
		if (insert) {
			count++;
		}
		return insert;
	}
	public boolean remove(T key) {
		boolean x = this.table[this.hash(key)].remove(key);
		if (x) {
			this.count--;
		}
		return x;
	}
}
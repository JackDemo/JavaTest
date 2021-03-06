package main;

import java.util.ArrayList;

public abstract class AbstractGraph<T> {

	protected static final int Max_WEIGHT = 0x0000ffff;
	protected ArrayList<T> vertexList;
	public AbstractGraph(int length) {
		this.vertexList = new ArrayList<T>(length);
	}
	public AbstractGraph() {
		this(10);
	}
	public int verlistCount() {
		return this.vertexList.size();
	}
	public String toString() {
		return "顶点集合：" + this.vertexList.toString()+"\n";
	}
	public T getVex(int i) {
		return this.vertexList.get(i);
	}
	public void setVex(int i,T t) {
		this.vertexList.set(i, t);
	}
	public abstract int insertVertex(T t);
	public abstract void removeVertex(int i);
	public abstract int weight(int i ,int j);
	protected abstract int next(int i,int j);
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}

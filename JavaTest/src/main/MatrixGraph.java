package main;

public class MatrixGraph<T> extends AbstractGraph<T> {

	protected Matrix matrix;
	public void insertEdge(int i,int j,int weight) {
		if (i!=j) {
			if (weight<=0|| weight>Max_WEIGHT) {
				weight = Max_WEIGHT;
				this.matrix.set(i, j, weight);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
	}
	public void insetEdge(Triple edge) {
		this.insertEdge(edge.row, edge.column, edge.value);
	}
	public void removeEdge(int i,int j) {
		if (i!=j) {
			this.matrix.set(i, j, Max_WEIGHT);
		}
	}
	public void removeEdge(Triple t) {
		this.removeEdge(t.row,t.column);
	}
	@Override
	public int insertVertex(T t) {
		this.vertexList.add(t);
		int i = this.vertexList.size();
		if (i>this.matrix.getRows()) {
			this.matrix.setRowsColumns(i,i);
		}
		for (int j = 0; j < i; j++) {
			this.matrix.set(i, j, Max_WEIGHT);
			this.matrix.set(j, i, Max_WEIGHT);
		}
		return i-1;
	}

	@Override
	public void removeVertex(int i) {
		int n = this.vertexList.size();
		if (i>=0&&i<n) {
			this.vertexList.remove(i);
			for (int j = i+1; j < n; j++) {
				for (int k = 0; k < n; k++) {
					this.matrix.set(j-1, k, this.matrix.get(j, k));
				}
			}
			for (int j = i+1; j < n; j++) {
				for (int k = 0; k < n; k++) {
					this.matrix.set(k, j-1, this.matrix.get(k, j-1));
					
				}
			}
			this.matrix.setRowsColumns(n-1, n-1);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public int weight(int i, int j) {
		return this.matrix.get(i, j);
	}

	@Override
	protected int next(int i, int j) {
		int n = this.vertexList.size();
		if (i>=0&&i<n&&j>=-1&&j<n&&i!=j) {
			for (int k = j+1; k < n; k++) {
				if (this.matrix.get(i, k)>0&&this.matrix.get(i, k)<Max_WEIGHT) {
					return k;
				}		
			}
		}
		return -1;
	}

}

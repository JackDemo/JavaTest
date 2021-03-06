package main;

public class MyList<T>{
	/*public static void main(String[] args) {
		System.out.println("make myList ");

	}*/
	protected Object[] element;//数组成员 保护类型
	protected int n;//顺序表长度
	public MyList(int length){//构造容量为length的空表
		this.element = new Object[length];	
		this.n = 0;
	}
	public MyList(){
		this(64);
	}
	public MyList(T[] values){
		this(values.length);
		for (int i = 0 ; i < values.length ; i++ ) {
			element[i] = values[i];
		}
		this.n = element.length;
	}
	public MyList(MyList<?> list) {
		this.n = list.n;
		for (int i = 0; i < list.n; i++) {
			this.element[i] = list.element[i];
		}
	}
	public boolean isEmpty(){
		return n==0;
	}
	public int getSize(){
		return this.n;
	}
	public T get(int i){
		if(i<this.n&&i>=0){
			return (T)element[i];
		}		
		return null;
	}
	public void set(int i ,T t){
		if(i>=0 && i<element.length){
			element[i] = t;
		}else if(t==null){
			throw new NullPointerException("x==null");
		}else{
			throw new IndexOutOfBoundsException(i+" ");
		}
	}
	public String toString(){
		StringBuilder str = new StringBuilder(this.getClass().getName()+"(");
		if(this.n>0){
			for (int i = 0 ; i < n ; i++ ) {
						str.append(this.element[i] + " ");
			}		
		}
		return str.append(")").toString();
	}
	public int insert(int i,T t){
		if (t==null) {
			throw new NullPointerException("x==null");
		}
		if (i<0) i = 0;
		if (i>element.length) i=element.length; 
		Object[] source = this.element;
		if (this.n == element.length) {
			this.element = new Object[source.length*2];
			for (int j = 0 ; j < i ; j++) {
				this.element[j] = source[j];
			}
		}
		for (int j = this.n-1 ; j>=i ; j--) {
			this.element[j+1] = source[j];
		}
		//使用从前向后的赋值方式在 存在[1,2,3,null,null]这种形式下使用同一指针，
//		for (int j = i ; j<this.n; j++) {
//			this.element[j+1] = source[j];
//		}
		
		this.element[i] = t;		

		this.n++;
		return i;
	}
	public int insert(T x) {
		return this.insert(this.n,x);
	}	
	public T remove(int i){
		if (i>=0&&i<this.n&&this.n>0) {
			T old = (T)this.element[i];
			for (int j = i; j < this.n-1 ;j++ ) {
				this.element[j] = this.element[j+1];
			}
			this.element[n-1] = null;
			this.n--;
			return old;
		}
		return null;
	}
	public void clear(){
		this.n = 0;
	}
	public int search(T key) {
		for (int i = 0; i < this.n; i++) {
			if (this.element[i].equals(key)) {
				return i;
			}
			
		}
		return -1;
	}
	public boolean contains(T key) {
		return this.search(key)!=-1;
	}
	public int insertDifferent(T xT) {
		if (this.search(xT)!=-1) {
			return this.search(xT);
		}else {
			return this.insert(n, xT);
		}
	}
	public T remove(T key) {
		if (this.contains(key)) {
			return this.remove(this.search(key));
		}
		else {
			return null;
		}
	}
	public boolean equals(Object object) {
		if(this == object) return true;
		if (object instanceof MyList<?>) {
			MyList<T> myList = (MyList<T>)object;
			if (myList.getSize() == this.getSize()) {
				for (int i = 0; i < this.n; i++) {
					if (!(myList.get(i).equals(this.element[i]))) {
						return false;
					}
				return true;
					
				}
			}	
		}
		return false;
	}
	public void addAll(MyList<? extends T> list) {
		for (int i = 0; i < list.n; i++) {
			this.insert((T)list.element[i]);
			
		}
	}
	public static void main(String[] args) {
		Integer[] nums = new Integer[]{1,2,3,4};
		MyList<Integer> test = new MyList<Integer>(nums);
		test.addAll(new MyList<Integer>(nums));
		System.out.println(test.toString());
		//insert remove get set search contains isEmpty
//		test.insert(2, 7);
//		test.insert(0,3);
//		test.insert(new Integer(8));
//		
//		System.out.println(test.toString());
//		System.out.println(test.insertDifferent(7));
//		System.out.println(test.toString());
//		
//		System.out.println(test.remove((Integer)3));
//		System.out.println(test.toString());
//		System.out.println(test.equals(new MyList<Integer>(nums)));
//		//System.out.println(test.toString());
//		System.out.println(test.insert(2,2));
//		//System.out.println(test.toString());
//		System.out.println(test.insert(0,2));
//		//System.out.println(test.toString());
//		System.out.println(test.n);
//		
//		System.out.println(test.insert(test.n-1,2));
//		System.out.println(test.toString());
//
//		System.out.println(test.remove(3));
//		System.out.println(test.toString());
//		System.out.println(test.remove(4));
//		System.out.println(test.toString());
//
//		test.clear();
//		System.out.println(test.toString());

	}
}
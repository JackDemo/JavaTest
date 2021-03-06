package main;


public class SortedMyList<T extends Comparable<? super T>> extends MyList<T> {

	public SortedMyList() {
		super();
	}
	public SortedMyList(int length) {
		super(length);
	}
	public SortedMyList(T[] values) {
		super(values.length);
		for (int i = 0; i < values.length; i++) {
			this.insert(values[i]);
			
		}
	}
    public SortedMyList(MyList<? extends T> list)
    {
        super();                                           
        for (int i=0; i<list.n; i++)             
            this.insert(list.get(i));                  
    }
    public SortedMyList(SortedMyList<? extends T> list)
    {
        super(list);
    }
	public int insert(T x) {
		int i = 0;
		if (this.isEmpty()||x.compareTo(this.get(this.getSize()-1))>0) {
			i = this.n;
		}else {
			while (i<this.n&& x.compareTo(this.get(i))>0) {
				i++;
			}
		}
		super.insert(i,x);
		return i ;
	}
	public void set(int i ,T x) {
		throw new java.lang.UnsupportedOperationException("set(int i,T x)");
	}
	public int insert(int i,T x) {
		throw new java.lang.UnsupportedOperationException("insert(int i,T x");
	}
	public static void main(String[] args) {
		Integer[] numsIntegers = new Integer[] {70,20,80,30,60};
		MyList<Integer> myList = new MyList<Integer>(numsIntegers);
		SortedMyList<Integer> sortedMyList = new SortedMyList<Integer>(myList);
		
		myList.insert(0, 10);
		myList.insert(50);
		sortedMyList.insert(50);
		System.out.println(myList.toString());
		System.out.println(sortedMyList.toString());
	}

}

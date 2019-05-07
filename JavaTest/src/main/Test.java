package main;

public class Test {

	public static void main(String[] args) {
		//MyList<String> mylist = new MyList();
		System.out.println("Josephus Problem");
		Test name = new Test(5,0,2);
	}
	public Test(int number,int start,int distance) {
		MyList< String> myList = new MyList<String>(number);
		for (int i = 0; i < number; i++) {
			myList.insert(i, (char)('A'+i)+"");
			
		}
		System.out.println(myList.toString());
		int i = start;
		while (myList.getSize()>1) {
			if (i+distance-1<myList.getSize()) {
				i = i + distance-1;
				System.out.println(myList.remove(i));
			}else {
				i = (i+distance-1)%myList.getSize();
				System.out.println(myList.remove((i)));
				
			}
		}
		System.out.println(myList.toString());
	}
}

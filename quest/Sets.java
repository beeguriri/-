package quest;

public class Sets {
	
	private int [] parent;
	private int n;
	
	public Sets(int size) {
		
		n = size;
		parent = new int [size+1]; //don't want to use parent[0]
		
		for(int i=1; i<=n; i++)
			parent[i] = -1;
	}
	
	public void display() {
		
		for(int i=1; i<=n; i++) { 
		
			System.out.println("display: index = " + i 
					+ " display: value = " + parent[i] );	
		
		}
	}
	
	public void simpleUnion(int i, int j) {
		
		//i, j 합집합 => j의 루트를 i로 바꿈
		parent[j] = i;
		
	}
	
	public int simpleFind(int i) {
		
		//i의 루트를 찾음 (루트는 -1)
		while(parent[i] > 0)
			i = parent[i];
		
		return i;
		
	}
	
	public void weightedUnion(int i, int j) {
		
		int temp = parent[i] + parent[j];
		
		//parent는 음수의 값을 가짐 (-1에서부터 원소의 갯수를 표현)
		//i의 집합갯수가 2개면 parent[i] = -2
		//j의 집합갯수가 3개면 parent[j] = -3
		//parent[i]>parent[j]이 되고
		//집합갯수가 작은게 큰 쪽으로 붙도록 함!
		if(parent[i] > parent[j]) {
			parent[i] = j;
			parent[j] = temp;
			
		} else {
			parent[j] = i;
			parent[i] = temp;
		}
	}
	
	//parent[i]값을 바꿔줌 (참조->참조->참조(parent) 형태에서 바로 parent 가리킬수 있게 함)
	public int collapsingFind(int i) {
		
		int r;
		
		for(r=i; parent[r]>0; r=parent[r]);
		while( i!= r ) {
			int s = parent[i];
			parent[i] = r;
			i = s;
		}
		
		return r;
	}
	

	public static void main(String[] args) {

		Sets m = new Sets(8);
		m.display();
		
		System.out.println("find result : " + m.collapsingFind(5));
		
		m.weightedUnion(1, 2);
		m.weightedUnion(3, 4);
		m.weightedUnion(5, 6);
		m.weightedUnion(7, 8);

		m.display();
		
		System.out.println("find result : " + m.collapsingFind(5));
		System.out.println("find result : " + m.collapsingFind(6));
		
		m.weightedUnion(1, 3);
		m.weightedUnion(5, 7);
		
		m.display();
		
		System.out.println("find result : " + m.collapsingFind(5));
		System.out.println("find result : " + m.collapsingFind(6));
		System.out.println("find result : " + m.collapsingFind(7));
		System.out.println("find result : " + m.collapsingFind(8));
		
		m.weightedUnion(1, 5);
		m.display();

		System.out.println("find result : " + m.collapsingFind(1));
		System.out.println("find result : " + m.collapsingFind(2));
		System.out.println("find result : " + m.collapsingFind(3));
		System.out.println("find result : " + m.collapsingFind(4));
		System.out.println("find result : " + m.collapsingFind(5));
		System.out.println("find result : " + m.collapsingFind(6));
		System.out.println("find result : " + m.collapsingFind(7));
		System.out.println("find result : " + m.collapsingFind(8));
		
		m.display();
		System.out.println("pause");
		
	}

}
 
package Lec06_Sort;

class Point {
	
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
}

class QuikStack {
	
	private int top; 		// 스택 포인터
	private Point data [];	// 스택용 배열
	
	public QuikStack() {
		top = 0;
		data = new Point[100];
	}
	
	public void push (Point p) {
		data[top++] = p;
	}
	
	public Point pop() {	
		return data[--top];
	}
	
	public boolean isEmpty() {
		if(top == 0) return true;
		else return false;
	}
}

//퀵 정렬(비재귀 버전)
public class QuickSort {

		// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
		static void swap(int[] a, int idx1, int idx2) {
			
			int t = a[idx1];
			a[idx1] = a[idx2];
			a[idx2] = t;
			
		}

		// --- 퀵 정렬(비재귀 버전)---//
		//left : 나눈 배열의 제일 왼쪽 index
		//right : 나눈 배열의 제일 오른쪽 index
		//피벗을 기준으로 작은값은 왼쪽, 큰값은 오른쪽 배열에 오게 한다!
		//pl, pr : left, right pointer
		//pl > pr이면 정렬 끝
		static void quickSort(int[] a, int left, int right) {

			QuikStack st = new QuikStack();
			Point pt = new Point(left, right);
			st.push(pt);
			
			System.out.println("\n배열 정렬:: ");

			//스택이 비어있지 않으면
			while(!st.isEmpty()) {
				
				Point temp = st.pop();
				
				int pl = left = temp.getX(); 
				int pr = right = temp.getY();
				
				int x = a[(left+right)/2];	//피벗을 배열의 가운대 값으로 둠
				
				do {
					
					//pl의 요소가 피벗보다 작으면 pl 포인터 오른쪽으로 이동
					while(a[pl] < x) pl++;	
					
					//pr의 요소가 피벗보다 크면 pr포인터 왼쪽으로 이동
					while(a[pr] > x) pr--;	
					
					if (pl <= pr) swap(a, pl++, pr--);	
					
					
				} while(pl <= pr);
				
				//정렬결과 확인
				for(int num : a) System.out.print(" " + num);
				System.out.println();
				
				//왼쪽 그룹의 인덱스 푸시
				if(left < pr)	st.push(new Point(left, pr));
				
//				for(int i = left; i <pr; i++)	System.out.print(" " + a[i]);
//				System.out.println();
				
				//오른쪽 그룹의 인덱스 푸시
				//오른쪽 그룹을 나중에 푸시했으므로 오른쪽 그룹부터 다시 정렬
				if(pl < right)	st.push(new Point(pl, right));
				
//				for(int i = pl; i <right; i++)	System.out.print(" " + a[i]);
//				System.out.println();
				
			}
		}
	
	public static void main(String[] args) {
		
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}
		
//		int [] x = {5, 8, 4, 2, 6, 1, 3, 9, 7};
		int nx = x.length;
		
		System.out.println("정렬 전 배열: ");
		for (int num : x) System.out.print(" " + num);
		System.out.println();
		
		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("\n오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
	}
}

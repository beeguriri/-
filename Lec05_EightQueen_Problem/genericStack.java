package Lec05_EightQueen_Problem;   //Chap4_스택과큐;

class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public String toString() {
		return "<" + ix + ", " + iy + ">";
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

//Point라는 클래스에 대하여 사용하게 만들거임
//Int형 데이터는 책에 있음
public class genericStack {
	
	//--- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	// Exception : 자바에서 제공하는 기본 라이브러리
	// EmptyGenericStackException : 사용자 클래스
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;
		public EmptyGenericStackException() {
//			super(); 		//Exception의 생성자를 부름 (컴파일러가 자동으로 생성하므로, 안써도 됨)
		}
	}

	//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public OverflowGenericStackException() {
		}
	}

    private Point data[];	// 스택용 배열
	//private List<T> data;
	private int capacity;	// 스택의 크기
	private int top; 		// 스택 포인터

	//--- 생성자(constructor) ---//
	public genericStack(int capacity) {
		top = 0;							
		this.capacity = capacity;
		// this.data = new T[capacity]; 	// 스택 본체용 배열을 생성
		try {
			data = new Point[capacity];		//실습은 Point라는 클래스를 사용하기 위함 
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}	
	}

	//--- 스택에 x를 푸시 ---//
	public Point push(Point x) throws OverflowGenericStackException {
		//System.out.println("top = " + top +"capacity = " + capacity);
		if (top >= capacity)							//top이 용량을 넘어서면
			throw new OverflowGenericStackException();	//예외처리
		return data[top++] = x;	
	}
	
	//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point pop() throws EmptyGenericStackException  {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data[--top];
	}

	//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point peek() throws EmptyGenericStackException  {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data[top - 1];
	}

	//--- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data[i].equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	//--- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("stack이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data[i] + " ");
			System.out.println();
		}
	}
}
